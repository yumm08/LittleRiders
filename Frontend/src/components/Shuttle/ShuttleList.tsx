import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import ShuttleCard from './ShuttleCard'

interface Props {
  show: number
}

export default function ShuttleList({ show }: Props) {
  return (
    <CardListContainer type="차량" onClick={() => {}}>
      <CardCarousel show={show}>
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
      </CardCarousel>
    </CardListContainer>
  )
}
