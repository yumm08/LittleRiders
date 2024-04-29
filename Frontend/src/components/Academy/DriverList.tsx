import { useState } from 'react'

import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import AddEmployeeModal from './AddDriverModal'
import EmployeeCard from './EmployeeCard'

type Props = {
  show: number
}

export default function DriverList({ show }: Props) {
  const [modalState, setModalState] = useState(false)
  const openAddDriverModal = () => {
    setModalState(!modalState)
  }
  return (
    <>
      <CardListContainer type="기사" openModal={openAddDriverModal}>
        <CardCarousel show={show}>
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
          <EmployeeCard />
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
