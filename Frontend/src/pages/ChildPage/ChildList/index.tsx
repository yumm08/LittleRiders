import ChildCard from '@pages/ChildPage/ChildList/ChildCard'
import ChildListHeader from '@pages/ChildPage/ChildList/ChildListHeader'

import { useFetchChildList } from '@hooks/child'

import { ChildInfo } from '@types'

export default function ChidlList() {
  const { childList, isLoading } = useFetchChildList()

  // TODO: 로딩 컴포넌트 추후에 추가
  if (isLoading) {
    return <div>Loading...</div>
  }

  return (
    <>
      <ChildListHeader />

      <div className="grid grid-cols-6 gap-4">
        {childList.map((child: ChildInfo) => (
          <ChildCard key={child.academyChildId} childInfo={child} />
        ))}
      </div>
    </>
  )
}
