<template>
  <footer :style="{ backgroundColor: store.colors.secondary }" class="footer">
    <div
      :style="{ color: store.colors.accent }"
      class="content has-text-centered"
    >
      <router-link
        :style="{
          color: store.colors.accent,
          fontSize: store.fontSizes.paragraph + 'px',
        }"
        to="/politicasdeprivacidad"
      >
        Políticas de Privacidad
      </router-link>
    </div>
    <div
      v-if="pdfUrl"
      :style="{ color: store.colors.accent }"
      class="content has-text-centered"
    >
      <a
        :href="pdfUrl"
        target="_blank"
        :style="{ 
          color: store.colors.accent,
          fontSize: store.fontSizes.paragraph + 'px',
          textDecoration: 'none'
        }"
        class="manual-link"
      >
        Manual de uso
      </a>
    </div>
    <div
      :style="{ color: store.colors.accent }"
      class="content has-text-centered"
    >
      <strong>Creado por:</strong> <span>Flavio Franchich</span>
    </div>
  </footer>
</template>

<script>
import { useValoresStore } from "../store/useValoresStore.js";
import { ref, onMounted } from "vue";

export default {
  setup() {
    const pdfUrl = ref(null);

    onMounted(() => {
      // Recuperar los archivos desde localStorage
      const storedFiles =
        JSON.parse(localStorage.getItem("uploadedFiles")) || {};

      if (storedFiles.pdf) {
        pdfUrl.value = storedFiles.pdf[0]; // Recuperar el archivo PDF
        console.log("PDF URL cargada:", pdfUrl.value);
      }
    });

    return {
      pdfUrl,
      store: useValoresStore(),
    };
  },
};
</script>

<style scoped>
.footer {
  color: black;
  padding: var(
    --bulma-footer-padding,
    20px
  );
}

.manual-link {
  text-decoration: none;
}
</style>