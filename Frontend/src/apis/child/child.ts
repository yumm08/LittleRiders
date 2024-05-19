import axiosInstance from '@utils/httpCommons'

import { ChildStatus } from '@types'

const BASE_URL = 'academy'

/**
 * 원생 목록을 불러오는 API 요청 함수
 */
export const getChildList = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/child`)

  return response
}

/**
 * 한 원생의 개인정보를 불러오는 API 요청 함수
 *
 * @param academyChildId 원생 ID
 */
export const getChild = async (academyChildId: number) => {
  const response = await axiosInstance.get(
    `${BASE_URL}/child/${academyChildId}`,
  )

  return response
}

/**
 * 한 원생의 재학상태를 변경하는 API 요청 함수
 *
 * @param academyChildId 원생 ID
 * @param status 변경할 원생의 재학상태
 */
export const putChild = async (academyChildId: number, status: ChildStatus) => {
  const response = await axiosInstance.put(
    `${BASE_URL}/child/${academyChildId}`,
    { status },
  )

  return response
}
