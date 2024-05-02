import { useState } from 'react'

import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'
import DriveHistoryNaverMap from '@components/Shuttle/DriveHistoryNaverMap'

import {
  useFetchDriveDetailInfoByHistory,
  useFetchDriveHistoryList,
  useFetchDriveInfoByDayList,
} from '@hooks/shuttle/driverHistory'

import DriveHistoryList from './DriveHistoryList'

import Page from '@layouts/Page'
import { useParams } from 'react-router-dom'

export default function DriveHistoryPage() {
  const { shuttleId } = useParams()
  // 날짜 선택
  const [dateId, setDateId] = useState<number>(0)
  // 운행 기록 선택
  const [historyId, setHistoryId] = useState<number>(0)
  const onClickChangeDateId = (id: number) => {
    setDateId(id)
    // 날짜 변경 시 가장 첫번째 운행 기록을 자동으로 조회하기
    setHistoryId(0)
  }
  const onClickChangeHistoryId = (id: number) => {
    setHistoryId(id)
  }

  const { driveHistoryList, isLoading: driveHistoryLoading } =
    useFetchDriveHistoryList(parseInt(shuttleId as string))

  const { driveInfoByDayList, isLoading: driveInfoByDayLoading } =
    useFetchDriveInfoByDayList(
      parseInt(shuttleId as string),
      driveHistoryList,
      dateId,
    )

  const {
    driveDetailInfoByHistory,
    isLoading: driveDetailInfoByHistoryLoading,
  } = useFetchDriveDetailInfoByHistory(historyId)

  if (
    driveHistoryLoading ||
    driveInfoByDayLoading ||
    driveDetailInfoByHistoryLoading
  )
    return <div>Loading...</div>

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
          <DriveHistoryList
            driveHistoryList={driveHistoryList}
            onClickChangeDateId={onClickChangeDateId}
            dateId={dateId}
          />
        </div>
        {/* 네이버 지도 */}
        <Spacing style="w-[40px]" />
        <DriveHistoryNaverMap
          driveInfoByDayList={driveInfoByDayList}
          driveDetailInfoByHistory={driveDetailInfoByHistory}
          onClickHistoryId={onClickChangeHistoryId}
          historyId={historyId}
        />
      </div>
    </Page>
  )
}
