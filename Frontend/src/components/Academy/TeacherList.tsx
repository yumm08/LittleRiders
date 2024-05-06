import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { modalStore } from '@stores/modalStore'

import { useFetchTeacherList } from '@hooks/academy/useFetchTeacherList'

import AddTeacherModal from './AddTeacherModal'
import TeacherCard from './TeacherCard'

type Props = {
  show: number
}

export default function TeacherList({ show }: Props) {
  const addTeacherModalState = modalStore(
    (state) => state.modalController.addTeacherModal,
  )
  const changeModalState = modalStore((state) => state.changeModalState)
  const openAddTeacherModal = () => {
    changeModalState('addTeacherModal')
  }
  const { teacherList, isLoading } = useFetchTeacherList()
  if (isLoading) return <div>Loading...</div>
  return (
    <>
      <CardListContainer type="직원" openModal={openAddTeacherModal}>
        <CardCarousel show={show}>
          {teacherList?.map((data) => {
            return (
              <TeacherCard
                name={data.name}
                phoneNumber={data.phoneNumber}
                image={data.image}
              />
            )
          })}
        </CardCarousel>
      </CardListContainer>
      {addTeacherModalState && (
        <AddTeacherModal
          modalTitle="직원 등록"
          modalSwitch={openAddTeacherModal}
        />
      )}
    </>
  )
}
