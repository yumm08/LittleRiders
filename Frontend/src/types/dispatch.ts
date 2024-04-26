export type Station = {
  id: number
  name: string
  latitude: number
  longitude: number
  visitOrder?: number
}

export type Route = {
  id: number
  name: string
  stationList?: Station[]
}
