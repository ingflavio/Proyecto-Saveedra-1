<template>
  <div class="containerContent is-relative">
    <div class="banner"></div>
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
        <swiper-slide
          ><div class="card">
            <div class="card-image">
              <figure class="image">
                <img src="../../public/card1.jpg" alt="Placeholder image" />
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-left"></div>
                <div class="media-content">
                  <p class="title is-4">Miss Fortune</p>
                </div>
              </div>
            </div>
          </div></swiper-slide
        >
        <swiper-slide
          ><div class="card">
            <div class="card-image">
              <figure class="image">
                <img src="../../public/card1.jpg" alt="Placeholder image" />
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-left"></div>
                <div class="media-content">
                  <p class="title is-4">Miss Fortune</p>
                </div>
              </div>
            </div>
          </div></swiper-slide
        >
        <swiper-slide
          ><div class="card">
            <div class="card-image">
              <figure class="image">
                <img src="../../public/card1.jpg" alt="Placeholder image" />
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-left"></div>
                <div class="media-content">
                  <p class="title is-4">Miss Fortune</p>
                </div>
              </div>
            </div>
          </div></swiper-slide
        >
        <swiper-slide
          ><div class="card">
            <div class="card-image">
              <figure class="image">
                <img src="../../public/card1.jpg" alt="Placeholder image" />
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-left"></div>
                <div class="media-content">
                  <p class="title is-4">Miss Fortune</p>
                </div>
              </div>
            </div>
          </div></swiper-slide
        >
        <swiper-slide
          ><div class="card">
            <div class="card-image">
              <figure class="image">
                <img src="../../public/card1.jpg" alt="Placeholder image" />
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-left"></div>
                <div class="media-content">
                  <p class="title is-4">Miss Fortune</p>
                </div>
              </div>
            </div>
          </div></swiper-slide
        >
        <swiper-slide
          ><div class="card">
            <div class="card-image">
              <figure class="image">
                <img src="../../public/card1.jpg" alt="Placeholder image" />
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-left"></div>
                <div class="media-content">
                  <p class="title is-4">Miss Fortune</p>
                </div>
              </div>
            </div>
          </div></swiper-slide
        >
      </swiper>
    </div>
  </div>
</template>

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

.sliderIndex{
    padding: 50px;
}
</style>

<script>
import { Swiper, SwiperSlide } from "swiper/vue";

import "swiper/css";

import "swiper/css/free-mode";
import "swiper/css/pagination";

import { FreeMode, Pagination } from "swiper/modules";

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    return {
      modules: [FreeMode, Pagination],
    };
  },
};
</script>
