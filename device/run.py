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

import urllib.request


form_class = uic.loadUiType("untitled.ui")[0]
formRounteInfoClass = uic.loadUiType("untitled2.ui")[0]
formScanBarcodeClass = uic.loadUiType("untitled3.ui")[0]

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

    def run(self):
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
        self.terminate()
        


    async def async_run(self):
        while True:
            if(self.speed <=3):
                print("haha")
                latitude = self.latitude
                longitude = self.longitude
                await asyncio.sleep(3.0)
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

        # self.hide()
        # self.second = BarcodeScanView()
        # self.second.exec()

        buzzerHelper.beep()
        self.hide()
        self.second = ChoiceRouteview()
        self.second.exec()
        self.show()
        

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
        self.titleName.setText("등록된 코스 목록")
        self.mapLoad = False
        self.nextButton.setVisible(False)
        self.show()
    

    def addItem(self,data):
        item = RouteInfoListWidget(data=data)
        self.courseListWidget.addItem(item)

    def clickItem(self,items):
        buzzerHelper.beep()
        #stationList = apiFetcher.getStationListByRouteId(items.getId())
        stationList = items.getStationList()
        self.webview.page().runJavaScript(f"reChangePostion({stationList})")

    def doubleClickItem(self,items):
        pass

    def closeWindow(self):
        buzzerHelper.beep()
        self.close()

    def on_load_finished(self, success):
        if success:
            self.mapLoad = True
            if(len(self.routeList) >0):
                self.webview.page().runJavaScript(f'reChangePostion({self.routeList[0]["stationList"]})')

    def nextButtonEvent(self):
        print("hihi")


class ChoiceRouteview(QDialog,QWidget,formRounteInfoClass):

    def __init__(self):
        super(ChoiceRouteview,self).__init__()
        self.setupUi(self)

        self.routeList = apiFetcher.getRouteList()
        for i in self.routeList:
            self.addItem(i)
        
        self.exitButton.clicked.connect(self.closeWindow)
        self.webview = QWebEngineView()
        self.webview.setUrl(QUrl("https://device.littleriders.co.kr/route"))
        self.stationMapLayout.addWidget(self.webview)
        self.webview.loadFinished.connect(self.on_load_finished)
        self.titleName.setText("코스를 선택하세요")
        self.mapLoad = False
        self.clicked = None
        self.show()
    

    def addItem(self,data):
        item = RouteInfoListWidget(data=data)
        self.courseListWidget.addItem(item)

    def clickItem(self,items):
        buzzerHelper.beep()
        #stationList = apiFetcher.getStationListByRouteId(items.getId())
        stationList = items.getStationList()
        self.webview.page().runJavaScript(f"reChangePostion({stationList})")
        self.clicked = items

    def closeWindow(self):
        buzzerHelper.beep()
        self.close()

    def on_load_finished(self, success):
        if success:
            self.mapLoad = True
            if(len(self.routeList) >0):
                self.webview.page().runJavaScript(f'reChangePostion({self.routeList[0]["stationList"]})')

    def nextButtonEvent(self):
        if(not self.clicked):
            buzzerHelper.beep()
            buzzerHelper.beep()
            QMessageBox.about(self,'운행 오류!','코스를 선택해주세요!')
            return
        

        buzzerHelper.beep()
        self.hide()
        self.close()
        self.second = BarcodeScanView(routeId=self.clicked.getId())
        self.second.exec()
        

        # self.hide()
        # self.second = ChoiceRouteview()
        # self.second.exec()
        






