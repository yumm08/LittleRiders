import PendingListItem from '@pages/ChildPage/PendingList/PendingListItem'

import { PendingChildInfo } from '@types'

interface Props {
  pendingChildList: PendingChildInfo[]
  onChildCheck: (id: number, isChecked: boolean) => void
  checkChildIdList: number[]
}

export default function PendingListTableBody({
  pendingChildList,
  onChildCheck,
  checkChildIdList,
}: Props) {
  return (
    <tbody>
      {pendingChildList.map((pendingChildInfo) => (
        <PendingListItem
          key={pendingChildInfo.academyChildAllowPendingId}
          pendingChildInfo={pendingChildInfo}
          onChildCheck={onChildCheck}
          isChecked={checkChildIdList.includes(
            pendingChildInfo.academyChildAllowPendingId,
          )}
        />
      ))}
    </tbody>
  )
}
