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
    <div className="flex-col p-2 ">
      <strong className="text-2xl">{title}</strong>
      <Divider />
      <Spacing className="h-[15px]" />
      {children}
      <Spacing className="h-[50px]" />
      <Button color="bg-lightgreen" full onClick={onClick}>
        <span className="text-xl text-white">차량 추가</span>
      </Button>
    </div>
  )
}
