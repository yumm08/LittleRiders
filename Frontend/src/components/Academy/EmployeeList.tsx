import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import EmployeeCard from './EmployeeCard'

type Props = {
  show: number
}

export default function EmployeeList({ show }: Props) {
  return (
    <CardListContainer type="직원" onClick={() => {}}>
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
