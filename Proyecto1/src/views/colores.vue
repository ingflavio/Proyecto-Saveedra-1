<template>
  <div class="colores-container">
    <h1 class="title">Configuración de Colores y Tipografía</h1>
    
    <section class="font-size-section">
      <h2 class="subtitle">Ajustar Tamaño de Fuente</h2>
      <div class="field" v-for="(value, key) in fontSizes" :key="key">
        <label class="label">Tamaño de {{ key.charAt(0).toUpperCase() + key.slice(1) }}</label>
        <div class="control">
          <input
            class="input"
            type="number"
            :value="value"
            @input="updateFontSize(key, $event.target.value)"
          />
        </div>
      </div>
    </section>

    <section class="color-section">
      <h2 class="subtitle">Seleccionar Colores de la Página</h2>
      <div class="field" v-for="(colorValue, key) in colors" :key="key">
        <label class="label">{{ key.charAt(0).toUpperCase() + key.slice(1) }}</label>
        <div class="control">
          <input type="color" :value="colorValue" @input="updateColor(key, $event.target.value)" />
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useValoresStore } from '../store/useValoresStore.js';

const store = useValoresStore();

// Copias reactivas de los valores del store
const fontSizes = ref({ ...store.fontSizes });
const colors = ref({ ...store.colors });

// Funciones para actualizar los valores
const updateFontSize = (key, value) => {
  fontSizes.value[key] = value; // Actualiza la copia local
  store.setFontSize(key, value); // También actualiza el store
};

const updateColor = (key, value) => {
  colors.value[key] = value; // Actualiza la copia local
  store.setColor(key, value); // También actualiza el store
};
</script>

<style scoped>
.colores-container {
  padding: 20px;
}

.subtitle {
  margin-top: 20px;
}

.field {
  margin-bottom: 15px;
}
</style>
