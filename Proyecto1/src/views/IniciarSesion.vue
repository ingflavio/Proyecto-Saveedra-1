<template>
  <div class="containerContent is-relative">
    <section class="section register-section">
      <div class="container">
        <div class="columns is-centered">
          <div class="column is-half">
            <div :style="{ backgroundColor: store.colors.secondary }" class="box">
              <h1 :style="{ color: store.colors.accent, fontSize: store.fontSizes.title + 'px' }" class="title has-text-centered">Iniciar Sesión</h1>
              <form @submit.prevent="login">
                <div class="field">
                  <label :style="{ color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" class="label">Nombre de Usuario</label>
                  <div class="control">
                    <input class="input" type="text" v-model="username" required />
                  </div>
                </div>

                <div class="field">
                  <label :style="{ color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" class="label">Contraseña</label>
                  <div class="control">
                    <input class="input" type="password" v-model="password" required />
                  </div>
                </div>

                <div class="field is-grouped is-grouped-centered">
                  <div class="control">
                    <button :style="{ backgroundColor: store.colors.button, color: store.colors.accent, fontSize: store.fontSizes.paragraph + 'px' }" class="button" type="submit">Enviar</button>
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
const password = ref('');
const message = ref('');
const messageClass = ref('');
const router = useRouter();
const store = useValoresStore();

const login = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/Login', {
      username: username.value,
      password: password.value,
    });
    message.value = 'Usuario autenticado';
    messageClass.value = 'success-message';
    localStorage.setItem('token', response.data.token);
    setTimeout(() => {
      router.push('/').then(() => {
        window.location.reload();
      });
    }, 1250);
  } catch (error) {
    message.value = 'Error en el inicio de sesión';
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
  position: relative;
  z-index: 1;
}
.success-message, .error-message {
  font-size: 1.5em;
  font-weight: bold;
  margin-top: 20px;
  text-align: center;
}
</style>
