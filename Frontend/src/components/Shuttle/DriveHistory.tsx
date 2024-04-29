

interface Props {
    id : number
    onClick:(id:number)=>void
}
export default function DriveHistory({id,onClick}:Props) {
  return (
    <li className="w-full h-12 border-b-2 flex justify-center items-center border-x-2 font-semibold">
        <button onClick={()=>{
            onClick(id)
        }}>240417 운행일지</button>
    </li>
  )
}