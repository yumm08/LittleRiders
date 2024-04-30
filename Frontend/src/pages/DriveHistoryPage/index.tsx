import { useState } from 'react'

import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'
import DriveHistory from '@components/Shuttle/DriveHistory'
import DriveHistoryNaverMap from '@components/Shuttle/DriveHistoryNaverMap'

import { useFetchDriveHistoryList } from '@hooks/shuttle/driverHistory'

import Page from '@layouts/Page'
import { DUMMY_DRIVE_HISTORY } from '@mocks/shuttle/dummy'
import { useParams } from 'react-router-dom'

const DUMMY = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
export default function DriveHistoryPage() {
  const { shuttleId } = useParams()
  const [historyId, setHistoryId] = useState<number>(0)
  const [date,setDate] = useState<string>('')

  const { driveHistoryList, isLoading } = useFetchDriveHistoryList(
    parseInt(shuttleId as string),
  )

  const {driveInfoByDayList,isLoading} = useFetchDriveInfoByDayList(date)

  if (isLoading) return <div>Loading...</div>
  return (
    <Page>
      <Spacing style="h-[60px]" />
      <strong className="text-2xl">N호차 운행 기록</strong>
      <Divider />
      <Spacing style="h-[40px]" />
      <div className="flex h-[550px] w-full">
        {/* 목록 리스트 */}
        <div className="h-[550px] w-[270px] ">
          <div className="flex h-12 w-full items-center justify-center rounded-t-xl bg-lightgreen">
            <span className="text-xm text-white">운행 기록</span>
          </div>
          <ul className="h-[400px] w-full overflow-scroll overflow-x-hidden">
            {driveHistoryList?.map((history) => {
              return <DriveHistory key={history.time} time={history.time} />
            })}
          </ul>
        </div>
        {/* 네이버 지도 */}
        <Spacing style="w-[40px]" />
        <DriveHistoryNaverMap data={DUMMY_DRIVE_HISTORY(historyId)} />
      </div>
    </Page>
  )
}
