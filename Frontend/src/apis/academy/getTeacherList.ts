import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

export const getTeacherList = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/teacher`)

  return response
}
