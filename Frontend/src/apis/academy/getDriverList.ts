import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

export const getDriverList = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/driver`)

  return response
}
