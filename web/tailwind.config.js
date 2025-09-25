/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}", "./src/**/*.html", "./src/**/*.ts"],
  theme: {
    extend: {
      colors: {
        'mono': '#0D0D0D',
        'blanco': '#FFF',
        'analogo': '#666',
        'azul-menu': '#0870C1',
        'azul-menu-selector': '#075C9F',
        'azul-button': '#0A6EBE',
        'bg-gray': '#EFF0F0',
        'card-bg': '#D9D9D9',
        'green-reminder': '#6DCB54',
        'orange-reminder': '#FF982A',
        'purple-reminder': '#8439D5',
      },
      fontFamily: {
        'montserrat': ['Montserrat', 'sans-serif'],
      },
      boxShadow: {
        'card': '4px 4px 4px 0 rgba(0, 0, 0, 0.25)',
      }
    },
  },
  plugins: [require("@tailwindcss/typography")],
};
