import { MouseEventHandler } from 'react'

import Button from '@components/Shared/Button'
import ImageUploadButton from '@components/Shared/ImageUploadButton'
import Modal from '@components/Shared/Modal'
import Spacing from '@components/Shared/Spacing'
import TextField from '@components/Shared/TextField'

import useFileUpload from '@hooks/useFileUpload'
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
  const { uploadImgUrl, onChangeImageUpload } = useFileUpload()
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
      <div className="flex flex-col">
        <strong className="flex">사진 첨부</strong>
      </div>
      <Spacing style="h-3" />
      <ImageUploadButton
        uploadImgUrl={uploadImgUrl}
        onChangeImageUpload={onChangeImageUpload}
      />
      <Spacing style="h-3" />
      <Button full onClick={submitCarData} color="bg-lightgreen">
        <strong className="text-white">차량 등록</strong>
      </Button>
    </Modal>
  )
}
