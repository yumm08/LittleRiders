import pynmea2
from Model import RMCPosition
file = open('sensor.txt', encoding='utf8')
idx = 0
for line in file.readlines():
    if(line.find("RMC") >0):
        msg = pynmea2.parse(line)
        position = RMCPosition(msg)
        print(repr(position))


