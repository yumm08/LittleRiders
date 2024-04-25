import {FaRoute, FaTrashAlt, FaPencilAlt} from 'react-icons/fa'
interface Props {
  id: number,
  name : string,
}

export default function RouteListItem({id, name}: Props) {
  return (
    <div className="h-16 m-2 bg-slate-200 p-5 rounded shadow-sm flex items-center">
      <FaRoute className="me-5" size={24}/>
      <p className="text-xl">등원 A </p><FaPencilAlt />
      <FaTrashAlt/>
    </div>
  )
}

