
import { createRouter, createWebHistory } from 'vue-router';
import Index from '../views/Index.vue';
import Register from '../views/Register.vue'; 

const routes = [
  { path: '/', component: Index },
  { path: '/register', component: Register }, 
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
