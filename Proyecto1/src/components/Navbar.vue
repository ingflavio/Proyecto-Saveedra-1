<template>
  <nav :style="{ backgroundColor: store.colors.primary }" class="navbar is-transparent">
    <div class="navbar-brand">
      <div class="navbar-burger js-burger" data-target="navbarExampleTransparentExample">
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>

    <div id="navbarExampleTransparentExample" class="navbar-menu">
      <div class="navbar-start">
        <router-link class="navbar-item" 
                     :style="{ color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" 
                     to="/"> Inicio </router-link>
        <router-link class="navbar-item" 
                     :style="{ color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" 
                     to="/galeria"> Galería </router-link>
        <router-link class="navbar-item" 
                     :style="{ color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" 
                     to="/politicas"> Politicas </router-link>
        <router-link class="navbar-item" 
                     :style="{ color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" 
                     to="/colores"> Colores </router-link>
      </div>
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="field is-grouped">
            <p class="control" v-if="!isAuthenticated">
              <router-link class="button buttonNav" 
                           :style="{ backgroundColor: store.colors.button, color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" 
                           to="/Login">
                <span :style="{ fontSize: store.fontSizes.paragraph + 'px' }">Iniciar Sesión</span>
              </router-link>
            </p>
            <p class="control" v-if="!isAuthenticated">
              <router-link class="button buttonNav" 
                           :style="{ backgroundColor: store.colors.button, color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" 
                           to="/register">
                <span :style="{ fontSize: store.fontSizes.paragraph + 'px' }">Registrarte</span>
              </router-link>
            </p>
            <p class="control" v-if="isAuthenticated">
              <router-link class="button buttonNav" 
                           :style="{ backgroundColor: store.colors.button, color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" 
                           to="/perfil">
                <span :style="{ fontSize: store.fontSizes.paragraph + 'px' }">Perfil</span>
              </router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar-item {
  color: inherit !important; 
}

.buttonNav {
  transition: background-color 0.3s; 
}

@media only screen and (max-width: 1023px) {
  .field.is-grouped {
    justify-content: center;
  }
}
</style>

<script setup>
import { ref, onMounted } from 'vue';
import { useValoresStore } from "../store/useValoresStore.js";

const store = useValoresStore();
const isAuthenticated = ref(false);

onMounted(() => {
  const token = localStorage.getItem("token");
  if (token) {
    isAuthenticated.value = true;
  }
});
</script>
