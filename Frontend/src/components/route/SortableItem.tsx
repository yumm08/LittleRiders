import { UniqueIdentifier } from '@dnd-kit/core'
import { useSortable } from '@dnd-kit/sortable'
import { CSS } from '@dnd-kit/utilities'

interface Props {
  id: UniqueIdentifier
}

export default function SortableItem({ id }: Props) {
  const { attributes, listeners, setNodeRef, transform, transition } =
    useSortable({ id })

  return (
    <div
      style={{ transform: CSS.Transform.toString(transform), transition }}
      ref={setNodeRef}
      {...attributes}
      {...listeners}
      className="border w-auto h-auto m-3 p-3 rounded-md bg-white"
    >
      <div className="text-center">{id}</div>
    </div>
  )
}
