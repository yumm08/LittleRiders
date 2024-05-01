import {
  useRedrawMarkers,
  useRedrawPolyLine,
  useRedrawStudentMarkers,
  useSetMap,
} from '@hooks/shuttle/driveHistoryMap'

import { ChildLogDropDown } from './ChildLogDropDown'
import { RouteLogDropDown } from './RouteLogDropDown'

interface Props {
  data: any
}

/**
 *
 * @summary Route 페이지에서 사용되는 NaverMap
 * @returns
 */

export default function DriveHistoryNaverMap({ data }: Props) {
  const type = '등원'

  // Map 초기화
  const { naverMap } = useSetMap({ type, data: data[type] })
  // Map에 마커 찍기
  useRedrawMarkers({ naverMap, data: data[type] })
  useRedrawStudentMarkers({ naverMap, data: data[type] })
  // Map에 선 그리기
  useRedrawPolyLine({ naverMap, data: data[type] })

  return (
    <div
      id="map"
      className="relative h-[450px] w-screen rounded-md max-2xl:w-full"
    >
      <RouteLogDropDown />
      <ChildLogDropDown />
    </div>
  )
}
