import { useState } from 'react'

import { ItemListView, NaverMap, RouteList } from '@components/Dispatch'

import { useGetRouteList } from '@hooks/dispatch'

import Page from '@layouts/Page'

export default function DispatchPage() {
  const [selectedRouteId, setSelectedRouteId] = useState<number>(-1)
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
        <NaverMap />
        <RouteList
          routeList={routeList}
          selectedRouteId={selectedRouteId}
          handleRouteClick={handleRouteClick}
        />
      </div>
      <ItemListView selectedRouteId={selectedRouteId} />
    </Page>
  )
}
