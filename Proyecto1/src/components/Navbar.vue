<template>
  <nav class="navbar is-transparent">
    <div class="navbar-brand">
      <div
        class="navbar-burger js-burger"
        data-target="navbarExampleTransparentExample"
      >
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>

    <div id="navbarExampleTransparentExample" class="navbar-menu">
      <div class="navbar-start">
        <router-link class="navbar-item" to="/"> Inicio </router-link>
        <router-link class="navbar-item" to="#"> Galería </router-link>
        <router-link class="navbar-item" to="/colores"> Colores </router-link> <!-- Nuevo enlace -->
      </div>
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="field is-grouped">
            <p class="control" v-if="!isAuthenticated">
              <router-link class="button buttonNav" to="/Login">
                <span>Iniciar Sesión</span>
              </router-link>
            </p>
            <p class="control" v-if="!isAuthenticated">
              <router-link class="button buttonNav" to="/register">
                <span>Registrarte</span>
              </router-link>
            </p>
            <p class="control" v-if="isAuthenticated">
              <router-link class="button buttonNav" to="/perfil">
                <span>Perfil</span>
              </router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const isAuthenticated = ref(false);

onMounted(() => {
  const token = localStorage.getItem('token');
  if (token) {
    isAuthenticated.value = true;
  }
});
</script>

<style>
.navbar {
  background-color: #48cae4 !important;
  min-height: 30px;
  max-height: 120px;
}

.navbar-menu {
  background-color: #48cae4 !important;
}

.navbar-item {
  color: black !important;
}

.buttonNav {
  background-color: #b3c4cc;
  color: black;
  --bulma-button-border-style: none;
}

.buttonNav:hover {
  background-color: #919da3;
}

strong {
  color: black !important;
}

@media only screen and (max-width: 1023px) {
  .field.is-grouped {
    justify-content: center;
  }
}
</style>
