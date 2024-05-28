/**
 * plugins/index.js
 *
 * Automatically included in `./src/main.js`
 */

// Plugins
import vuetify from './vuetify'
import router from '../router'
import store from '../store'
import "vue3-snackbar/styles";

import { SnackbarService, Vue3Snackbar } from "vue3-snackbar";

export function registerPlugins (app) {
  app
    .use(vuetify)
    .use(router)
    .use(store)
    .use(SnackbarService)
    .component('Vue3Snackbar', Vue3Snackbar);
}
