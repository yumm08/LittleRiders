import { useRef, useState } from 'react'

import { ItemListView, RouteList } from '@components/Dispatch'

import { useGetRouteList } from '@hooks/dispatch/dispatch'

import Page from '@layouts/Page'

export default function DispatchPage() {
  const [selectedRouteId, setSelectedRouteId] = useState<number>(-1)
  const mapDiv = useRef<HTMLDivElement>(null)
  const { routeList, isLoading, error } = useGetRouteList()

  const handleRouteClick = (id: number) => {
    setSelectedRouteId(id)
  }
  // TODO ERROR, LOADING PAGE
  if (isLoading) {
    return <div> is Loading ... </div>
  }
  if (error) {
    return <div> Error ... </div>
  }
  return (
    <Page>
      <div className="mx-auto flex h-3/6 w-[1536px] justify-center max-2xl:mx-10 max-2xl:w-full max-2xl:flex-row">
        <div
          id="map"
          className="h-550 w-7/12 max-2xl:w-full"
          ref={mapDiv}
        ></div>
        <RouteList
          routeList={routeList}
          selectedRouteId={selectedRouteId}
          handleRouteClick={handleRouteClick}
        />
      </div>
      <ItemListView selectedRouteId={selectedRouteId} mapDiv={mapDiv} />
    </Page>
  )
}
