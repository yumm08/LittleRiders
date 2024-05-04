import { useEffect, useState } from 'react'

import { Station } from '@types'
import { FaPencilAlt, FaTrashAlt } from 'react-icons/fa'

interface Props {
  station: Station
  selectedStation: number
  handleClick: (id: number) => void
}

export default function StationListItem({
  station,
  selectedStation,
  handleClick,
}: Props) {
  const [isClicked, setIsClicked] = useState<boolean>(false)

  useEffect(() => {
    setIsClicked(selectedStation === station.id)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedStation])
  return (
    <>
      <div
        className={`flex items-center p-2 px-4 transition-all ease-in-out ${isClicked ? ' shadow-inner-lg bg-yellow' : 'bg-white hover:bg-yellow hover:bg-opacity-40 '}`}
        onClick={() => handleClick(station.id)}
      >
        <img src="/src/assets/image/bus-stop-icon.svg" width={40} />
        <div className="flex w-full items-center justify-between">
          <p className="mx-2 items-center text-lg font-bold">{station.name}</p>
          <div className="flex justify-evenly gap-3">
            <FaPencilAlt />
            <FaTrashAlt />
          </div>
        </div>
      </div>
      <hr />
    </>
  )
}
