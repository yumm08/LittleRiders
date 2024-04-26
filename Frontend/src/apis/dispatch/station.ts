import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'station'

/**
 *
 * @returns
 */
export const postStation = async () => {
  const res = await axiosInstance.post(`/${BASE_URL}/`)
  return res
}
/**
 *
 * @returns
 */
export const getStationList = async () => {
  const res = await axiosInstance.get(`/${BASE_URL}`, {})
  return res
}

/**
 *
 * @param stationId
 * @returns
 */
export const deleteStation = async (stationId: number) => {
  const res = await axiosInstance.delete(`/${BASE_URL}/${stationId}`)
  return res
}

/**
 *
 * @param stationId
 * @returns
 */
export const putStation = async (stationId: number) => {
  const res = await axiosInstance.put(`/${BASE_URL}/${stationId}`)
  return res
}
