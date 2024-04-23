import { ColorPalette } from '@style/ColorPalette'

interface Props {
  innerText: string
  full?: boolean
  onClick: React.MouseEventHandler<HTMLButtonElement>
  color: ColorPalette
}
/**
 * @summary 버튼 컴포넌트
 * @param innerText : 버튼 내부 텍스트
 * @param full(optional) : 레이아웃 전체 꽉 채우고 싶을 때 사용
 * @param onClick : 버튼 클릭 시 실행할 콜백함수
 * @param color : 버튼 배경 색깔 (colorPalette에 명시된 이름 삽입)
 * @returns
 */
export default function Button({
  innerText,
  full = false,
  onClick,
  color,
}: Props) {
  const fullWidth = full ? 'w-full' : ''
  const bgColor = 'bg-' + color

  return (
    <button
      onClick={onClick}
      className={`${bgColor} ${fullWidth} p-5 rounded-lg flex justify-center items-center`}
    >
      <span className="text-xl">{innerText}</span>
    </button>
  )
}
