import { useState } from 'react'

import {
  DndContext,
  KeyboardSensor,
  MouseSensor,
  TouchSensor,
  useSensor,
  useSensors,
} from '@dnd-kit/core'
import { SortableContext, sortableKeyboardCoordinates } from '@dnd-kit/sortable'

export default function ItemListView() {
  const [items, setItems] = useState(
    Array.from({ length: 10 }, (_, k) => k).map((k) => ({
      id: `item${k}`,
      content: `item${k}`,
    })),
  )

  const [activeId, setActiveId] = useState(null)

  const handleDragStart = ({ active }) => setActiveId(active.id)

  const handleDragCancel = () => setActiveId(null)

  const handleDragOver = ({ active, over }) => {}

  const handleDragEnd = ({ active, over }) => {}

  const moveBetweenContainers = () => {}

  const sensors = useSensors(
    useSensor(MouseSensor),
    useSensor(TouchSensor),
    useSensor(KeyboardSensor, {
      coordinateGetter: sortableKeyboardCoordinates,
    }),
  )
  return (
    <div className="w-[1536px] max-2xl:flex-row max-2xl:w-full max-2xl:mx-10 h-3/6 flex mx-auto justify-evenly">
      <DndContext
        sensors={sensors}
        onDragStart={handleDragStart}
        onDragCancel={handleDragCancel}
        onDragOver={handleDragOver}
        onDragEnd={handleDragEnd}
      >
        <div className="container">
          <Droppable />
        </div>
      </DndContext>
    </div>
  )
}
