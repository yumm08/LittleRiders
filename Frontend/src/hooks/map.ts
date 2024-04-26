import { useRef, useState } from 'react'

import { wow } from '../assets/image/academy.svg'

import { BASE_LAT, BASE_LNG } from '@constants/map'
import { Station } from '@types'

const DEFAULT_OPTION = {
  center: new naver.maps.LatLng(BASE_LAT, BASE_LNG),
  zoom: 7,
  minZoom: 7,
  ZoomControl: true,
  disableKineticPan: false,
}

export function MapHook() {
  const map = useRef<naver.maps.Map>()
  const [pathList, setPathList] = useState<naver.maps.LatLng[]>([])
  const [markerList, setMarkerList] = useState<naver.maps.Marker[]>([])
  const [polyline, setPolyline] = useState<naver.maps.Polyline>()
  //const [circleList, setCircleList] = useState<naver.maps.Circle[]>([])

  /**
   * 초기 맵 생성 (useEffect 내에서 선언)
   * @param mapId map 을 생성할 div의 id
   * @param options map에 들어갈 여러가지 option, 없을 경우 defaultOption으로 생성
   */
  const initMap = (mapId: string, options = DEFAULT_OPTION) => {
    if (map.current) return
    map.current = new naver.maps.Map(mapId, options)
    console.log(map.current)
  }

  /**
   * 초기 Polyline Overlay 생성
   */
  const initPolyLine = () => {
    setPolyline(
      new naver.maps.Polyline({
        map: map.current,
        path: pathList,
        strokeWeight: 3,
        strokeColor: '#555555',
        strokeOpacity: 0.8,
      }),
    )
  }
  /**
   * marker 위에 infowindow Event를 추가하는 함수
   * @param content infowindow안에 띄워줄 content (HTMLElement | String)
   * @param marker 대상이 되는 Marker
   */
  const addInfoWindow = async (marker: naver.maps.Marker, content: string) => {
    const infoWindow = new naver.maps.InfoWindow({
      content: content,
      maxWidth: 140,
      backgroundColor: '#EEEEEE',
      borderColor: '#111111',
      borderWidth: 5,
      anchorSize: new naver.maps.Size(30, 30),
      anchorSkew: true,
      anchorColor: '#EEEEEE',
      pixelOffset: new naver.maps.Point(20, -20),
    })
    naver.maps.Event.addListener(marker, 'mouseon', () => {
      if (map.current) infoWindow.open(map.current, marker)
    })

    naver.maps.Event.addListener(marker, 'mouseout', () => {
      if (map.current) infoWindow.close()
    })
  }

  /**
   * Route에 따른 정류장과 어린이집 마커 추가
   */
  const drawRouteMarkers = async (newPathList: naver.maps.LatLng[]) => {
    setMarkerList([
      new naver.maps.Marker({
        position: newPathList[0],
        map: map.current,
        icon: {
          content:
            '<img src="/src/assets/image/academy.svg" style="width:30px; height:30px"/>',
          anchor: new naver.maps.Point(16, 16),
          origin: new naver.maps.Point(29, 50),
        },
      }),
    ])

    const academyInfoWindowContent = [
      '<div class="iw_inner">',
      '  <h3>어린이집</h3>',
      '</div>',
    ].join('')

    addInfoWindow(markerList[0], academyInfoWindowContent)

    for (let k = 1; k < newPathList.length - 1; k++) {
      const stationInfoWindowContent = [
        '<div class="iw_inner">',
        ` <h3>정류장 ${k}</h3>`,
        '</div>',
      ].join('')
      setMarkerList((prev) => [
        ...prev,
        new naver.maps.Marker({
          position: newPathList[k],
          map: map.current,
          icon: {
            content: [
              '<img src="/src/assets/image/bus-stop.svg" style="width:30px; height:30px"/>',
            ].join(''),
            size: new naver.maps.Size(50, 52),
            anchor: new naver.maps.Point(15, 30),
            origin: new naver.maps.Point(29, 50),
          },
        }),
      ])
      addInfoWindow(markerList[k], stationInfoWindowContent)
    }
  }

  /**
   * draw polylines to represent the route
   */
  const drawPolyLines = (newPathList: naver.maps.LatLng[]) => {
    if (polyline) polyline.setPath(newPathList)
  }

  // TODO 학원 좌표 arg로 받아야함
  /**
   * draw whole rotue to the map
   * @param stationRoute station 배열 정보
   */
  const drawRoute = (stationRoute: Station[]) => {
    const newPathList = []
    // TODO 이부분에 args 로 받은 학원 좌표 추가
    console.log(map.current)
    newPathList.push(new naver.maps.LatLng(BASE_LAT, BASE_LNG))

    for (let k = 0; k < stationRoute.length; k++) {
      newPathList.push(
        new naver.maps.LatLng(
          stationRoute[k].latitude,
          stationRoute[k].longitude,
        ),
      )
    }
    // TODO 여기도 마찬가지로 학원 좌표 추가
    newPathList.push(new naver.maps.LatLng(BASE_LAT, BASE_LNG))

    console.log(newPathList)
    setPathList(newPathList)

    drawPolyLines(newPathList)
    drawRouteMarkers(newPathList)
  }

  naver.maps.InfoWindow

  /**
   * \
   */
  // const drawCircleList = () => {}

  return {
    map,
    pathList,
    markerList,
    polyline,
    initMap,
    initPolyLine,
    addInfoWindow,
    drawPolyLines,
    drawRoute,
    drawRouteMarkers,
  }
}
