import HeaderMenuList from '@layouts/Header/HeaderMenuList'
import HeaderTop from '@layouts/Header/HeaderTop'

export default function Header() {
  return (
    <div className="h-[120px] w-full px-[200px] font-bold shadow-md">
      <HeaderTop />
      <HeaderMenuList />
    </div>
  )
}
