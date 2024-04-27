import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

/**
 * 원생 목록을 불러오는 API 요청 함수
 */
export const getChildList = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/child`)

  return response
}
