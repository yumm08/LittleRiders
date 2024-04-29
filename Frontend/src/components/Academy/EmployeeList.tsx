import { useState } from 'react'

import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import EmployeeCard from './EmployeeCard'

type Props = {
  show: number
}

export default function EmployeeList({ show }: Props) {
  const [modalState, setModalState] = useState(false)
  const openAddEmployeeModal = () => {
    setModalState(!modalState)
  }
  return (
    <CardListContainer type="직원" openModal={openAddEmployeeModal}>
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
  )
}
