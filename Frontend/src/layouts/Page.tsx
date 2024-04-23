import React from 'react'

type Props = {
  children: React.ReactNode
}

export default function Page({ children }: Props) {
  return (
    <div className="mx-[200px] w-100vw h-screen mt-[120px]">{children}</div>
  )
}
