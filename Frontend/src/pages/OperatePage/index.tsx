import DeviceList from '@components/Academy/DeviceList'
import DriverList from '@components/Academy/DriverList'
import TeacherList from '@components/Academy/TeacherList'
import Spacing from '@components/Shared/Spacing'
import ShuttleList from '@components/Shuttle/ShuttleList'

import Page from '@layouts/Page'

export default function OperatePage() {
  return (
    <Page>
      <Spacing style="h-5" />
      <DeviceList show={5} />
      <Spacing style="h-5" />
      <ShuttleList show={3} />
      <Spacing style="h-5" />
      <TeacherList show={1} />
      <Spacing style="h-5" />
      <DriverList show={7} />
      <Spacing style="h-5" />
    </Page>
  )
}
