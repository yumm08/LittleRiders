import OperatePage from '@pages/ManagePage'

import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

const queryClient = new QueryClient()

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <OperatePage />
    </QueryClientProvider>
  )
}

export default App
