import ChildPage from '@pages/ChildPage'
import DispatchPage from '@pages/DispatchPage'
import DriveHistoryPage from '@pages/DriveHistoryPage'
import HomePage from '@pages/HomePage'
import MyPage from '@pages/MyPage'
import OperatePage from '@pages/OperatePage'
import SignUpPage from '@pages/SignUpPage'

import App from 'App'
import { createBrowserRouter } from 'react-router-dom'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: 'signup',
        element: <SignUpPage />,
      },
      {
        index: true,
        element: <HomePage />,
      },
      {
        path: 'home',
        element: <HomePage />,
      },
      {
        path: 'manage',
        children: [
          {
            path: 'child',
            element: <ChildPage />,
          },
          {
            path: 'dispatch',
            element: <DispatchPage />,
          },
          {
            path: 'operate',
            element: <OperatePage />,

          },
          {
            path : 'drive-history/:id',
            element:<DriveHistoryPage/>
          }
        ],
      },
      {
        path: 'mypage',
        element: <MyPage />,
      },
    ],
  },
])

export default router
