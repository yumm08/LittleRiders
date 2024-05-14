import { useEffect, useRef } from 'react'

import { useQuery } from '@tanstack/react-query'

import { useSetRealTimeMap } from '@hooks/main/realTimeMap'

import { BoardInfo, InitData, InitDataLocationInfo, LocationInfo } from '@types'

interface Props {
  shuttleId: number
  realTimeMap: naver.maps.Map
  isSelected: boolean
}

export default function Shuttle({ shuttleId, realTimeMap, isSelected }: Props) {
  const curLocationInfo = useRef<LocationInfo | InitDataLocationInfo>()

  const {
    drawRealTimeMarker,
    drawPolylineWithList,
    setDirection,
    initPolyline,
    drawBoardMarker,
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

      if (locationList.length > 0) {
        locationList.sort(
          (a, b) => new Date(a.time).getTime() - new Date(b.time).getTime(),
        )

        const lastLocationInfo = locationList.at(-1) as InitDataLocationInfo
        curLocationInfo.current = lastLocationInfo
        drawRealTimeMarker(lastLocationInfo, realTimeMap)
      }

      initPolyline(realTimeMap)

      locationList.forEach((locationInfo) => {
        drawPolylineWithList(locationInfo, realTimeMap)
      })
    }
  }, [initData])

  // init 데이터가 있다면, board marker를 그린다
  useEffect(() => {
    if (initData) {
      const boardList = initData.boardList

      boardList.forEach((boardInfo: BoardInfo) => {
        drawBoardMarker(boardInfo, realTimeMap)
      })
    }
  }, [initData])

  // init 데이터가 있다면, drop marker를 그린다
  useEffect(() => {
    if (initData) {
      const boardList = initData.boardList

      boardList.forEach((boardInfo: BoardInfo) => {
        drawBoardMarker(boardInfo, realTimeMap)
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

  // 선택한 호차의 위치를 맵의 중심으로 한다
  useEffect(() => {
    if (isSelected && curLocationInfo.current) {
      if (isSelected) {
        const { latitude, longitude } = curLocationInfo.current
        const latLng = new naver.maps.LatLng(latitude, longitude)
        realTimeMap.setCenter(latLng)
      }
    }
  }, [isSelected])

  return null
}
