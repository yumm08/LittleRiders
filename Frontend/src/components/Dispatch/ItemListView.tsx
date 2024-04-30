import { RefObject, useEffect, useRef, useState } from 'react'

import SortableContainer from '@components/Dispatch/SortableContainer'
import Button from '@components/Shared/Button'

import { useFetchChildList } from '@hooks/child'
import { useGetRouteDetail, useGetStationList } from '@hooks/dispatch'
import '@hooks/map'
import { MapHook } from '@hooks/map'

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
  mapDiv: RefObject<HTMLDivElement>
}

// TODO : 함수 분리 해야 한다. Refactor coming soon...
export default function ItemListView({ mapDiv, selectedRouteId }: Props) {
  const [activeStationId, setActiveStationId] = useState<UniqueIdentifier>()
  const [activeChildId, setActiveChildId] = useState<UniqueIdentifier>()
  const [activeStationName, setActiveStationName] = useState<string>('')
  const [activeChildName, setActiveChildName] = useState<string>('')
  const [markerList, setMarkerList] = useState<naver.maps.Marker[]>([])
  const [stationItems, setStationItems] = useState<{
    [key: string]: Station[]
  }>({ stationList: [], selectedStationList: [] })

  const mapRef = useRef<naver.maps.Map | null>(null)

  const {
    routeDetail,
    isLoading: isRouteDetailLoading,
    isPending: isRouteDetailPending,
  } = useGetRouteDetail(selectedRouteId)

  const { stationList, isLoading: isStationListLoading } = useGetStationList()
  const { childList, isLoading: isChildListLoading } = useFetchChildList()

  const { drawRoute, initMap, initPolyLine } = MapHook(
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
  }, [isRouteDetailLoading, isStationListLoading, selectedRouteId])

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
        onDragStart={(e) => {
          handleDragStart(e)
        }}
        onDragOver={(e) => {
          handleDragOver(e)
        }}
        onDragEnd={(e) => {
          handleDragEnd(e)
        }}
        onDragCancel={handleDragCancel}
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
          <SortableItem
            id={activeStationId!}
            name={activeStationName}
            index={0}
          />
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
