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
        <select v-model="fileType">
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

        <!-- Input para subtítulos -->
        <input
          v-if="fileType === 'video'"
          type="file"
          accept=".vtt"
          @change="handleSubtitle"
          placeholder="Subtítulos opcionales"
        />

        <p v-if="fileType !== 'privacy'">Nombres de archivos subidos:</p>
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
          >
            <!-- Inserta el track para subtítulos si está disponible -->
            <track v-if="subtitleUrl" :src="subtitleUrl" kind="subtitles" srclang="es" label="Español" default />
          </video>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from "vue";
import { useValoresStore } from "../store/useValoresStore.js";
import QuillEditor from '../components/wysiwygeditor.vue';
import { usePolicyStore } from '../store/usePolicyStore.js';

const store = useValoresStore();
const policyStore = usePolicyStore();
const policyContent = ref(policyStore.privacyPolicy);
const updatePolicyContent = (newContent) => {
  policyStore.setPrivacyPolicy(newContent);
};

const fileType = ref("image");
const files = ref([]);
const fileNames = ref([]);
const previewUrls = ref([]);
const videoUrl = ref(null);
const subtitleUrl = ref(null); // URL del archivo de subtítulos

const colors = computed(() => store.colors);

const acceptedTypes = computed(() => {
  if (fileType.value === "image") return "image/*";
  if (fileType.value === "audio") return "audio/*";
  if (fileType.value === "video") return "video/*";
  return "*/*";
});

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

function handleSubtitle(event) {
  const subtitleFile = event.target.files[0];
  if (subtitleFile) {
    const reader = new FileReader();
    reader.onload = (e) => {
      subtitleUrl.value = e.target.result;
      localStorage.setItem("subtitle", subtitleUrl.value);
    };
    reader.readAsDataURL(subtitleFile);
  }
}

function saveToLocalStorage() {
  const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
  const existingFiles = storedFiles[fileType.value] || [];
  const newFiles = previewUrls.value.filter(url => !existingFiles.includes(url));
  storedFiles[fileType.value] = [...existingFiles, ...newFiles];

  if (videoUrl.value) {
    storedFiles.video = [videoUrl.value];
  }
  localStorage.setItem("uploadedFiles", JSON.stringify(storedFiles));
}

watch(fileType, () => {
  const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
  previewUrls.value = storedFiles[fileType.value] || [];
  subtitleUrl.value = localStorage.getItem("subtitle"); // Carga los subtítulos guardados
});
</script>


<style>
div.box{
  margin-top: 20px;
}
</style>
