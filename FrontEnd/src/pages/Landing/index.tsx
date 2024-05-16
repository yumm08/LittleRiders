export default function Landing() {
  return (
    <div className="flex h-full w-full flex-row p-4">
      <div className="flex w-1/2 flex-col items-center justify-center ">
        <div className="m-2 h-1/2 w-5/6">
          <img className="h-full" src="/default.png"></img>
        </div>
        <div className="flex h-1/2 w-5/6 flex-col divide-y-[1px] rounded border ">
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              호차
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              2호차
            </div>
          </div>
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              차량
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              스타렉크스
            </div>
          </div>
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              번호판
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              29-1923차
            </div>
          </div>
        </div>
      </div>
      <div className="flex h-full w-1/2 flex-col items-center justify-between">
        <button className="rounde m-2 h-1/2 w-5/6 border-2 p-3 text-2xl font-bold transition-colors ease-in-out active:bg-lightgreen active:text-slate-100">
          운행하러가기
        </button>
        <button className="m-2 h-1/6 w-5/6 rounded border-2 border-darkgreen p-2 text-2xl font-bold transition-colors ease-in-out active:bg-lightgreen">
          최근 이동 기록
        </button>
        <button className="m-2 h-1/6 w-5/6 rounded border-2 border-darkgreen  p-2 text-2xl font-bold transition-colors ease-in-out active:bg-lightgreen">
          노선 보기
        </button>
        <button className="mt-2 h-1/6 w-5/6 rounded border-2 border-darkgreen  p-2 text-2xl font-bold transition-colors ease-in-out active:bg-lightgreen">
          단말기 고유 정보
        </button>
      </div>
    </div>
  )
}
