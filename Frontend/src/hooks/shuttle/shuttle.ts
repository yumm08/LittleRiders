import { useEffect } from 'react'

import { useQuery, useQueryClient } from '@tanstack/react-query'

import { getShuttle } from '@apis/shuttle/getShuttle'

import { BoardInfo, DropInfo, EndInfo, InitData, LocationInfo } from '@types'
import { EventSourcePolyfill } from 'event-source-polyfill'

export const useFetchShuttleList = () => {
  const { data: shuttleList, ...rest } = useQuery({
    queryKey: ['getShuttleList'],
    queryFn: getShuttle,
    select: (data) => {
      const shuttleList = data.data
      return shuttleList
    },
  })

  return { shuttleList, ...rest }
}

export const useFetchRealTimeShuttleInfo = () => {
  const queryClient = useQueryClient()

  useEffect(() => {
    const eventSourceUrl = `/api/academy/connection`

    const eventSource = new EventSourcePolyfill(eventSourceUrl, {
      headers: {
        Authorization: sessionStorage.getItem('accessToken') as string,
      },
      withCredentials: true,
      heartbeatTimeout: 86400000,
    })

    const handleInit = async (event: MessageEvent) => {
      const initData: InitData = await JSON.parse(event.data)
      const shuttleId = initData.shuttleId

      queryClient.setQueryData(['initData', shuttleId], initData)
    }

    const handleLocation = async (event: MessageEvent) => {
      const locationInfo: LocationInfo = await JSON.parse(event.data)
      const shuttleId = locationInfo.shuttleId

      queryClient.setQueryData(['locationInfo', shuttleId], locationInfo)
    }

    const handleBoard = async (event: MessageEvent) => {
      const boardInfo: BoardInfo = await JSON.parse(event.data)
      const shuttleId = boardInfo.shuttleId

      queryClient.setQueryData(['boardInfo', shuttleId], boardInfo)
    }

    const handleDrop = async (event: MessageEvent) => {
      const dropInfo: DropInfo = await JSON.parse(event.data)
      const shuttleId = dropInfo.shuttleId

      queryClient.setQueryData(['dropInfo', shuttleId], dropInfo)
    }

    const handleEnd = async (event: MessageEvent) => {
      const endInfo: EndInfo = await JSON.parse(event.data)
      const shuttleId = endInfo.shuttleId

      queryClient.setQueryData(['endInfo', shuttleId], endInfo)
    }

    eventSource.addEventListener('init', () => handleInit)
    eventSource.addEventListener('location', () => handleLocation)
    eventSource.addEventListener('board', () => handleBoard)
    eventSource.addEventListener('drop', () => handleDrop)
    eventSource.addEventListener('end', () => handleEnd)

    return () => {
      eventSource.removeEventListener('init', () => handleInit)
      eventSource.removeEventListener('location', () => handleLocation)
      eventSource.removeEventListener('board', () => handleBoard)
      eventSource.removeEventListener('drop', () => handleDrop)
      eventSource.removeEventListener('end', () => handleEnd)

      eventSource.close()
    }
  }, [])
}
