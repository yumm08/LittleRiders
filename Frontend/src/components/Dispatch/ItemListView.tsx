import { RefObject, useEffect, useRef, useState } from 'react'

import SortableContainer from '@components/Dispatch/SortableContainer'
import Button from '@components/Shared/Button'

import { useGetRouteDetail, useGetStationList } from '@hooks/dispatch'
import '@hooks/map'
import { MapHook } from '@hooks/map'

import SortableItem from './SortableItem'

import {
  DndContext,
  DragEndEvent,
  DragOverEvent,
  DragOverlay,
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
  mapDiv: RefObject<HTMLDivElement>
}

// TODO : 함수 분리 해야 한다. Refactor coming soon...
export default function ItemListView({ mapDiv, selectedRouteId }: Props) {
  const mapRef = useRef<naver.maps.Map | null>(null)
  const [markerList, setMarkerList] = useState<naver.maps.Marker[]>([])
  const [stationItems, setStationItems] = useState<{
    [key: string]: Station[]
  }>({ stationList: [], selectedStationList: [] })
  const {
    routeDetail,
    isLoading: isRouteDetailLoading,
    isPending: isRouteDetailPending,
  } = useGetRouteDetail(selectedRouteId)

  const { stationList, isLoading: isStationListLoading } = useGetStationList()

  const { drawRoute, initMap, initPolyLine, deleteMarkers } = MapHook(
    mapRef,
    markerList,
    setMarkerList,
  )

  const sensors = useSensors(
    useSensor(MouseSensor, {
      activationConstraint: {
        distance: 10,
      },
    }),
    useSensor(TouchSensor),
    useSensor(KeyboardSensor, {
      coordinateGetter: sortableKeyboardCoordinates,
    }),
  )

  /**
   * 지금 집고 있는 아이템이 어느 컨테이너에 있는지 확인하는 함수
   * @param id 현재 집고 있는 id
   * @returns
   */
  const findContainer = (id: UniqueIdentifier) => {
    if (id in stationItems) {
      return id
    }
    return Object.keys(stationItems).find((key) =>
      stationItems[key].some((item) => item.id === Number(id.toString())),
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
    if (
      !activeContainer ||
      !overContainer ||
      activeContainer === overContainer
    ) {
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

    if (
      !activeContainer ||
      !overContainer ||
      activeContainer !== overContainer
    ) {
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
        [overContainer]: arrayMove(
          items[overContainer],
          activeIndex,
          overIndex,
        ),
      }))
    }
  }

  /**
   * stationList, routeList 가 변경되었을 때 stationItems(모아둔 꾸러미) 내부 변경
   */
  useEffect(() => {
    console.log('Loading Changed')
    if (!isRouteDetailLoading && !isRouteDetailPending) {
      if (!isStationListLoading) {
        setStationItems((prev) => ({
          ...prev,
          stationList: [...stationList],
        }))
        setStationItems({
          stationList: [
            ...stationList.filter(
              (item: Station) =>
                !routeDetail.stationList.some(
                  (sItem: Station) => sItem.id === item.id,
                ),
            ),
          ],
          selectedStationList: [...routeDetail.stationList],
        })
      }
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [isRouteDetailLoading, isStationListLoading])

  useEffect(() => {
    console.log('selectedStationListChanged')
    drawRoute(stationItems['selectedStationList'])

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [stationItems['selectedStationList']])

  useEffect(() => {
    console.log('init')
    initMap(mapDiv)
    initPolyLine()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return (
    <div className="mx-auto flex h-[350px] w-[1536px] justify-evenly max-2xl:mx-10 max-2xl:w-full max-2xl:flex-row">
      <DndContext
        sensors={sensors}
        collisionDetection={closestCorners}
        onDragOver={handleDragOver}
        onDragEnd={handleDragEnd}
      >
        <SortableContainer
          subject="전체 정류장"
          id="stationList"
          items={stationItems['stationList']}
          isLoading={isStationListLoading}
          isPending={isRouteDetailPending}
        />
        <SortableContainer
          subject="선택된 정류장"
          id="selectedStationList"
          items={stationItems['selectedStationList']}
          isLoading={isRouteDetailLoading}
          isPending={isRouteDetailPending}
        />
        <DragOverlay>
          <div>+</div>
        </DragOverlay>
      </DndContext>
      <div className=" flex-row justify-center  self-center">
        <div className="m-1 mb-3">
          <Button onClick={() => {}}>수정</Button>
        </div>
        <div className="m-1 mt-3">
          <Button color="bg-yellow" onClick={() => {}}>
            취소
          </Button>
        </div>
      </div>
      <DndContext
        sensors={sensors}
        collisionDetection={closestCorners}
        onDragOver={handleDragOver}
        onDragEnd={handleDragEnd}
      >
        <SortableContainer
          subject="모든 어린이"
          id="stationList"
          items={stationItems['stationList']}
          isLoading={isRouteDetailLoading}
          isPending={isRouteDetailPending}
        />
        <SortableContainer
          subject="하차할 어린이"
          id="selectedStationList"
          items={stationItems['selectedStationList']}
          isLoading={isRouteDetailLoading}
          isPending={isRouteDetailPending}
        />
      </DndContext>
    </div>
  )
}
