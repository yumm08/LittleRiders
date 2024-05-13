export type LocationInfo = {
  shuttleId: number
  latitude: number
  longitude: number
  speed: number
}

export type InitData = {
  shuttleId: number
  driverId: number
  teacherId: number
  routeId: number
  locationList: LocationInfo[]
  boardList: []
  dropList: []
}
