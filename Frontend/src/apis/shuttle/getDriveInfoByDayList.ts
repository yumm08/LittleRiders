import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

export const getDriveInfoByDayList = async (date:string) => {
  return await axiosInstance.get(`${BASE_URL}/history/${date}`)
}
