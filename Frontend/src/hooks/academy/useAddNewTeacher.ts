import { useMutation, useQueryClient } from '@tanstack/react-query'

import { postNewTeacher } from '@apis/academy/addNewTeacher'

import { TeacherInfo } from '@types'

export const useAddNewTeacher = () => {
  const queryClient = useQueryClient()
  const { mutate: addNewTeacher, ...rest } = useMutation({
    mutationFn: ({ name, phoneNumber, image }: TeacherInfo) => {
      const formData = new FormData()
      formData.append('name', name)
      formData.append('phoneNumber', phoneNumber)
      if (image) formData.append('image', image)
      return postNewTeacher(formData)
    },

    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['getTeacherList'] })
    },
  })

  return { addNewTeacher, ...rest }
}
