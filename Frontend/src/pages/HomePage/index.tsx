import { useEffect, useState } from 'react'

import ChildList from '@pages/ChildPage/ChildList'

import RealTimeMap from '@components/Main/RealTimeMap'
import ShuttleInfo from '@components/Main/ShuttleInfo'
import Spacing from '@components/Shared/Spacing'

import { useFetchShuttleList } from '@hooks/shuttle'

import Page from '@layouts/Page'
import { AcademyShuttle } from '@types'

export default function HomePage() {
  const [selectedShuttle, setSelectedShuttle] = useState<AcademyShuttle>(null!)
  const { shuttleList, isLoading } = useFetchShuttleList()

  const handleShuttleButtonClick = (shuttle: AcademyShuttle) => {
    setSelectedShuttle(shuttle)
  }

  useEffect(() => {
    if (!isLoading && shuttleList) {
      setSelectedShuttle(shuttleList[0])
    }
  }, [isLoading, shuttleList])

  if (isLoading) {
    return <div>Loading...</div>
  }

  return (
    <>
      <Spacing style="h-[120px]" />

      <div className="flex h-1/2 w-full border-b-2">
        {selectedShuttle && <ShuttleInfo selectedShuttle={selectedShuttle} />}
        {selectedShuttle && (
          <RealTimeMap
            shuttleList={shuttleList}
            selectedShuttle={selectedShuttle}
            onSelect={handleShuttleButtonClick}
          />
        )}
      </div>

      <Page>
        <ChildList />
      </Page>
    </>
  )
}
