import ShuttleStatusLabel from '@components/Main/ShuttleStatusLabel'

import { AcademyShuttle } from '@types'

interface Props {
  selectedShuttle: AcademyShuttle
}

export default function ShuttleInfo({ selectedShuttle }: Props) {
  return (
    <div className="h-full w-1/6">
      {selectedShuttle && (
        <div className="flex items-center border-b-2 p-4">
          <ShuttleStatusLabel status={selectedShuttle.status} />
          <p className="mx-auto text-xl font-bold">{selectedShuttle.name}</p>
        </div>
      )}
      {!selectedShuttle && (
        <div className="flex h-full w-full items-center justify-center border-r-2">
          <p className="text-xl font-bold text-lightgray">
            등록된 셔틀이 없습니다
          </p>
        </div>
      )}
    </div>
  )
}
