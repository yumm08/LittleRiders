import ChildPage from '@pages/ChildPage'
import DispatchPage from '@pages/DispatchPage'
import DriveHistoryPage from '@pages/DriveHistoryPage'
import HomePage from '@pages/HomePage'
import MyPage from '@pages/MyPage'
import OperatePage from '@pages/OperatePage'
import SignInPage from '@pages/SignInPage'
import SignUpPage from '@pages/SignUpPage'

import ProtectedRoute from '@routes/ProtectedRoute'
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
        path: 'signin',
        element: <SignInPage />,
      },
      {
        element: <ProtectedRoute />,
        children: [
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
                path: 'drive-history/:id',
                element: <DriveHistoryPage />,
              },
            ],
          },
          {
            path: 'mypage',
            element: <MyPage />,
          },
        ],
      },
    ],
  },
])

export default router
