import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'

import { getChild, getChildList, putChild } from '@apis/child'

import { ChildStatus } from '@types'

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

export const useFetchChild = (academyChildId: number) => {
  const { data: childDetailInfo, ...rest } = useQuery({
    queryKey: ['getChild', academyChildId],
    queryFn: () => getChild(academyChildId),
    select: (data) => {
      const childDetailInfo = data.data

      return childDetailInfo
    },
  })

  return { childDetailInfo, ...rest }
}

export const usePutChild = (academyChildId: number, status: ChildStatus) => {
  const queryClient = useQueryClient()

  const { mutate: updateChildStatus, ...rest } = useMutation({
    mutationFn: () => putChild(academyChildId, status),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['getChildList'] })

      await queryClient.invalidateQueries({
        queryKey: ['getChild', academyChildId],
      })
    },
  })

  return { updateChildStatus, ...rest }
}
