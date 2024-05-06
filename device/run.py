import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from PyQt5.QtWebEngineWidgets import QWebEngineView
from PyQt5.QtCore import  QUrl,QThread, pyqtSignal
from PyQt5.QtWidgets import QWidget
from PyQt5.QtGui import QFont
from Model import *
from APIFetch import APIFetcher
from Repository import *
from SensorHelper import SensorHelper
from BluetoohHelper import BluetoothHelper
import sys
import asyncio

from datetime import datetime
from BuzzerHelper import BuzzerHelper

form_class = uic.loadUiType("untitled.ui")[0]
formRounteInfoClass = uic.loadUiType("untitled2.ui")[0]
modelHelper = ModelHelper()
buzzerHelper = BuzzerHelper()
terminalRepository =TerminalRepository(modelHelper=modelHelper)
positionRepository = PositionRepository(modelHelper=modelHelper)
terminalNumber = terminalRepository.findById(1).getTerminalNumber()
apiFetcher = APIFetcher(terminalNumber)
print(terminalNumber)

class ObserverInterface():
    def notify(self,*args,**kwargs):
        pass


class Provider:

    def __init__(self):
        self.__observers = []

    def register(self,observer:ObserverInterface):
        self.__observers.append(observer)

    def notifyAll(self,*args,**kwargs):
        for observer in self.__observers:
            observer.notify(*args,**kwargs)



class PositionThread(QThread,Provider):
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


class BluetoothThread(QThread,Provider):
    def __init__(self):
        super().__init__()
        self.bluetoothHelper = BluetoothHelper()

    def run(self):
        loop = asyncio.new_event_loop()
        asyncio.set_event_loop(loop)
        loop.run_until_complete(self.async_run())

    async def async_run(self):
        while True:
            beaconUUIDList = await self.bluetoothHelper.getBeaconUUIDList()
            self.notifyAll(beaconUUIDList=beaconUUIDList)
 


class PositionSaver(ObserverInterface):
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
        apiFetcher.uploadPosition(position)
        entity = Position(latitude=latitude,longitude=longitude,speed=speed,time=datetime.now())
        self.positionRepository.save(entity)


class MainWindow(QMainWindow, form_class,ObserverInterface):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.exitButton.clicked.connect(self.close)
        self.webview = QWebEngineView()
        self.webview.setUrl(QUrl("https://device.littleriders.co.kr"))
        self.mapLayout.addWidget(self.webview)
        self.webview.loadFinished.connect(self.on_load_finished)
        self.serialNumberText.setText(f"{terminalNumber}")
        self.mapLoad = False


    def on_load_finished(self, success):
        if success:
            self.mapLoad = True
            position = positionRepository.findLastPosition()
            if(not position):
                self.webview.page().runJavaScript(f'console.log("hello world")')

                return
            self.webview.page().runJavaScript(f'change({position.latitude},{position.longitude})')

            

    def startDriveButtonEvent(self):
        canDrive = False
        #여기서 api fetching 진행후 반환
        
        if not canDrive:
            buzzerHelper.beep()
            buzzerHelper.beep()
            QMessageBox.about(self,'운행이 불가능합니다.','기사님과 선탑자가 탑승하지 않았어요.')
        
        pass
    def courseInfoButtonEvent(self):
        buzzerHelper.beep()
        self.hide()
        self.second = RouteInfoWindow()
        self.second.exec()
        self.show()


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
    


class RouteInfoListWidget(QListWidgetItem):
    def __init__(self,data=None,parent=None):
        super().__init__("",parent)
    
        self.id = data["id"]
        self.name = data["name"]
        self.stationList = data["stationList"]
        self.setText(self.name)
        self.setFont(QFont("Arial",23))
        # su.__init__(parent)

    def getId(self):
        return self.id
    
    def getStationList(self):
        return self.stationList

class RouteInfoWindow(QDialog,QWidget,formRounteInfoClass):

    def __init__(self):
        super(RouteInfoWindow,self).__init__()
        self.setupUi(self)

        self.routeList = apiFetcher.getRouteList()
        for i in self.routeList:
            self.addItem(i)
        
        self.exitButton.clicked.connect(self.closeWindow)
        self.webview = QWebEngineView()
        self.webview.setUrl(QUrl("https://device.littleriders.co.kr/route"))
        self.stationMapLayout.addWidget(self.webview)
        self.webview.loadFinished.connect(self.on_load_finished)
        self.mapLoad = False
        self.show()
    

    def addItem(self,data):
        item = RouteInfoListWidget(data=data)
        self.courseListWidget.addItem(item)

    def clickItem(self,items):
        buzzerHelper.beep()
        #stationList = apiFetcher.getStationListByRouteId(items.getId())
        stationList = items.getStationList()
        self.webview.page().runJavaScript(f"reChangePostion({stationList})")

    def closeWindow(self):
        buzzerHelper.beep()
        self.close()

    def on_load_finished(self, success):
        if success:
            self.mapLoad = True
            if(len(self.routeList) >0):
                self.webview.page().runJavaScript(f'reChangePostion({self.routeList[0]["stationList"]})')


if __name__ == "__main__":
    app = QApplication(sys.argv)
    win = MainWindow()
    
    positionSaver = PositionSaver()
    postionThread = PositionThread()
    postionThread.start()
    postionThread.register(win)
    #postionThread.register(positionSaver)

    bluetoothThread = BluetoothThread()
    bluetoothThread.start()

    
    win.show()
    app.exec()
