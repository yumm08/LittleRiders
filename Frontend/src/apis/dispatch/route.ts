import axiosInstance from '@utils/httpCommons'

import { Route } from '@types'

const BASE_URL = 'academy/route'

/**
 * get Route List Axios Call
 * @returns response
 */
export const getRouteList = async () => {
  const response = await axiosInstance.get(`/${BASE_URL}`)
  return response
}

/**
 * get Route Detail Axios Call
 * @param routeId : number
 * @returns response
 */
export const getRouteDetail = async (routeId: number) => {
  const response = await axiosInstance.get(`/${BASE_URL}/${routeId}`)
  return response
}

/**
 * post, Add Route Detail to Server, Axios Call
 * @param route  Route, Route Detail Data
 * @returns response
 */
export const postRoute = async (route: Route) => {
  const response = await axiosInstance.post(`/${BASE_URL}`, route)
  return response
}

/**
 * put, Modify Route Detail to Server, Axios Call
 * @param routeId : number
 * @param route: Route
 * @returns response
 */
export const putRoute = async (routeId: number, route: Route) => {
  const response = await axiosInstance.put(`/${BASE_URL}/${routeId}`, route)
  return response
}

/**
 * delete Route Axios Call
 * @param routeId : number
 * @returns response
 */
export const deleteRoute = async (routeId: number) => {
  const response = await axiosInstance.delete(`/${BASE_URL}/${routeId}`)
  return response
}

// TODO 원생 노선 추가 (원생 Type 지정, API 작성 대기)
/**
 *
 * @returns
 */
export const postRouteChild = async () => {
  const response = await axiosInstance.get(`/${BASE_URL}/child`)
  return response
}
