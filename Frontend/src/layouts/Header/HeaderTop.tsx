import { FaBell } from 'react-icons/fa6'

export default function HeaderTop() {
  return (
    <div className="flex h-1/2 items-center justify-between">
      {/* Logo */}
      <div className="flex h-9 w-44 items-center justify-center bg-lightgray">
        Logo
      </div>

      <div className="flex items-center gap-10">
        <FaBell className="text-xl text-yellow" />
        <div className="text-sm">로그아웃</div>
      </div>
    </div>
  )
}
