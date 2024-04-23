interface Props {
  innerText: string
  full?: boolean
  onClick: React.MouseEventHandler<HTMLButtonElement>
  color?: string
}
/**
 * @summary 버튼 컴포넌트
 * @param innerText : 버튼 내부 텍스트
 * @param full(optional) : 레이아웃 전체 꽉 채우고 싶을 때 사용
 * @param onClick : 버튼 클릭 시 실행할 콜백함수
 * @param color : 버튼 배경 색깔  'bg-' 형태로 주입
 * @returnsa
 */

export default function Button({
  innerText,
  full = false,
  onClick,
  color = 'bg-lightgreen',
}: Props) {
  const fullWidth = full ? 'w-full' : ''

  const className = `${color} ${fullWidth} p-3 rounded-lg flex justify-center items-center`
  return (
    <button onClick={onClick} className={className}>
      <span className="text-xl text-white">{innerText}</span>
    </button>
  )
}
