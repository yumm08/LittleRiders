import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

export const postNewShuttle = async (formData: FormData) => {
  await axiosInstance.post(`${BASE_URL}/shuttle`, formData, {
    method: 'post',
    data: formData,
  })
}
