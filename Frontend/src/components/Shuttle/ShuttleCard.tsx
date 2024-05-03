import Button from '@components/Shared/Button'

import shuttle from '@assets/Mock/shuttle.png'
import { Shuttle } from '@types'
import { useNavigate } from 'react-router'

interface Props {
  data: Shuttle
}
export default function ShuttleCard({ data }: Props) {
  const navigate = useNavigate()
  const handleShuttleCardClick = () => {
    navigate(`/manage/drive-history/${data.id}`)
  }
  return (
    <div className="w-[200px] flex-col items-center justify-center">
      <div className="relative">
        <img
          className="w-full rounded-md border-2 border-slate-300 p-3"
          src={shuttle}
        ></img>
        <strong className="text-xm absolute left-2 top-2">{data.name}</strong>
        {/* <strong className="absolute bottom-2 left-2 text-xs">
          단말기 번호 : A12J5034
        </strong> */}
      </div>
      <div className="flex items-center justify-between pt-2">
        <div className="flex flex-col">
          <strong className="text-xm">{data.type}</strong>
          <span className="text-xm text-darkgray">{data.licenseNumber}</span>
        </div>
        <Button onClick={handleShuttleCardClick}>
          <span className=" text-xs text-white">운행기록</span>
        </Button>
      </div>
    </div>
  )
}
