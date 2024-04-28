import { useMutation } from '@tanstack/react-query'

import { postSignUp, postValidate } from '@apis/auth'

import { SignUpInfo } from '@types'
import { HttpStatusCode } from 'axios'
import { useNavigate } from 'react-router-dom'
import Swal from 'sweetalert2'

export const useSignUp = () => {
  const navigate = useNavigate()

  const { mutate: signUp, ...rest } = useMutation({
    mutationFn: (signUpInfo: SignUpInfo) => postSignUp(signUpInfo),
    onSuccess: async (response) => {
      const { status } = response

      if (status === HttpStatusCode.Ok) {
        const result = await Swal.fire({
          text: '회원가입에 성공했습니다.',
          icon: 'success',
        })

        if (result.isConfirmed) {
          navigate('/signin')
        }
      }
    },
    // TODO: 이미 가입된 회원 등 에러처리 필요
    onError: () => {},
  })

  return { signUp, ...rest }
}

export const useValidate = () => {
  const { mutate: validate, ...rest } = useMutation({
    mutationFn: ({ email, code }: { email: string; code: string }) =>
      postValidate(email, code),
    onSuccess: () => {
      alert('인증에 성공하였습니다')
    },
    onError: () => {
      alert('인증에 실패하였습니다')
    },
  })

  return { validate, ...rest }
}
