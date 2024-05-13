import { useEffect, useRef } from 'react'

import { useQuery } from '@tanstack/react-query'

import { useSetRealTimeMap } from '@hooks/main/realTimeMap'

import { InitData, LocationInfo } from '@types'

interface Props {
  shuttleId: number
  realTimeMap: naver.maps.Map
}

export default function Shuttle({ shuttleId, realTimeMap }: Props) {
  const curLocationInfo = useRef<LocationInfo>()

  const {
    drawRealTimeMarker,
    drawPolylineWithList,
    setDirection,
    initPolyline,
  } = useSetRealTimeMap()

  const { data: locationInfo } = useQuery<LocationInfo>({
    queryKey: ['locationInfo', shuttleId],
  })

  const { data: initData } = useQuery<InitData>({
    queryKey: ['initData', shuttleId],
  })

  // init 데이터가 있다면, polyline을 그린다
  useEffect(() => {
    if (initData) {
      const locationList = initData.locationList

      initPolyline(realTimeMap)

      locationList
        .sort((a, b) => new Date(a.time) - new Date(b.time))
        .forEach((locationInfo) => {
          drawPolylineWithList(locationInfo, realTimeMap)
        })
    }
  }, [initData])

  // 위치 정보가 변화하고 있다면, 실시간으로 마커를 찍는다
  useEffect(() => {
    if (locationInfo) {
      drawRealTimeMarker(locationInfo, realTimeMap)

      if (curLocationInfo.current) {
        setDirection(curLocationInfo.current, locationInfo)
      }

      curLocationInfo.current = locationInfo
    }
  }, [locationInfo])

  // 위치 정보가 변화하고 있다면, 실시간으로 polyline을 그린다
  useEffect(() => {
    if (locationInfo) {
      drawPolylineWithList(locationInfo, realTimeMap)
    }
  }, [locationInfo])

  return null
}
