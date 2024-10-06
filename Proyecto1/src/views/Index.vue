<template>
  <div class="containerContent is-relative">
    <div
      class="banner"
      :style="{ backgroundColor: store.colors.secondary }"
    ></div>
    <div class="sliderIndex">
      <swiper
        :slidesPerView="3"
        :spaceBetween="100"
        :freeMode="true"
        :pagination="{
          clickable: true,
        }"
        :modules="modules"
        class="mySwiper"
      >
        <swiper-slide v-for="(item, index) in slides" :key="index">
          <div
            :style="{ color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }"
            class="slide-content"
          >
            {{ item.title }}
          </div>
        </swiper-slide>
      </swiper>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "swiper/vue";
import { FreeMode, Pagination } from "swiper/modules";

import "swiper/css";
import "swiper/css/free-mode";
import "swiper/css/pagination";

import { useValoresStore } from '../store/useValoresStore.js';

export default {
  setup() {
    const store = useValoresStore();
    const slides = [

    ];
    
    return {
      modules: [FreeMode, Pagination],
      slides,
      store, 
    };
  },
};
</script>

<style>
.banner {
  width: 100%;
  min-height: 600px;
  background-image: linear-gradient(
      to bottom,
      rgba(46, 49, 53, 0.212),
      rgba(0, 0, 0, 0.87)
    ),
    url("../../public/banner.jpg");
  background-size: cover;
  background-position: center;
}

.sliderIndex {
  padding: 50px;
}

.slide-content {
  padding: 20px;
}
</style>
