import { useQuery } from '@tanstack/react-query'

import { getRouteDetail, getRouteList, getStationList } from '@apis/dispatch'

export const useGetRouteList = () => {
  const { data: routeList, ...rest } = useQuery({
    queryKey: ['routeList'],
    queryFn: getRouteList,
    select: (data) => {
      console.log(data)
      const routeList = data?.data
      return routeList
    },
  })
  return { routeList, ...rest }
}

export const useGetRouteDetail = (routeId: number) => {
  const { data: routeDetail, ...rest } = useQuery({
    queryKey: ['routeDetail', routeId],
    queryFn: () => getRouteDetail(routeId),
    select: (data) => {
      const routeDetail = data?.data
      return routeDetail
    },
    enabled: routeId !== -1,
  })
  return { routeDetail, ...rest }
}

export const useGetStationList = () => {
  const { data: stationList, ...rest } = useQuery({
    queryKey: ['stationList'],
    queryFn: getStationList,
    select: (data) => {
      const stationList = data?.data
      return stationList
    },
  })
  return { stationList, ...rest }
}

// TODO childList query add
/**
 *
 */
// export const useGetChildList = () => {
//   const { data: childList, ...rest } = useQuery({
//     queryKey: ['childList'],
//     queryFn: getChildList,
//     select: (data) => {
//       const childList = data?.data.content
//       return childList
//     },
//   })

//   return { childList, ...rest }
// }

export const RouteModifyMutate = () => {}

export const StationModifyMutate = () => {}

export const RouteDeleteMutate = () => {}

export const StationDeleteMutate = () => {}
