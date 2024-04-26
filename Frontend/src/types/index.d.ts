const GENDER = {
  FEMALE: 'FEMALE',
  MALE: 'MALE',
} as const

export * from '@types/child'
export type Gender = (typeof GENDER)[keyof typeof GENDER]
