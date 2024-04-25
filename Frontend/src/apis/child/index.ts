import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'admin'

/**
 * 원생 대기 목록을 불러오는 함수
 */
export const getPendingChildList = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/child/pending`)

  return response
}

/**
 * 원생 승인 요청을 거절하는 함수
 */
export const deletePendingChildList = async (
  academyChildAllowPendingList: number[],
) => {
  const response = await axiosInstance.delete(`${BASE_URL}/child/pending`, {
    data: { academyChildAllowPendingList },
  })

  return response
}
