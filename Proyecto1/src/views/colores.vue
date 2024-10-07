<template>
  <div class="colores-container">
    <h1 class="title">Ajustar Tamaño de Fuente</h1>
    <div class="box" :style="{ backgroundColor: colors.secondary }">
      <section class="font-size-section">
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

      <h1 class="title">Configuración de Colores y Tipografía</h1>
      <section class="color-section">
        <div class="field" v-for="(colorValue, key) in colors" :key="key">
          <label class="label">{{ key.charAt(0).toUpperCase() + key.slice(1) }}</label>
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

<style scoped>
.colores-container {
  position: relative;
  padding-top: 40px;
  min-height: calc(100vh - 90px - 30px);
  padding-bottom: 40px;
  background-image: linear-gradient(
      to bottom,
      rgba(46, 49, 53, 0.212),
      rgba(0, 0, 0, 0.87)
    ),
    url("../../public/banner.jpg");
  background-size: cover;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
}

.box {
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 500px;
  
  margin: auto; /* Asegura que la caja esté centrada horizontalmente */
}

.title {
  margin-top: 20px;
  text-align: center;
}

.field {
  margin-bottom: 15px;
}
</style>
