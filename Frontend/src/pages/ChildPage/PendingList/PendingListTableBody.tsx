import PendingListItem from '@pages/ChildPage/PendingList/PendingListItem'

import { PendingChildInfo } from '@types'

interface Props {
  pendingChildList: PendingChildInfo[]
  onChildCheck: (id: number, isChecked: boolean) => void
  checkChildIdList: number[]
  onReject: (academyChildAllowPendingId: number) => void
}

export default function PendingListTableBody({
  pendingChildList,
  onChildCheck,
  checkChildIdList,
  onReject,
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
          onReject={onReject}
        />
      ))}
    </tbody>
  )
}
