import ChildPage from '@pages/ChildPage'
import DispatchPage from '@pages/DispatchPage'
import DriveHistoryPage from '@pages/DriveHistoryPage'
import MainPage from '@pages/MainPage'
import MyPage from '@pages/MyPage'
import OperatePage from '@pages/OperatePage'
import SignInPage from '@pages/SignInPage'
import SignUpPage from '@pages/SignUpPage'
import StationPage from '@pages/StationPage'

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
            element: <MainPage />,
          },
          {
            path: 'home',
            element: <MainPage />,
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
                path: 'dispatch/station',
                element: <StationPage />,
              },
              {
                path: 'operate',
                element: <OperatePage />,
              },
              {
                path: 'drive-history/:shuttleId',
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
