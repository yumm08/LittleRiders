import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'route'

export const getRouteList = async () => {
  const res = await axiosInstance.get(`/${BASE_URL}`)
  console.log(res)
  return res
}

export const getRouteDetail = async (routeId: number) => {
  const res = await axiosInstance.get(`/${BASE_URL}/${routeId}`)
  return res
}

export const postRoute = async () => {
  const res = await axiosInstance.post(`/${BASE_URL}`)
  return res
}

export const putRoute = async (routeId: number) => {
  const res = await axiosInstance.put(`/${BASE_URL}/${routeId}`)
  return res
}

export const deleteRoute = async (routeId: number) => {
  const res = await axiosInstance.delete(`/${BASE_URL}/${routeId}`)
  return res
}

export const postRouteChild = async () => {
  const res = await axiosInstance.get(`/${BASE_URL}/`)
  return res
}
