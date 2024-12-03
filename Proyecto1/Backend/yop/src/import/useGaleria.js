import { ref, computed, watch } from "vue";
import { usePolicyStore } from "@/store/usePolicyStore.js";

export function useGaleria() {
  const policyStore = usePolicyStore();
  const policyContent = ref(policyStore.privacyPolicy);

  const fileType = ref("privacy");
  const files = ref([]);
  const fileNames = ref([]);
  const previewUrls = ref([]);
  const videoUrl = ref(null);
  const subtitleUrl = ref(null);

  const acceptedTypes = computed(() => {
    if (fileType.value === "image") return "image/*";
    if (fileType.value === "audio") return "audio/*";
    if (fileType.value === "video") return "video/*";
    if (fileType.value === "pdf") return "application/pdf";
    return "*/*";
  });

  const updatePolicyContent = (newContent) => {
    policyStore.setPrivacyPolicy(newContent);
  };

  function handleFiles(event) {
    const selectedFiles = Array.from(event.target.files);

    const valid =
      (fileType.value === "image" && selectedFiles.length >= 4) ||
      (fileType.value === "audio" && selectedFiles.length >= 3) ||
      (fileType.value === "video" && selectedFiles.length === 1) ||
      (fileType.value === "pdf" && selectedFiles.length === 1);

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
      const reader = new FileReader();
      reader.onload = (e) => {
        previewUrls.value = [e.target.result];
      };
      reader.readAsDataURL(files.value[0]);
    } else if (fileType.value === "video") {
      const reader = new FileReader();
      reader.onload = (e) => {
        videoUrl.value = e.target.result;
      };
      reader.readAsDataURL(files.value[0]);
    } else {
      previewUrls.value = [];
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
        subtitleUrl.value = e.target.result;
      };
      reader.readAsDataURL(subtitleFile);
    }
  }

  function removeImage(url) {
    previewUrls.value = previewUrls.value.filter(
      (previewUrl) => previewUrl !== url
    );

    const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
    storedFiles[fileType.value] = storedFiles[fileType.value]?.filter(
      (storedUrl) => storedUrl !== url
    );
    localStorage.setItem("uploadedFiles", JSON.stringify(storedFiles));
  }

  function saveToLocalStorage() {
    // Verifica que haya al menos 4 imágenes en previewUrls
    if (fileType.value === "image" && previewUrls.value.length < 4) {
      alert("Debes tener al menos 4 imágenes en la vista previa para guardar.");
      return;
    }
  
    if (!files.value.length && !videoUrl.value) {
      alert("No hay archivos para guardar.");
      return;
    }
  
    const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
    const existingFiles = storedFiles[fileType.value] || [];
  
    if (fileType.value === "video" && videoUrl.value) {
      storedFiles.video = [videoUrl.value];
    } else if (fileType.value === "pdf") {
      storedFiles.pdf = [previewUrls.value[0]];
    } else {
      const newFiles = previewUrls.value.filter(
        (url) => !existingFiles.includes(url)
      );
      storedFiles[fileType.value] = [...existingFiles, ...newFiles];
    }
  
    localStorage.setItem("uploadedFiles", JSON.stringify(storedFiles));
    if (fileType.value === "video" && subtitleUrl.value) {
      localStorage.setItem("subtitle", subtitleUrl.value);
    }
  
    alert("Archivos guardados exitosamente.");
    updatePreview();
  }

  function updatePreview() {
    const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
    previewUrls.value = storedFiles[fileType.value] || [];
    if (fileType.value === "video") {
      videoUrl.value = storedFiles.video?.[0] || null;
      subtitleUrl.value = localStorage.getItem("subtitle") || null;
    }
  }

  watch(fileType, () => {
    updatePreview();
  });

  return {
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
  };
}
