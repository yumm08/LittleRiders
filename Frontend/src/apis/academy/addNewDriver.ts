import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'academy'

export const postNewDriver = async (formData: FormData) => {
  await axiosInstance.post(`${BASE_URL}/driver`, formData, {
    method: 'post',
    data: formData,
  })
}
