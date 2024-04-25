import { ChangeEvent, useState } from 'react'

import PendingListHeader from '@pages/ChildPage/PendingList/PendingListHeader'
import PendingListTable from '@pages/ChildPage/PendingList/PendingListTable'
import PendingListTableBody from '@pages/ChildPage/PendingList/PendingListTableBody'
import PendingListTableHeader from '@pages/ChildPage/PendingList/PendingListTableHeader'

import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'

import { useMutation, useQueryClient } from '@tanstack/react-query'

import { deletePendingChildList } from '@apis/child'

import COLOR_PALETTE from '@style/ColorPalette'
import { PendingChildInfo } from '@types'
import { HttpStatusCode } from 'axios'
import Swal from 'sweetalert2'

interface Props {
  pendingChildList: PendingChildInfo[]
}

export default function PendingList({ pendingChildList }: Props) {
  const [checkChildIdList, setCheckChildIdList] = useState<number[]>([])

  const queryClient = useQueryClient()

  const isAllChecked = pendingChildList.length === checkChildIdList.length

  const { mutate } = useMutation({
    mutationFn: (checkChildIdList: number[]) =>
      deletePendingChildList(checkChildIdList),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['getPendingChildList'] })
      setCheckChildIdList([])
    },
  })

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
        pendingChildList.map((item) => item.academyChildAllowPendingId),
      )
    } else {
      setCheckChildIdList([])
    }
  }

  const handleCheckedChildReject = async () => {
    const result = await Swal.fire({
      text: `${checkChildIdList.length}명의 가입을 거절하시겠습니까?`,
      icon: 'question',
      showDenyButton: true,
      confirmButtonText: '예',
      confirmButtonColor: COLOR_PALETTE.lightgreen,
      denyButtonText: '아니오',
      allowOutsideClick: false,
    })

    if (result.isDenied) {
      return
    }

    mutate(checkChildIdList, {
      onSuccess: (response) => {
        const status = response.status

        if (status === HttpStatusCode.Ok) {
          Swal.fire({
            text: `${checkChildIdList.length}명의 가입을 거절하였습니다.`,
            icon: 'success',
          })
        }
        return
      },
    })
  }

  const handleOneChildReject = async (academyChildAllowPendingId: number) => {
    const childName = pendingChildList.find(
      (child) =>
        child.academyChildAllowPendingId === academyChildAllowPendingId,
    )?.childName

    const result = await Swal.fire({
      text: `${childName}님의 가입을 거절하시겠습니까?`,
      icon: 'question',
      showDenyButton: true,
      confirmButtonText: '예',
      confirmButtonColor: COLOR_PALETTE.lightgreen,
      denyButtonText: '아니오',
      allowOutsideClick: false,
    })

    if (result.isDenied) {
      return
    }

    mutate([academyChildAllowPendingId], {
      onSuccess: (response) => {
        const status = response.status

        if (status === HttpStatusCode.Ok) {
          Swal.fire({
            text: `${childName}님의 가입을 거절하였습니다.`,
            icon: 'success',
          })
        }
        return
      },
    })
  }

  return (
    <>
      <PendingListHeader
        checkCount={checkChildIdList.length}
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
          onReject={handleOneChildReject}
        />
      </PendingListTable>
    </>
  )
}
