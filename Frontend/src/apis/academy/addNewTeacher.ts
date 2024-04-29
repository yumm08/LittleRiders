import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

export const postNewTeacher = async (formData: FormData) => {
  await axiosInstance.post(`${BASE_URL}/teacher`, formData, {
    method: 'post',
    data: formData,
  })
}
