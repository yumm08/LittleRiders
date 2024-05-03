import { useState } from 'react'

import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import AddDeviceModal from './AddDeviceModal'

type Props = {
  show: number
}

export default function DeviceList({ show }: Props) {
  const [modalState, setModalState] = useState(false)
  const openAddDeviceModal = () => {
    setModalState(!modalState)
  }

  return (
    <>
      <CardListContainer type="단말기" openModal={openAddDeviceModal}>
        <CardCarousel show={show}>
          <div>단말기1</div>
          <div>단말기1</div>
          <div>단말기1</div>
          <div>단말기1</div>
          <div>단말기1</div>
        </CardCarousel>
      </CardListContainer>
      {modalState && (
        <AddDeviceModal
          modalTitle="단말기 등록"
          modalSwitch={openAddDeviceModal}
        />
      )}
    </>
  )
}
