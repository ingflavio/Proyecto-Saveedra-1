<template>
  <div class="colores-container">
    <h1 class="title" :style="{ color: colors.accent, fontSize: fontSizes.title + 'px' }">Ajustar Tamaño de Fuente</h1>
    <div class="box" :style="{ backgroundColor: colors.secondary }">
      <section class="font-size-section">
        <div class="field" v-for="(value, key) in fontSizes" :key="key">
          <label 
            class="label" 
            :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px' }"
          >
            Tamaño de {{ key.charAt(0).toUpperCase() + key.slice(1) }}
          </label>
          <div class="control">
            <input
              class="input"
              type="number"
              :value="value"
              @input="updateFontSize(key, $event.target.value)"
              :style="{ backgroundColor: colors.secondary, color: colors.accent }"
            />
          </div>
        </div>
      </section>

      <h2 class="subtitle" :style="{ color: colors.accent, fontSize: fontSizes.subtitle + 'px' }">Configuración de Colores y Tipografía</h2>
      <section class="color-section">
        <div class="field" v-for="(colorValue, key) in colors" :key="key">
          <label class="label"  :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px' }">{{ key.charAt(0).toUpperCase() + key.slice(1) }}</label>
          <div class="control">
            <input type="color" :value="colorValue" @input="updateColor(key, $event.target.value)" />
          </div>
        </div>
      </section>
    </div>
  </div>
</template>


<script setup>
import { ref, computed } from 'vue';
import { useValoresStore } from '../store/useValoresStore.js';

const store = useValoresStore();

// Copias reactivas de los valores del store
const fontSizes = ref({ ...store.fontSizes });
const colors = computed(() => store.colors);

// Funciones para actualizar los valores
const updateFontSize = (key, value) => {
  fontSizes.value[key] = value; // Actualiza la copia local
  store.setFontSize(key, value); // También actualiza el store
};

const updateColor = (key, value) => {
  store.setColor(key, value); // Actualiza el store
};
</script>