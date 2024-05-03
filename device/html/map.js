

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
 
 
let marker = new naver.maps.Marker(markerOptions);

const change = (latitude, longitude) => {
    const position = new naver.maps.LatLng(latitude, longitude);
    marker.setPosition(position);
    map.setCenter(position)
}
 