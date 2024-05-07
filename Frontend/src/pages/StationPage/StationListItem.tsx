import { useEffect, useState } from 'react'

import { useDeleteStation } from '@hooks/dispatch'

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
  const { removeStation } = useDeleteStation()
  const handleDeleteClick = () => {
    removeStation(station.id)
  }

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
          <p className="text-md mx-2 w-2/3 items-center truncate">
            {station.name}
          </p>
          <div className="flex justify-evenly gap-3">
            <FaPencilAlt />
            <FaTrashAlt onClick={handleDeleteClick} />
          </div>
        </div>
      </div>
      <hr />
    </>
  )
}
