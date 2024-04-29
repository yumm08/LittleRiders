import { HttpResponse, http } from 'msw'

const BASE_URL = '/api/admin/driver'

export const handlers = [
  http.post(BASE_URL, () => {
    return new HttpResponse(null, { status: 200 })
  }),
]
