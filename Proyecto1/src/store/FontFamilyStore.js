import { defineStore } from "pinia";

export const useFontFamilyStore = defineStore("FontFamilyStore", {
  state: () => ({
    fonts: {
      title: "Arial, sans-serif",
      paragraph: "Roboto, sans-serif",
    },
  }),
  actions: {
    setFont(key, fontFamily) {
      this.fonts[key] = fontFamily;
    },
    resetFonts() {
      this.fonts = {
        title: "Arial, sans-serif",
        paragraph: "Roboto, sans-serif",
      };
    },
  },
  persist: true,
});