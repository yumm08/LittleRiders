import { render, screen } from '@testing-library/react';
import { describe, it } from 'vitest';

import App from '@/App';

describe('App.tsx Rendering Test', () => {
  it('App.tsx shows "App"', async () => {
    render(<App />);

    await screen.findByText('App');
  });
});