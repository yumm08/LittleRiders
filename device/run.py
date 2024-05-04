import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from PyQt5.QtWebEngineWidgets import QWebEngineView
from PyQt5.QtCore import QUrl,QThread, pyqtSignal
from Model import *
from APIFetch import APIFetcher
from Repository import *
from SensorHelper import SensorHelper
import sys

from datetime import datetime

form_class = uic.loadUiType("untitled.ui")[0]
modelHelper = ModelHelper()
terminalRepository =TerminalRepository(modelHelper=modelHelper)
positionRepository = PositionRepository(modelHelper=modelHelper)
terminalNumber = terminalRepository.findById(1).getTerminalNumber()
apiFetcher = APIFetcher(terminalNumber)


class PositionObserverInterface():
    def notify(self,*args,**kwargs):
        pass


class PositionProvider:

    def __init__(self):
        self.__observers = []

    def register(self,observer:PositionObserverInterface):
        self.__observers.append(observer)

    def notifyAll(self,*args,**kwargs):
        for observer in self.__observers:
            observer.notify(*args,**kwargs)



class PositionThread(QThread,PositionProvider):
    positionUploadEvent = pyqtSignal(RMCPosition)
    def run(self):
        sensorReceiver = SensorHelper().getSensorReceiver()
        while True:
            try:
                position = sensorReceiver.getPosition()
                self.notifyAll(position=position)
            except Exception as e:
                pass

    def stop(self):
        self.terminate()



    
class MainWindow(QMainWindow, form_class,PositionObserverInterface):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.exitButton.clicked.connect(self.close)
        self.webview = QWebEngineView()
        self.webview.setUrl(QUrl("https://device.littleriders.co.kr"))
        self.mapLayout.addWidget(self.webview)
        self.webview.loadFinished.connect(self.on_load_finished)
        self.mapLoad = False


    def on_load_finished(self, success):
        if success:
            self.mapLoad = True
            self.webview.page().runJavaScript('console.log("helloworld")')

    def terminalInfoButtonEvent(self):
        terminalNumber = terminalRepository.findById(1)
    def courseInfoButtonEvent(self):
        self.latitudeText.setText("위도값이에요")
        self.longitudeText.setText("경도값이에요")
        self.speedText.setText("속도값이에요")


        #routeList = apiFetcher.getRouteList()
        #self.uuidText.setText(json.dumps(routeList,ensure_ascii=False,indent=4))

    def notify(self,*args,**kwargs):
        position = kwargs.get("position",None)
        if(not position):
            return
        position = kwargs["position"]
        latitude = position.getLatitude()
        longitude = position.getLongitude()
        speed = position.getSpeed()
        if(self.mapLoad):
            self.webview.page().runJavaScript(f'change({latitude},{longitude})')
        self.latitudeText.setText(f"{latitude}")
        self.longitudeText.setText(f"{longitude}")
        self.speedText.setText(f"{speed}")



class PositionSaver(PositionObserverInterface):
    def __init__(self):
        modelHelper = ModelHelper()
        self.positionRepository = PositionRepository(modelHelper=modelHelper)

    def notify(self,*args,**kwargs):
        position = kwargs.get("position",None)
        if(not position):
            return
        latitude = position.getLatitude()
        longitude = position.getLongitude()
        speed = position.getSpeed()
        entity = Position(latitude=latitude,longitude=longitude,speed=speed,time=datetime.now())
        self.positionRepository.save(entity)

if __name__ == "__main__":
    app = QApplication(sys.argv)
    win = MainWindow()
    
    positionSaver = PositionSaver()
    postionThread = PositionThread()
    postionThread.start()
    postionThread.register(win)
    postionThread.register(positionSaver)
    
    win.show()
    app.exec()
