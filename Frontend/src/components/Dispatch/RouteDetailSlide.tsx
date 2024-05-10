import { RefObject, useEffect, useState } from 'react'

import {
  RouteDetailChild,
  RouteDetailSlideFooter,
  RouteDetailSlideHeader,
  RouteDetailStation,
} from '@components/Dispatch'

import { useFetchChildList } from '@hooks/child'
import {
  useDeleteStation,
  useGetRouteDetail,
  useGetStationList, // usePostRouteChild,
  usePostRouteStation, // usePutRoute,
} from '@hooks/dispatch'
import '@hooks/map'
import { MapHook } from '@hooks/map'

import {
  KeyboardSensor,
  MouseSensor,
  TouchSensor,
  UniqueIdentifier,
  useSensor,
  useSensors,
} from '@dnd-kit/core'
import { sortableKeyboardCoordinates } from '@dnd-kit/sortable'
import { ChildInfo, ChildtoStationArgType, Station } from '@types'

interface Props {
  selectedRouteId: number
  selectedRouteName: string
  setSelectedRouteId: React.Dispatch<React.SetStateAction<number>>
  mapRef: RefObject<naver.maps.Map>
  handleAddButton: () => void
}

// TODO : 함수 분리 해야 한다. Refactor coming soon...
export default function RouteDetailSlide({
  mapRef,
  selectedRouteId,
  selectedRouteName,
  setSelectedRouteId,
  handleAddButton,
}: Props) {
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
  const { removeStation } = useDeleteStation()
  // const { modifyRouteChild } = usePostRouteChild()
  const { modifyRouteStation } = usePostRouteStation()

  const { drawRoute, initPolyLine, drawRouteMarkers, deleteMarkers, moveMap } =
    MapHook(mapRef)
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
  const handleStationRemoveClick = (
    e: React.MouseEvent<SVGElement, MouseEvent>,
    id: UniqueIdentifier,
  ) => {
    e.stopPropagation()
    if (Number(id.toString()) === selectedStation) {
      deleteMarkers(selectedStationMarker, setSelectedStationMarker)
      setSelectedStation(-1)
    }
    removeStation(Number(id.toString()))
  }

  const handleStationItemHover = () => {}

  const handleModifyClick = () => {
    const stationListTemp: Station[] = []
    const stationListChildListTemp: ChildtoStationArgType[] = []
    const selectedStationListTemp = stationItems.selectedStationList
    for (let k = 0; k < selectedStationListTemp.length; k++) {
      stationListTemp.push({
        id: selectedStationListTemp[k].id,
        visitOrder: k,
      })
      const academyChildIdList: number[] = []
      if (selectedStationListTemp[k].childList) {
        for (let s = 0; s < selectedStationListTemp[k].childList!.length; s++) {
          academyChildIdList.push(
            selectedStationListTemp[k].childList![s].academyChildId,
          )
        }
      }
      stationListChildListTemp.push({
        stationId: selectedStationListTemp[k].id,
        academyChildIdList: academyChildIdList,
      })
    }

    modifyRouteStation({
      routeId: Number(selectedRouteId.toString()),
      stationList: stationListTemp,
    })

    // modifyRouteChild({
    //   routeId: Number(selectedRouteId.toString()),
    //   stationList: stationListChildListTemp,
    // })
  }

  const handleCancelClick = () => {
    setSelectedRouteId(-1)
    setTimeout(function () {
      window.dispatchEvent(new Event('resize'))
    }, 550)
  }

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
  }, [isRouteDetailLoading, isStationListLoading, selectedRouteId, stationList])

  // 초기 ChildList 구현
  useEffect(() => {
    if (!isChildListLoading && childList) {
      setChildItems({
        childList: [...childList],
        selectedChildList: [],
      })
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [isChildListLoading])

  useEffect(() => {
    drawRoute(stationItems['selectedStationList'], markerList, setMarkerList)

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [stationItems['selectedStationList']])

  useEffect(() => {
    setStationItems((prev) => {
      prev.selectedStationList?.forEach((station: Station) => {
        if (station.id === selectedStation) {
          station.childList = [...childItems['selectedChildList']]
        }
      })
      return { ...prev }
    })
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [childItems['selectedChildList']])

  useEffect(() => {
    initPolyLine()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return (
    <div className="mx-auto mt-[120px] flex h-[calc(100%-120px)] flex-col justify-evenly max-2xl:mx-10 max-2xl:w-full max-2xl:flex-row">
      <RouteDetailSlideHeader
        selectedRouteName={selectedRouteName}
        handleAddButton={handleAddButton}
      />
      <div className="flex h-[calc(100%-120px)]">
        <div className="">
          <RouteDetailStation
            sensors={sensors}
            stationItems={stationItems}
            setActiveStationId={setActiveStationId}
            setActiveStationName={setActiveStationName}
            setStationItems={setStationItems}
            isRouteDetailLoading={isRouteDetailLoading}
            isRouteDetailPending={isRouteDetailPending}
            activeStationId={activeStationId}
            activeStationName={activeStationName}
            selectedStation={selectedStation}
            isStationListLoading={isStationListLoading}
            handleStationItemClick={handleStationItemClick}
            handleStationRemoveClick={handleStationRemoveClick}
            handleStationItemHover={handleStationItemHover}
          />
        </div>
        <div className="">
          <RouteDetailChild
            sensors={sensors}
            childItems={childItems}
            setActiveChildId={setActiveChildId}
            setActiveChildName={setActiveChildName}
            setChildItems={setChildItems}
            childDragDisabled={childDragDisabled}
            isRouteDetailLoading={isRouteDetailLoading}
            isRouteDetailPending={isRouteDetailPending}
            activeChildId={activeChildId}
            activeChildName={activeChildName}
          />
        </div>
      </div>
      <RouteDetailSlideFooter
        handleModifyClick={handleModifyClick}
        handleCancelClick={handleCancelClick}
      />
    </div>
  )
}
