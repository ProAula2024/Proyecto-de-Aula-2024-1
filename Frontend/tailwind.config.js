/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      fontFamily: {
        "opensans": ["Open Sans", "sans-serif"],
        "yellowtail": ["Yellowtail", "cursive"],
      },

      colors: {
        "complementary": "#27DB8B",
      },
    },
  },
  plugins: [],
}