import { useEffect } from 'react'

export default function useModal() {
  useEffect(() => {
    document.documentElement.style.scrollbarGutter = 'stable' // 추가
    document.body.style.overflow = 'hidden'
    return () => {
      document.body.style.overflow = 'unset'
    }
  }, [])
}
