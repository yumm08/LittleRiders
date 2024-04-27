import { MouseEventHandler } from 'react'

import useModal from '@hooks/useModal'

import BackDrop from './BackDrop'
import ModalPortal from './ModalPortal'

interface Props {
  openModal: MouseEventHandler<HTMLDivElement>
  children: React.ReactNode
  modalTitle: string
}
/**
 *
 * @param openModal 모달의 on/off 상태를 관리할 함수를 넘긴다
 * @param modalTitle 모달의 제목
 * @returns
 */
export default function Modal({ openModal, children, modalTitle }: Props) {
  useModal()

  return (
    <ModalPortal>
      {/* 뒷 배경  */}
      <BackDrop onClick={openModal}>
        <div
          onClick={(e) => {
            e.stopPropagation()
          }}
          className=" relative left-1/2 top-1/2 z-20 w-[760px] -translate-x-1/2 -translate-y-1/2 flex-col rounded-md bg-white p-4 text-center"
        >
          <strong className="mx-auto inline-block">{modalTitle}</strong>
          {children}
        </div>
      </BackDrop>
    </ModalPortal>
  )
}
