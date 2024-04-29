import { useEffect } from 'react'

import { useAuthStore } from '@stores/authStore'

import { Outlet, useNavigate } from 'react-router-dom'
import Swal from 'sweetalert2'

export default function ProtectedRoute() {
  const { isSignedIn } = useAuthStore()
  const navigate = useNavigate()

  useEffect(() => {
    if (!isSignedIn) {
      Swal.fire({
        text: '로그인이 필요한 페이지입니다!',
        icon: 'error',
        confirmButtonText: '확인',
      }).then(() => {
        navigate('/signin')
      })
    }
  }, [isSignedIn, navigate])

  return isSignedIn && <Outlet />
}
