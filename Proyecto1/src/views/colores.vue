<template>
  <div class="colores-container">
    <h1 class="title">Configuración de Colores y Tipografía</h1>
    
    <!-- Apartado para Font Size -->
    <section class="font-size-section">
      <h2 class="subtitle">Ajustar Tamaño de Fuente</h2>
      <div class="field">
        <label class="label">Tamaño de Títulos</label>
        <div class="control">
          <input
            class="input"
            type="number"
            v-model="titleSize"
          />
        </div>
      </div>
      <div class="field">
        <label class="label">Tamaño de Subtítulos</label>
        <div class="control">
          <input
            class="input"
            type="number"
            v-model="subtitleSize"
          />
        </div>
      </div>
      <div class="field">
        <label class="label">Tamaño de Párrafos</label>
        <div class="control">
          <input
            class="input"
            type="number"
            v-model="paragraphSize"
          />
        </div>
      </div>
    </section>

    <!-- Apartado para Tipografía -->
    <section class="typography-section">
      <h2 class="subtitle">Subir Tipografías</h2>
      <div class="field">
        <label class="label">Tipografía para Títulos</label>
        <div class="control">
          <input type="file" @change="handleFileUpload('title', $event)" />
        </div>
      </div>
      <div class="field">
        <label class="label">Tipografía para Todo lo Demás</label>
        <div class="control">
          <input type="file" @change="handleFileUpload('body', $event)" />
        </div>
      </div>
    </section>

    <!-- Apartado para Colores -->
    <section class="color-section">
      <h2 class="subtitle">Seleccionar Colores de la Página</h2>
      <div class="field">
        <label class="label">Color Primario</label>
        <div class="control">
          <input type="color" v-model="primaryColor" />
        </div>
      </div>
      <div class="field">
        <label class="label">Color Secundario</label>
        <div class="control">
          <input type="color" v-model="secondaryColor" />
        </div>
      </div>
      <div class="field">
        <label class="label">Color de Acento</label>
        <div class="control">
          <input type="color" v-model="accentColor" />
        </div>
      </div>
      <button class="button is-link" @click="applyStyles">Aplicar Estilos</button>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useValoresStore } from '../store/useValoresStore.js';

const store = useValoresStore();

const titleSize = ref(store.fontSizes.title);
const subtitleSize = ref(store.fontSizes.subtitle);
const paragraphSize = ref(store.fontSizes.paragraph);
const primaryColor = ref(store.colors.primary);
const secondaryColor = ref(store.colors.secondary);
const accentColor = ref(store.colors.accent);

const applyStyles = () => {
  store.setFontSize('title', titleSize.value);
  store.setFontSize('subtitle', subtitleSize.value);
  store.setFontSize('paragraph', paragraphSize.value);
  store.setColor('primary', primaryColor.value);
  store.setColor('secondary', secondaryColor.value);
  store.setColor('accent', accentColor.value);
};

onMounted(() => {
  titleSize.value = store.fontSizes.title;
  subtitleSize.value = store.fontSizes.subtitle;
  paragraphSize.value = store.fontSizes.paragraph;
  primaryColor.value = store.colors.primary;
  secondaryColor.value = store.colors.secondary;
  accentColor.value = store.colors.accent;
});
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
  