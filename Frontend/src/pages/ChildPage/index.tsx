import PendingList from '@pages/ChildPage/PendingList'

import Spacing from '@components/Shared/Spacing'

import { useQuery } from '@tanstack/react-query'

import { getPendingChildList } from '@apis/child'

import Page from '@layouts/Page'

export default function ChildPage() {
  const { data, isLoading } = useQuery({
    queryKey: ['getPendingChildList'],
    queryFn: getPendingChildList,
    select: (data) => {
      const pendingChildList = data.data.pendingChildList
      return pendingChildList
    },
  })

  if (isLoading) {
    return <div>Loading...</div>
  }

  return (
    <Page>
      <Spacing style="h-[60px]" />
      <PendingList pendingChildList={data} />
    </Page>
  )
}
