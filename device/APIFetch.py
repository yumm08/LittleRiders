import requests
from Model import RMCPosition
import random
class APIFetcher:
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):         # Foo 클래스 객체에 _instance 속성이 없다면
            cls._instance = super().__new__(cls)  # Foo 클래스의 객체를 생성하고 Foo._instance로 바인딩
        return cls._instance                      # Foo._instance를 리턴

    def __init__(self, terminalNumber : str):
        cls = type(self)
        if not hasattr(cls, "_init"):             # Foo 클래스 객체에 _init 속성이 없다면
            self.terminalNumber = terminalNumber
            self.BASE_URL = "https://littleriders.co.kr/api"
            self.signIn()
            cls._init = True

    def signIn(self):
        
        url = f"{self.BASE_URL}/shuttle/account/sign-in"
        response = requests.post(url=url,json={"terminalNumber":self.terminalNumber})
        if(response.status_code != 200):
            raise Exception()
        self.headers = {"Authorization" : response.headers["Authorization"]}
        self.cookies = {'refresh-token': response.cookies["refresh-token"]}

        
    
    def _reIssue(self):
        url = f"{self.BASE_URL}/account/re-issue"
        response = requests.get(url=url,cookies=self.cookies)
        if(response.status_code != 200):
            raise Exception()
        self.headers = {"Authorization" : response.headers["Authorization"]}
        self.cookies = {'refresh-token': response.cookies["refresh-token"]}


    def getStationListByRouteId(self,id) -> list:
        print("call here")

        url = f"{self.BASE_URL}/shuttle/route/{id}"

        
        itemList= [ {"id":1,"name":"강남역","latitude": 37.4979084,"longitude":127.0276954 },
            {"id":2,"name":"언주역","latitude": 37.5071434,"longitude":127.0340362 },
            {"id":3,"name":"역삼역","latitude": 37.5006407,"longitude":127.0370402  },
            {"id":4,"name":"선릉역","latitude": 37.5044794,"longitude":127.0489385  },
            {"id":5,"name":"신논현역", "latitude":37.5044369, "longitude":127.0246162},
            {"id":6,"name":"논현역", "latitude":37.5109732, "longitude":127.0214405  },
            {"id":7,"name":"학동역", "latitude":37.5142071, "longitude":127.0316544  },
            {"id":8,"name":"반포역", "latitude":37.5079604, "longitude":127.0116558  },
            {"id":9,"name":"교대역 1번출구", "latitude":37.4940609,"longitude": 127.0156148 },
            {"id":10,"name":"교대역 9번출구", "latitude":37.4932948,"longitude": 127.013147 }
            ]
        
        result = random.sample(itemList,random.randint(1,len(itemList)))
        for index,i in enumerate(result):
            i["visitOrder"]=index+1
        
    
        return result
    
    def _getRandomStation(self) -> list:
        stationList = [ {"id":1,"name":"강남역","latitude": 37.4979084,"longitude":127.0276954 },
            {"id":2,"name":"언주역","latitude": 37.5071434,"longitude":127.0340362 },
            {"id":3,"name":"역삼역","latitude": 37.5006407,"longitude":127.0370402  },
            {"id":4,"name":"선릉역","latitude": 37.5044794,"longitude":127.0489385  },
            {"id":5,"name":"신논현역", "latitude":37.5044369, "longitude":127.0246162},
            {"id":6,"name":"논현역", "latitude":37.5109732, "longitude":127.0214405  },
            {"id":7,"name":"학동역", "latitude":37.5142071, "longitude":127.0316544  },
            {"id":8,"name":"반포역", "latitude":37.5079604, "longitude":127.0116558  },
            {"id":9,"name":"교대역 1번출구", "latitude":37.4940609,"longitude": 127.0156148 },
            {"id":10,"name":"교대역 9번출구", "latitude":37.4932948,"longitude": 127.013147 }
            ]
        choiced = list(random.sample(stationList,random.randint(1,len(stationList))))

        return [{'visitOrder': index+1, **i} for index, i in enumerate(choiced)]




    def getRouteList(self) -> list:
    
        url = f"{self.BASE_URL}/shuttle/route"

        

        return [
                {"id":1,"name":"등원A","stationList": self._getRandomStation()},
                {"id":2,"name":"등원B","stationList":self._getRandomStation()},
                {"id":3,"name":"등원C","stationList":self._getRandomStation()},
                {"id":4,"name":"등원D","stationList":self._getRandomStation()},
                {"id":5,"name":"등원E","stationList":self._getRandomStation()},
                {"id":6,"name":"등원F","stationList":self._getRandomStation()},
                {"id":7,"name":"등원G","stationList":self._getRandomStation()},
                {"id":8,"name":"등원H","stationList":self._getRandomStation()},
                {"id":9,"name":"등원I","stationList":self._getRandomStation()},
                {"id":10,"name":"등원J","stationList":self._getRandomStation()},
                {"id":11,"name":"등원K","stationList":self._getRandomStation()},
                {"id":12,"name":"등원!","stationList":self._getRandomStation()},
                {"id":13,"name":"등원2","stationList":self._getRandomStation()},
                {"id":14,"name":"등원3","stationList":self._getRandomStation()},
                {"id":15,"name":"등원4","stationList":self._getRandomStation()},
                {"id":16,"name":"등원5","stationList":self._getRandomStation()},
                {"id":17,"name":"등원6","stationList":self._getRandomStation()},
                {"id":18,"name":"등원7","stationList":self._getRandomStation()},
                {"id":19,"name":"등원8","stationList":self._getRandomStation()},
                {"id":20,"name":"등원9","stationList":self._getRandomStation()}
                
            ]
        

        response = requests.get(url,headers=self.headers)
        if(response.status_code == 400):
            self._reIssue()
            return self.getRouteList()
        
        return response.json()
        
    def uploadPosition(self,position :RMCPosition) -> None:
        url = f"{self.BASE_URL}/shuttle/location"
        data = position.toJson()
        
        response = requests.post(url,headers=self.headers,json=data)
        print(response.status_code)
        if(response.status_code != 201):
            self._reIssue()
            return self.uploadPosition(position=position)

if __name__ == "__main__":
    apiFetcher = APIFetcher("")
    print(apiFetcher.getRouteList())


