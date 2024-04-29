import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

export const postNewDriver = async (formData: FormData) => {
  await axiosInstance.post(`${BASE_URL}/driver`, formData, {
    method: 'post',
    data: formData,
  })
}
