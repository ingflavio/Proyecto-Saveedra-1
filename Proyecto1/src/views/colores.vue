<template>
  <div class="colores-container">
    <h1 class="title" :style="{ color: colors.accent, fontSize: fontSizes.title + 'px', fontFamily: fonts.title }">
      Ajustar Tamaño de Fuente
    </h1>
    <div class="box" :style="{ backgroundColor: colors.secondary }">
      <section class="font-size-section">
        <div class="field" v-for="(value, key) in fontSizes" :key="key">
          <label class="label" :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px' }">
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

      <h2 class="subtitle" :style="{ color: colors.accent, fontSize: fontSizes.subtitle + 'px', fontFamily: fonts.subtitle }">
        Configuración de Colores y Tipografía
      </h2>
      <section class="color-section">
        <div class="field" v-for="(colorValue, key) in colors" :key="key">
          <label class="label" :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px' }">
            {{ key.charAt(0).toUpperCase() + key.slice(1) }}
          </label>
          <div class="control">
            <input type="color" :value="colorValue" @input="updateColor(key, $event.target.value)" />
          </div>
        </div>
      </section>

      <section class="font-upload-section">
        <div class="field">
          <label class="label" :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px' }">
            Subir Tipografía para Títulos
          </label>
          <div class="control">
            <input
              type="file"
              accept=".ttf"
              @change="event => selectedTitleFont = event.target.files[0]"
              :style="{ backgroundColor: colors.secondary, color: colors.accent }"
            />
          </div>
        </div>
        <div class="field">
          <label class="label" :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px' }">
            Subir Tipografía para Textos
          </label>
          <div class="control">
            <input
              type="file"
              accept=".ttf"
              @change="event => selectedParagraphFont = event.target.files[0]"
              :style="{ backgroundColor: colors.secondary, color: colors.accent }"
            />
          </div>
        </div>
      </section>

      <!-- Botón para guardar los cambios -->
      <div class="field">
        <div class="control">
          <button class="button is-primary" @click="saveSettings">Guardar Cambios</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useValoresStore } from '../store/useValoresStore.js';

const store = useValoresStore();

const fontSizes = ref({ ...store.fontSizes });
const fonts = ref({ ...store.fonts });
const colors = computed(() => store.colors);
const selectedTitleFont = ref(null);
const selectedParagraphFont = ref(null);

const updateFontSize = (key, value) => {
  fontSizes.value[key] = value;
  store.setFontSize(key, value);
};

const updateColor = (key, value) => {
  store.setColor(key, value);
};

const uploadFont = async (key, file) => {
  if (file) {
    const formData = new FormData();
    formData.append('file', file);
    try {
      // Subir archivo y obtener la ruta
      const response = await axios.post('http://localhost:8080/api/uploadFile', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      const fontPath = response.data;
      console.log(`Fuente ${key} subida con éxito: ${fontPath}`);

      // Mandar la ruta al backend
      await store.uploadFont(key, fontPath);
    } catch (error) {
      console.error(`Error al subir la fuente ${key}:`, error);
    }
  }
};

const saveSettings = async () => {
  if (selectedTitleFont.value) {
    await uploadFont('title', selectedTitleFont.value);
  }
  if (selectedParagraphFont.value) {
    await uploadFont('paragraph', selectedParagraphFont.value);
  }
  await store.guardarColores();
  console.log('Configuraciones guardadas');
};

onMounted(async () => {
  await store.getConfiguration();
});
</script>
