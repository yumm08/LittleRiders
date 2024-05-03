import { useMutation, useQueryClient } from '@tanstack/react-query'

import { postNewDevice } from '@apis/academy/addNewDevice'

import { Device } from '@types'

export const useAddNewDevice = () => {
  const queryClient = useQueryClient()
  const { mutate: addNewDevice, ...rest } = useMutation({
    mutationFn: ({ serialNumber }: Device) => {
      return postNewDevice({ serialNumber })
    },

    onSuccess: async () => {
      alert('기사 추가 완료')
      await queryClient.invalidateQueries({ queryKey: ['addNewDeivce'] })
    },
  })

  return { addNewDevice, ...rest }
}
