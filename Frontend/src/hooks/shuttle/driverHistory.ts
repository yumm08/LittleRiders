import { useQuery } from '@tanstack/react-query'

import { getDriveHistoryList } from '@apis/shuttle/getDriveHistory'
import { getDriveInfoByDayList } from '@apis/shuttle/getDriveInfoByDayList'
import { getDriveDetailInfoByHistoryList } from '@apis/shuttle/getDriveInfoByRouteList'

import { DriveHistoryType } from '@types'

/**
 * @summary 차량ID를 넘겨 차량별 운행 날짜 리스트를 받아오는 훅
 * @param shuttleId
 * @returns
 */
export const useFetchDriveHistoryList = (shuttleId: number) => {
  const { data: driveHistoryList, ...rest } = useQuery({
    queryKey: ['getDriveHistoryList', shuttleId],
    queryFn: () => getDriveHistoryList(shuttleId),
    select: (data) => {
      const driveHistoryList: DriveHistoryType[] = data.data
      return driveHistoryList
    },
  })

  return { driveHistoryList, ...rest }
}
/**
 * @summary 특정 차량의 특정 날짜의 운행 기록 리스트를 받아오는 훅
 * @param date
 * @returns
 */
export const useFetchDriveInfoByDayList = (date: string) => {
  const { data: driveInfoByDayList, ...rest } = useQuery({
    queryKey: ['getDriveInfoByDayList', date],
    queryFn: () => getDriveInfoByDayList(date),
    select: (data) => {
      const driveInfoByDayList: any[] = data.data
      return driveInfoByDayList
    },
  })

  return { driveInfoByDayList, ...rest }
}
/**
 * @summary  id를 받아서, 특정 차량의 특정 날짜의 운행 상세정보를 받아오는 훅
 * @param id 노선 id
 * @returns
 */
export const useFetchDriveDetailInfoByHistoryList = (id: number) => {
  const { data: driveDetailInfoByHistoryList, ...rest } = useQuery({
    queryKey: ['getDriveInfoByRouteList', id],
    queryFn: () => getDriveDetailInfoByHistoryList(id),
    select: (data) => {
      const driveDetailInfoByHistoryList: any[] = data.data
      return driveDetailInfoByHistoryList
    },
  })

  return { driveDetailInfoByHistoryList, ...rest }
}
