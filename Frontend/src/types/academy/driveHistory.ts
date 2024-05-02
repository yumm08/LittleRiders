export interface DriveInfoByDay {
  id: number
  routeName: string
  startTime: string
  endTime: string
}

export interface DriveDetailInfoByHistory {
  id: string
  shuttle: Shuttle
  driver: Driver
  teacher: Teacher
  locationList: DriveLocation[]
}

export interface Shuttle {
  id: number
  name: string
  type: string
  licenseNumber: string
}

export interface Driver {
  id: number
  name: string
  phoneNumber: string
}

export interface Teacher {
  id: number
  name: string
  phoneNumber: string
}

export interface DriveLocation {
  latitude: number
  longitude: number
  time: string
}
