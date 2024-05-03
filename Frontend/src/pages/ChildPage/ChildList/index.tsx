import ChildListHeader from '@pages/ChildPage/ChildList/ChildListHeader'

import ChildCardList from '@components/Child/Child/ChildCardList'

import { useFetchChildList } from '@hooks/child'

export default function ChildList() {
  const { childList, isLoading } = useFetchChildList()

  // TODO: 로딩 컴포넌트 추후에 추가
  if (isLoading) {
    return <div>Loading...</div>
  }

  return (
    <>
      <ChildListHeader />

      <ChildCardList childList={childList} />
    </>
  )
}
