import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import ShuttleCard from './ShuttleCard'

interface Props {
  show: number
}
const DUMMY = [1,2,3,4,5,6,7,8,9,10]
export default function ShuttleList({ show }: Props) {
  return (
    <CardListContainer type="차량" onClick={() => {}}>
      <CardCarousel show={show}>
        {DUMMY.map((id)=>{
          return <ShuttleCard id={id} />
        })}
      </CardCarousel>
    </CardListContainer>
  )
}
