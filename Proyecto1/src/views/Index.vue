<template>
  <div class="galeria" :style="{ display: 'flex', alignItems: 'center', flexDirection: 'column', color: 'white' }">
    <!-- Swiper for Images -->
    <swiper class="mySwiper" :slides-per-view="1" space-between="10">
      <swiper-slide v-for="(url, index) in previewUrls" :key="index">
        <img :src="url" alt="Image Preview" class="slide-content" v-if="fileType === 'image'" />
      </swiper-slide>
    </swiper>

    <!-- Video Section -->
    <div v-if="videoUrl" class="video-container">
      <h3>Video:</h3>
      <video :src="videoUrl" controls width="500"></video>
    </div>

    <!-- Audio Section -->
    <div v-if="audioUrls.length" class="audio-container">
      <h3 >Audios:</h3>
      <audio v-for="(url, index) in audioUrls" :key="index" :src="url" controls></audio>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import { ref, onMounted } from 'vue';

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    const previewUrls = ref([]);
    const audioUrls = ref([]);
    const videoUrl = ref(null);
    const fileType = ref("image");

    onMounted(() => {
      const storedFiles = JSON.parse(localStorage.getItem("uploadedFiles")) || {};
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
    });

    return {
      previewUrls,
      audioUrls,
      videoUrl,
      fileType,
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
}

.slide-content {
  max-width: 100%;
  max-height: 300px;
  width: auto;
  height: 100%;
  object-fit: contain;
}

.video-container {
  margin-top: 20px;
}

.audio-container {
  margin-top: 20px;
}
</style>
