import { useState } from 'react'

import ChildQR from '@components/Child/Child/ChildQR'
import BackDrop from '@components/Shared/BackDrop'
import GenderIcon from '@components/Shared/GenderIcon'
import ModalPortal from '@components/Shared/ModalPortal'
import Spacing from '@components/Shared/Spacing'
import Title from '@components/Shared/Title'

import { useFetchChild, usePutChild } from '@hooks/child'
import useModal from '@hooks/useModal'

import { formatBirthToAge } from '@utils/formatUtils'

import { ChildDetailInfo, ChildStatus } from '@types'

interface Props {
  onShow: () => void
  academyChildId: number
  childStatus: ChildStatus
}

export default function ChildDetailModal({
  onShow,
  academyChildId,
  childStatus,
}: Props) {
  const [selectedStatus, setSelectedStatus] = useState<ChildStatus>(childStatus)

  const { childDetailInfo, isLoading } = useFetchChild(academyChildId)
  const { updateChildStatus } = usePutChild(academyChildId, selectedStatus)

  useModal()

  if (isLoading) {
    return <div>loading</div>
  }

  const {
    imagePath,
    name,
    birthDate,
    gender,
    familyName,
    familyPhoneNumber,
    address,
    cardType,
    cardNumber,
  }: ChildDetailInfo = childDetailInfo

  const handleStatusChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedValue = e.target.value

    if (
      selectedValue === 'ATTENDING' ||
      selectedValue === 'LEAVE' ||
      selectedValue === 'GRADUATE'
    ) {
      setSelectedStatus(selectedValue as ChildStatus)
    }
  }

  const handleUpdateClick = () => {
    updateChildStatus()
  }

  return (
    <ModalPortal>
      <BackDrop onClick={onShow}>
        <div className="flex h-full w-full items-center justify-center">
          <div
            className="w-1/3 rounded-xl bg-white p-12"
            onClick={(e) => e.stopPropagation()}
          >
            <div className="flex gap-8">
              <img src={imagePath} alt="" className="aspect-square w-36" />
              <div className="flex w-full flex-col justify-between">
                <div className="flex items-center">
                  <span className="text-3xl font-bold">{name}</span>
                  <GenderIcon gender={gender} size={50} />
                  <span className="text-lg">
                    ({formatBirthToAge(birthDate)})
                  </span>
                </div>

                <p className="text-2xl">{birthDate}</p>

                <div className="flex w-full justify-between gap-2">
                  <select
                    className="w-full border"
                    value={selectedStatus}
                    onChange={handleStatusChange}
                  >
                    <option value="ATTENDING">재학</option>
                    <option value="GRADUATE">졸업</option>
                    <option value="LEAVE">전학</option>
                  </select>

                  <button
                    className="w-1/3 rounded-md border-2"
                    onClick={handleUpdateClick}
                  >
                    수정
                  </button>
                </div>
              </div>
            </div>

            <Spacing style="my-10" />

            <div className="flex flex-col items-center">
              <Title text="부모 정보" />

              <div className="mt-2 flex flex-col items-start gap-4 border-y-2 py-4 text-lg">
                <p>
                  <span className="font-bold">이름 : </span>
                  {familyName}
                </p>
                <p>
                  <span className="font-bold">연락처 : </span>
                  {familyPhoneNumber}
                </p>
                <p>
                  <span className="font-bold">주소 : </span>
                  <span className="">{address}</span>
                </p>
              </div>
            </div>

            <Spacing style="my-2" />

            <div className="flex w-full justify-center">
              <div className="flex aspect-square w-40 flex-col items-center rounded-md border p-2">
                <div className="flex w-full items-center justify-center border-b">
                  <span className="font-bold">{cardType}</span>
                </div>
                {cardType === 'BARCODE' && <ChildQR value={cardNumber} />}
                {cardType === 'BEACON' && <div>{cardNumber}</div>}
              </div>
            </div>
          </div>
        </div>
      </BackDrop>
    </ModalPortal>
  )
}
