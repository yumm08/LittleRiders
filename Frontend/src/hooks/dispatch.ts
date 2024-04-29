import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'

import {
  getRouteDetail,
  getRouteList,
  getStationList,
  postRoute,
  putRoute,
} from '@apis/dispatch'

import { Route } from '@types'

export const useGetRouteList = () => {
  const { data: routeList, ...rest } = useQuery({
    queryKey: ['routeList'],
    queryFn: getRouteList,
    select: (data) => {
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
export const usePostRoute = () => {
  const queryClient = useQueryClient()
  const { mutate: addRoute, ...rest } = useMutation({
    mutationFn: (route: Route) => postRoute(route),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['routeList'] })
    },
  })

  return { addRoute, ...rest }
}
export const usePutRoute = () => {
  const queryClient = useQueryClient()
  const { mutate: modifyRoute, ...rest } = useMutation({
    mutationFn: ({ routeId, route }: { routeId: number; route: Route }) =>
      putRoute(routeId, route),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['routeList'] })
    },
  })

  return { modifyRoute, ...rest }
}

export const StationModifyMutate = () => {}

export const RouteDeleteMutate = () => {}

export const StationDeleteMutate = () => {}
