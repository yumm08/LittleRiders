import ChildStatusLabel from '@pages/ChildPage/ChildList/ChildStatusLabel'

import GenderIcon from '@components/Shared/GenderIcon'

import { formatBirthToAge } from '@utils/formatUtils'

import { ChildInfo } from '@types'

interface Props {
  childInfo: ChildInfo
}

export default function ChildCard({ childInfo }: Props) {
  const { name, birthDate, gender, imagePath, childStatus } = childInfo

  return (
    <div className="flex h-72 flex-col justify-between border p-4">
      {/* 사진 */}
      <div className="mb-4 aspect-square w-full bg-lightgray">
        <img src={imagePath} alt="" />
      </div>
      {/* 정보 */}
      <div className="flex flex-col gap-2">
        <div className="flex items-center justify-between">
          <span className="text-xl">{name}</span>
          <span className="text-sm">({formatBirthToAge(birthDate)})</span>
          <span>
            <GenderIcon gender={gender} size={30} />
          </span>
        </div>
        <div className="flex items-center justify-between gap-2">
          <p className="text-sm">{birthDate}</p>
          <ChildStatusLabel childStatus={childStatus} />
        </div>
      </div>
    </div>
  )
}
