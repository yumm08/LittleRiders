import { useState } from 'react'

import TeacherSmallCard from '@components/Academy/TeacherSmallCard'
import BottomSheet from '@components/Shared/BottomSheet'
import Spacing from '@components/Shared/Spacing'

import { useSetParentMap } from '@hooks/parent/map'

import './ParentViewPage.css'

import { MdAutorenew } from 'react-icons/md'

export default function RealTimeParentView() {
  useSetParentMap()
  const [bottomsheetState, setBottomSheetState] = useState(true)
  const changeBottomSheetState = () => {
    setBottomSheetState(!bottomsheetState)
  }
  const renewPage = () => {
    window.location.reload()
  }
  return (
    <div className=" relative mx-auto my-0 flex h-[100dvh] min-w-[360px] max-w-[768px] touch-none flex-col items-center bg-white">
      <div id="parentMap" className="h-full w-full "></div>
      <header className=" absolute mt-[4%] flex h-[6%] w-[95%] items-center justify-center rounded-md border-[2px] border-lightgreen bg-white  text-lg  text-black">
        <span>운행 현황</span>
        <div
          onClick={renewPage}
          className="absolute right-[0%] top-[150%] z-50 rounded-lg bg-lightgreen p-2 text-white"
        >
          <MdAutorenew />
        </div>
        <div className="absolute left-[3%] flex items-center text-sm text-black">
          <div className="light h-[10px] w-[10px] rounded-full bg-red"></div>
          <Spacing style="w-2" />
          <span className="font-bold text-red">운행중</span>
        </div>
      </header>
      <BottomSheet title="운행 정보" visibleHandler={changeBottomSheetState}>
        <div className="mx-[3%] mb-[5%] flex w-[100%] justify-around">
          <TeacherSmallCard />
          <TeacherSmallCard />
        </div>
      </BottomSheet>
    </div>
  )
}
