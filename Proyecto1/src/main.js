import './assets/main.css';
import { createApp } from 'vue';
import App from './App.vue';
import '@/assets/main.scss';
import router from './components/router.js';
createApp(App)
.use(router)
.mount('#app')

