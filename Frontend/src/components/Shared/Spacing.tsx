interface Props {
  direction: 'vertical' | 'horizontal'
  size: string
}
/**
 * @summary 공백을 주고 싶을 때 편리하게 사용 가능한 컴포넌트
 * @param direction 가로 공백인지, 세로 공백인지 결정
 * @param size 얼마나 공백을 줄지 결정 (테일윈드 단위로 써주세요)
 * @returns
 */
export default function Spacing({ direction, size }: Props) {
  const baseClass = direction === 'vertical' ? 'h-' : 'w-'
  const className = baseClass + { size }
  return <div className={className}></div>
}
