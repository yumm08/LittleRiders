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
            #requests.post(f"{self.BASE_URL}")
            print("init call apiFetcher")
            cls._init = True


    def getRouteList(self) -> dict:
        
        return [
            {"id": 1, "name": "탕수육 코스"},
            {"id": 2, "name": "탕후루 코스"},
            {"id": 3, "name": "햄버거 오마카세"},
            {"id": 4, "name": "소고기 오마카세"}
        ]

    def uploadPosition(self,posistion : RMCPosition) -> None:
        print(posistion)

if __name__ == "__main__":

    apitFetcher = APIFetcher()


