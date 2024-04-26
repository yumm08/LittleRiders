import { Gender } from '@types'

const CHILD_STATUS = {
  GRADUATE: 'GRADUATE',
  LEAVE: 'LEAVE',
  ATTENDING: 'ATTENDING',
} as const

export type ChildStatus = (typeof CHILD_STATUS)[keyof typeof CHILD_STATUS]

export type ChildInfo = {
  academyChildId: number
  name: string
  birthDate: string
  gender: Gender
  imagePath: string
  childStatus: ChildStatus
}
