<template>
    <label :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px' }" class="label">
      <slot></slot> <!-- Esto permite que puedas pasar contenido al label -->
    </label>
  </template>
  
  <script setup>
  import { computed } from 'vue';
  import { useValoresStore } from '../store/useValoresStore.js'; // Ajusta la ruta si es necesario
  
  const store = useValoresStore();
  const fontSizes = computed(() => store.fontSizes);
  const colors = computed(() => store.colors);
  </script>
  
  <style scoped>
  .label {
    /* Puedes agregar estilos adicionales aquí si es necesario */
  }
  </style>
  