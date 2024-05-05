import requests
from Model import RMCPosition
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


    def getRouteList(self) -> dict:
        url = f"{self.BASE_URL}/shuttle/route"
        
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


