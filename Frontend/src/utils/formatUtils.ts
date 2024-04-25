/**
 * 생일 연-월-일을 받아서 만 나이를 반환하는 함수
 *
 * @param birth 'yyyy-mm-dd' 형태의 DateString
 * @returns 만 나이
 *
 * @example
 * ```ts
 * formatBirthToAge(1999-08-05) // 만 24세
 * ```
 */
export const formatBirthToAge = (birth: string) => {
  const birthDate = new Date(birth)
  const birthMonth = birthDate.getMonth()
  const birthDay = birthDate.getDate()

  const today = new Date()
  const currentMonth = today.getMonth()
  const currentDay = today.getDate()

  let age = today.getFullYear() - birthDate.getFullYear()

  // 현재 달이 생일 달보다 작다면,
  if (currentMonth < birthMonth) {
    age--
  }
  // 달은 같지만, 오늘 날짜가 생일 날짜보다 작다면,
  else if (currentMonth === birthMonth && currentDay < birthDay) {
    age--
  }

  return `만 ${age}세`
}
