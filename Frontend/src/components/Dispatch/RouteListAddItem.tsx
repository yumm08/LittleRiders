import { useState } from 'react'

import { FaPlusCircle } from 'react-icons/fa'

type Props = {
  handleCancelAddClick: () => void
  handleConfirmAddClick: (value: string) => void
}

export default function RouteListAddItem({
  handleConfirmAddClick,
  handleCancelAddClick,
}: Props) {
  const [inputValue, setInputValue] = useState<string>('')

  return (
    <div
      className={`m-2 flex h-16 items-center rounded border-2  p-5 shadow-sm transition-all focus:border-lightgreen 
  `}
    >
      <FaPlusCircle className="me-5" size={40} />

      <input
        className="m-2 h-10 w-full p-2"
        placeholder="노선의 이름을 입력해주세요..."
        onChange={(e) => {
          setInputValue(e.target.value)
        }}
        autoFocus
      ></input>
      <div className="flex w-full justify-end">
        <button
          className="m-1 rounded-md border-2 border-darkgreen  px-2 py-1 transition-all hover:bg-lightgreen active:bg-darkgreen"
          onClick={() => handleConfirmAddClick(inputValue)}
        >
          추가
        </button>
        <button
          className="brder-2 m-1 rounded-md border-yellow px-2 py-1 transition-all hover:bg-yellow active:border-red active:bg-red"
          onClick={handleCancelAddClick}
        >
          삭제
        </button>
      </div>
    </div>
  )
}
