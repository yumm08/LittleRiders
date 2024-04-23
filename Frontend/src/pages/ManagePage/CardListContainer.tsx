import { MouseEventHandler } from 'react'

import Button from '@components/Shared/Button'
import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'

interface Props {
  title: string
  onClick: MouseEventHandler<HTMLButtonElement>
  children: React.ReactNode
}

export default function CardListContainer({ title, onClick, children }: Props) {
  return (
    <div className="flex-col p-2">
      <strong className="text-2xl">{title}</strong>
      <Divider />
      {children}
      <Spacing className="h-5" />
      <Button
        color="bg-lightgreen"
        innerText="차량 추가"
        full
        onClick={onClick}
      />
    </div>
  )
}
