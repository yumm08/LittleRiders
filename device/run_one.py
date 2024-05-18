import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from PyQt5.QtWebEngineWidgets import QWebEngineView
from PyQt5.QtCore import  QUrl,QThread, pyqtSignal
from PyQt5.QtWidgets import QWidget
from PyQt5.QtGui import QFont,QPixmap
from Model import *
from APIFetch import APIFetcher
from Repository import *
from SensorHelper import SensorHelper
from BluetoohHelper import BluetoothHelper
import sys
import asyncio
from datetime import datetime
from BuzzerHelper import BuzzerHelper
from PyQt5.QtCore import Qt,QEvent
from PyQt5.QtWebChannel import QWebChannel

from PyQt5.QtCore import pyqtSlot,QObject,QVariant

import urllib.request


form_class = uic.loadUiType("untitled_single.ui")[0]
formRounteInfoClass = uic.loadUiType("untitled2.ui")[0]
formScanBarcodeClass = uic.loadUiType("untitled3_copy.ui")[0]

formStartDriveClass = uic.loadUiType("untitled4.ui")[0]

modelHelper = ModelHelper()
buzzerHelper = BuzzerHelper()
terminalRepository =TerminalRepository(modelHelper=modelHelper)
positionRepository = PositionRepository(modelHelper=modelHelper)
terminalNumber = terminalRepository.findById(1).getTerminalNumber()
apiFetcher = APIFetcher(terminalNumber)
print(terminalNumber)

speed = 100
class ObserverInterface():
    def notify(self,*args,**kwargs):
        pass


class Provider:

    def __init__(self):
        self.__observers = []

    def register(self,observer:ObserverInterface):
        self.__observers.append(observer)

    def detach(self,observer: ObserverInterface):
        self.__observers.remove(observer)


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
                raise e

    def stop(self):
        self.terminate()

class BluetoothThread(QThread,Provider,ObserverInterface):
    def __init__(self):
        super().__init__()
        self.bluetoothHelper = BluetoothHelper()
        self.speed = 100
        self.latitude = 10000
        self.longitude = 10000
        self.start = True

    def run(self):
        self.start = True
        loop = asyncio.new_event_loop()
        asyncio.set_event_loop(loop)
        loop.run_until_complete(self.async_run())

    def notify(self,*args,**kwargs):
        position = kwargs.get("position",None)
        if(not position):
            return
        self.latitude = position.getLatitude()
        self.longitude = position.getLongitude()
        self.speed = position.getSpeed()

    def stop(self):
        self.start = False
        self.terminate()

    async def async_run(self):
        while self.start:
            if(self.latitude < 1000):
                latitude = self.latitude
                longitude = self.longitude
                await asyncio.sleep(4.0)
                beaconUUIDList = await self.bluetoothHelper.getBeaconUUIDList()
                beaconUUIDListWithLatitudeLongitude = {
                    "latitude" : latitude,
                    "longitude" : longitude,
                    "beaconUUIDList" : beaconUUIDList
                }
                self.notifyAll(beaconUUIDListWithLatitudeLongitude=beaconUUIDListWithLatitudeLongitude)


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
        entity = Position(latitude=latitude,longitude=longitude,speed=speed,time=datetime.now())
        self.positionRepository.save(entity)



