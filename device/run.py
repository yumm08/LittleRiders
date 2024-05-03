import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from PyQt5.QtWebEngineWidgets import QWebEngineView
from PyQt5.QtCore import QUrl
import os
from Model import *
from APIFetch import APIFetcher
from Repository import *
from SensorParser import SensorMockingGenerator

import threading

form_class = uic.loadUiType("untitled.ui")[0]
modelHelper = ModelHelper()
terminalRepository =TerminalRepository(modelHelper=modelHelper)
positionRepository = PositionRepository(modelHelper=modelHelper)
terminalNumber = terminalRepository.findById(1).getTerminalNumber()
apiFetcher = APIFetcher(terminalNumber)

class Observer:
    def __init__(self,function):
        self.function = function
    def update(self,object):
        self.function(object)



    
class MainWindow(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.observer = Observer(lambda x:self.courseInfoUpdateByObserver(x))
        self.exitButton.clicked.connect(lambda x: os.exit())
        web = QWebEngineView()
        web.setUrl(QUrl("https://www.google.com"))
        print(type(self.mapLayout))
        self.mapLayout.addWidget(web)


    def terminalInfoButtonEvent(self):
        terminalNumber = terminalRepository.findById(1)
        print(terminalNumber)
        #self.uuidText.setText(terminalNumber.getTerminalNumber())
    def courseInfoButtonEvent(self):
        self.latitudeText.setText("위도값이에요")
        self.longitudeText.setText("경도값이에요")
        self.speedText.setText("속도값이에요")

    def courseInfoUpdateByObserver(self,position):
        self.latitudeText.setText(f"{position.getLatitude()}")
        self.longitudeText.setText(f"{position.getLongitude()}")
        self.speedText.setText(f"{position.getSpeed()}")

        pass
        #routeList = apiFetcher.getRouteList()
        #self.uuidText.setText(json.dumps(routeList,ensure_ascii=False,indent=4))








app = QApplication(sys.argv)
win = MainWindow()

observer = win.observer

SensorMockingGenerator()
import time
def threadingFunction(observer):
    sensorGenerator = SensorMockingGenerator()
    while True:
        position = sensorGenerator.generateSensorValue()
        observer.update(position)
        time.sleep(0.1)

thead = threading.Thread(target= lambda :threadingFunction(observer))
thead.start()

win.show()
app.exec()
