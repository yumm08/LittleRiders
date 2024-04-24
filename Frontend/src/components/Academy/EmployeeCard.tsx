import employee from '@assets/Mock/employee.jpg'
import ColorPalette from '@style/ColorPalette'
import { MdCancel } from 'react-icons/md'

export default function EmployeeCard() {
  return (
    <div className="w-[150px] flex-col justify-center items-center relative">
      <MdCancel
        size={'22'}
        className="absolute top-1 right-1 z-10"
        color={`${ColorPalette['lightgreen']}`}
      />
      <div>
        <img
          className="w-full h-[150px] border-2 border-slate-300 rounded-md "
          src={employee}
        ></img>
      </div>
      <div className="flex items-center justify-between pt-1">
        <div className="flex flex-col">
          <strong className="text-xm">차은우</strong>
          <span className="text-darkgray text-xs">010-9212-4492</span>
        </div>
      </div>
    </div>
  )
}