class MainWindow(QMainWindow, form_class,ObserverInterface):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        # self.exitButton.clicked.connect(self.close)
        self.webview = QWebEngineView()
        self.webview.setUrl(QUrl("https://device2.littleriders.co.kr"))
        self.mapLayout.addWidget(self.webview)
        self.webview.loadFinished.connect(self.on_load_finished)
        # self.serialNumberText.setText(f"{terminalNumber}")
        self.shuttleInfo = apiFetcher.getShuttleInfo()
        self.url = ""

        self.channel = QWebChannel()
        self.channel.registerObject('mainHandler', self)
        self.webview.page().setWebChannel(self.channel)
        self.webview.urlChanged.connect(self.show_current_url)
        self.routeId = None
        self.driverInfo = None
        self.teacherInfo = None

        self.barcode = ""

        self.onDrive = False

        self.showMaximized()

        
        self.webview.setSizePolicy(QSizePolicy.Expanding, QSizePolicy.Expanding)

    def show_current_url(self,url):
        self.url = url.toString()


    def on_load_finished(self, success):
        if success:
            self.mapLoad = True
            command = "shuttleState.setInfo({name:'%s', licenseNumber :'%s', image: 'https://littleriders.co.kr/api/content/%s',type:'%s'})"%(self.shuttleInfo["name"],self.shuttleInfo["licenseNumber"],self.shuttleInfo['image'],self.shuttleInfo['type']) #,phoneNumber:'%s',image='https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo["image"])
            self.webview.page().runJavaScript(command)

    @pyqtSlot()
    def rerenderShuttleInfo(self):
        command = "shuttleState.setInfo({name:'%s', licenseNumber :'%s', image: 'https://littleriders.co.kr/api/content/%s',type:'%s'})"%(self.shuttleInfo["name"],self.shuttleInfo["licenseNumber"],self.shuttleInfo['image'],self.shuttleInfo['type']) #,phoneNumber:'%s',image='https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo["image"])
        self.webview.page().runJavaScript(command)

    @pyqtSlot()
    def renderRouteListRequest(self):
        self.routeList = apiFetcher.getRouteList()
        command = f"routeState.setInfo({self.routeList})"
        self.webview.page().runJavaScript(command)

    @pyqtSlot(QVariant)
    def choiceRouteId(self,routeId):
        print("라우트 아이디",routeId)
        self.routeId = routeId

    @pyqtSlot(result=QVariant)
    def canMoveTagBarcodePage(self):
        return self.routeId != None
    
    @pyqtSlot()
    def beep(self):
        buzzerHelper.beep()
    
    @pyqtSlot(result=QVariant)
    def canMoveStartDrivePage(self):
        return self.routeId != None and self.driverInfo != None and self.teacherInfo != None
    
    @pyqtSlot()
    def startDrive(self):
        print(self.routeId)
        print(self.driverInfo["id"])
        print(self.teacherInfo["id"])
    

    def keyPressEvent(self, e):
        self.endScanButton.setFocus(True)
        
        if(e.key()==Qt.Key_Return):
            if(self.barcode.startswith("DRIVER")):
                self.renderDriverByUuid(self.barcode.lstrip("DRIVER_"))
            elif(self.barcode.startswith("TEACHER")):
                self.renderTeacherByUuid(self.barcode.lstrip("TEACHER_"))
            else:
                buzzerHelper.beep()
                buzzerHelper.beep()
            self.barcode = ""
            return
        try:
            # print(chr(e.key()))
            self.barcode += chr(e.key())  
        except:
            pass

    def endScanButtonEvent(self):
        if(self.barcode.startswith("DRIVER")):
            self.renderDriverByUuid(self.barcode.replace("DRIVER_",""))
        elif(self.barcode.startswith("TEACHER")):
            self.renderTeacherByUuid(self.barcode.replace("TEACHER_",""))
        else:
            buzzerHelper.beep()
            buzzerHelper.beep()
        self.barcode = ""

    def renderDriverByUuid(self,uuid):
        if(not self.url.endswith("qr")):
            return
        self.driverInfo = apiFetcher.getDriverInfo(uuid)
        print(self.driverInfo)
        command = "driverState.setInfo({name:'%s 기사님', phoneNumber :'%s', image: 'https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo['image']) #,phoneNumber:'%s',image='https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo["image"])
        self.webview.page().runJavaScript(command)

    def renderTeacherByUuid(self,uuid):
        if(not self.url.endswith("qr")):
            return
        
        self.teacherInfo = apiFetcher.getTeacherInfo(uuid)
        print(self.teacherInfo)
        command = "teacherState.setInfo({name:'%s 선생님', phoneNumber :'%s', image: 'https://littleriders.co.kr/api/content/%s'})"%(self.teacherInfo["name"],self.teacherInfo["phoneNumber"],self.teacherInfo['image']) #,phoneNumber:'%s',image='https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo["image"])
        self.webview.page().runJavaScript(command)

    
    def startDrive(self):
        self.onDriver = True
        postionThread.register(bluetoothThread)
        postionThread.register(self)
        bluetoothThread.start()
        bluetoothThread.register(self)
        



    def notify(self,*args,**kwargs):
        position = kwargs.get("position",None)
        beaconUUIDListWithLatitudeLongitude = kwargs.get("beaconUUIDListWithLatitudeLongitude",None)
        if(position and self.onDrive):
            apiFetcher.uploadPosition(position)

        
        if(beaconUUIDListWithLatitudeLongitude and self.onDrive):
            latitude = beaconUUIDListWithLatitudeLongitude["latitude"]
            longitude = beaconUUIDListWithLatitudeLongitude["longitude"]
            beaconUUIDList = beaconUUIDListWithLatitudeLongitude["beaconUUIDList"]
            beaconUUIDSet = set(list(beaconUUIDList.keys()))
            boardUuidSet =  (self.beaconUUIDSet | beaconUUIDSet) - self.beaconUUIDSet
            
            dropUuidSet = (self.beaconUUIDSet | beaconUUIDSet) - beaconUUIDSet
            self.beaconUUIDSet = beaconUUIDSet
            for uuid in dropUuidSet:
                apiFetcher.getDrop(uuid,latitude,longitude)
            for uuid in boardUuidSet:
                apiFetcher.getBoard(uuid,latitude,longitude)




