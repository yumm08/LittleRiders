import { MouseEventHandler } from 'react'

import Button from '@components/Shared/Button'
import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'

interface Props {
  type: string
  openModal: MouseEventHandler<HTMLButtonElement>
  children: React.ReactNode
}

export default function CardListContainer({ type, openModal, children }: Props) {
  return (
    <div className="flex-col p-2 ">
      <strong className="text-2xl">{`${type} 관리`}</strong>
      <Divider />
      <Spacing style="h-[15px]" />
      {children}
      <Spacing style="h-[50px]" />
      
      <Button color="bg-lightgreen" full onClick={openModal}>
        <span className="text-xm text-white">{`${type} 추가`}</span>
      </Button>
    </div>
  )
}
