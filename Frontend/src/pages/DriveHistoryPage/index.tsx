import Divider from "@components/Shared/Divider";
import Spacing from "@components/Shared/Spacing";
import DriveHistory from "@components/Shuttle/DriveHistory";
import DriveHistoryNaverMap from "@components/Shuttle/DriveHistoryNaverMap";
import Page from "@layouts/Page";
import { useState } from "react";

const DUMMY = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
export default function DriveHistoryPage() {
  const [historyId,setHistoryId] = useState<number>(0)

  //TODO 셔틀 ID로 API콜 해서 특정 셔틀의 간략한 운행 기록을 받아옵니다.
  // const { shuttleId } = useParams();
  // const {historyData,isLoading} = fetchDriveHistoryList(shuttleId)

  const handleClickDriveHistory = (id:number) => {
    setHistoryId(id)
  } 
  return (
    <Page>
      <strong className="text-2xl">N호차 운행 기록</strong>
      <Divider />
      <Spacing style="h-[40px]"/>
      <div className="w-full h-[550px]">
        {/* 목록 리스트 */}
        <div className="w-[270px] h-[550px] ">
          <div className="w-full h-14 bg-lightgreen rounded-t-xl flex justify-center items-center">
            <span className="text-xl text-white">운행 기록</span>
          </div>
          <ul className="w-full h-full overflow-scroll overflow-x-hidden">
            {DUMMY.map((e,i)=>{return <DriveHistory key={e} id={i} onClick={handleClickDriveHistory}/>})}
          </ul>
        </div>
        {/* 네이버 지도 */}
        <DriveHistoryNaverMap historyId={historyId}/>
      </div>
    </Page>
  )
}