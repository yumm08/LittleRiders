import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

export const getPendingChildList = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/child/pending`)

  console.log(response)

  return response
}
