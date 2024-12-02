<template>
  <div class="colores-container">
    <h1
      class="title"
      :style="{ color: colors.accent, fontSize: fontSizes.title + 'px', fontFamily: fontFamily.title}"
    >
      Ajustar Tamaño de Fuente
    </h1>
    <div class="box" :style="{ backgroundColor: colors.secondary }">
      <section class="font-size-section">
        <div class="field" v-for="(value, key) in fontSizes" :key="key">
          <label
            class="label"
            :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px', fontFamily: fontFamily.title}"
          >
            Tamaño de {{ key.charAt(0).toUpperCase() + key.slice(1) }}
          </label>
          <div class="control">
            <input
              class="input"
              type="number"
              :value="value"
              @input="updateFontSize(key, $event.target.value)"
              :style="{ backgroundColor: colors.secondary, color: colors.accent, fontFamily: fontFamily.paragraph}"
            />
          </div>
        </div>
      </section>

      <h2
        class="subtitle"
        :style="{ color: colors.accent, fontSize: fontSizes.subtitle + 'px', fontFamily: fontFamily.title }"
      >
        Configuración de Colores y Tipografía
      </h2>
      <section class="color-section">
        <div class="field" v-for="(colorValue, key) in colors" :key="key">
          <label
            class="label"
            :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px', fontFamily: fontFamily.paragraph   }"
          >
            {{ key.charAt(0).toUpperCase() + key.slice(1) }}
          </label>
          <div class="control">
            <input
              type="color"
              :value="colorValue"
              @input="updateColor(key, $event.target.value)"
            />
          </div>
        </div>
      </section>

      <section class="font-upload-section">
        <div class="field">
          <label
            class="label"
            :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px', fontFamily: fontFamily.paragraph  }"
          >
            Subir Tipografía para Títulos
          </label>
          <div class="control">
            <input
              type="file"
              accept=".ttf"
              @change="handleFontUpload('title')"
              :style="{ backgroundColor: colors.secondary, color: colors.accent, fontFamily: fontFamily.paragraph}"
            />
          </div>
        </div>
        <div class="field">
          <label
            class="label"
            :style="{ color: colors.accent, fontSize: fontSizes.paragraph + 'px', fontFamily: fontFamily.paragraph }"
          >
            Subir Tipografía para Textos
          </label>
          <div class="control">
            <input
              type="file"
              accept=".ttf"
              @change="handleFontUpload('paragraph')"
              :style="{ backgroundColor: colors.secondary, color: colors.accent, fontFamily: fontFamily.paragraph}"
            />
          </div>
        </div>
      </section>

      <div class="field">
        <div class="control">
          <button class="button is-primary" @click="saveSettings" :style="{ fontFamily: fontFamily.title }">
            Guardar Cambios
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useValoresStore } from "../store/useValoresStore.js";
import { useFontFamilyStore } from "@/store/FontFamilyStore.js";

const store = useValoresStore();
const FontFamilyStore = useFontFamilyStore();

const fontSizes = ref({ ...store.fontSizes });
const colors = computed(() => store.colors);
const fontFamily = computed(() => FontFamilyStore.fonts);

const updateFontSize = (key, value) => {
  fontSizes.value[key] = value;
  store.setFontSize(key, value);
};

const updateColor = (key, value) => {
  store.setColor(key, value);
};

const handleFontUpload = (key) => {
  const fileInput = event.target.files[0];
  if (!fileInput) return;

  const reader = new FileReader();
  reader.onload = () => {
    const fontDataUrl = reader.result;

    const fontName = `CustomFont_${key}`;
    const style = document.createElement("style");
    style.innerHTML = `
      @font-face {
        font-family: '${fontName}';
        src: url('${fontDataUrl}');
      }
    `;
    document.head.appendChild(style);

    // Actualiza el store con la nueva fuente
    FontFamilyStore.setFont(key, fontName);
  };

  reader.onerror = () => {
    console.error("Error al leer el archivo de fuente");
  };

  reader.readAsDataURL(fileInput);
};

const saveSettings = async () => {
  await store.guardarColores();
  console.log("Configuraciones guardadas");
};

onMounted(async () => {
  await store.getConfiguration();
});

</script>