import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';


export const useValoresStore = defineStore('example', () => {
  const colors = ref({
    primary: '#48cae4',
    secondary: '#b3c4cc',
    accent: 'black',
    button: '#28a745',
  });

  const fontSizes = ref({
    title: 32,
    subtitle: 24,
    paragraph: 16,
  });

  const getConfiguration = async () => {
    try {
      const token = localStorage.getItem('token');
      if (!token) throw new Error('Token no disponible');

      const response = await axios.get('http://localhost:8080/api/Colores/1', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const { primary_color, secondary, accent, button, titleSize, subtitleSize, paragraphSize, titleFontPath, paragraphFontPath } = response.data;
      colors.value = { primary: primary_color, secondary, accent, button };
      fontSizes.value = { title: titleSize, subtitle: subtitleSize, paragraph: paragraphSize };

      console.log('Configuración obtenida:', { colors: colors.value, fontSizes: fontSizes.value});
    } catch (error) {
      console.error('Error al obtener los colores:', error);
    }
  };

  const setColor = (colorType, newValue) => {
    colors.value[colorType] = newValue;
  };

  const setFontSize = (fontType, newSize) => {
    fontSizes.value[fontType] = newSize;
  };

  const setFontFamily = (fontType, newFont) => {
    fonts.value[fontType] = newFont;
  };

  const guardarColores = async () => {
    try {
      const token = localStorage.getItem('token');
      if (!token) throw new Error('Token no disponible');

      const colores = {
        primary_color: colors.value.primary,
        secondary: colors.value.secondary,
        accent: colors.value.accent,
        button: colors.value.button,
        titleSize: fontSizes.value.title,
        subtitleSize: fontSizes.value.subtitle,
        paragraphSize: fontSizes.value.paragraph,
      };

      const response = await axios.put('http://localhost:8080/api/Colores/1', colores, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      console.log('Colores actualizados:', response.data);
      toast.success('Colores guardados exitosamente');
    } catch (error) {
      console.error('Error al guardar los colores:', error);
      toast.error('Error al guardar los colores');
    }
  };

  // Subir las fuentes al backend con la ruta
  const uploadFont = async (key, file) => {
    try {
      const token = localStorage.getItem('token');
      if (!token) throw new Error('Token no disponible');

      // Crear un objeto FormData para enviar el archivo al backend
      const formData = new FormData();
      formData.append('file', file);

      // Hacer la solicitud para subir el archivo
      const responseUpload = await axios.post('http://localhost:8080/api/upload', formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'multipart/form-data',
        },
      });

      const fontPath = responseUpload.data.filePath;

      // Enviar la ruta al backend
      const response = await axios.post('http://localhost:8080/api/uploadFont', { key, fontPath }, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      console.log('Ruta de fuente subida:', response.data);
      toast.success('Fuente subida exitosamente');
    } catch (error) {
      console.error('Error al subir la fuente:', error);
      toast.error('Error al subir la fuente');
    }
  };

  getConfiguration();

  return {
    colors,
    fontSizes,
    setColor,
    setFontSize,
    setFontFamily,
    getConfiguration,
    guardarColores,
    uploadFont,
  };
});
