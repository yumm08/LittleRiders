import Spacing from '@components/Shared/Spacing'
import ShuttleList from '@components/Shuttle/ShuttleList'

import Page from '@layouts/Page'

export default function OperatePage() {
  return (
    <Page>
      <ShuttleList show={5} />
      <Spacing className="h-5" />
      <ShuttleList show={5} />
      <Spacing className="h-5" />
      <ShuttleList show={5} />
      <Spacing className="h-5" />
    </Page>
  )
}
