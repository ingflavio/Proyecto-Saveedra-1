<template>
  <div class="galeria2">
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
          <option value="pdf">PDF</option>
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

        <!-- Botón de Guardar -->
        <button
          class="guardarB"
          @click="saveToLocalStorage"
          :style="{
            backgroundColor: store.colors.primary,
            color: store.colors.accent,
          }"
        >
          Guardar
        </button>
      </div>

      <div
        v-if="fileType !== 'privacy'"
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
        <div v-if="fileType === 'pdf'">
          <embed
            v-for="url in previewUrls"
            :src="url"
            :key="url"
            type="application/pdf"
            width="600"
            height="500"
          />
        </div>
        <div v-if="fileType === 'video'">
          <video
            v-for="url in previewUrls"
            :src="url"
            :key="url"
            controls
            width="200"
          >
            <track
              v-if="subtitleUrl"
              :src="subtitleUrl"
              kind="subtitles"
              srclang="es"
              label="Español"
              default
            />
          </video>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from "vue";
import { useValoresStore } from "../store/useValoresStore.js";
import QuillEditor from "../components/wysiwygeditor.vue";
import { usePolicyStore } from "../store/usePolicyStore.js";

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
const videoUrl = ref(null); // URL específica para videos
const subtitleUrl = ref(null); // URL del archivo de subtítulos

const colors = computed(() => store.colors);

const acceptedTypes = computed(() => {
  if (fileType.value === "image") return "image/*";
  if (fileType.value === "audio") return "audio/*";
  if (fileType.value === "video") return "video/*";
  if (fileType.value === "pdf") return "application/pdf"; // Permitir solo PDF
  return "*/*";
});

function handleFiles(event) {
  const selectedFiles = Array.from(event.target.files);

  // Validación para cada tipo de archivo
  const valid =
    (fileType.value === "image" && selectedFiles.length >= 4) ||
    (fileType.value === "audio" && selectedFiles.length >= 3) ||
    (fileType.value === "video" && selectedFiles.length === 1) ||
    (fileType.value === "pdf" && selectedFiles.length === 1); // Solo 1 archivo PDF

  if (!valid) {
    alert(
      `Debe seleccionar ${
        fileType.value === "image"
          ? "al menos 4 imágenes"
          : fileType.value === "audio"
          ? "al menos 3 audios"
          : fileType.value === "video"
          ? "un solo video"
          : "un solo archivo PDF"
      }.`
    );
    return;
  }

  files.value = selectedFiles;
  fileNames.value = files.value.map((file) => file.name);

  if (fileType.value === "pdf") {
    // Para PDF, solo tomamos el primer archivo y reemplazamos cualquier existente
    const reader = new FileReader();
    reader.onload = (e) => {
      previewUrls.value = [e.target.result]; // Reemplazamos el contenido anterior
    };
    reader.readAsDataURL(files.value[0]);
  } else if (fileType.value === "video") {
    // Para video, solo tomamos el primer archivo y generamos su URL
    const reader = new FileReader();
    reader.onload = (e) => {
      videoUrl.value = e.target.result; // Actualizamos `videoUrl` directamente
    };
    reader.readAsDataURL(files.value[0]);
  } else {
    // Para imágenes o audios, generamos vista previa
    previewUrls.value = []; // Limpiamos las URLs previas
    files.value.forEach((file) => {
      const reader = new FileReader();
      reader.onload = (e) => {
        previewUrls.value.push(e.target.result);
      };
      reader.readAsDataURL(file);
    });
  }
}

function handleSubtitle(event) {
  const subtitleFile = event.target.files[0];
  if (subtitleFile) {
    const reader = new FileReader();
    reader.onload = (e) => {
      subtitleUrl.value = e.target.result; // Actualiza la URL del subtítulo
    };
    reader.readAsDataURL(subtitleFile);
  }
}

function saveToLocalStorage() {
  if (!files.value.length && !videoUrl.value) {
    alert("No hay archivos para guardar.");
    return;
  }

  const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
  const existingFiles = storedFiles[fileType.value] || [];

  if (fileType.value === "video" && videoUrl.value) {
    storedFiles.video = [videoUrl.value]; // Guardamos la URL del video
  } else if (fileType.value === "pdf") {
    storedFiles.pdf = [previewUrls.value[0]]; // Guardamos solo el PDF más reciente
  } else {
    const newFiles = previewUrls.value.filter(
      (url) => !existingFiles.includes(url)
    );
    storedFiles[fileType.value] = [...existingFiles, ...newFiles];
  }

  localStorage.setItem("uploadedFiles", JSON.stringify(storedFiles));
  if (fileType.value === "video" && subtitleUrl.value) {
    localStorage.setItem("subtitle", subtitleUrl.value); // Guardamos subtítulos si existen
  }

  alert("Archivos guardados exitosamente.");
  updatePreview(); // Actualizamos la vista previa
}

function updatePreview() {
  const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
  previewUrls.value = storedFiles[fileType.value] || [];
  if (fileType.value === "video") {
    videoUrl.value = storedFiles.video?.[0] || null; // Cargamos el video guardado
    subtitleUrl.value = localStorage.getItem("subtitle") || null; // Cargamos subtítulos si existen
  }
}

watch(fileType, () => {
  updatePreview(); // Actualizamos la vista previa al cambiar de tipo
});
</script>



<style>
div.box {
  margin-top: 20px;
}
</style>
