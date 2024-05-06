

const CENTER_LOCATION = {
    latitude:37.5257,
    longitude : 130.9073
}

let mapOptions = {
    center: new naver.maps.LatLng(CENTER_LOCATION.latitude, CENTER_LOCATION.longitude),
    zoom: 16
};

let map = new naver.maps.Map('map', mapOptions);

let markerOptions = {
    position:  new naver.maps.LatLng(CENTER_LOCATION.latitude, CENTER_LOCATION.longitude),
    map: map
};
 

let markerList = []

const reChangePostion = (positionList) => {
    for (marker of markerList) {
        marker.setMap(null);
    }
    markerList = [];

    let latitudeCenter = 0;
    let longitudeCenter = 0;
    for (p of positionList) {
        const position = new naver.maps.LatLng(p.latitude, p.longitude);
        latitudeCenter = latitudeCenter + p.latitude;
        longitudeCenter = longitudeCenter +p.longitude;
        markerList.push(new naver.maps.Marker({
            position: position,
            map: map,
            icon: {
                content: [
                    `<div class='pin' style="background: #009000"><span>${p.visitOrder}</span></div>`
                        ].join(''),
                size: new naver.maps.Size(38, 38),
                anchor: new naver.maps.Point(20, 40),
            }
        }));
    }
    latitudeCenter = latitudeCenter / positionList.length
    longitudeCenter = longitudeCenter / positionList.length

    map.setCenter(new naver.maps.LatLng(latitudeCenter, longitudeCenter));
    map.setZoom(13);


}
// const changePosition = (latitude) => {
//     const position = new naver.maps.LatLng(latitude, longitude);
//     marker.setPosition(position);
//     map.setCenter(position)
// }
 