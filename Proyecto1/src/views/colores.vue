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
              v-model="fontSizes.title"
              placeholder="Ejemplo: 32"
            />
          </div>
        </div>
        <div class="field">
          <label class="label">Tamaño de Subtítulos</label>
          <div class="control">
            <input
              class="input"
              type="number"
              v-model="fontSizes.subtitle"
              placeholder="Ejemplo: 24"
            />
          </div>
        </div>
        <div class="field">
          <label class="label">Tamaño de Párrafos</label>
          <div class="control">
            <input
              class="input"
              type="number"
              v-model="fontSizes.paragraph"
              placeholder="Ejemplo: 16"
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
            <input type="color" v-model="colors.primary" />
          </div>
        </div>
        <div class="field">
          <label class="label">Color Secundario</label>
          <div class="control">
            <input type="color" v-model="colors.secondary" />
          </div>
        </div>
        <div class="field">
          <label class="label">Color de Acento</label>
          <div class="control">
            <input type="color" v-model="colors.accent" />
          </div>
        </div>
        <button class="button is-link" @click="applyStyles">Aplicar Estilos</button>
      </section>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const fontSizes = ref({
    title: 32,
    subtitle: 24,
    paragraph: 16,
  });
  
  const colors = ref({
    primary: '#48cae4',
    secondary: '#b3c4cc',
    accent: '#919da3',
  });
  
  const handleFileUpload = (type, event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        console.log(`Archivo ${type} cargado:`, e.target.result);
        // Aquí puedes guardar el archivo o aplicar la tipografía
      };
      reader.readAsDataURL(file);
    }
  };
  
  const applyStyles = () => {
    document.documentElement.style.setProperty('--font-size-title', `${fontSizes.value.title}px`);
    document.documentElement.style.setProperty('--font-size-subtitle', `${fontSizes.value.subtitle}px`);
    document.documentElement.style.setProperty('--font-size-paragraph', `${fontSizes.value.paragraph}px`);
    
    document.documentElement.style.setProperty('--color-primary', colors.value.primary);
    document.documentElement.style.setProperty('--color-secondary', colors.value.secondary);
    document.documentElement.style.setProperty('--color-accent', colors.value.accent);


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
  