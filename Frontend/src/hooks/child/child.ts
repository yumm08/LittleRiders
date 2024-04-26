import { useQuery } from '@tanstack/react-query'

import { getChildList } from '@apis/child'

export const useFetchChildList = () => {
  const { data: childList, ...rest } = useQuery({
    queryKey: ['getChildList'],
    queryFn: getChildList,
    select: (data) => {
      const childList = data.data
      return childList
    },
  })

  return { childList, ...rest }
}
