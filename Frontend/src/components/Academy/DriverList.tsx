import { useState } from 'react'

import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { useFetchDriverList } from '@hooks/academy/useFetchDriverList'

import AddEmployeeModal from './AddDriverModal'
import DriverCard from './DriverCard'

type Props = {
  show: number
}

export default function DriverList({ show }: Props) {
  const [modalState, setModalState] = useState(false)
  const openAddDriverModal = () => {
    setModalState(!modalState)
  }
  const { driverList, isLoading } = useFetchDriverList()

  if (isLoading) return <div>Loading...</div>

  return (
    <>
      <CardListContainer type="기사" openModal={openAddDriverModal}>
        <CardCarousel show={show}>
          {driverList?.map((data) => {
            return (
              <DriverCard
                name={data.name}
                phoneNumber={data.phoneNumber}
                image={data.image}
              />
            )
          })}
        </CardCarousel>
      </CardListContainer>
      {modalState && (
        <AddEmployeeModal
          modalTitle="기사 등록"
          modalSwitch={openAddDriverModal}
        />
      )}
    </>
  )
}
