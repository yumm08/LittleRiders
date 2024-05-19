import { DriverCardType } from '@types'
import { MdCancel } from 'react-icons/md'

export default function DriverCard({
  name,
  phoneNumber,
  image,
}: DriverCardType) {
  return (
    <div className="relative w-[150px] flex-col items-center justify-center">
      <MdCancel
        size={'22'}
        className="absolute right-1 top-1 z-10 text-lightgreen"
      />
      <div>
        <img
          className="h-[150px] w-full rounded-md border-2 border-slate-300 "
          src={image}
        ></img>
      </div>
      <div className="flex items-center justify-between pt-1">
        <div className="flex flex-col">
          <strong className="text-xm">{name}</strong>
          <span className="text-xs text-darkgray">{phoneNumber}</span>
        </div>
      </div>
    </div>
  )
}
