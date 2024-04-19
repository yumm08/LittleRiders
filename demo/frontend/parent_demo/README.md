# 실행 방법

`map.html` 파일의 `8번 라인` 의 코드를 수정해야 한다.

[네이버 지도 api](https://console.ncloud.com/naver-service/application) 에서, `Web Dynamic Map, Static Map` API 사용 신청을 한다.

`Web 서비스 URL` 에 `http://127.0.0.1:5500` 과 같이 개발용 웹서버 주소를 적는다. 

```html
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId={API_KEY}"></script>
```
그 후 `API_KEY` 부분을 발급받은 `API_KEY` 로 교체한다.

개발용 웹서버를 실행 후 테스트하면 된다.

