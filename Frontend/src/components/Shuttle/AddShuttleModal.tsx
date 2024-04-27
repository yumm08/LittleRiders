import { MouseEventHandler } from 'react'

import Button from '@components/Shared/Button'
import Modal from '@components/Shared/Modal'
import Spacing from '@components/Shared/Spacing'
import TextField from '@components/Shared/TextField'

import useInput from '@hooks/useInput'

type Props = {
  modalSwitch: MouseEventHandler<HTMLDivElement>
  modalTitle: string
}

export default function AddShuttleModal({ modalSwitch, modalTitle }: Props) {
  const { state: licenseNumber, onChange: handleChangeCarNumber } =
    useInput<string>({ data: '' })
  const { state: carType, onChange: handleChangeCarType } = useInput<string>({
    data: '',
  })
  const { state: carName, onChange: handleChangeCarName } = useInput<string>({
    data: '',
  })
  const submitCarData = () => {
    const data = {
      licenseNumber: licenseNumber,
      type: carType,
      name: carName,
    }
    return data
  }
  return (
    <Modal modalSwitch={modalSwitch} modalTitle={modalTitle}>
      <TextField
        title="차량 번호"
        placeholder="차량 번호를 입력해주세요."
        type="text"
        onChange={handleChangeCarNumber}
      />
      <Spacing style="h-3" />
      <TextField
        title="차종"
        placeholder="차종을 입력해주세요."
        type="text"
        onChange={handleChangeCarType}
      />
      <Spacing style="h-3" />
      <TextField
        title="이름"
        placeholder="차량의 이름을 입력해주세요."
        type="text"
        onChange={handleChangeCarName}
      />
      <Spacing style="h-3" />
      {/* TODO : 이 부분에 사진 첨부 기능을 넣을 것입니다 */}
      <Button full onClick={submitCarData} color="bg-lightgreen">
        <strong className="text-white">차량 등록</strong>
      </Button>
    </Modal>
  )
}
