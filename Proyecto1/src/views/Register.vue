<template>
  <div class="containerContent is-relative">
    <section class="section register-section">
      <div class="container">
        <div class="columns is-centered">
          <div class="column is-half">
            <div :style="{ backgroundColor: store.colors.secondary }" class="box">
              <h1 :style="{ color: store.colors.accent, fontSize: store.fontSizes.title + 'px' }" class="title has-text-centered">Registro</h1>
              <form @submit.prevent="register">
                <div class="field">
                  <label :style="{ color: store.colors.accent, fontSize: store.fontSizes.subtitle + 'px' }" class="label">Nombre de Usuario</label>
                  <div class="control">
                    <input class="input" type="text" v-model="username" required />
                  </div>
                </div>

                <div class="field">
                  <label :style="{ color: store.colors.accent, fontSize: store.fontSizes.subtitle + 'px' }" class="label">Correo Electrónico</label>
                  <div class="control">
                    <input class="input" type="email" v-model="email" required />
                  </div>
                </div>

                <div class="field">
                  <label :style="{ color: store.colors.accent, fontSize: store.fontSizes.subtitle + 'px' }" class="label">Contraseña</label>
                  <div class="control">
                    <input class="input" type="password" v-model="password" required />
                  </div>
                </div>

                <div class="field">
                  <label :style="{ color: store.colors.accent, fontSize: store.fontSizes.subtitle + 'px' }" class="label">Repetir Contraseña</label>
                  <div class="control">
                    <input class="input" type="password" v-model="confirmPassword" required />
                  </div>
                </div>

                <div class="field is-grouped is-grouped-centered">
                  <div class="control">
                    <button :style="{ backgroundColor: store.colors.primary, color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" class="button is-link" type="submit">Registrarse</button>
                  </div>
                </div>
              </form>
              <p v-if="message" :class="messageClass" :style="{ color: messageClass === 'success-message' ? 'green' : 'red', fontSize: store.fontSizes.paragraph + 'px' }">{{ message }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useValoresStore } from '../store/useValoresStore.js';

const username = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const message = ref('');
const messageClass = ref('');
const router = useRouter();
const store = useValoresStore();

const register = async () => {
  if (password.value !== confirmPassword.value) {
    message.value = 'Las contraseñas no coinciden';
    messageClass.value = 'error-message';
    return;
  }

  try {
    const response = await axios.post('http://localhost:8080/api/Register', {
      username: username.value,
      correo: email.value,
      password: password.value,
    });
    message.value = response.data;
    messageClass.value = 'success-message';
    setTimeout(() => {
      router.push('/login');
    }, 1250);
  } catch (error) {
    message.value = 'Error en el registro';
    messageClass.value = 'error-message';
  }
};
</script>

<style scoped>
.register-section {
  position: relative;
  padding-top: 40px;
  min-height: calc(100vh - 90px - 30px);
  padding-bottom: 40px;
  background-image: linear-gradient(
      to bottom,
      rgba(46, 49, 53, 0.212),
      rgba(0, 0, 0, 0.87)
    ),
    url("../../public/banner.jpg");
  background-size: cover;
  background-position: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}
.box {
  padding: 20px;
  position: absolute;
  z-index: 1;
}
.success-message, .error-message {
  font-size: 1.5em;
  font-weight: bold;
  margin-top: 20px;
  text-align: center;
}
</style>
