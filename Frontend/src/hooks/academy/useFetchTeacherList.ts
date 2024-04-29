import { useQuery } from '@tanstack/react-query'

import { getTeacherList } from '@apis/academy/getTeacherList'

import { Teacher } from '@types'

export const useFetchTeacherList = () => {
  const { data: teacherList, ...rest } = useQuery({
    queryKey: ['getTeacherList'],
    queryFn: getTeacherList,
    select: (data) => {
      const teacherList: Teacher[] = data.data
      return teacherList
    },
  })

  return { teacherList, ...rest }
}
