import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'

import {
  deleteStation,
  getRouteDetail,
  getRouteList,
  getStationList,
  postRoute,
  postStation,
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

export const usePostStation = () => {
  const queryClient = useQueryClient()
  const { mutate: addStation, ...rest } = useMutation({
    mutationFn: ({
      name,
      latitude,
      longitude,
    }: {
      name: string
      latitude: number
      longitude: number
    }) => postStation(name, latitude, longitude),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['stationList'] })
    },
  })

  return { addStation, ...rest }
}

export const useModifyStation = () => {}

export const useDeleteRoute = () => {}

export const useDeleteStation = () => {
  const queryClient = useQueryClient()
  const { mutate: removeStation, ...rest } = useMutation({
    mutationFn: (stationId: number) => deleteStation(stationId),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['stationList'] })
    },
  })

  return { removeStation, ...rest }
}
