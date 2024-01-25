/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from '@/plugins'

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'

import axios from "axios";

axios.defaults.baseURL = process.env.NODE_ENV == 'production' ? "/api" : "http://localhost:8080/api";

const app = createApp(App)

registerPlugins(app)

app.mount('#app')
