import SortableItem from '@components/Dispatch/SortableItem'

import { useDroppable } from '@dnd-kit/core'
import { SortableContext, verticalListSortingStrategy } from '@dnd-kit/sortable'
import { Station } from '@types'

interface Props {
  id: string
  items: Station[]
  subject: string
}

/**
 *
 * @param id sortableContainer를 특정할 id 
 * @param items 표시할 데이터 items
 * @param subject 박스 위에 표시할 제목
 */
export default function SortableContainer({ id, items, subject }: Props) {
  const { setNodeRef } = useDroppable({ id })
  return (
    <SortableContext
      id={id}
      items={items}
      strategy={verticalListSortingStrategy}
    >
      <div className="flex-row p-1 m-5">
        <p className="text-xl font-bold m-1">
          {subject ? subject : '임시 제목'}
        </p>
        <div
          ref={setNodeRef}
          className="border w-80  rounded-md p-1 h-5/6 overflow-y-scroll bg-lightgreen shadow-md"
        >
          {items.map((item) => (
            <SortableItem key={item.id} id={item.id} />
          ))}
        </div>
      </div>
    </SortableContext>
  )
}
