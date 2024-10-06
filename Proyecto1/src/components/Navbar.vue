<template>
  <nav :style="{ backgroundColor: primaryColor }" class="navbar is-transparent">
    <div class="navbar-brand">
      <div
        class="navbar-burger js-burger"
        data-target="navbarExampleTransparentExample"
      >
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>

    <div id="navbarExampleTransparentExample" class="navbar-menu">
      <div class="navbar-start">
        <router-link class="navbar-item" to="/"> Inicio </router-link>
        <router-link class="navbar-item" to="#"> Galería </router-link>
        <router-link class="navbar-item" to="/colores"> Colores </router-link>
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


<style>
.navbar-item {
  color: black !important;
}

.buttonNav {
  background-color: #b3c4cc;
  color: black;
}

.buttonNav:hover {
  background-color: #919da3;
}

@media only screen and (max-width: 1023px) {
  .field.is-grouped {
    justify-content: center;
  }
}
</style>

<script setup>
import { ref, onMounted, watch } from "vue";
import { useValoresStore } from "../store/useValoresStore.js";

const store = useValoresStore();
const primaryColor = ref(store.colors.primary);

watch(() => store.colors.primary, (newColor) => {
  primaryColor.value = newColor;
});

const isAuthenticated = ref(false);

onMounted(() => {
  const token = localStorage.getItem("token");
  if (token) {
    isAuthenticated.value = true;
  }
});
</script>
