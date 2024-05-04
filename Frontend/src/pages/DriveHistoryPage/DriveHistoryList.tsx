import DriveHistory from '@components/Shuttle/DriveHistory'

import { DriveHistoryType } from '@types'

type Props = {
  driveHistoryList: DriveHistoryType[] | undefined
  onClickChangeDateId: (id: number) => void
  dateId: number
}

export default function DriveHistoryList({
  driveHistoryList,
  onClickChangeDateId,
  dateId,
}: Props) {
  return (
    <ul className="h-[400px] w-full overflow-y-auto overflow-x-hidden">
      {driveHistoryList?.map((history, i) => {
        return (
          <DriveHistory
            key={history.time + i}
            time={history.time}
            onClick={onClickChangeDateId}
            id={i}
            dateId={dateId}
          />
        )
      })}
    </ul>
  )
}
