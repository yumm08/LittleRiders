import EmptyCard from './EmptyCard'
import Title from './Title'

import {  useNavigate } from 'react-router-dom'

import { useState } from 'react';



declare global {
  interface Window {
    driverState: {
      setInfo: any;
    };
    teacherState: {
      setInfo: any;
    };
  }
}



window.driverState = {
  setInfo: () => { },
};


window.teacherState = {
  setInfo: () => { },
};







export default function QrCodePage() {

  const navigate = useNavigate()

  const [driverInfo, setDriverInfo] = useState({
    name: "기사님",
    phoneNumber: "연락처",
    image : null
  })

  const [teacherInfo, setTeacherInfo] = useState({
    name: "선생님",
    phoneNumber: "연락처",
    image : null
  })
  // const [driverName, setDriverName] = useState("짱구 씨발럼");
  // const [driverImage, setDriverImage]

  window.driverState.setInfo = setDriverInfo;
  window.teacherState.setInfo = setTeacherInfo;



  return (
    <div className="flex h-full w-full flex-col items-center  bg-lightgreen p-2">
      {/* 제목 부분 */}
      <Title />
      <div className="h-5"></div>
      {/* 기사, 선탑자 카드 부분 */}
      <div className="flex w-full justify-between ">
        {/* 다음 정류장 */}
        <EmptyCard
          name={driverInfo.name}
          phoneNumber={driverInfo.phoneNumber}
          image={driverInfo.image}
        />
        <EmptyCard
          name={teacherInfo.name}
          phoneNumber={teacherInfo.phoneNumber}
          image={teacherInfo.image}
        />
      </div>
      <div className="h-5"></div>
      <button
        className="flex w-full justify-center rounded-[10px]   bg-yellow p-2 font-bold"
        onClick={async () => { 
          if (await window.mainHandler.canMoveStartDrivePage()) {
            console.error("jsjsjs")
            window.mainHandler.startDrive()
            navigate("/driving")
          } 

         }}
      >
        운행 시작
      </button>
    </div>
  )
}