class StartDriveForm(QDialog,QWidget,formStartDriveClass,ObserverInterface):

    def __init__(self):
        super(StartDriveForm,self).__init__()
        self.setupUi(self)
        self.endDriveButton.clicked.connect(self.endDriveButtonEvent)

        self.webview = QWebEngineView()
        self.webview.setUrl(QUrl("https://device.littleriders.co.kr"))
        self.mapLayout.addWidget(self.webview)
        self.webview.loadFinished.connect(self.on_load_finished)
        self.mapLoad = False

        postionThread.register(bluetoothThread)
        postionThread.register(self)
        bluetoothThread.start()
        bluetoothThread.register(self)

        self.beaconUUIDSet = set()


    

    def endDriveButtonEvent(self):


        reply = QMessageBox.question(self, '운행종료', '정말로 운행을 종료할까요?',
                                    QMessageBox.Yes | QMessageBox.No, QMessageBox.No)

        if reply == QMessageBox.Yes:
            apiFetcher.getEndDrive()
            postionThread.detach(self)
            postionThread.detach(bluetoothThread)
            bluetoothThread.stop()
            
            self.close()
        else:
            print("NO")
       

    
    def notify(self,*args,**kwargs):
        position = kwargs.get("position",None)
        beaconUUIDListWithLatitudeLongitude = kwargs.get("beaconUUIDListWithLatitudeLongitude",None)
        if(position):
            latitude = position.getLatitude()
            longitude = position.getLongitude()
            speed = position.getSpeed()
            apiFetcher.uploadPosition(position)
            if(self.mapLoad):
                self.webview.page().runJavaScript(f'change({latitude},{longitude})')

        
        if(beaconUUIDListWithLatitudeLongitude):
            latitude = beaconUUIDListWithLatitudeLongitude["latitude"]
            longitude = beaconUUIDListWithLatitudeLongitude["longitude"]
            beaconUUIDList = beaconUUIDListWithLatitudeLongitude["beaconUUIDList"]

            beaconUUIDSet = set(list(beaconUUIDList.keys()))

            print(beaconUUIDSet)

            boardUuidSet =  (self.beaconUUIDSet | beaconUUIDSet) - self.beaconUUIDSet
            
            dropUuidSet = (self.beaconUUIDSet | beaconUUIDSet) - beaconUUIDSet
            self.beaconUUIDSet = beaconUUIDSet
            for uuid in dropUuidSet:
                apiFetcher.getDrop(uuid,latitude,longitude)
            for uuid in boardUuidSet:
                apiFetcher.getBoard(uuid,latitude,longitude)






if __name__ == "__main__":
    app = QApplication(sys.argv)
    win = MainWindow()
    
    positionSaver = PositionSaver()
    postionThread = PositionThread()
    postionThread.register(positionSaver)

    bluetoothThread = BluetoothThread()

    
    win.show()
    app.exec()
