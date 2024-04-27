import { useState } from 'react'

import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import AddShuttleModal from './AddShuttleModal'
import ShuttleCard from './ShuttleCard'

interface Props {
  show: number
}
const DUMMY = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

export default function ShuttleList({ show }: Props) {
  const [modalState, setModalState] = useState(false)
  const openAddShuttleModal = () => {
    setModalState(!modalState)
  }
  return (
    <>
      <CardListContainer type="차량" openModal={openAddShuttleModal}>
        <CardCarousel show={show}>
          {DUMMY.map((id) => {
            return <ShuttleCard id={id} />
          })}
        </CardCarousel>
      </CardListContainer>
      {modalState && (
        <AddShuttleModal
          modalTitle="차량 등록"
          modalSwitch={openAddShuttleModal}
        />
      )}
    </>
  )
}
