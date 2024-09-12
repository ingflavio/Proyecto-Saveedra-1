import './assets/main.css';
import { createApp } from 'vue';
import App from './App.vue';
import '@/assets/main.scss';
import router from './components/router.js';
import 'leaflet/dist/leaflet.css';

createApp(App)
.use(router)
.mount('#app');
