import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { modalStore } from '@stores/modalStore'

import { useFetchDriverList } from '@hooks/academy/useFetchDriverList'

import AddDriverModal from './AddDriverModal'
import DriverCard from './DriverCard'

type Props = {
  show: number
}

export default function DriverList({ show }: Props) {
  const addDriverModalState = modalStore(
    (state) => state.modalController.addDriverModal,
  )
  const changeModalState = modalStore((state) => state.changeModalState)
  const openAddDriverModal = () => {
    changeModalState('addDriverModal')
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
                key={data.name}
                name={data.name}
                phoneNumber={data.phoneNumber}
                image={data.image}
              />
            )
          })}
        </CardCarousel>
      </CardListContainer>
      {addDriverModalState && (
        <AddDriverModal
          modalTitle="기사 등록"
          modalSwitch={openAddDriverModal}
        />
      )}
    </>
  )
}
