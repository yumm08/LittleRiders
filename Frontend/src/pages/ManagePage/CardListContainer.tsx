import { MouseEventHandler } from 'react'

import Button from '@components/Shared/Button'
import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'

interface Props {
  type: string
  onClick: MouseEventHandler<HTMLButtonElement>
  children: React.ReactNode
}

export default function CardListContainer({ type, onClick, children }: Props) {
  return (
    <div className="flex-col p-2 ">
      <strong className="text-2xl">{`${type} 관리`}</strong>
      <Divider />
      <Spacing className="h-[15px]" />
      {children}
      <Spacing className="h-[50px]" />
      <Button color="bg-lightgreen" full onClick={onClick}>
        <span className="text-xm text-white">{`${type} 추가`}</span>
      </Button>
    </div>
  )
}
