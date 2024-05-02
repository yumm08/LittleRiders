import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'academy'

export const postNewShuttle = async (formData: FormData) => {
  await axiosInstance.post(`${BASE_URL}/shuttle`, formData, {
    method: 'post',
    data: formData,
  })
}
