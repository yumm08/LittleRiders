import { modalStore } from '@stores/modalStore'

import { useMutation, useQueryClient } from '@tanstack/react-query'

import { postNewDevice } from '@apis/academy/addNewDevice'

import { Device } from '@types'

export const useAddNewDevice = () => {
  const changeModalState = modalStore((state) => state.changeModalState)
  const queryClient = useQueryClient()
  const { mutate: addNewDevice, ...rest } = useMutation({
    mutationFn: ({ serialNumber }: Device) => {
      return postNewDevice({ serialNumber })
    },

    onSuccess: async () => {
      alert('단말기 추가 완료')
      changeModalState('addTerminalModal')
      await queryClient.invalidateQueries({ queryKey: ['addNewDevice'] })
    },
  })

  return { addNewDevice, ...rest }
}
