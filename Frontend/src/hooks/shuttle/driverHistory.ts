import { useQuery } from '@tanstack/react-query'

import { getDriveHistoryList } from '@apis/shuttle/getDriveHistory'

import { DriveHistory } from '@types'

export const useFetchDriveHistoryList = (shuttleId: number) => {
  const { data: driveHistoryList, ...rest } = useQuery({
    queryKey: ['getDriveHistoryList', shuttleId],
    queryFn: () => getDriveHistoryList(shuttleId),
    select: (data) => {
      const driveHistoryList: DriveHistory[] = data.data
      return driveHistoryList
    },
  })

  return { driveHistoryList, ...rest }
}

export const useFetchDriveInfoByDayList = (date: string) => {
  const { data: DriveInfoByDayList, ...rest } = useQuery({
    queryKey: ['getDriveHistoryList', date],
    queryFn: () => getDriveInfoByDayList(date),
    select: (data) => {
      const DriveInfoByDayList: DriveHistory[] = data.data
      return DriveInfoByDayList
    },
  })

  return { DriveInfoByDayList, ...rest }
}
