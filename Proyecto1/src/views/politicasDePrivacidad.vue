<template>
  <div class="colores-container">
    <div class="box" :style="{ backgroundColor: colors.secondary, color: colors.accent }">
      <div class="privacy-policy-text">
        <p v-html="privacyPolicy"></p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed } from "vue";
import { useValoresStore } from "../store/useValoresStore";
import { usePolicyStore } from "../store/usePolicyStore";

export default {
  setup() {
    // Inicializa los stores
    const store = useValoresStore();
    const fontSizes = ref({ ...store.fontSizes });
    const colors = computed(() => store.colors);

    const policyStore = usePolicyStore();
    // Obtiene el texto de la política de privacidad
    const privacyPolicy = policyStore.privacyPolicy;

    return {
      colors,
      fontSizes,
      privacyPolicy,
    };
  },
};
</script>


