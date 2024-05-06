import { RefObject, useEffect, useRef, useState } from 'react'

import Divider from '@components/Shared/Divider'
import Title from '@components/Shared/Title'

import { useGetStationList } from '@hooks/dispatch'
import { MapHook } from '@hooks/map'

import StationListItem from './StationListItem'

import { Station } from '@types'

interface Props {
  mapDiv: RefObject<HTMLDivElement>
}

export default function StationList({ mapDiv }: Props) {
  const [stationMarkerList, setStationMarkerList] = useState<
    naver.maps.Marker[]
  >([])

  const [selectedMarker, setSelectedMarker] = useState<naver.maps.Marker[]>([])
  const [selectedStation, setSelectedStation] = useState<number>(-1)
  const mapRef = useRef<naver.maps.Map>(null)
  const { initMap, deleteMarkers, drawRouteMarkers } = MapHook(mapRef)
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
    if (latLng) drawRouteMarkers(setSelectedMarker, latLng)
  }

  useEffect(() => {
    initMap(mapDiv)

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
    <div className="m-6 h-full w-1/3 gap-1 ">
      <Title text={'정류장 목록'} />
      <Divider />
      <div className="overflow-y-scroll ">
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
