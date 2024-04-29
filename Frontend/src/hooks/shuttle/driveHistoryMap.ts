import { useEffect, useState } from 'react'

import COLOR_PALETTE from '@style/ColorPalette'
import student from 'assets/Mock/employee.jpg'

type UseSetMapProps = {
  options: naver.maps.NaverStyleMapTypeOptions
}

export function useSetMap({ options }: UseSetMapProps) {
  const [naverMap, setNaverMap] = useState<naver.maps.Map>()

  useEffect(() => {
    const map = new naver.maps.Map('map', options)
    setNaverMap(map)
  }, [])
  return { naverMap }
}

type useRedrawMarkersProps = {
  naverMap: naver.maps.Map | undefined
  data: any
}

export function useRedrawMarkers({ naverMap, data }: useRedrawMarkersProps) {
  const [markers, setMarkers] = useState<naver.maps.Marker[]>([])
  useEffect(() => {
    markers.forEach((marker) => {
      marker.setMap(null)
    })
    const markerArray: naver.maps.Marker[] = []
    // marker 업데이트
    data.정류장리스트.forEach((e: any) => {
      const marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(e.위도, e.경도),
        map: naverMap,
      })
      markerArray.push(marker)
    })
    if (naverMap)
      naver.maps.Event.addListener(naverMap, 'idle', function () {
        updateMarkers(naverMap, markerArray)
      })
    setMarkers(markerArray)

    function updateMarkers(
      map: naver.maps.Map | undefined,
      markerArray: naver.maps.Marker[],
    ) {
      if (!map) return
      markers.forEach((marker) => {
        marker.setMap(null)
      })
      const mapBounds: naver.maps.Bounds = map.getBounds()
      let marker, position
      for (let i = 0; i < markerArray.length; i++) {
        marker = markerArray[i]
        position = marker.getPosition()
        if ((mapBounds as naver.maps.LatLngBounds).hasLatLng(position)) {
          showMarker(map, marker)
        } else {
          hideMarker(marker)
        }
      }
    }

    function showMarker(map: naver.maps.Map, marker: naver.maps.Marker) {
      marker.setMap(map)
    }

    function hideMarker(marker: naver.maps.Marker) {
      marker.setMap(null)
    }
  }, [data, naverMap])
  return {}
}

type UseRedrawPolyLineProps = {
  naverMap: naver.maps.Map | undefined
  data: any
}

export function useRedrawPolyLine({ naverMap, data }: UseRedrawPolyLineProps) {
  const [polyLines, setPolyLines] = useState<naver.maps.Polyline>()
  useEffect(() => {
    polyLines?.setMap(null)
    const polyline = new naver.maps.Polyline({
      map: naverMap,
      path: data.정류장리스트.map((e: any) => {
        return new naver.maps.LatLng(e.위도, e.경도)
      }),
      strokeColor: COLOR_PALETTE['lightgreen'],
      strokeWeight: 5,
    })
    setPolyLines(polyline)
  }, [data, naverMap])
}

export function useRedrawStudentMarkers({
  naverMap,
  data,
}: useRedrawMarkersProps) {
  const [markers, setMarkers] = useState<naver.maps.Marker[]>([])
  useEffect(() => {
    markers.forEach((marker) => {
      marker.setMap(null)
    })
    const markerArray: naver.maps.Marker[] = []
    // marker 업데이트
    data.원생승하차지점.forEach((e: any) => {
      const marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(e.위도, e.경도),
        map: naverMap,
        icon: {
          url: student,
          size: new naver.maps.Size(25, 25),
          origin: new naver.maps.Point(0, 0),
          anchor: new naver.maps.Point(25, 26),
        },
      })
      markerArray.push(marker)
    })
    if (naverMap)
      naver.maps.Event.addListener(naverMap, 'idle', function () {
        updateMarkers(naverMap, markerArray)
      })
    setMarkers(markerArray)

    function updateMarkers(
      map: naver.maps.Map | undefined,
      markerArray: naver.maps.Marker[],
    ) {
      if (!map) return
      markers.forEach((marker) => {
        marker.setMap(null)
      })
      const mapBounds: naver.maps.Bounds = map.getBounds()
      let marker, position
      for (let i = 0; i < markerArray.length; i++) {
        marker = markerArray[i]
        position = marker.getPosition()
        if ((mapBounds as naver.maps.LatLngBounds).hasLatLng(position)) {
          showMarker(map, marker)
        } else {
          hideMarker(marker)
        }
      }
    }

    function showMarker(map: naver.maps.Map, marker: naver.maps.Marker) {
      marker.setMap(map)
    }

    function hideMarker(marker: naver.maps.Marker) {
      marker.setMap(null)
    }
  }, [data, naverMap])
  return {}
}
