import { childList } from '@mocks/child/dummy'
import { HttpResponse, http } from 'msw'

const BASE_URL = '/api/admin/child'

export const handlers = [
  http.get(BASE_URL, () => {
    return HttpResponse.json(childList)
  }),
]
