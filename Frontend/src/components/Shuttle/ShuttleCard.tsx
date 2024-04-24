import Button from '@components/Shared/Button'

import shuttle from '@assets/Mock/shuttle.png'

export default function ShuttleCard() {
  const handleShuttleCardClick = () => {}
  return (
    <div className="w-[200px] flex-col justify-center items-center">
      <div className="relative">
        <img
          className="w-full border-2 border-slate-300 rounded-md p-3"
          src={shuttle}
        ></img>
        <strong className="absolute top-2 left-2 text-xm">1호차</strong>
        <strong className="absolute bottom-2 left-2 text-xs">
          단말기 번호 : A12J5034
        </strong>
      </div>
      <div className="flex items-center justify-between pt-2">
        <div className="flex flex-col">
          <strong className="text-xm">스타렉스</strong>
          <span className="text-darkgray text-xm">59 너 1482</span>
        </div>
        <Button onClick={handleShuttleCardClick}>
          <span className=" text-white text-xs">운행기록</span>
        </Button>
      </div>
    </div>
  )
}
