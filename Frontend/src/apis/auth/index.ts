import axiosInstance from '@utils/httpCommons'

export * from '@apis/auth/signIn'
export * from '@apis/auth/signUp'

const BASE_URL = '/account'

export const getSignOut = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/sign-out`)

  return response
}
