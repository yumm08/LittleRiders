import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import EmployeeCard from './EmployeeCard'

type Props = {
  show: number
}

export default function DriverList({ show }: Props) {
  return (
    <CardListContainer type="기사" onClick={() => {}}>
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
