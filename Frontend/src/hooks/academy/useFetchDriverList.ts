import { useQuery } from '@tanstack/react-query'

import { getDriverList } from '@apis/academy/getDriverList'

import { Driver } from '@types'

export const useFetchDriverList = () => {
  const { data: driverList, ...rest } = useQuery({
    queryKey: ['getDriverList'],
    queryFn: getDriverList,
    select: (data) => {
      const driverList: Driver[] = data.data
      return driverList
    },
  })

  return { driverList, ...rest }
}
