import { ChangeEvent, useState } from 'react'

import PendingListHeader from '@pages/ChildPage/PendingList/PendingListHeader'
import PendingListTable from '@pages/ChildPage/PendingList/PendingListTable'
import PendingListTableBody from '@pages/ChildPage/PendingList/PendingListTableBody'
import PendingListTableHeader from '@pages/ChildPage/PendingList/PendingListTableHeader'

import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'

import { PendingChildInfo } from '@types'

interface Props {
  pendingChildList: PendingChildInfo[]
}

export default function PendingList({ pendingChildList }: Props) {
  const [checkChildIdList, setCheckChildIdList] = useState<number[]>([])
  const isAllChecked = pendingChildList.length === checkChildIdList.length

  const handleChildCheck = (id: number, isChecked: boolean) => {
    if (isChecked) {
      setCheckChildIdList((prev) => [...prev, id])
    } else {
      setCheckChildIdList(checkChildIdList.filter((item) => item !== id))
    }
  }

  const handleAllCheck = async (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.checked) {
      setCheckChildIdList(
        pendingChildList.map((item) => item.academyChildAllowPendingId),
      )
    } else {
      setCheckChildIdList([])
    }
  }

  return (
    <>
      <PendingListHeader checkCount={checkChildIdList.length} />

      <Divider />
      <Spacing style="h-3" />

      <PendingListTable>
        <PendingListTableHeader
          isAllChecked={isAllChecked}
          onAllCheck={handleAllCheck}
        />

        <PendingListTableBody
          pendingChildList={pendingChildList}
          onChildCheck={handleChildCheck}
          checkChildIdList={checkChildIdList}
        />
      </PendingListTable>
    </>
  )
}
