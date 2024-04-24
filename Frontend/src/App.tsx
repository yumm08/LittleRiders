import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

import Header from '@layouts/Header'
import { Outlet } from 'react-router-dom'

const queryClient = new QueryClient()

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <Header />
      <Outlet />
    </QueryClientProvider>
  )
}

export default App
