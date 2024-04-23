/** @type {import('tailwindcss').Config} */
import COLOR_PALETTE from './src/style/ColorPalette'

export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: COLOR_PALETTE,
    },
  },
  plugins: [],
}
