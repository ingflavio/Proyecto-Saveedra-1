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
        <label :style="{ fontFamily: fontFamily.paragraph }" for="fileType"
          >Tipo de Acción:</label
        >
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

        <p
          :style="{ fontFamily: fontFamily.paragraph }"
          v-if="fileType !== 'privacy'"
        >
          Nombres de archivos subidos:
        </p>
        <ul v-if="fileType !== 'privacy'">
          <li v-for="file in fileNames" :key="file">{{ file }}</li>
        </ul>

        <!-- Botón de Guardar -->
        <button
          class="guardarB"
          @click="saveToLocalStorage"
          :style="{
            backgroundColor: store.colors.primary,
            color: store.colors.accent,
            fontFamily: fontFamily.title,
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
        :class="{
          'flex-row': !previewUrls.length,
          'flex-col': previewUrls.length,
        }"
      >
        <h3 :style="{ fontFamily: fontFamily.paragraph, textAlign: center }">
          Vista Previa
        </h3>
        <div class="image-container" v-if="fileType == 'image'">
          <div
            v-for="url in previewUrls"
            :key="url"
            class="image-badges"
            style="position: relative; display: inline-block; margin: 5px"
          >
            <img :src="url" alt="preview" @load="getImageDimensions(url)" />
            <button @click="removeImage(url)" class="close-btn">X</button>

            <div v-if="imageDimensions[url]" class="image-dimensions">
              {{ imageDimensions[url].width }} x
              {{ imageDimensions[url].height }}
            </div>
          </div>
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
import { computed, ref } from "vue";
import QuillEditor from "../components/wysiwygeditor.vue";
import { useGaleria } from "../../Backend/yop/src/import/useGaleria.js";
import { useValoresStore } from "@/store/useValoresStore";
import { useFontFamilyStore } from "@/store/FontFamilyStore";

const store = useValoresStore();
const fontFamilyStore = useFontFamilyStore();

const colors = computed(() => store.colors);
const fontFamily = computed(() => fontFamilyStore.fonts);

const {
  policyContent,
  updatePolicyContent,
  fileType,
  files,
  fileNames,
  previewUrls,
  videoUrl,
  removeImage,
  subtitleUrl,
  acceptedTypes,
  handleFiles,
  handleSubtitle,
  saveToLocalStorage,
} = useGaleria();

async function enviarDatos() {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/RegisterMedia",
      {
        fileType: fileType.value,
        files: previewUrls.value,
        subtitle: fileType.value === "video" ? subtitleUrl.value : null,
      }
    );

    // Mensaje de éxito
    message.value = response.data.message || "Datos enviados correctamente";
    messageClass.value = "success-message";

    // Redirigir o hacer algo adicional después de 1.25 segundos
    setTimeout(() => {
      console.log("Redirigir o refrescar la vista.");
    }, 1250);
  } catch (error) {
    // Manejo de errores
    message.value = "Error al enviar los datos.";
    messageClass.value = "error-message";
    console.error(error);
  }
}

const imageDimensions = ref({});

function getImageDimensions(url) {
  const img = new Image();
  img.src = url;
  img.onload = () => {
    imageDimensions.value[url] = {
      width: img.naturalWidth,
      height: img.naturalHeight,
    };
  };
}
</script>

<style>
div.box {
  margin-top: 20px;
}
</style>
