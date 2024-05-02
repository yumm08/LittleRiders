import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'academy'

export const postNewTeacher = async (formData: FormData) => {
  await axiosInstance.post(`${BASE_URL}/teacher`, formData, {
    method: 'post',
    data: formData,
  })
}
