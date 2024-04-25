import { MouseEventHandler } from 'react'

import useModal from '@hooks/useModal'

import BackDrop from './BackDrop'
import ModalPortal from './ModalPortal'

interface Props {
  openModal: MouseEventHandler<HTMLDivElement>
  children: React.ReactNode
  modalTitle: string
}
export default function Modal({ openModal, children, modalTitle }: Props) {
  useModal()
  return (
    <ModalPortal>
      {/* 뒷 배경  */}
      <BackDrop onClick={openModal}>
        <div className=" relative left-1/2 top-1/2 z-20 w-[760px] -translate-x-1/2 -translate-y-1/2 flex-col rounded-md bg-white p-4 text-center">
          <strong className="mx-auto inline-block">{modalTitle}</strong>
          {children}
        </div>
      </BackDrop>
    </ModalPortal>
  )
}
