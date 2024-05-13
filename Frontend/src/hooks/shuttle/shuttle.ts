import { useEffect } from 'react'

import { useQuery, useQueryClient } from '@tanstack/react-query'

import { getShuttle } from '@apis/shuttle/getShuttle'

import { InitData, LocationInfo } from '@types'
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

    const handleInit = (event: MessageEvent) => {
      const initData: InitData = JSON.parse(event.data)
      const shuttleId = initData.shuttleId

      queryClient.setQueryData(['initData', shuttleId], initData)
    }

    const handleLocation = (event: MessageEvent) => {
      const locationInfo: LocationInfo = JSON.parse(event.data)
      const shuttleId = locationInfo.shuttleId

      queryClient.setQueryData(['locationInfo', shuttleId], locationInfo)
    }

    eventSource.addEventListener('init', handleInit)
    eventSource.addEventListener('location', handleLocation)

    return () => {
      eventSource.removeEventListener('init', () => console.log('init close'))
      eventSource.removeEventListener('location', () =>
        console.log('location close'),
      )

      eventSource.close()
    }
  }, [])
}
