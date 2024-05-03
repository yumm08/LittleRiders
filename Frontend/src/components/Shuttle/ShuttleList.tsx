import { useState } from 'react'

import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { useFetchShuttleList } from '@hooks/shuttle/useFetchShuttleList'

import AddShuttleModal from './AddShuttleModal'
import ShuttleCard from './ShuttleCard'

interface Props {
  show: number
}

export default function ShuttleList({ show }: Props) {
  const [modalState, setModalState] = useState(false)
  const openAddShuttleModal = () => {
    setModalState(!modalState)
  }
  const { shuttleList, isLoading } = useFetchShuttleList()
  if (isLoading) return <div>isLoading...</div>
  return (
    <>
      <CardListContainer type="차량" openModal={openAddShuttleModal}>
        <CardCarousel show={show}>
          {shuttleList?.map((data) => {
            return <ShuttleCard key={data.shuttleId} data={data} />
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
