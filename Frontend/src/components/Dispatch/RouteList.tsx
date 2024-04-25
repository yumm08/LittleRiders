import RouteListItem from '@components/Dispatch/RouteListItem'

interface Props {
  routeList: any
  setSelectedRouteId: any
}

export default function RouteList({ routeList, setSelectedRouteId }: Props) {
  return (
    <div className="mx-1 w-4/12 flex-row rounded-md border shadow-md max-xl:w-full">
      <div className="mx-5 mt-5 flex justify-between">
        <p className="text-xl">노선 목록</p>
        <button className="text-xl text-darkgreen">+ 노선추가</button>
      </div>
      <div className="mt-4 h-96 flex-row overflow-y-auto">
        <RouteListItem id={1234} name="이름이다 이름" />
      </div>
    </div>
  )
}
