const GENDER = {
  FEMALE: 'FEMALE',
  MALE: 'MALE',
} as const

export type Gender = (typeof GENDER)[keyof typeof GENDER]

export * from '@types/auth'
export * from '@types/child'
export * from '@types/dispatch'
