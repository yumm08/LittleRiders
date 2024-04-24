import RoutePage from '@pages/DipatchPage'

import App from 'App'
import { createBrowserRouter } from 'react-router-dom'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
  },
  {
    path: '/route',
    element: <RoutePage />,
  },
])

export default router
