import axiosInstance from '@utils/httpCommons'

import { DriveHistoryType } from '@types'

const BASE_URL = 'academy'

export const getDriveInfoByDayList = async (
  shuttleId: number,
  driveHistoryList: DriveHistoryType[] | undefined,
  dateId: number,
) => {
  if (!driveHistoryList) return
  return await axiosInstance.get(`${BASE_URL}/history/shuttle`, {
    params: {
      shuttleId: shuttleId,
      date: driveHistoryList[dateId].time,
    },
  })
}
