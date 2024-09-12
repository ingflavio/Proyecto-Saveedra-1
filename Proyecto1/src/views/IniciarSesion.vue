<template>
    <div class="containerContent is-relative">
      <section class="section register-section">
        <div class="container">
          <div class="columns is-centered">
            <div class="column is-half">
              <div class="box">
                <h1 class="title has-text-centered">Iniciar Sesión</h1>
                <form @submit.prevent="login">
                  <div class="field">
                    <label class="label">Nombre de Usuario</label>
                    <div class="control">
                      <input class="input" type="text" v-model="username" required />
                    </div>
                  </div>
  
                  <div class="field">
                    <label class="label">Contraseña</label>
                    <div class="control">
                      <input class="input" type="password" v-model="password" required />
                    </div>
                  </div>
  
                  <div class="field is-grouped is-grouped-centered">
                    <div class="control">
                      <button class="button is-link" type="submit">Enviar</button>
                    </div>
                  </div>
                </form>
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
  
  const username = ref('');
  const password = ref('');
  
  const login = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/Login', {
      username: username.value,
      password: password.value,
    });
    console.log('Usuario autenticado:', response.data);
    localStorage.setItem('token', response.data.token);
  } catch (error) {
    console.error('Error al autenticar el usuario:', error);

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
    background-color:  #919da3; /* Fondo blanco semitransparente para que el banner sea visible */
  }
  .title {
    margin-bottom: 20px;
  }
  .footer {
    padding: 1px !important; /* Reduce el padding del footer */
    background-color: #b3c4cc;
    color: black;
  }
  </style>
  