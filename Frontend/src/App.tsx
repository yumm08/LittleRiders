import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

import Header from '@layouts/Header'
import { Outlet, useLocation } from 'react-router-dom'

const queryClient = new QueryClient()

function App() {
  const location = useLocation()
  const hideHeaderPathList = ['/signin', '/signup']
  const showHeader = !hideHeaderPathList.includes(location.pathname)

  return (
    <QueryClientProvider client={queryClient}>
      {showHeader && <Header />}
      <Outlet />
    </QueryClientProvider>
  )
}

export default App
