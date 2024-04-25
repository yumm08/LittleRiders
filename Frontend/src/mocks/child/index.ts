import { pendingChildList } from '@mocks/child/dummy'
import { HttpResponse, http } from 'msw'

const BASE_URL = '/api'

export const handlers = [
  http.get(`${BASE_URL}/admin/child/pending`, () => {
    console.log('Captured a "GET /child/pending" request')
    return HttpResponse.json({
      pendingChildList,
    })
  }),
  // http.delete(`${BASE_URL}/admin/child/pending`, async ({ request }) => {
  //   console.log('Captured a "DELETE /child/pending" request')
  //   const { academyChildAllowPendingList } = await request.json()

  //   for(const id of academyChildAllowPendingList) {
  //     const index = pendingChildList.findIndex(child => child.academy_child_allow_pending_id === id)
  //     if(index === -1) {
  //       pendingChildList.splice(index, 1)
  //     }
  //   }

  //   return HttpResponse.
  // }),
]
