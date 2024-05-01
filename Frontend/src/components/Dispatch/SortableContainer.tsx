import SortableItem from '@components/Dispatch/SortableItem'

import { UniqueIdentifier, useDroppable } from '@dnd-kit/core'
import { SortableContext, verticalListSortingStrategy } from '@dnd-kit/sortable'
import { ChildInfo, Station } from '@types'

interface Props {
  id: string
  items: Station[] | ChildInfo[]
  subject: string
  isLoading: boolean
  isPending?: boolean
  selectedStation?: number
  onClick?: (id: number) => void
  onHover?: () => void
}

/**
 *
 * @param id sortableContainer를 특정할 id
 * @param items 표시할 데이터 items
 * @param subject 박스 위에 표시할 제목
 */
export default function SortableContainer({
  id,
  items,
  subject,
  isLoading,
  isPending = true,
  onClick,
  selectedStation,
}: Props) {
  const { setNodeRef } = useDroppable({ id })
  if (isLoading || isPending || !items) {
    return (
      <div className="m-5 h-5/6 flex-row p-1">
        <p className="m-1 text-xl font-bold">
          {subject ? subject : '임시 제목'}
        </p>
        <div
          ref={setNodeRef}
          className="flex h-5/6 w-80 items-center justify-center overflow-y-scroll rounded-md border bg-white p-1 shadow-md"
        >
          <p>선택된 노선이 없습니다.</p>
        </div>
      </div>
    )
  }

  let data: (UniqueIdentifier | { id: UniqueIdentifier })[] = []
  if (items.length > 0) {
    data = items.map((item) => {
      if ('academyChildId' in item) return item['academyChildId']
      else return item['id']
    })
  }

  return (
    <SortableContext
      id={id}
      items={data}
      strategy={verticalListSortingStrategy}
    >
      <div className="m-5 flex-row p-1">
        <p className="m-1 text-xl font-bold">
          {subject ? subject : '임시 제목'}
        </p>
        <div
          ref={setNodeRef}
          className="h-5/6 w-80 flex-row items-center overflow-y-scroll rounded-md border bg-white p-1 shadow-md"
        >
          {items.map((item, index) =>
            'id' in item ? (
              <SortableItem
                key={item.id}
                id={item.id}
                selectedStation={selectedStation}
                name={item.name}
                type={id}
                index={index}
                onClick={onClick}
              />
            ) : (
              <SortableItem
                key={item.academyChildId}
                id={item.academyChildId}
                name={item.name}
                type={id}
                index={index}
              />
            ),
          )}
        </div>
      </div>
    </SortableContext>
  )
}
