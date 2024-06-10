/**
 * plugins/vuetify.js
 *
 * Framework documentation: https://vuetifyjs.com`
 */

// Styles
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'
import { pl } from 'vuetify/locale'

// Composables
import { createVuetify } from 'vuetify'

// https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
export default createVuetify({
  locale: {
    locale: 'pl',
    fallback: 'pl',
    messages: { pl },
  },
  theme: {
    themes: {
      light: {
        colors: {
          dark: '#2D2415',
          light: '#FFFEED',
          lightGray: '#A2A39A',
          darkGray: '#606968',
          gold: '#B78B63'
        },
        
      },
    },
  },
})
