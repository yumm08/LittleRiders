import Button from '@components/Shared/Button'
import GenderIcon from '@components/Shared/GenderIcon'

import { formatBirthToAge } from '@utils/formatUtils'

import { PendingChildInfo } from '@types'

interface Props {
  pendingChildInfo: PendingChildInfo
  onChildCheck: (id: number, isChecked: boolean) => void
  isChecked: boolean
  onReject: (academyChildAllowPendingId: number) => void
}

export default function PendingListItem({
  pendingChildInfo,
  onChildCheck,
  isChecked,
  onReject,
}: Props) {
  const {
    academyChildAllowPendingId,
    address,
    childBirthDate,
    childName,
    gender,
  } = pendingChildInfo

  return (
    <tr className="border-b bg-white hover:bg-gray-50">
      <td className="w-4 p-4">
        <input
          type="checkbox"
          className="h-4 w-4 rounded"
          onChange={(e) =>
            onChildCheck(academyChildAllowPendingId, e.target.checked)
          }
          checked={isChecked}
        />
      </td>
      <td className="px-6 py-4">
        <span>{childName}</span>
      </td>
      <td className="px-6 py-4">
        <GenderIcon gender={gender} style="w-full" size={20} />
      </td>
      <td className="px-6 py-4">{formatBirthToAge(childBirthDate)}</td>
      <td className="px-6 py-4">{childBirthDate}</td>
      <td className="px-6 py-4">{address}</td>
      <td className="px-6 py-4">
        <div className="flex justify-end gap-3">
          <Button color="bg-lightgreen" onClick={() => {}}>
            <span className="font-bold text-white">승인</span>
          </Button>
          <Button
            color="bg-red"
            onClick={() => onReject(academyChildAllowPendingId)}
          >
            <span className="font-bold text-white">거절</span>
          </Button>
        </div>
      </td>
    </tr>
  )
}
