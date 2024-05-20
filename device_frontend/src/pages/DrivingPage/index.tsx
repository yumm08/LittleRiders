import { useState } from 'react'

import Title from './Title'

import { useNavigate } from 'react-router-dom'

declare global {
  interface Window {
    boardState: {
      setInfo: any
    }
    readyState: {
      setInfo: any
    }
    stationState: {
      setInfo: any
    }
  }
}

window.boardState = {
  setInfo: () => {},
}

window.readyState = {
  setInfo: () => {},
}

window.stationState = {
  setInfo: () => {},
}
export default function DrivingPage() {
  const navigate = useNavigate()
  const [boardChildList, setBoardChildList] = useState<any[]>([])
  const [readyChildList, setReadyChildList] = useState<any[]>([])

  const [station, setStation] = useState({
    before: '역삼역',
    now: '강남역',
    after: '희준이네집',
  })

  window.boardState.setInfo = setBoardChildList
  window.readyState.setInfo = setReadyChildList
  window.stationState.setInfo = setStation

  return (
    <div className="flex h-full w-full flex-col items-center bg-gradient-to-r from-green-400 to-blue-500  p-2">
      {/* 제목 부분 */}
      <div className="flex w-full flex-row justify-between px-4">
        <Title />
        <button
          className="transition-color rounded bg-yellow p-1 px-8 text-2xl font-bold shadow-md shadow-slate-700 active:bg-white"
          onClick={async () => {
            if (await window.mainHandler.endDrive()) {
              navigate('/')
              window.mainHandler.rerenderShuttleInfo()
            }
          }}
        >
          운 행 종 료
        </button>
      </div>
      <div className="h-[20px]"></div>
      {/* 기사, 선탑자 카드 부분 */}
      <div className="flex h-[80%] w-full justify-around">
        <div className="relative flex h-[300px] w-[50%] justify-center rounded-[5px] border-4 border-darkgreen bg-white font-bold text-black">
          <span className="font text-2xl font-extrabold text-darkgreen">
            이번 정류장
          </span>
          <span className="absolute top-[50%] h-2 w-full -translate-y-1/2 bg-lightgreen"></span>
          <div className="absolute top-[50%] h-[175px] w-[175px] -translate-y-1/2 rounded-full bg-lightgreen"></div>
          <div className="absolute top-[50%]  h-[165px] w-[165px] -translate-y-1/2 rounded-full  bg-white"></div>
          <div className="absolute top-[50%] z-10 flex h-[150px] w-[150px] -translate-y-1/2 flex-col items-center justify-center overflow-hidden rounded-full border-4 border-blue-400 bg-white text-center">
            <div className="h-full w-full">
              <div className="h-2/3 w-full rounded-t-full bg-white"></div>
              <div className="h-1/3 w-full rounded-b-full bg-blue-400"></div>
            </div>
            <p className="absolute z-50 w-full truncate text-3xl">
              {station.now}
            </p>
          </div>

          <span className="text-md absolute left-0 top-[40%] text-rose-800">
            이전 정류장
          </span>
          <p className="absolute left-0 top-[52%] w-24 truncate text-left text-lg">
            {station.before}
          </p>

          <span className="text-md absolute right-0 top-[40%] text-blue-800">
            다음 정류장
          </span>
          <p className="absolute right-0 top-[52%] w-24 truncate text-right text-lg">
            {station.after}
          </p>
        </div>
        <div className="flex h-[98%] w-[20%] flex-col items-center overflow-hidden rounded-t-xl border-4 border-darkgreen bg-white">
          <div className=" flex w-full justify-center bg-darkgreen p-2 text-xl font-bold text-white">
            탑승 현황
          </div>
          <ul className="flex h-[100%] w-full flex-col overflow-y-auto">
            {boardChildList.map((item, index) => {
              return (
                <li
                  key={index}
                  className=" flex w-full justify-center border-b-2 border-darkgreen p-3 ps-5 text-3xl font-bold tracking-[0.4em] text-green-900"
                  onClick={() => {
                    window.mainHandler.beep()
                    window.mainHandler.showChildInfo(item)
                  }}
                >
                  {item.name}
                </li>
              )
            })}
          </ul>
        </div>
        <div className="flex h-[98%] w-[20%] flex-col items-center overflow-hidden rounded-t-xl border-2 border-yellow bg-white">
          <div className=" flex w-full justify-center bg-yellow p-2 text-xl font-bold">
            대기 현황
          </div>
          <ul className="flex h-[100%] w-full flex-col overflow-y-auto">
            {readyChildList.map((item, index) => {
              return (
                <li
                  key={index}
                  className="flex w-full items-center justify-evenly border-b-2 border-yellow p-3 ps-5 text-3xl font-bold tracking-[0.4em]"
                  onClick={() => {
                    window.mainHandler.beep()
                    window.mainHandler.showChildInfo(item)
                  }}
                >
                  {item.name}
                </li>
              )
            })}
          </ul>
        </div>
      </div>
    </div>
  )
}
