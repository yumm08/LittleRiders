

class RMCPosition:
    def __init__(self,msg):
        latitude = float(msg.lat)
        longitude = float(msg.lon)
        latitude_head = latitude //100
        longitude_head = longitude //100
        latitude_tail = (latitude%100) /60
        longitude_tail = (longitude%100)/60
        self.latitude = round(latitude_head + latitude_tail,5)
        self.longitude = round(longitude_head + longitude_tail,5)
        self.speed = int(float(msg.spd_over_grnd))

    def getLatitude(self):
        return self.latitude
    
    def getLongitude(self):
        return self.longitude
    
    def getSpeed(self):
        return self.speed
    
    def toJson(self):
        return {
            "latitude" : self.latitude,
            "longitude" : self.longitude,
            "speed" : self.speed
        }

    def __repr__(self):
        return(f"latitude={self.latitude},longitude={self.longitude},speed={self.speed}")

