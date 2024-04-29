import { useAuthStore } from '@stores/authStore'

import { useMutation } from '@tanstack/react-query'

import { postSignIn } from '@apis/auth'

import { SignInInfo } from '@types'
import { HttpStatusCode } from 'axios'
import { useNavigate } from 'react-router-dom'
import Swal from 'sweetalert2'

export const useSignIn = () => {
  const navigate = useNavigate()

  const { setSignedIn } = useAuthStore()

  const { mutate: signIn, ...rest } = useMutation({
    mutationFn: (signInInfo: SignInInfo) => postSignIn(signInInfo),
    onSuccess: async (response) => {
      const { status } = response

      if (status === HttpStatusCode.Ok) {
        setSignedIn()

        const result = await Swal.fire({
          text: '로그인에 성공했습니다.',
          icon: 'success',
        })

        if (result.isConfirmed) {
          navigate('/')
        }
      }
    },
  })

  return { signIn, ...rest }
}
