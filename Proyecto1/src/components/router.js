
import { createRouter, createWebHistory } from 'vue-router';
import Index from '../views/Index.vue';
import Register from '../views/Register.vue'; 
import Inciar from '../views/IniciarSesion.vue'
import perfil from '../components/perfil.vue';
import Colores from '../views/colores.vue';
import Galeria from '../views/galeria.vue';
import Politicas from '@/views/politicas.vue';
import PoliticasDePrivacidad from '@/views/politicasDePrivacidad.vue';

const routes = [
  {path: '/', component: Index },
  {path: '/register', component: Register }, 
  {path: '/Login', component: Inciar},
  {path: '/Perfil', component: perfil},
  {path: '/galeria', component: Galeria},
  {path: '/politicas', component: Politicas},
  {path: '/politicasdeprivacidad', component: PoliticasDePrivacidad},
  {path: '/Colores', component: Colores}

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
