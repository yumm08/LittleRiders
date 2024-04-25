import { useState } from 'react'

import { ItemListView, NaverMap, RouteList } from '@components/Dispatch'

import { useQuery } from '@tanstack/react-query'

import { getRouteList } from '@apis/dispatch'

import Page from '@layouts/Page'

export default function DispatchPage() {
  const [selectedRouteId, setSelectedRouteId] = useState<number>(0)

  const { data, error, isLoading } = useQuery({
    queryKey: ['route'],
    queryFn: () => {
      getRouteList()
    },
  })

  if (isLoading) {
    return <div> is Loading ... </div>
  }
  return (
    <Page>
      <div className="mx-auto flex h-3/6 w-[1536px] justify-center max-2xl:mx-10 max-2xl:w-full max-2xl:flex-row">
        <NaverMap />
        <RouteList routeList={data} setSelectedRouteId={setSelectedRouteId} />
      </div>
      <ItemListView selectedRouteId />
    </Page>
  )
}
