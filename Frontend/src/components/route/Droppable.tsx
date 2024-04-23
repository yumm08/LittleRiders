import React from 'react'

type DroppableProps = {
  id: string
  // TODO 데이터 정해지면 변경
  items: unknown
}

/**
 * Droppable 구역 / Children 정의
 * @param id droppable 특정을 위한 id 값
 * @param items draggable을 위한 데이터 값
 */
const Droppable = ({ id, items }: DroppableProps) => {
  const { setNodeRef } = useDroppable({ id })
}
