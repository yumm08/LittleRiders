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
  console.log(id, name)

  useEffect(() => {
    setIsClicked(id === selectedRouteId)
  }, [selectedRouteId, id])

  return (
    <div
      className={`m-2 flex h-16 items-center rounded border-2  p-5 shadow-sm transition-all hover:border-lightgreen
      ${isClicked ? `  bg-lightgreen` : ` bg-slate-200`}`}
      onClick={() => {
        onRouteClick(id)
      }}
    >
      <FaRoute className="me-5" size={24} />
      <p className="text-xl">{name}</p>
      <FaPencilAlt />
      <FaTrashAlt />
    </div>
  )
}
