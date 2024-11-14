<template>
    <div class="box" :style="{ backgroundColor: 'white' }">
      <h2
        class="subtitle"
        :style="{ color: colors.accent, fontSize: fontSizes.subtitle + 'px' }"
      >
        Configuración de Colores y Tipografía
      </h2>
      <div ref="editorContainer" class="quill-editor"></div>
    </div>
    <div></div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useValoresStore } from "../store/useValoresStore.js";

const store = useValoresStore();

// Copias reactivas de los valores del store
const fontSizes = ref({ ...store.fontSizes });
const colors = computed(() => store.colors);

const updateColor = (key, value) => {
  store.setColor(key, value); // Actualiza el store
};
</script>

<script>
import Quill from "quill";
import "quill/dist/quill.snow.css";

export default {
  name: "QuillEditor",
  props: {
    content: String,
  },
  emits: ["contentUpdated"],
  mounted() {
    this.editor = new Quill(this.$refs.editorContainer, {
      theme: "snow",
      modules: {
        toolbar: [
          [{ header: [1, 2, false] }],
          [{ font: [] }],
          [{ list: "ordered" }, { list: "bullet" }],
          ["bold", "italic", "underline", "strike"],
          ["link", "image"],
        ],
      },
    });

    // Alternativamente, usar la siguiente línea para garantizar que las listas ordenadas se carguen correctamente:
    this.editor.clipboard.dangerouslyPasteHTML(this.content);

    // Detectar cambios en el contenido del editor
    this.editor.on("text-change", () => {
      const content = this.editor.root.innerHTML; // Asegúrate de que el contenido se captura con las etiquetas HTML
      this.$emit("contentUpdated", content); // Emitir el contenido con las etiquetas de alineación
    });
  },
};
</script>

<style scoped>
.quill-editor {
  height: 300px;
  color: black;
}
</style>
