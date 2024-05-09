import { SyntheticEvent } from 'react'

import Button from '@components/Shared/Button'

import Logo from '@assets/Mock/Logo.webp'
import { Shuttle } from '@types'
import { useNavigate } from 'react-router'

interface Props {
  data: Shuttle
}
export default function ShuttleCard({ data }: Props) {
  const navigate = useNavigate()
  const handleShuttleCardClick = () => {
    navigate(`/manage/drive-history/${data.shuttleId}`)
  }
  const addDefaultImage = (e: SyntheticEvent<HTMLImageElement, Event>) => {
    e.currentTarget.src = Logo
  }
  return (
    <div className="w-[150px] flex-col items-center justify-center">
      <div className="relative">
        <img
          className="h-[150px] w-[150px] rounded-md border-2 border-slate-300"
          src={data.imagePath}
          alt="이미지"
          onError={addDefaultImage}
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
