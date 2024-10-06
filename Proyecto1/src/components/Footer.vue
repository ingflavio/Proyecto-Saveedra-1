<template>
  <footer :style="{ backgroundColor: store.colors.secondary }" class="footer">
    <div :style="{ color: store.colors.accent }" class="content has-text-centered">
      <strong>Creador por:</strong> <span>Flavio Franchich</span>
    </div>
  </footer>
</template>

<script setup>
import { useValoresStore } from '../store/useValoresStore.js';

const store = useValoresStore();
</script>

<style scoped>
.footer {
  color: black; /* Color por defecto si no se establece desde el store */
  padding: var(--bulma-footer-padding, 20px); /* Usar la variable si está definida */
}
</style>
