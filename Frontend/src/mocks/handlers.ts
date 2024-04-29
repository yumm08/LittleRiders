import { HttpResponse, http } from 'msw'

export const handlers = [
  http.get('/posts', () => {
    return HttpResponse.json({
      message: 'posts mocking success',
    })
  }),
]
