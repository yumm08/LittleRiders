import { useState } from 'react'

import SearchAddressModal from '@components/Auth/SignUp/SearchAddressModal'
import Button from '@components/Shared/Button'
import GenderIcon from '@components/Shared/GenderIcon'

import { ChildRegistInfo } from '@types'
import { useFormContext } from 'react-hook-form'

export default function ChildRegistFormBody() {
  const { register } = useFormContext<ChildRegistInfo>()
  const [openAddressSearchModal, setOpenAddressSearchModal] = useState(false)

  const searchAddressClickHandler = async (e: React.MouseEvent) => {
    e.preventDefault()

    setOpenAddressSearchModal(true)
  }

  const modalToggleHandler = () => {
    setOpenAddressSearchModal((prev) => !prev)
  }

  return (
    <>
      <div className="flex">
        <input
          {...register('image', {
            setValueAs: (fileList) => fileList[0],
          })}
          id="picture"
          type="file"
          className=""
          accept="image/*"
        />
      </div>

      <input
        {...register('name')}
        type="text"
        placeholder="원생 이름을 입력해주세요"
        className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
        required
      />

      <input
        {...register('birthDate')}
        type="date"
        placeholder="생일을 입력해주세요"
        className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
      />

      <div className="flex items-center">
        <span>성별 : </span>
        <input {...register('gender')} type="radio" id="male" value="MALE" />
        <label htmlFor="male">
          <GenderIcon gender="MALE" />
        </label>

        <input
          {...register('gender')}
          type="radio"
          id="female"
          value="FEMALE"
        />
        <label htmlFor="female">
          <GenderIcon gender="FEMALE" />
        </label>
      </div>

      <input
        {...register('familyName')}
        type="text"
        placeholder="보호자 성함을 입력해주세요"
        className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
        required
      />

      <input
        {...register('phoneNumber')}
        type="tel"
        placeholder="보호자 휴대폰 번호를 입력해주세요"
        className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
        required
      />

      <div className="flex gap-2">
        <input
          {...register('address')}
          placeholder="주소를 입력해주세요"
          type="text"
          className="bg-lightblue text-md pointer-events-none w-full rounded-md border border-lightgray p-3"
          required
          readOnly
        />
        <Button
          color="bg-lightgreen"
          onClick={(e) => searchAddressClickHandler(e)}
        >
          <p className="w-20 font-bold text-white">주소 검색</p>
        </Button>
        {openAddressSearchModal && (
          <SearchAddressModal onModalToggle={modalToggleHandler} />
        )}
      </div>

      <textarea
        placeholder="특이사항 혹은 메모를 입력하세요"
        className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
        rows={10}
      />
    </>
  )
}
