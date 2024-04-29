import axiosInstance from '@utils/httpCommons'

import { Station } from '@types'

const BASE_URL = 'station'

/**
 * post Station to Server, Axios call
 * @param station : Station {name, lat, lng}
 * @returns response
 */
export const postStation = async (station: Station) => {
  const response = await axiosInstance.post(`/${BASE_URL}`, station)
  return response
}

// NOTE BE 에 API 수정 요청 진행 (pagenation 불필요 판단)

/**
 * get StationList Axios call
 * @returns
 */
export const getStationList = async () => {
  const response = await axiosInstance.get(`/${BASE_URL}`)
  return response
}

export const deleteStation = async (stationId: number) => {
  const response = await axiosInstance.delete(`/${BASE_URL}/${stationId}`)
  return response
}

/**
 * put, modify Station Info Axios call
 * @param stationId : number
 * @param station : Station
 * @returns
 */
export const putStation = async (stationId: number, station: Station) => {
  const response = await axiosInstance.put(`/${BASE_URL}/${stationId}`, station)
  return response
}
