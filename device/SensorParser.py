import pynmea2
from Model import RMCPosition
# import serial
# file = open('sensor.txt', encoding='utf8')
# idx = 0
# for line in file.readlines():
#     if(line.find("RMC") >0):
#         msg = pynmea2.parse(line)
#         position = RMCPosition(msg)
#         print(repr(position))


class SensorReceiver:
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):   
            cls._instance = super().__new__(cls)  
        return cls._instance                    

    def __init__(self, observerList):
        cls = type(self)
        self.serialPort = serial.Serial("/dev/serial0", 9600, timeout=0.5)
        if not hasattr(cls, "_init"):             
            cls._init = True
    
    def getPosition(self):
        data = self.serialPort.readline()
        data = data.decode("utf8")
        if(data.find("RMC") > 0):
            msg = pynmea2.parse(data)
            latitude = float(msg.lat)
            longitude = float(msg.lon)
            speed = float(msg.spd_over_grnd)
            return RMCPosition(latitude,longitude,speed)

        raise Exception("error")

    

class SensorMockingGenerator:

    def __init__(self):
        with open("sensor_log/log2.txt","r",encoding="utf8") as f:
            self.positionList = []
            for line in f.readlines():
                try:
                    if(line.find("RMC") > 0):
                        msg = pynmea2.parse(line)
                        latitude = float(msg.lat)
                        longitude = float(msg.lon)
                        speed = float(msg.spd_over_grnd)
                        self.positionList.append(RMCPosition(latitude,longitude,speed))
                except:
                    
                    pass

                
            self.idx = 0

    def generateSensorValue(self):
        self.idx = (self.idx +1)%len(self.positionList)
        return self.positionList[self.idx]
        


if __name__ == "__main__":
    sensor = SensorMockingGenerator()
    while True:
        print(sensor.generateSensorValue())