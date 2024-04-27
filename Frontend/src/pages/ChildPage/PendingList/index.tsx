import { ChangeEvent, useState } from 'react'

import PendingListHeader from '@pages/ChildPage/PendingList/PendingListHeader'
import PendingListTable from '@pages/ChildPage/PendingList/PendingListTable'
import PendingListTableBody from '@pages/ChildPage/PendingList/PendingListTableBody'
import PendingListTableHeader from '@pages/ChildPage/PendingList/PendingListTableHeader'

import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'

import {
  useApproveChild,
  useFetchPendingChildList,
  useRejectChild,
} from '@hooks/child'

import { showQuestionAlert, showSuccessAlert } from '@utils/alertUtils'

import { PendingChildInfo } from '@types'
import { HttpStatusCode } from 'axios'

export default function PendingList() {
  const [checkChildIdList, setCheckChildIdList] = useState<number[]>([])

  const { pendingChildList, isLoading } = useFetchPendingChildList()
  const { approveChild } = useApproveChild()
  const { rejectChild } = useRejectChild()

  if (isLoading) {
    return <div>Loading...</div>
  }

  const isAllChecked = pendingChildList.length === checkChildIdList.length
  const checkCount = checkChildIdList.length

  const handleChildCheck = (id: number, isChecked: boolean) => {
    if (isChecked) {
      setCheckChildIdList((prev) => [...prev, id])
    } else {
      setCheckChildIdList(checkChildIdList.filter((item) => item !== id))
    }
  }

  const handleAllCheck = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.checked) {
      setCheckChildIdList(
        pendingChildList.map(
          (child: PendingChildInfo) => child.academyChildAllowPendingId,
        ),
      )
    } else {
      setCheckChildIdList([])
    }
  }

  const handleCheckedChildApprove = async () => {
    const result = await showQuestionAlert({
      text: `${checkChildIdList.length}명의 가입을 승인하시겠습니까?`,
    })

    if (result.isDenied) {
      return
    }

    approveChild(checkChildIdList, {
      onSuccess: (response) => {
        const status = response.status

        if (status === HttpStatusCode.Ok) {
          showSuccessAlert({
            text: `${checkChildIdList.length}명의 가입을 승인하였습니다.`,
          }).then(() => setCheckChildIdList([]))
        }
        return
      },
    })
  }

  const handleCheckedChildReject = async () => {
    const result = await showQuestionAlert({
      text: `${checkChildIdList.length}명의 가입을 거절하시겠습니까?`,
    })

    if (result.isDenied) {
      return
    }

    rejectChild(checkChildIdList, {
      onSuccess: (response) => {
        const status = response.status

        if (status === HttpStatusCode.Ok) {
          showSuccessAlert({
            text: `${checkChildIdList.length}명의 가입을 거절하였습니다.`,
          }).then(() => setCheckChildIdList([]))
        }
        return
      },
    })
  }

  const handleOneChildApprove = async (academyChildAllowPendingId: number) => {
    const childName = pendingChildList.find(
      (child: PendingChildInfo) =>
        child.academyChildAllowPendingId === academyChildAllowPendingId,
    )?.childName

    const result = await showQuestionAlert({
      text: `${childName}님의 가입을 승인하시겠습니까?`,
    })

    if (result.isDenied) {
      return
    }

    approveChild([academyChildAllowPendingId], {
      onSuccess: (response) => {
        const status = response.status

        if (status === HttpStatusCode.Ok) {
          showSuccessAlert({
            text: `${childName}님의 가입을 승인하였습니다.`,
          }).then(() =>
            setCheckChildIdList(
              checkChildIdList.filter(
                (id) => id !== academyChildAllowPendingId,
              ),
            ),
          )
        }
        return
      },
    })
  }

  const handleOneChildReject = async (academyChildAllowPendingId: number) => {
    const childName = pendingChildList.find(
      (child: PendingChildInfo) =>
        child.academyChildAllowPendingId === academyChildAllowPendingId,
    )?.childName

    const result = await showQuestionAlert({
      text: `${childName}님의 가입을 거절하시겠습니까?`,
    })

    if (result.isDenied) {
      return
    }

    rejectChild([academyChildAllowPendingId], {
      onSuccess: (response) => {
        const status = response.status

        if (status === HttpStatusCode.Ok) {
          showSuccessAlert({
            text: `${childName}님의 가입을 거절하였습니다.`,
          }).then(() =>
            setCheckChildIdList(
              checkChildIdList.filter(
                (id) => id !== academyChildAllowPendingId,
              ),
            ),
          )
        }
        return
      },
    })
  }

  return (
    <>
      <PendingListHeader
        checkCount={checkCount}
        onApprove={handleCheckedChildApprove}
        onReject={handleCheckedChildReject}
      />

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
          onApprove={handleOneChildApprove}
          onReject={handleOneChildReject}
        />
      </PendingListTable>
    </>
  )
}
