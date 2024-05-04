import { useState } from 'react'

import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import AddTerminalModal from './AddTerminalModal'

type Props = {
  show: number
}

export default function TerminalList({ show }: Props) {
  const [modalState, setModalState] = useState(false)
  const openAddDeviceModal = () => {
    setModalState(!modalState)
  }

  return (
    <>
      <CardListContainer type="단말기" openModal={openAddDeviceModal}>
        <CardCarousel show={show}>
          <div>단말기1</div>
          {/* <div>단말기2</div>
          <div>단말기3</div>
          <div>단말기4</div> */}
          {/* <div>단말기1</div> */}
        </CardCarousel>
      </CardListContainer>
      {modalState && (
        <AddTerminalModal
          modalTitle="단말기 등록"
          modalSwitch={openAddDeviceModal}
        />
      )}
    </>
  )
}