class BarcodeScanView(QDialog,QWidget,formScanBarcodeClass):

    def __init__(self,routeId):
        super(BarcodeScanView,self).__init__()
        self.setupUi(self)
        self.exitButton.clicked.connect(self.closeWindow)
    
        self.driverImagePixmap = QPixmap("scan.png").scaled(180, 180, Qt.KeepAspectRatio)
        # self.driverImagePixmap.loadFromData(apiFetcher.getImage())
        self.teacherImagePixmap = QPixmap("scan.png").scaled(180, 180, Qt.KeepAspectRatio)
        self.driverImageLabel.setPixmap(self.driverImagePixmap)
        self.teacherImageLabel.setPixmap(self.teacherImagePixmap)
        self.teacherInfo = None
        self.driverInfo = None
        self.barcode = ""

        self.endScanButton.setFocus(True)

        self.routeId= routeId
        print(self.routeId)
        self.show()

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
            print(chr(e.key()))
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
    

        #print(chr(e.key()),end="")

    def renderDriverByUuid(self,uuid):

        self.driverInfo = apiFetcher.getDriverInfo(uuid)
        image = apiFetcher.getImage(self.driverInfo["image"])
        self.driverImagePixmap.loadFromData(image)
        self.driverImagePixmap = self.driverImagePixmap.scaled(180, 180, Qt.KeepAspectRatio)
        self.driverNameLabel.setText(self.driverInfo["name"])
        self.driverPhoneLabel.setText(self.driverInfo["phoneNumber"])
        self.driverImageLabel.setPixmap(self.driverImagePixmap)

    def renderTeacherByUuid(self,uuid):
        self.teacherInfo = apiFetcher.getTeacherInfo(uuid)
        image = apiFetcher.getImage(self.teacherInfo["image"])
        self.teacherImagePixmap.loadFromData(image)
        self.teacherImagePixmap = self.teacherImagePixmap.scaled(180, 180, Qt.KeepAspectRatio)
        self.teacherNameLabel.setText(self.teacherInfo["name"])
        self.teacherPhoneLabel.setText(self.teacherInfo["phoneNumber"])
        self.teacherImageLabel.setPixmap(self.teacherImagePixmap)

    
    def nextButtonEvent(self):
        
        if(self.driverInfo == None and self.teacherInfo == None):
            buzzerHelper.beep()
            buzzerHelper.beep()
            QMessageBox.critical(self,'운행 오류','기사님과 선생님 QR을 스캔해주세요.')
            return
        if(self.driverInfo == None):
            buzzerHelper.beep()
            buzzerHelper.beep()
            QMessageBox.critical(self,'운행 오류','기사님 QR을 스캔해주세요.')
            return

        if(self.teacherInfo == None):
            buzzerHelper.beep()
            buzzerHelper.beep()
            QMessageBox.critical(self,'운행 오류','선생님 QR을 스캔해주세요.')
            return
        
        apiFetcher.getStartDrive(routeId=self.routeId,driverId=self.driverInfo["id"],teacherId=self.teacherInfo["id"])
        buzzerHelper.beep()
        self.hide()
        self.close()
        self.second = StartDriveForm()
        self.second.exec()
        return
        # QMessageBox.about(self,'운행을 시작했습니다!',f'운행시작!! \nriverid={self.driverInfo["id"]}\n,routeId={self.routeId},\nteacherId={self.teacherInfo["id"]}')


    def closeWindow(self):
        buzzerHelper.beep()
        self.close()
    




class BoardChildListWidget(QListWidgetItem):
    def __init__(self,data=None,parent=None):
        super().__init__("",parent)
    
        self.id = data["id"]
        self.name = data["name"]
        self.setText(self.name)
        self.setFont(QFont("Arial",23))
        # su.__init__(parent)

    def getId(self):
        return self.id
    
    def getStationList(self):
        return self.stationList


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


    def addItem(self,data):
        item = BoardChildListWidget(data=data)
        self.courseListWidget.addItem(item)


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
       

    def on_load_finished(self, success):
        if success:
            self.mapLoad = True
            position = positionRepository.findLastPosition()
            if(not position):
                self.webview.page().runJavaScript(f'console.log("hello world")')

                return
            self.webview.page().runJavaScript(f'change({position.latitude},{position.longitude})')


    
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
    postionThread.start()
    postionThread.register(win)
    postionThread.register(positionSaver)

    bluetoothThread = BluetoothThread()

    
    win.show()
    app.exec()
