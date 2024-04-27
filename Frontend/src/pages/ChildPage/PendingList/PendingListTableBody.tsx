import PendingListItem from '@pages/ChildPage/PendingList/PendingListItem'

import { PendingChildInfo } from '@types'

interface Props {
  pendingChildList: PendingChildInfo[]
  onChildCheck: (id: number, isChecked: boolean) => void
  checkChildIdList: number[]
  onApprove: (academyChildAllowPendingId: number) => void
  onReject: (academyChildAllowPendingId: number) => void
}

export default function PendingListTableBody({
  pendingChildList,
  onChildCheck,
  checkChildIdList,
  onApprove,
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
          onApprove={onApprove}
          onReject={onReject}
        />
      ))}
    </tbody>
  )
}
