
interface Props {
  time: string
  onClick : (id:number) => void
  id : number
}
export default function DriveHistory({ time, onClick,id }: Props) {
  return (
    <li className="flex h-12 w-full cursor-pointer items-center justify-center border-x-2 border-b-2 font-semibold">
      <button onClick={()=>{
        onClick(id)
      }}>{time}</button>
    </li>
  )
}
