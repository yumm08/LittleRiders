import axiosInstance from '@utils/httpCommons'

import API from '@constants/api'

export const postNewShuttle = async (formData: FormData) => {
  await axiosInstance.post(`${API.BASE_URL}/${API.POST_SHUTTLE}`, formData, {
    method: 'post',
    data: formData,
  })
}
