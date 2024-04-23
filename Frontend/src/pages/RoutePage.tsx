import { ItemListView, NaverMap, RouteList } from '@components/route'

export default function RoutePage() {
  return (
    <div className="flex-row h-[100vh] w-[100vw]">
      <div className="w-[1536px] max-2xl:flex-row max-2xl:w-full max-2xl:mx-10 h-3/6 flex mx-auto justify-center">
        <NaverMap />
        <RouteList />
      </div>
      <ItemListView />
    </div>
  )
}
