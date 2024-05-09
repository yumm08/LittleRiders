import ShuttleStatusLabel from '@components/Main/ShuttleStatusLabel'

import { AcademyShuttle } from '@types'

interface Props {
  selectedShuttle: AcademyShuttle
}

export default function ShuttleInfo({ selectedShuttle }: Props) {
  const { name, status } = selectedShuttle

  return (
    <div className="h-full w-1/6">
      <div className="flex items-center border-b-2 p-4">
        <ShuttleStatusLabel status={status} />
        <p className="mx-auto text-xl font-bold">{name}</p>
      </div>
    </div>
  )
}
