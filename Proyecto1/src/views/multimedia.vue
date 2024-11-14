<template>
  <div class="galeria">
    <div class="galeria__Container">
      <div
        :style="{
          backgroundColor: store.colors.secondary,
          color: colors.accent,
        }"
        class="galeriaInput"
      >
        <label for="fileType">Tipo de Acción:</label>
        <select v-model="fileType" @change="resetInput">
          <option value="image">Imagen</option>
          <option value="audio">Audio</option>
          <option value="video">Video</option>
          <option value="privacy">Privacidad</option>
        </select>
        <QuillEditor
          v-if="fileType === 'privacy'"
          :content="policyContent"
          @contentUpdated="updatePolicyContent"
        />
        <input
          v-if="fileType !== 'privacy'"
          type="file"
          :accept="acceptedTypes"
          :multiple="fileType !== 'video'"
          @change="handleFiles"
        />
        <p v-if="fileType !== 'privacy'">Nombres de archivos subidos:</p>
        <br />
        <ul>
          <li v-for="file in fileNames" :key="file">{{ file }}</li>
        </ul>
      </div>
      <div v-if="fileType !== 'privacy'"
        :style="{
          backgroundColor: store.colors.secondary,
          color: colors.accent,
        }"
        class="galeriaPrevia"
      >
        <h3>Vista Previa</h3>
        <div v-if="fileType === 'image'">
          <img
            v-for="url in previewUrls"
            :src="url"
            :key="url"
            alt="preview"
            width="100"
          />
        </div>
        <div v-if="fileType === 'audio'">
          <audio
            v-for="url in previewUrls"
            :src="url"
            :key="url"
            controls
          ></audio>
        </div>
        <div v-if="fileType === 'video'">
          <video
            v-for="url in previewUrls"
            :src="url"
            :key="url"
            controls
            width="200"
          ></video>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from "vue";
import { useValoresStore } from "../store/useValoresStore.js";
import QuillEditor from '../components/wysiwygeditor.vue'
import { usePolicyStore } from '../store/usePolicyStore.js';

const store = useValoresStore();

// Importa el store de Pinia
const policyStore = usePolicyStore();

// Crea una propiedad reactiva para el contenido del editor
const policyContent = ref(policyStore.privacyPolicy);

// Función para actualizar el contenido en el store cuando se modifique en el editor
const updatePolicyContent = (newContent) => {
  console.log(newContent);  // Verifica si las etiquetas de alineación están presentes
  policyStore.setPrivacyPolicy(newContent);
};

const fileType = ref("image");
const files = ref([]);
const fileNames = ref([]);
const previewUrls = ref([]);
const videoUrl = ref(null);

const colors = computed(() => store.colors);

const acceptedTypes = computed(() => {
  if (fileType.value === "image") return "image/*";
  if (fileType.value === "audio") return "audio/*";
  if (fileType.value === "video") return "video/*";
  return "*/*";
});

function resetInput() {
  files.value = [];
  previewUrls.value = [];
  fileNames.value = [];
}

function handleFiles(event) {
  const selectedFiles = Array.from(event.target.files);

  const valid =
    (fileType.value === "image" && selectedFiles.length >= 4) ||
    (fileType.value === "audio" && selectedFiles.length >= 3) ||
    (fileType.value === "video" && selectedFiles.length === 1);

  if (!valid) {
    alert(
      `Debe seleccionar ${
        fileType.value === "image"
          ? "al menos 4 imágenes"
          : fileType.value === "audio"
          ? "al menos 3 audios"
          : "un solo video"
      }.`
    );
    return;
  }

  files.value = selectedFiles;
  previewUrls.value = [];
  fileNames.value = files.value.map((file) => file.name);

  files.value.forEach((file) => {
    const reader = new FileReader();
    reader.onload = (e) => {
      previewUrls.value.push(e.target.result);
      saveToLocalStorage();
    };
    reader.readAsDataURL(file);
  });

  if (fileType.value === "video") {
    videoUrl.value = previewUrls.value[0];
  }
}

function saveToLocalStorage() {
  const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
  storedFiles[fileType.value] = previewUrls.value;

  if (videoUrl.value) {
    storedFiles.video = [videoUrl.value];
  }

  localStorage.setItem("uploadedFiles", JSON.stringify(storedFiles));
}

watch(fileType, () => {
  const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
  previewUrls.value = storedFiles[fileType.value] || [];
});
</script>

<style>
div.box{
  margin-top: 20px;
}
</style>