import { Route } from '@types'

export const routeList: Route[] = [
  {
    id: 1,
    name: '동원 A',
  },
  {
    id: 2,
    name: '등원B',
  },
  {
    id: 3,
    name: '하원A',
  },
]

export const routeDetail: Route = {
  id: 0,
  name: `등원`,
  stationList: [
    {
      id: 1,
      name: '대봉 빌딩 앞',
      latitude: 37.5021259,
      longitude: 127.042259,
      visitOrder: 1,
    },
    {
      id: 2,
      name: '신라스테이 역삼 앞',
      latitude: 37.504858,
      longitude: 127.041869,
      visitOrder: 2,
    },
    {
      id: 3,
      name: '신한 은행 앞',
      latitude: 37.508435,
      longitude: 127.038947,
      visitOrder: 3,
    },
    {
      id: 4,
      name: '언주역 앞',
      latitude: 37.507503,
      longitude: 127.0342544,
      visitOrder: 4,
    },
    {
      id: 5,
      name: '국민은행 역삼역 지점',
      latitude: 37.4999198,
      longitude: 127.0377596,
      visitOrder: 5,
    },
  ],
}
