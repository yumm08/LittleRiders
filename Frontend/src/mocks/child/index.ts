import { pendingChildList } from '@mocks/child/dummy'
import { HttpResponse, http } from 'msw'

const BASE_URL = '/api'

type PostChildReqestBody = {
  academyChildAllowPendingList: number[]
}
type DeleteChildRequestBody = {
  academyChildAllowPendingList: number[]
}

export const handlers = [
  http.get(`${BASE_URL}/admin/child/pending`, () => {
    console.log('Captured a "GET /child/pending" request')
    return HttpResponse.json({
      pendingChildList,
    })
  }),
  http.post<never, PostChildReqestBody, never>(
    `${BASE_URL}/admin/child/pending`,
    async ({ request }) => {
      console.log('Captured a "POST /child/pending" request')
      const { academyChildAllowPendingList } = await request.json()

      console.log(request)

      for (const id of academyChildAllowPendingList) {
        const index = pendingChildList.findIndex(
          (child) => child.academyChildAllowPendingId === id,
        )
        if (index !== -1) {
          pendingChildList.splice(index, 1)
        }
      }

      return new HttpResponse(null, { status: 200 })
    },
  ),
  http.delete<never, DeleteChildRequestBody, never>(
    `${BASE_URL}/admin/child/pending`,
    async ({ request }) => {
      console.log('Captured a "DELETE /child/pending" request')
      const { academyChildAllowPendingList } = await request.json()

      for (const id of academyChildAllowPendingList) {
        const index = pendingChildList.findIndex(
          (child) => child.academyChildAllowPendingId === id,
        )
        if (index !== -1) {
          pendingChildList.splice(index, 1)
        }
      }

      return new HttpResponse(null, { status: 200 })
    },
  ),
]
