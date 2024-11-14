<template>
  <div id="app">
    <QuillEditor :content="policyContent" @contentUpdated="updatePolicyContent" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import QuillEditor from '../components/wysiwygeditor.vue';
import { usePolicyStore } from '../store/usePolicyStore';

// Importa el store de Pinia
const policyStore = usePolicyStore();

// Crea una propiedad reactiva para el contenido del editor
const policyContent = ref(policyStore.privacyPolicy);

// Función para actualizar el contenido en el store cuando se modifique en el editor
const updatePolicyContent = (newContent) => {
  console.log(newContent);  // Verifica si las etiquetas de alineación están presentes
  policyStore.setPrivacyPolicy(newContent);
};
</script>

<style scoped>
/* Estilos opcionales específicos para el componente */
</style>