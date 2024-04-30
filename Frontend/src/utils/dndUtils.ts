/**
 * 드래그를 시작했을 때 activeId를 설정하는 함수
 * @param event 드래그 시작 이벤트
 */
const handleDragStart = (event: DragStartEvent) => {
  const { active } = event
  const { id } = active

  setActiveStationId(id)
  setActiveStationName(() => {
    for (const key of Object.keys(stationItems)) {
      const foundItem = stationItems[key].find((item) => item.id === id)
      if (foundItem) {
        return foundItem.name
      }
    }
    return '선택한 정류장'
  })
}

const handleDragCancel = () => setActiveStationId(undefined)

/**
 * 지금 집고 있는 아이템이 어느 컨테이너에 있는지 확인하는 함수
 * @param id 현재 집고 있는 id
 * @returns
 */
const findContainer = (id: UniqueIdentifier, items) => {
  if (id in items) {
    return id
  }
  return Object.keys(items).find((key) =>
    items[key].some((item) => item.id === Number(id.toString())),
  )
}

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

  // 선택한 아이템의 container와 아이템이 올라가 있는 container 찾기

  const activeContainer = findContainer(id)
  const overContainer = findContainer(over?.id)
  if (!activeContainer || !overContainer || activeContainer === overContainer) {
    return
  }

  setStationItems((prev) => {
    const activeItems = prev[activeContainer]
    const overItems = prev[overContainer]
    const activeIndex = activeItems.findIndex(
      (item) => item.id === Number(id.toString()),
    )
    const overIndex = overItems.findIndex(
      (item) => item.id === Number(overId.toString()),
    )

    // 아이템을 인덱스에 추가하고, 아이템이 콘테이너 밖(위, 아래) 로 간 경우 처리
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
        stationItems[activeContainer][activeIndex],
        ...prev[overContainer].slice(newIndex, prev[overContainer].length),
      ],
    }
  })
  setActiveStationId(undefined)
}

/**
 * 드래그가 끝났을 때 실행되는 함수 (Drop)
 * @param DragEndEvent 이벤트 관련 인자
 * @returns
 */
const handleDragEnd = ({ active, over }: DragEndEvent) => {
  const id = active.id.toString()
  const overId = over?.id

  if (!overId) return

  const activeContainer = findContainer(id)
  const overContainer = findContainer(overId)

  if (!activeContainer || !overContainer || activeContainer !== overContainer) {
    return
  }

  const activeIndex = stationItems[activeContainer].findIndex(
    (item) => item.id === Number(id),
  )
  const overIndex = stationItems[activeContainer].findIndex(
    (item) => item.id === overId,
  )

  if (activeIndex !== overIndex) {
    setStationItems((items) => ({
      ...items,
      [overContainer]: arrayMove(items[overContainer], activeIndex, overIndex),
    }))
  }
}
export { handleDragStart, handleDragCancel, handleDragEnd, handleDragOver }
