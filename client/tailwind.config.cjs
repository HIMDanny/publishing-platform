/* eslint-disable @typescript-eslint/no-var-requires */
/* eslint-disable no-undef */
/** @type {import('tailwindcss').Config} */

const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      fontFamily: {
        sans: ['Montserrat', ...defaultTheme.fontFamily.sans],
        archivo: ['Archivo', ...defaultTheme.fontFamily.sans],
      },
      container: {
        padding: '6.25rem',
        screens: {
          '3xl': '1720px',
        },
      },
    },
  },
  plugins: [require('prettier-plugin-tailwindcss')],
};
