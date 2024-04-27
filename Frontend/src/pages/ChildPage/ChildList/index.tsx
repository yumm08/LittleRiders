import ChildListHeader from '@pages/ChildPage/ChildList/ChildListHeader'

import ChildList from '@components/Child/ChildList'

import { useFetchChildList } from '@hooks/child'

export default function ChidlList() {
  const { childList, isLoading } = useFetchChildList()

  // TODO: 로딩 컴포넌트 추후에 추가
  if (isLoading) {
    return <div>Loading...</div>
  }

  return (
    <>
      <ChildListHeader />

      <ChildList childList={childList} />
    </>
  )
}
