const MULTICAMPUS_LOCATION = {
    latitude: 37.5034921,
    longitude: 127.0337143,
};

var mapOptions = {
    center: new naver.maps.LatLng(
        MULTICAMPUS_LOCATION.latitude,
        MULTICAMPUS_LOCATION.longitude
    ),
    zoom: 16,
};

var map = new naver.maps.Map("map", mapOptions);

naver.maps.Event.addListener(map, "click", function (e) {
    const latitue = e.latlng.lat();
    const longitude = e.latlng.lng();
    console.log(`{ latitude : ${latitue} , longitude : ${longitude} }`);
});



const baseUrl = `https://demo.wookoo.shop/api/shuttle/${Math.floor(Math.random() * 4) + 1}` //이 1~ 4번 버스중 랜덤픽


const init = async () => {
    const data = await fetch(`${baseUrl}/route`);
    const polygon = await data.json();

    var pathList = [];
    for (p of polygon) {
        pathList.push(new naver.maps.LatLng(p.latitude, p.longitude));
    }
    map.setCenter(pathList[0]);
    pathList.push(
        new naver.maps.LatLng(polygon[0].latitude, polygon[0].longitude)
    );

    var polyline = new naver.maps.Polyline({
        map: map,
        path: pathList,
        stroke: 10,
        strokeColor: "#009000",
        strokeOpacity: 0.6,
        strokeWeight: 3,
    });

    var marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(37.4981383, 127.0281461),
        map: map,
        icon: {
            content: [
                `<div class='pin' style="background: #009000"><span>bus</span></div>`,
            ].join(""),
            size: new naver.maps.Size(38, 38),
            anchor: new naver.maps.Point(20, 40),
        },
    });

    setInterval(async () => {
        const data = await fetch(
            `${baseUrl}/location`
        );
        const latlong = await data.json();
        console.log(latlong);
        const busLocation = new naver.maps.LatLng(
            latlong.latitude,
            latlong.longitude
        );
        marker.setPosition(busLocation);
        map.setCenter(busLocation);
    }, 1000);
};
init();
// asyncLoop();
