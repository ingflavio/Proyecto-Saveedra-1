<template>
  <div class="galeria">
    <!-- Video Section -->
    <div v-if="videoUrl" class="video-container">
      <video :src="videoUrl" controls width="500">
        <track
          v-if="subtitlesUrl"
          :src="subtitlesUrl"
          kind="subtitles"
          srclang="es"
          label="Español"
          default
        />
      </video>
    </div>
  </div>
  <div
    :style="{
      display: 'flex',
      alignItems: 'center',
      flexDirection: 'column',
      color: 'white',
    }"
  >
    <swiper
      :slidesPerView="3"
      :spaceBetween="30"
      :loop="true"
      :navigation="true"
      :modules="modules"
      class="mySwiper"
    >
      <swiper-slide
        v-for="(url, index) in previewUrls"
        :key="index"
        class="slide-card"
      >
        <div class="card">
          <div class="card-image">
            <figure class="image is-4by3">
              <img :src="url" alt="Image Preview" v-if="fileType === 'image'" />
            </figure>
          </div>
        </div>
      </swiper-slide>
    </swiper>

    <!-- Audio Section -->
    <div v-if="audioUrls.length" class="audio-container">
      <h3>Audios:</h3>
      <audio
        v-for="(url, index) in audioUrls"
        :key="index"
        :src="url"
        controls
      ></audio>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import "swiper/css/navigation";
import { Navigation } from "swiper/modules";
import { ref, onMounted } from "vue";
import {getStoredFiles, getSubtitle } from '../../Backend/yop/src/import/importFiles';

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    const previewUrls = ref([]);
    const audioUrls = ref([]);
    const videoUrl = ref(null);
    const subtitlesUrl = ref(null);
    const pdfUrl = ref(null);
    const fileType = ref("image");

    onMounted(() => {
      const storedFiles = getStoredFiles();

      if (storedFiles.image) {
        previewUrls.value = storedFiles.image;
        fileType.value = "image";
      }
      if (storedFiles.audio) {
        audioUrls.value = storedFiles.audio;
      }
      if (storedFiles.video) {
        videoUrl.value = storedFiles.video[0];
      }
      if (storedFiles.pdf) {
        pdfUrl.value = storedFiles.pdf[0];
      }

      const storedSubtitle = getSubtitle();
      if (storedSubtitle) {
        subtitlesUrl.value = storedSubtitle;
      }
    });

    return {
      previewUrls,
      audioUrls,
      videoUrl,
      subtitlesUrl,
      pdfUrl,
      fileType,
      modules: [Navigation],
    };
  },
};
</script>


<style scoped>
.mySwiper {
  display: flex;
  justify-content: center;
}

.swiper-slide {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 300px;
  background-color: transparent;
}

.video-container {
  margin-top: 20px;
}

.audio-container {
  margin-top: 20px;
}

.slide-card {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.card {
  width: 400px !important;
}

.card-image img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}
</style>
