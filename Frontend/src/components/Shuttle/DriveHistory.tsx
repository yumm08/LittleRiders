interface Props {
  time: string
}
export default function DriveHistory({ time }: Props) {
  return (
    <li className="flex h-12 w-full cursor-pointer items-center justify-center border-x-2 border-b-2 font-semibold">
      <button>{time}</button>
    </li>
  )
}
