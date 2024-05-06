import { useEffect, useState } from 'react'

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

export default function RealTimeMap({
  shuttleList,
  selectedShuttle,
  onSelect,
}: Props) {
  const { shuttleId } = selectedShuttle

  // shuttleId로 실시간 셔틀 위치 SSE 요청
  useFetchRealTimeShuttleInfo(shuttleId)

  const { drawRealTimeMarker, drawPolyLine } = useSetRealTimeMap()

  // 임시 초기 위치
  const [position, setPosition] = useState({
    latitude: 37.566295,
    longitude: 126.977945,
  })

  // 상,하,좌,우 이동 버튼 (임시)
  const handleMove = (dir: number) => {
    const dr = [1, -1, 0, 0]
    const dc = [0, 0, -1, 1]

    setPosition((prev) => {
      const newPosition = {
        latitude: prev.latitude + dr[dir] / 10000,
        longitude: prev.longitude + dc[dir] / 10000,
      }

      drawPolyLine(prev, newPosition)

      return newPosition
    })
  }

  // 현재 position에 따라서 마커를 새로 그림
  useEffect(() => {
    drawRealTimeMarker(position)
  }, [drawRealTimeMarker, position])

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
        <button onClick={() => handleMove(0)}>위로 이동</button>
        <button onClick={() => handleMove(1)}>아래로 이동</button>
        <button onClick={() => handleMove(2)}>왼쪽으로 이동</button>
        <button onClick={() => handleMove(3)}>오른쪽으로 이동</button>
      </div>
    </div>
  )
}
