import { useEffect, useState } from 'react'

import { FaPencilAlt, FaRoute, FaTrashAlt } from 'react-icons/fa'

interface Props {
  id: number
  name: string
  selectedRouteId: number
  onRouteClick: (id: number) => void
}

export default function RouteListItem({
  id,
  name,
  selectedRouteId,
  onRouteClick,
}: Props) {
  const [isClicked, setIsClicked] = useState<boolean>(false)

  // TODO Modify and Delete Feature Need to ADD
  useEffect(() => {
    setIsClicked(id === selectedRouteId)
  }, [selectedRouteId, id])

  return (
    <div
      className={`m-2 flex h-16 items-center rounded-lg border-2  p-5 shadow-sm transition-all hover:border-lightgreen
      ${isClicked ? `  border-lightgreen bg-lightgreen text-white` : ` bg-slate-200`}`}
      onClick={() => {
        onRouteClick(id)
      }}
    >
      <FaRoute className="me-5" size={50} />
      <p className="w-full text-xl">{name}</p>
      <div className="flex w-full justify-end gap-3">
        <FaPencilAlt />
        <FaTrashAlt />
      </div>
    </div>
  )
}
