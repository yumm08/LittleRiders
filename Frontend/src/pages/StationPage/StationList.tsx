import { RefObject, useEffect, useState } from 'react'

import StationListItem from '@pages/StationPage/StationListItem'

import Button from '@components/Shared/Button'
import Divider from '@components/Shared/Divider'
import Title from '@components/Shared/Title'

import { useGetStationList } from '@hooks/dispatch/dispatch'
import { MapHook } from '@hooks/map'

import { BASE_LAT, BASE_LNG } from '@constants'
import { Station } from '@types'

interface Props {
  mapDiv: RefObject<HTMLDivElement>
  mapRef: RefObject<naver.maps.Map>
  handleAddButton: () => void
}

export default function StationList({
  mapDiv,
  mapRef,
  handleAddButton,
}: Props) {
  const [stationMarkerList, setStationMarkerList] = useState<
    naver.maps.Marker[]
  >([])

  const [selectedMarker, setSelectedMarker] = useState<naver.maps.Marker[]>([])
  const [selectedStation, setSelectedStation] = useState<number>(-1)
  const { initMap, deleteMarkers, drawRouteMarkers, moveMap } = MapHook(mapRef)
  const { stationList, isLoading } = useGetStationList()

  const handleClick = (id: number) => {
    setSelectedStation(id)
    deleteMarkers(selectedMarker, setSelectedMarker)

    let latLng
    stationList.forEach((station: Station) => {
      if (station.id === selectedStation) {
        latLng = new naver.maps.LatLng(station.latitude!, station.longitude!)
      }
    })
    if (latLng) {
      drawRouteMarkers(setSelectedMarker, latLng)
      moveMap(latLng)
    }
  }

  useEffect(() => {
    initMap(mapDiv, {
      center: new naver.maps.LatLng(BASE_LAT, BASE_LNG),
      zoom: 15,
      minZoom: 7,
      zoomControl: true,
      disableKineticPan: true,
      disableDoubleClickZoom: true,
    })

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])
  useEffect(() => {
    if (stationList) {
      deleteMarkers(stationMarkerList, setStationMarkerList)
      const latLngList = []
      for (let k = 0; k < stationList.length; k++) {
        latLngList.push(
          new naver.maps.LatLng(
            stationList[k].latitude,
            stationList[k].longitude,
          ),
        )
      }
      drawRouteMarkers(
        setStationMarkerList,
        latLngList,
        'bus-stop-2.svg',
        new naver.maps.Size(50, 52),
        new naver.maps.Point(15, 30),
        new naver.maps.Point(29, 50),
      )
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [stationList])

  // TODO isLoading
  if (isLoading) {
    return <div>Loading</div>
  }

  return (
    <div className="m-6 h-[90%] w-1/3 gap-1 ">
      <div className="flex justify-between">
        <Title text={'정류장 목록'} />
        <Button
          color="rounded transition ease-in-out hover:bg-gray-100 bg-white active:bg-gray-300"
          onClick={handleAddButton}
        >
          + 정류장 추가
        </Button>
      </div>
      <Divider />
      <div className="h-full overflow-y-scroll ">
        {stationList ? (
          stationList.map((station: Station) => (
            <StationListItem
              key={station.id}
              station={station}
              selectedStation={selectedStation}
              handleClick={handleClick}
            />
          ))
        ) : (
          <div>등록된 정류장이 없습니다.</div>
        )}
      </div>
    </div>
  )
}
