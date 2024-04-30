import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

export const getDriveHistoryList = async (shuttleId: number) => {
  return await axiosInstance.get(`${BASE_URL}/shuttle/${shuttleId}/history`)
}
