import { MENU_LIST } from '@constants'
import HeaderMenuListItem from '@layouts/Header/HeaderMenuListItem'

export default function HeaderMenuList() {
  return (
    <div className="flex h-1/2 w-full items-center gap-24">
      {MENU_LIST.map(({ text, path }, index) => (
        <HeaderMenuListItem key={index} text={text} path={path} />
      ))}
    </div>
  )
}
