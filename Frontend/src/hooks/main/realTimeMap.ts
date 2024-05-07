import { useState } from 'react'

import COLOR_PALETTE from '@style/ColorPalette'

const DEFAULT_OPTIONS = {
  zoom: 18,
  minZoom: 7,
  zoomControl: false,
  disableKineticPan: false,
}

type Position = {
  latitude: number
  longitude: number
}

// 전의 위치와 현재 위치를 기반으로 방향(방위각) 계산
const getDirection = (prevPosition: Position, curPosition: Position) => {
  const { latitude: lat1, longitude: lon1 } = prevPosition
  const { latitude: lat2, longitude: lon2 } = curPosition

  const dLon = lon2 - lon1
  const y = Math.sin(dLon) * Math.cos(lat2)
  const x =
    Math.cos(lat1) * Math.sin(lat2) -
    Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLon)

  let rotateDegree = Math.atan2(y, x) * (180 / Math.PI)

  if (rotateDegree < 0) {
    rotateDegree += 360
  }

  return rotateDegree
}

export const useSetRealTimeMap = () => {
  const [realTimeMap, setRealTimeMap] = useState<naver.maps.Map | null>(null!)
  const [realTimeMarker, setRealTimeMarker] = useState<naver.maps.Marker>()
  const [, setPolyline] = useState<naver.maps.Polyline>()

  // 맵 초기화 하는 함수
  const initRealTimeMap = () => {
    const map = new naver.maps.Map('realtime-map', DEFAULT_OPTIONS)

    setRealTimeMap(map)
  }

  // 현재 위치에 기반해서 마커를 찍는 함수
  const drawRealTimeMarker = (position: Position) => {
    if (!realTimeMap) {
      return
    }

    if (realTimeMarker) {
      realTimeMarker.setPosition(
        new naver.maps.LatLng(position.latitude, position.longitude),
      )
      realTimeMap.setCenter(
        new naver.maps.LatLng(position.latitude, position.longitude),
      )
    } else {
      const location = new naver.maps.LatLng(
        position.latitude,
        position.longitude,
      )

      const marker = new naver.maps.Marker({
        position: location,
        map: realTimeMap,
        icon: {
          content: `<div id="marker" style="transform:translate(-25px, -25px);width:50px;height:50px"><img src="/src/assets/image/bus.svg" style="width:50px; height:50px;" /></div>`,
        },
      })

      realTimeMap.setCenter(location)

      setRealTimeMarker(marker)
    }
  }

  // 전의 위치와 현재 위치를 기반으로 두 위치를 잇는 polyline을 그리는 함수
  const drawPolyLine = (prevPosition: Position, curPosition: Position) => {
    if (!realTimeMap) {
      return
    }

    const path = [
      new naver.maps.LatLng(prevPosition.latitude, prevPosition.longitude),
      new naver.maps.LatLng(curPosition.latitude, curPosition.longitude),
    ]

    const newPolyLine = new naver.maps.Polyline({
      path: path,
      strokeColor: COLOR_PALETTE.darkgreen,
      strokeWeight: 10,
      strokeOpacity: 0.7,
      strokeLineCap: 'round',
    })

    if (realTimeMarker) {
      const rotateDegree = getDirection(prevPosition, curPosition) + 90
      // const content = `<div id="marker" style="transform:translate(-15px, -15px);width:30px;height:30px"><img src="/src/assets/image/bus.svg" style="width:30px; height:30px; transform:rotate(${rotateDegree}deg);" /></div>`
      const content = `<div id="marker" style="transform:translate(-25px, -25px);width:50px;height:50px"><img src="/src/assets/image/bus.svg" style="width:50px; height:50px; transform:rotate(${rotateDegree}deg);" /></div>`
      realTimeMarker.setIcon({
        content: content,
      })
    }
    // drawRealTimeMarker(curPosition)

    newPolyLine.setMap(realTimeMap)

    setPolyline(newPolyLine)
  }

  return { realTimeMap, drawRealTimeMarker, drawPolyLine, initRealTimeMap }
}
