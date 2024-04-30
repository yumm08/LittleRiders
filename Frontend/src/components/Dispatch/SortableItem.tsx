import { UniqueIdentifier } from '@dnd-kit/core'
import { useSortable } from '@dnd-kit/sortable'
import { CSS } from '@dnd-kit/utilities'
import { MdDragHandle } from 'react-icons/md'

interface Props {
  id: UniqueIdentifier
  name: string
  type?: string
  index: number
  onClick?: () => void
}

export default function SortableItem({ id, name, type, index }: Props) {
  const { attributes, listeners, setNodeRef, transform, transition } =
    useSortable({ id })

  const sortIcon = (type: string | undefined) => {
    if (!type) {
      return (
        <div className="flex w-8 justify-center">
          <MdDragHandle size={30} />
        </div>
      )
    }
    if (type === 'selectedStationList')
      return <p className="w-8 text-center text-xl font-bold">{index}</p>
    if (type === 'stationList')
      return <img src="/src/assets/image/bus-stop-icon.svg" className="w-8" />
    if (type.includes('childList')) return <></>
  }
  return (
    <div
      style={{ transform: CSS.Transform.toString(transform), transition }}
      ref={setNodeRef}
      {...attributes}
      {...listeners}
      className="m-3 h-auto w-[273px] rounded-md border bg-white p-3 shadow-md"
    >
      <div className="flex items-center justify-start">
        {sortIcon(type)}
        <p className="ms-2 text-center">{name}</p>
      </div>
    </div>
  )
}
