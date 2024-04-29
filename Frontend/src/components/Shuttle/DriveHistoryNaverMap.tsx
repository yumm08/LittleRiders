import { useState } from 'react'

import {
  useRedrawMarkers,
  useRedrawPolyLine,
  useRedrawStudentMarkers,
  useSetMap,
} from '@hooks/shuttle/driveHistoryMap'

const options = {
  center: new naver.maps.LatLng(37.359924641705476, 127.1148204803467),
  zoom: 13,
  minZoom: 7,
  zoomControl: true,
  disableKineticPOan: false,
}
const focused = 'border-lightgreen text-lightgreen'
const nonFocused = 'border-lightgray text-lightgray'

interface Props {
  data: any
}

/**
 *
 * @summary Route 페이지에서 사용되는 NaverMap
 * @returns
 */

export default function DriveHistoryNaverMap({ data }: Props) {
  const [type, setType] = useState<string>('등원')
  const onClickTypeHandler = (e: any) => {
    const id = e.target.id
    if (id === '등원' && type === '하원') setType('등원')
    else if (id === '하원' && type === '등원') setType('하원')
  }
  // Map 초기화
  const { naverMap } = useSetMap({ options })
  // Map에 마커 찍기
  useRedrawMarkers({ naverMap, data: data[type] })
  useRedrawStudentMarkers({ naverMap, data: data[type] })
  // Map에 선 그리기
  useRedrawPolyLine({ naverMap, data: data[type] })

  return (
    <div
      id="map"
      className="max-2xl:w-ful relative h-[450px] w-screen rounded-md"
    >
      <div
        onClick={onClickTypeHandler}
        id="등원"
        className={`border-c-lightgreen absolute left-[50px] top-[10px] z-50 rounded-xl border-2  bg-white p-2 font-bold ${type === '등원' ? focused : nonFocused}`}
      >
        등원 : {data['등원'].노선이름}
      </div>
      <div
        onClick={onClickTypeHandler}
        id="하원"
        className={`border-c-lightgreen absolute left-[180px] top-[10px] z-50 rounded-xl border-2  bg-white p-2 font-bold ${type === '하원' ? focused : nonFocused}`}
      >
        하원 : {data['하원'].노선이름}
      </div>
    </div>
  )
}
