

const MULTICAMPUS_LOCATION = {
   latitude:37.5034921,
   longitude : 127.0337143
}

var mapOptions = {
    center: new naver.maps.LatLng(MULTICAMPUS_LOCATION.latitude, MULTICAMPUS_LOCATION.longitude),
    zoom: 16
};

var map = new naver.maps.Map('map', mapOptions);

naver.maps.Event.addListener(map, 'click', function(e) {
    const latitue = e.latlng.lat()
    const longitue = e.latlng.lng()
    console.log(
        `{ latitude : ${latitue} , longitude : ${longitue} }`
    );
});

console.log(map)
const polygon = [
    { latitude : 37.5043773 , longitude : 127.0244983 }
    ,{ latitude : 37.4979936 , longitude : 127.0276096 }
    ,{ latitude : 37.5025048 , longitude : 127.0428017 }
    ,{ latitude : 37.505654 , longitude : 127.0414069 }
    ,{ latitude : 37.5086328 , longitude : 127.0385316 }
]

var pathList = []
for (p of polygon){
    pathList.push(new naver.maps.LatLng(p.latitude,p.longitude));
}
pathList.push(new naver.maps.LatLng(polygon[0].latitude,polygon[0].longitude));
var polyline = new naver.maps.Polyline({
    map: map,
    path: pathList,
    stroke: 10,
    strokeColor: '#009000',
    strokeOpacity: 0.6,
    strokeWeight: 3
});




const busMove = [
    { latitude : 37.4981383 , longitude : 127.0281461 }
,{ latitude : 37.4983766 , longitude : 127.0289722 }
,{ latitude : 37.4985128 , longitude : 127.029573 }
,{ latitude : 37.4987341 , longitude : 127.0301416 }
,{ latitude : 37.4988958 , longitude : 127.0306459 }
,{ latitude : 37.499032 , longitude : 127.0314398 }
,{ latitude : 37.4992533 , longitude : 127.0320621 }
,{ latitude : 37.4994236 , longitude : 127.032856 }
,{ latitude : 37.4996108 , longitude : 127.0332101 }
,{ latitude : 37.49973 , longitude : 127.0335641 }
,{ latitude : 37.4998577 , longitude : 127.0340791 }
,{ latitude : 37.500113 , longitude : 127.0348731 }
,{ latitude : 37.5003599 , longitude : 127.0355597 }
,{ latitude : 37.5005386 , longitude : 127.0362249 }
,{ latitude : 37.500794 , longitude : 127.0371046 }
,{ latitude : 37.5009472 , longitude : 127.0378342 }
,{ latitude : 37.5011514 , longitude : 127.0382956 }
,{ latitude : 37.5013132 , longitude : 127.0387998 }
,{ latitude : 37.5014834 , longitude : 127.0392826 }
,{ latitude : 37.5016111 , longitude : 127.0398727 }
,{ latitude : 37.5017387 , longitude : 127.0403984 }
,{ latitude : 37.501926 , longitude : 127.0410314 }
,{ latitude : 37.5020707 , longitude : 127.0414176 }
,{ latitude : 37.5022409 , longitude : 127.0420077 }
,{ latitude : 37.5023771 , longitude : 127.0424047 }
,{ latitude : 37.5025048 , longitude : 127.0427266 }
]

var markerOptions = {
    position:  new naver.maps.LatLng(37.4981383, 127.0281461),
    map: map,
    icon: {
        content: [
            `<div class='pin' style="background: #009000"><span>bus</span></div>`
                ].join(''),
        size: new naver.maps.Size(38, 38),
        anchor: new naver.maps.Point(20, 40),
    },
};


var marker = new naver.maps.Marker(markerOptions);





let currentIndex = 0;

// 비동기적으로 반복문 실행하는 함수
function asyncLoop() {
    // 현재 인덱스를 출력하거나 작업 수행
//    console.log(busMove[currentIndex]);

    marker.setPosition(new naver.maps.LatLng(busMove[currentIndex].latitude,busMove[currentIndex].longitude))

    if(currentIndex == 3){
        let marker = new naver.maps.Marker(
            {
                position:  new naver.maps.LatLng(busMove[currentIndex].latitude,busMove[currentIndex].longitude),
                map : map,
                icon: {
                    content: [
                        `<div class='pin' style="background: blue" ><span>on</span></div>`
                            ].join(''),
                    size: new naver.maps.Size(38, 38),
                    anchor: new naver.maps.Point(20, 40),
                },
            }
        )

        naver.maps.Event.addListener(marker, 'click', function(e) {
            alert("김관우 승차")
        });
    }


    if(currentIndex == 7){
        let marker = new naver.maps.Marker(
            {
                position:  new naver.maps.LatLng(busMove[currentIndex].latitude,busMove[currentIndex].longitude),
                map : map,
                icon: {
                    content: [
                        `<div class='pin' style="background: red" ><span>off</span></div>`
                            ].join(''),
                    size: new naver.maps.Size(38, 38),
                    anchor: new naver.maps.Point(20, 40),
                },
            }
        )
        naver.maps.Event.addListener(marker, 'click', function(e) {
            alert("김관우 하차")
        });
    }

    // 다음 인덱스로 이동
    currentIndex++;

   

    // 모든 요소를 방문했으면 종료
    if (currentIndex === busMove.length) {
        return;
    }

    // 다음 반복을 1초 후에 실행
    setTimeout(asyncLoop, 300);
}
asyncLoop()