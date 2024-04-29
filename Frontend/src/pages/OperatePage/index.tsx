import DriverList from '@components/Academy/DriverList'
import TeacherList from '@components/Academy/TeacherList'
import Spacing from '@components/Shared/Spacing'
import ShuttleList from '@components/Shuttle/ShuttleList'

import Page from '@layouts/Page'

export default function OperatePage() {
  return (
    <Page>
      <Spacing style="h-[60px]" />
      <ShuttleList show={5} />
      <Spacing style="h-5" />
      <TeacherList show={7} />
      <Spacing style="h-5" />
      <DriverList show={7} />
      <Spacing style="h-5" />
    </Page>
  )
}
