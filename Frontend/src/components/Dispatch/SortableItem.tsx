import { UniqueIdentifier } from '@dnd-kit/core'
import { useSortable } from '@dnd-kit/sortable'
import { CSS } from '@dnd-kit/utilities'

interface Props {
  id: UniqueIdentifier
  name: string
}

export default function SortableItem({ id, name }: Props) {
  const { attributes, listeners, setNodeRef, transform, transition } =
    useSortable({ id })

  return (
    <div
      style={{ transform: CSS.Transform.toString(transform), transition }}
      ref={setNodeRef}
      {...attributes}
      {...listeners}
      className="m-3 h-auto w-auto rounded-md border bg-white p-3 shadow-md"
    >
      <div className="text-center">
        <p>{name}</p>
      </div>
    </div>
  )
}
