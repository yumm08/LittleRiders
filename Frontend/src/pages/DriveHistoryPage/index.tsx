import { useCallback, useState } from 'react'

import BackPageButton from '@components/Shared/BackPageButton'
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
  const [dateId, setDateId] = useState<number>(0)
  const [historyId, setHistoryId] = useState<number>(0)

  const onClickChangeDateId = useCallback((id: number) => {
    setDateId(id)
    // 날짜 변경 시 가장 첫번째 운행 기록을 자동으로 조회하기
    setHistoryId(0)
  }, [])

  const onClickChangeHistoryId = useCallback((id: number) => {
    setHistoryId(id)
  }, [])

  const {
    driveHistoryList,
    isLoading: driveHistoryLoading,
    isError,
  } = useFetchDriveHistoryList(parseInt(shuttleId as string))

  const { driveInfoByDayList, isLoading: driveInfoByDayLoading } =
    useFetchDriveInfoByDayList(
      parseInt(shuttleId as string),
      driveHistoryList,
      dateId,
    )

  const {
    driveDetailInfoByHistory,
    isLoading: driveDetailInfoByHistoryLoading,
  } = useFetchDriveDetailInfoByHistory(historyId, driveInfoByDayList)

  if (isError)
    return (
      <div className=" flex h-[100vh] w-[100vw] flex-col items-center justify-center bg-lightgreen text-white">
        <strong className="text-2xl">차량의 운행 기록이 없습니다!</strong>
        <Spacing style="h-5" />
        <BackPageButton />
      </div>
    )

  if (
    driveHistoryLoading ||
    driveInfoByDayLoading ||
    driveDetailInfoByHistoryLoading
  )
    return (
      <div className=" flex h-[100vh] w-[100vw] flex-col items-center justify-center bg-lightgreen text-white">
        <div role="status">
          <svg
            aria-hidden="true"
            className="inline h-8 w-8 animate-spin fill-darkgray text-gray-200 dark:text-gray-600"
            viewBox="0 0 100 101"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z"
              fill="currentColor"
            />
            <path
              d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z"
              fill="currentFill"
            />
          </svg>
          <span className="sr-only">Loading...</span>
        </div>
      </div>
    )

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
