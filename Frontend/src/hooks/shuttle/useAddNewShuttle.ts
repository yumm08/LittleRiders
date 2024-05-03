import { modalStore } from '@stores/modalStore'

import { useMutation, useQueryClient } from '@tanstack/react-query'

import { postNewShuttle } from '@apis/shuttle'

interface Shuttle {
  licenseNumber: string
  type: string
  name: string
  image: File | null
}
export const useAddNewShuttle = () => {
  const queryClient = useQueryClient()
  const modalSwitch = modalStore((state) => state.modalSwitch)

  const { mutate: addNewShuttle, ...rest } = useMutation({
    mutationFn: async ({ licenseNumber, type, name, image }: Shuttle) => {
      const formData = new FormData()
      formData.append('licenseNumber', licenseNumber)
      formData.append('type', type)
      formData.append('name', name)
      if (image) formData.append('image', image)
      return await postNewShuttle(formData)
    },

    onSuccess: async () => {
      alert('가입 성공!')
      modalSwitch()
      await queryClient.invalidateQueries({ queryKey: ['getShuttleList'] })
    },
    onError: async () => {
      alert('실패')
    },
  })

  return { addNewShuttle, ...rest }
}
