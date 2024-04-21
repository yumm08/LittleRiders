import { useEffect } from 'react';

import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import axios from 'axios';

import A from '@/components/A';

const queryClient = new QueryClient();

function App() {
  useEffect(() => {
    const getPosts = async () => {
      const res = await axios.get('/posts');

      console.log(res.data);
    };

    getPosts();
  });

  return (
    <QueryClientProvider client={queryClient}>
      <div className="text-3xl font-bold underline">
        App
        <A />
      </div>
    </QueryClientProvider>
  );
}

export default App;
