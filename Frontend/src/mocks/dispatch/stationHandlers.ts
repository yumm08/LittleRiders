import { HttpResponse, http } from 'msw'

const BASE_URL = 'station'

export const stationHandlers = [
  http.get(`/api/${BASE_URL}`, () => {
    console.log('Captured a "GET /state" request')
    return HttpResponse.json({
      content: [
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
      ],
    })
  }),
]
