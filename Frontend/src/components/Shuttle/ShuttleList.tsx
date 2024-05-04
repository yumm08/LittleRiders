import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { modalStore } from '@stores/modalStore'

import { useFetchShuttleList } from '@hooks/shuttle/useFetchShuttleList'

import AddShuttleModal from './AddShuttleModal'
import ShuttleCard from './ShuttleCard'

interface Props {
  show: number
}

export default function ShuttleList({ show }: Props) {
  const addShuttleModalState = modalStore(
    (state) => state.modalController.addShuttleModal,
  )
  const changeModalState = modalStore((state) => state.changeModalState)
  const openAddShuttleModal = () => {
    changeModalState('addShuttleModal')
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
      {addShuttleModalState && (
        <AddShuttleModal
          modalTitle="차량 등록"
          modalSwitch={openAddShuttleModal}
        />
      )}
    </>
  )
}
