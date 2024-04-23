import CardCarousel from '@pages/ManagePage/CardCarousel'
import CardListContainer from '@pages/ManagePage/CardListContainer'

interface Props {
  show: number
}

export default function ShuttleList({ show }: Props) {
  return (
    <CardListContainer title="직원관리" onClick={() => {}}>
      <CardCarousel show={show} />
    </CardListContainer>
  )
}
