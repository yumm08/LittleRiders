import { useState } from 'react'

import RouteListItem from '@components/Dispatch/RouteListItem'

import { usePostRoute } from '@hooks/dispatch/dispatch'

import RouteListAddItem from './RouteListAddItem'

import { Route } from '@types'

interface Props {
  routeList: Route[]
  selectedRouteId: number
  handleRouteClick: (id: number) => void
}

export default function RouteList({
  routeList,
  selectedRouteId,
  handleRouteClick,
}: Props) {
  const [isAddButtonClicked, setIsAddButtonClicked] = useState<boolean>(false)
  const { addRoute } = usePostRoute()

  const handleAddRouteClick = () => {
    setIsAddButtonClicked(true)
  }

  const handleCancelAddClick = () => {
    setIsAddButtonClicked(false)
  }

  const handleConfirmAddClick = (value: string) => {
    const route: Route = {
      name: value,
      stationList: [],
    }
    addRoute(route)
    setIsAddButtonClicked(false)
  }
  return (
    <div className="mx-1 w-4/12 flex-row rounded-md border shadow-md max-xl:w-full">
      <div className="mx-5 mt-5 flex justify-between">
        <p className="text-xl">노선 목록</p>
        <button
          className="text-xl text-darkgreen"
          onClick={handleAddRouteClick}
        >
          + 노선추가
        </button>
      </div>
      <div className="mt-4 h-96 flex-row overflow-y-auto">
        {routeList &&
          routeList.map(({ id, name }) => (
            <RouteListItem
              key={id}
              id={id!}
              name={name}
              onRouteClick={() => {
                handleRouteClick(id!)
              }}
              selectedRouteId={selectedRouteId}
            />
          ))}
        {isAddButtonClicked && (
          <RouteListAddItem
            handleConfirmAddClick={handleConfirmAddClick}
            handleCancelAddClick={handleCancelAddClick}
          />
        )}
      </div>
    </div>
  )
}
