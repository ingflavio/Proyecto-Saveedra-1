
import { createRouter, createWebHistory } from 'vue-router';
import Index from '../views/Index.vue';
import Register from '../views/Register.vue'; 
import Inciar from '../views/IniciarSesion.vue'

const routes = [
  { path: '/', component: Index },
  { path: '/register', component: Register }, 
  {path: '/Login', component: Inciar}
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
