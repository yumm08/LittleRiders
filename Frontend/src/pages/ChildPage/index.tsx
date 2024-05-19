import ChildList from '@pages/ChildPage/ChildList'
import PendingList from '@pages/ChildPage/PendingList'

import Spacing from '@components/Shared/Spacing'

import Page from '@layouts/Page'

export default function ChildPage() {
  return (
    <Page>
      <Spacing style="h-[60px]" />
      <PendingList />

      <Spacing style="h-[100px]" />
      <ChildList />
    </Page>
  )
}
