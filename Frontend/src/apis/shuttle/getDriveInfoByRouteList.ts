import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'academy'

export const getDriveDetailInfoByHistoryList = async (historyId: number) => {
  return await axiosInstance.get(`${BASE_URL}/history/${historyId}`)
}
