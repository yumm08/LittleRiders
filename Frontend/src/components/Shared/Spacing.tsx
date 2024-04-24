interface Props {
  style: string
}
/**
 * @summary 공백을 주고 싶을 때 편리하게 사용 가능한 컴포넌트
 * @param direction 가로 공백인지, 세로 공백인지 결정
 * @param size 얼마나 공백을 줄지 결정
 */
export default function Spacing({ style }: Props) {
  return <div className={style}></div>
}
