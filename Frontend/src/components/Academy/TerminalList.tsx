import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { modalStore } from '@stores/modalStore'

import AddTerminalModal from './AddTerminalModal'

type Props = {
  show: number
}

export default function TerminalList({ show }: Props) {
  const addTerminalModalState = modalStore(
    (state) => state.modalController.addTerminalModal,
  )
  const changeModalState = modalStore((state) => state.changeModalState)
  const openAddTerminalModal = () => {
    changeModalState('addTerminalModal')
  }

  return (
    <>
      <CardListContainer type="단말기" openModal={openAddTerminalModal}>
        <CardCarousel show={show}>
          <div>단말기1</div>
          {/* <div>단말기2</div>
          <div>단말기3</div>
          <div>단말기4</div> */}
          {/* <div>단말기1</div> */}
        </CardCarousel>
      </CardListContainer>
      {addTerminalModalState && (
        <AddTerminalModal
          modalTitle="단말기 등록"
          modalSwitch={openAddTerminalModal}
        />
      )}
    </>
  )
}
