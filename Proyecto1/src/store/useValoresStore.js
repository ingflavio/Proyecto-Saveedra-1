// store/prStore.js
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useValoresStore = defineStore('example', () => {
  // Colores reactivos
  const colors = ref({
    primary: '#48cae4',
    secondary: '#b3c4cc',
    accent: '#919da3',
  });

  // Tamaños de fuente reactivos
  const fontSizes = ref({
    title: 32,
    subtitle: 24,
    paragraph: 16,
  });

  // Funciones para actualizar colores y tamaños de fuente
  const setColor = (colorType, newValue) => {
    colors.value[colorType] = newValue;
  };

  const setFontSize = (fontType, newSize) => {
    fontSizes.value[fontType] = newSize;
  };

  return {
    colors,
    fontSizes,
    setColor,
    setFontSize,
  };
});