import { RefObject, useEffect, useRef, useState } from 'react'

import SortableContainer from '@components/Dispatch/SortableContainer'
import { SortableItem } from '@components/Dispatch/SortableItem'
import Button from '@components/Shared/Button'

import { useFetchChildList } from '@hooks/child'
import { useGetRouteDetail, useGetStationList } from '@hooks/dispatch/dispatch'
import '@hooks/map'
import { MapHook } from '@hooks/map'

import {
  handleChildDragCancel,
  handleChildDragEnd,
  handleChildDragOver,
  handleChildDragStart,
  handleStationDragCancel,
  handleStationDragEnd,
  handleStationDragOver,
  handleStationDragStart,
} from '@utils/dndUtils'

import {
  DndContext,
  DragOverlay,
  KeyboardSensor,
  MouseSensor,
  TouchSensor,
  UniqueIdentifier,
  closestCorners,
  useSensor,
  useSensors,
} from '@dnd-kit/core'
import { sortableKeyboardCoordinates } from '@dnd-kit/sortable'
import { ChildInfo, Station } from '@types'

interface Props {
  selectedRouteId: number
  mapDiv: RefObject<HTMLDivElement>
}

// TODO : 함수 분리 해야 한다. Refactor coming soon...
export default function ItemListView({ mapDiv, selectedRouteId }: Props) {
  const [activeStationId, setActiveStationId] = useState<UniqueIdentifier>()
  const [activeStationName, setActiveStationName] = useState<string>('')
  const [stationItems, setStationItems] = useState<{
    [key: string]: Station[]
  }>({ stationList: [], selectedStationList: [] })
  const [activeChildId, setActiveChildId] = useState<UniqueIdentifier>()
  const [activeChildName, setActiveChildName] = useState<string>('')
  const [childItems, setChildItems] = useState<{
    [key: string]: ChildInfo[]
  }>({ childList: [], selectedChildList: [] })

  const [selectedStation, setSelectedStation] = useState<number>(-1)
  // const [hoveredStation, setHoveredStation] = useState<number>(-1)
  const [markerList, setMarkerList] = useState<naver.maps.Marker[]>([])
  const mapRef = useRef<naver.maps.Map | null>(null)
  const [selectedStationMarker, setSelectedStationMarker] = useState<
    naver.maps.Marker[]
  >([])
  const [childDragDisabled, setChildDragDisabled] = useState<boolean>(false)
  const {
    routeDetail,
    isLoading: isRouteDetailLoading,
    isPending: isRouteDetailPending,
  } = useGetRouteDetail(selectedRouteId)

  const { stationList, isLoading: isStationListLoading } = useGetStationList()
  const { childList, isLoading: isChildListLoading } = useFetchChildList()

  const {
    drawRoute,
    initMap,
    initPolyLine,
    drawRouteMarkers,
    deleteMarkers,
    moveMap,
  } = MapHook(mapRef)
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

  const handleStationItemClick = (stationId: number) => {
    setSelectedStation(stationId)
    deleteMarkers(selectedStationMarker, setSelectedStationMarker)

    let station: Station | undefined
    // let containerKey: string
    for (const key of Object.keys(stationItems)) {
      const stations = stationItems[key]
      station = stations.find((station) => {
        return station.id === stationId
      })
      if (station) {
        break
      }
    }
    if (station) {
      drawRouteMarkers(
        setSelectedStationMarker,
        [new naver.maps.LatLng(station.latitude!, station.longitude!)],
        'bus-stop-clicked.svg',
        new naver.maps.Size(50, 52),
        new naver.maps.Point(15, 30),
        new naver.maps.Point(29, 50),
      )
      moveMap(new naver.maps.LatLng(station.latitude!, station.longitude!))
    }
  }

  const handleStationItemHover = () => {}

  const handleModifyClick = () => {}

  const handleCancelClick = () => {}

  useEffect(() => {
    if (stationItems.selectedStationList) {
      const temp = stationItems.selectedStationList.find(
        (station) => station.id === selectedStation,
      )
      if (temp && temp.childList) {
        setChildDragDisabled(false)
        setChildItems((prev) => ({
          ...prev,
          selectedChildList: [...temp.childList!],
        }))
      } else {
        setChildDragDisabled(true)
        setChildItems((prev) => ({
          ...prev,
          selectedChildList: [],
        }))
      }
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedStation])
  /**
   * stationList, routeList 가 변경되었을 때 stationItems(모아둔 꾸러미) 내부 변경
   */
  useEffect(() => {
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
      setChildItems(() => {
        const tempChildList: ChildInfo[] = []
        if (childList) {
          childList.forEach((child: ChildInfo) => {
            let isSame = false
            routeDetail.stationList.forEach((station: Station) => {
              if (
                station.childList!.some(
                  (stationChild) =>
                    stationChild.academyChildId === child.academyChildId,
                )
              ) {
                isSame = true
              }
            })
            if (!isSame) tempChildList.push(child)
          })
          return {
            childList: [...tempChildList],
            selectedChildList: [],
          }
        }
        return { childList: [], selectedChildList: [] }
      })
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [isRouteDetailLoading, isStationListLoading, selectedRouteId])

  // 초기 ChildList 구현
  useEffect(() => {
    if (!isChildListLoading) {
      setChildItems({
        childList: [...childList],
        selectedChildList: [],
      })
    }
  }, [childList, isChildListLoading])

  useEffect(() => {
    drawRoute(stationItems['selectedStationList'], markerList, setMarkerList)

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [stationItems['selectedStationList']])

  useEffect(() => {
    stationItems.selectedStationList?.forEach((station: Station) => {
      if (station.id === selectedStation) {
        station.childList = [...childItems['selectedChildList']]
      }
    })
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [childItems['selectedChildList']])

  useEffect(() => {
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
          handleStationDragStart(
            e,
            stationItems,
            setActiveStationId,
            setActiveStationName,
          )
        }}
        onDragOver={(e) => {
          handleStationDragOver(
            e,
            stationItems,
            setActiveStationId,
            setStationItems,
          )
        }}
        onDragEnd={(e) => {
          handleStationDragEnd(e, stationItems, setStationItems)
        }}
        onDragCancel={() => handleStationDragCancel(setActiveStationId)}
      >
        <SortableContainer
          subject="전체 정류장"
          id="stationList"
          selectedStation={selectedStation}
          items={stationItems['stationList']}
          isLoading={isStationListLoading}
          isPending={isRouteDetailPending}
          onClick={handleStationItemClick}
          onHover={handleStationItemHover}
        />
        <SortableContainer
          subject="선택된 정류장"
          id="selectedStationList"
          selectedStation={selectedStation}
          items={stationItems['selectedStationList']}
          isLoading={isRouteDetailLoading}
          isPending={isRouteDetailPending}
          onClick={handleStationItemClick}
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
          <Button color="px-3 bg-lightgreen " onClick={handleModifyClick}>
            수정
          </Button>
        </div>
        <div className="m-1 mt-3">
          <Button color="px-3 bg-yellow" onClick={handleCancelClick}>
            취소
          </Button>
        </div>
      </div>
      <DndContext
        sensors={sensors}
        collisionDetection={closestCorners}
        onDragStart={(e) => {
          handleChildDragStart(
            e,
            childItems,
            setActiveChildId,
            setActiveChildName,
          )
        }}
        onDragOver={(e) => {
          handleChildDragOver(e, childItems, setActiveChildId, setChildItems)
        }}
        onDragEnd={(e) => {
          handleChildDragEnd(e, childItems, setChildItems)
        }}
        onDragCancel={() => handleChildDragCancel(setActiveChildId)}
      >
        <SortableContainer
          subject="모든 어린이"
          id="childList"
          items={childItems['childList']}
          isDisabled={childDragDisabled}
          isLoading={isRouteDetailLoading}
          isPending={isRouteDetailPending}
        />
        <SortableContainer
          subject="하차할 어린이"
          id="selectedChildList"
          items={childItems['selectedChildList']}
          isDisabled={childDragDisabled}
          isLoading={isRouteDetailLoading}
          isPending={isRouteDetailPending}
        />
        <DragOverlay>
          <SortableItem id={activeChildId!} name={activeChildName} index={0} />
        </DragOverlay>
      </DndContext>
    </div>
  )
}
