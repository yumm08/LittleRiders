import { render, screen } from '@testing-library/react'
import App from 'App'
import { describe, it } from 'vitest'

describe('App.tsx Rendering Test', () => {
  it('App.tsx shows "App"', async () => {
    render(<App />)

    await screen.findByText('App')
  })
})
