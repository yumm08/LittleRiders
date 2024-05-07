import { useEffect, useRef } from 'react'

import { useQuery } from '@tanstack/react-query'

import { useSetRealTimeMap } from '@hooks/main/realTimeMap'
import { useFetchRealTimeShuttleInfo } from '@hooks/shuttle'

import { AcademyShuttle } from '@types'

const SHUTTLE_BUTTON_STYLE = {
  SELECT: 'bg-yellow shadow-inner shadow-darkgray',
  NON_SELECT: 'bg-white',
}

interface Props {
  shuttleList: AcademyShuttle[]
  selectedShuttle: AcademyShuttle
  onSelect: (shuttle: AcademyShuttle) => void
}

type Position = {
  latitude: number
  longitude: number
}

type RealTimeShuttleInfo = {
  latitude: number
  longitude: number
  speed: number
}

export default function RealTimeMap({
  shuttleList,
  selectedShuttle,
  onSelect,
}: Props) {
  const { shuttleId } = selectedShuttle
  const curPosition = useRef<Position>(null!)
  const { initRealTimeMap, drawRealTimeMarker, drawPolyLine } =
    useSetRealTimeMap()

  // shuttleId로 실시간 셔틀 위치 SSE 요청
  useFetchRealTimeShuttleInfo(shuttleId)

  // React Query를 사용하여 쿼리 데이터 가져오기
  const { data: realTimeShuttleInfo } = useQuery<RealTimeShuttleInfo>({
    queryKey: ['realTimeShuttleInfo', shuttleId],
  })

  useEffect(() => {
    initRealTimeMap()
  }, [shuttleId])

  useEffect(() => {
    if (realTimeShuttleInfo) {
      console.log(realTimeShuttleInfo)
      const { latitude, longitude } = realTimeShuttleInfo
      const position = { latitude, longitude }

      drawRealTimeMarker(position)
    }
  }, [realTimeShuttleInfo])

  useEffect(() => {
    if (realTimeShuttleInfo) {
      const { latitude, longitude } = realTimeShuttleInfo
      const position = { latitude, longitude }

      if (curPosition.current) {
        drawPolyLine(curPosition.current, position)
      }

      curPosition.current = position
    }
  }, [realTimeShuttleInfo])

  return (
    <div id="realtime-map" className="relative h-full w-5/6">
      <div className="t-0 absolute z-10 flex h-11 w-full gap-2 p-2">
        {shuttleList.map((shuttle) => (
          <button
            key={shuttle.shuttleId}
            className={`rounded-xl px-4 ${selectedShuttle?.shuttleId === shuttle.shuttleId ? SHUTTLE_BUTTON_STYLE.SELECT : SHUTTLE_BUTTON_STYLE.NON_SELECT}`}
            onClick={() => onSelect(shuttle)}
          >
            <span className="font-bold">{shuttle.name}</span>
          </button>
        ))}
      </div>
    </div>
  )
}
