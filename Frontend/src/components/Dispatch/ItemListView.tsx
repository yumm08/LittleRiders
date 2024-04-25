import { useState } from 'react'

import SortableContainer from './SortableContainer'
import SortableItem from './SortableItem'

import {
  DndContext,
  DragEndEvent,
  DragOverEvent,
  DragOverlay,
  DragStartEvent,
  KeyboardSensor,
  MouseSensor,
  TouchSensor,
  UniqueIdentifier,
  closestCorners,
  useSensor,
  useSensors,
} from '@dnd-kit/core'
import { arrayMove, sortableKeyboardCoordinates } from '@dnd-kit/sortable'
import { Station } from '@types'

interface Props {
  selectedRouteId: number
}

// TODO : 함수 분리 해야 한다. Refactor coming soon...
export default function ItemListView({ selectedRouteId }: Props) {
  const [items, setItems] = useState<{ [key: string]: Station[] }>({
    station: Array.from({ length: 10 }, (_, k) => k).map((k) => ({
      id: `item-${k}`,
      name: `item-${k}`,
    })),
    'selected-station': [],
  })

  const [activeId, setActiveId] = useState<UniqueIdentifier>()

  const sensors = useSensors(
    useSensor(MouseSensor),
    useSensor(TouchSensor),
    useSensor(KeyboardSensor, {
      coordinateGetter: sortableKeyboardCoordinates,
    }),
  )

  const findContainer = (id: UniqueIdentifier) => {
    if (id in items) {
      return id
    }

    return Object.keys(items).find((key) =>
      items[key].some((item) => item.id === id.toString()),
    )
  }

  const handleDragStart = (event: DragStartEvent) => {
    const { active } = event
    const { id } = active

    setActiveId(id)
  }

  const handleDragCancel = () => setActiveId(undefined)

  /**
   * Container가 다른 곳의 Over 됐을 때의 Event 처리
   * @param event dragOverEvent
   * @returns
   */
  const handleDragOver = (event: DragOverEvent) => {
    const { active, over } = event
    const id = active.id.toString()
    const overId = over?.id

    if (!overId) return

    // container 찾기

    const activeContainer = findContainer(id)
    const overContainer = findContainer(over?.id)

    if (
      !activeContainer ||
      !overContainer ||
      activeContainer === overContainer
    ) {
      return
    }

    setItems((prev) => {
      const activeItems = prev[activeContainer]
      const overItems = prev[overContainer]

      const activeIndex = activeItems.findIndex(
        (item) => item.id === id.toString(),
      )
      const overIndex = overItems.findIndex(
        (item) => item.id === overId.toString(),
      )

      let newIndex: number
      if (overId in prev) {
        newIndex = overItems.length + 1
      } else {
        const activeTop = active.rect.current.translated?.top ?? 0
        const isBelowLastItem =
          over &&
          overIndex === overItems.length - 1 &&
          activeTop > over.rect.top + over.rect.height

        const modifier = isBelowLastItem ? 1 : 0
        newIndex = overIndex >= 0 ? overIndex + modifier : overItems.length + 1
      }

      return {
        ...prev,
        [activeContainer]: [
          ...prev[activeContainer].filter((item) => item.id !== active.id),
        ],
        [overContainer]: [
          ...prev[overContainer].slice(0, newIndex),
          items[activeContainer][activeIndex],
          ...prev[overContainer].slice(newIndex, prev[overContainer].length),
        ],
      }
    })
  }

  const handleDragEnd = ({ active, over }: DragEndEvent) => {
    const id = active.id.toString()
    const overId = over?.id

    if (!overId) return

    const activeContainer = findContainer(id)
    const overContainer = findContainer(overId)

    if (
      !activeContainer ||
      !overContainer ||
      activeContainer !== overContainer
    ) {
      return
    }

    const activeIndex = items[activeContainer].findIndex(
      (item) => item.id === id,
    )
    const overIndex = items[activeContainer].findIndex(
      (item) => item.id === overId,
    )

    if (activeIndex !== overIndex) {
      setItems((items) => ({
        ...items,
        [overContainer]: arrayMove(
          items[overContainer],
          activeIndex,
          overIndex,
        ),
      }))
    }
    setActiveId(undefined)
  }

  return (
    <div className="mx-auto flex h-3/6 w-[1536px] justify-evenly max-2xl:mx-10 max-2xl:w-full max-2xl:flex-row">
      <DndContext
        sensors={sensors}
        collisionDetection={closestCorners}
        onDragStart={handleDragStart}
        onDragOver={handleDragOver}
        onDragEnd={handleDragEnd}
        onDragCancel={handleDragCancel}
      >
        <SortableContainer
          subject="전체 정류장"
          id="station"
          items={items['station']}
        />
        <SortableContainer
          subject="선택된 정류장"
          id="selected-station"
          items={items['selected-station']}
        />
        <DragOverlay>
          {activeId ? <SortableItem id={activeId} /> : null}
        </DragOverlay>
      </DndContext>
      <DndContext
        sensors={sensors}
        collisionDetection={closestCorners}
        onDragStart={handleDragStart}
        onDragOver={handleDragOver}
        onDragEnd={handleDragEnd}
        onDragCancel={handleDragCancel}
      >
        <SortableContainer
          subject="모든 어린이"
          id="station"
          items={items['station']}
        />
        <SortableContainer
          subject="하차할 어린이"
          id="selected-station"
          items={items['selected-station']}
        />
        <DragOverlay>
          {activeId ? <SortableItem id={activeId} /> : null}
        </DragOverlay>
      </DndContext>
    </div>
  )
}
