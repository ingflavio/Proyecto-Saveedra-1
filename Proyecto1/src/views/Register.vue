<template>
  <div class="containerContent is-relative">

  <section class="section register-section">
    <div class="container">
      <div class="columns is-centered">
        <div class="column is-half">
          <div class="box">
            <h1 class="title has-text-centered">Registro</h1>
            <form @submit.prevent="register">
              <div class="field">
                <label class="label">Nombre de Usuario</label>
                <div class="control">
                  <input class="input" type="text" v-model="username" required />
                </div>
              </div>
              <div class="field">
                <label class="label">Correo Electrónico</label>
                <div class="control">
                  <input class="input" type="email" v-model="email" required />
                </div>
              </div>

              <div class="field">
                <label class="label">Contraseña</label>
                <div class="control">
                  <input class="input" type="password" v-model="password" required />
                </div>
              </div>

              <div class="field">
                <label class="label">Repetir Contraseña</label>
                <div class="control">
                  <input class="input" type="password" v-model="confirmPassword" required />
                </div>
              </div>

              <div class="field is-grouped is-grouped-centered">
                <div class="control">
                  <button class="button is-link" type="submit">Registrarse</button>
                </div>
              </div>
            </form>

    <section class="section register-section">
      <div class="box">
        <h1 class="title has-text-centered">Registro</h1>
        <form @submit.prevent="register">
          <div class="field">
            <label class="label">Nombre de Usuario</label>
            <div class="control">
              <input class="input" type="text" v-model="username" required />
            </div>

          </div>

          <div class="field">
            <label class="label">Correo Electrónico</label>
            <div class="control">
              <input class="input" type="email" v-model="email" required />
            </div>
          </div>

          <div class="field">
            <label class="label">Contraseña</label>
            <div class="control">
              <input
                class="input"
                type="password"
                v-model="password"
                required
              />
            </div>
          </div>

          <div class="field">
            <label class="label">Repetir Contraseña</label>
            <div class="control">
              <input
                class="input"
                type="password"
                v-model="confirmPassword"
                required
              />
            </div>
          </div>

          <div class="field is-grouped is-grouped-centered">
            <div class="control">
              <button class="button is-link" type="submit">Registrarse</button>
            </div>
          </div>
        </form>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref } from "vue";

const username = ref("");
const email = ref("");
const password = ref("");
const confirmPassword = ref("");

const register = () => {
  if (password.value !== confirmPassword.value) {
    alert("Las contraseñas no coinciden");
    return;
  }
  // Aquí puedes agregar la lógica para manejar el registro
  console.log("Usuario registrado:", {
    username: username.value,
    email: email.value,
  });
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
    flex-direction: row;
    justify-content: center;
    align-items: center;
}

.box {

  padding: 20px;
  position: relative;
  z-index: 1;
  background-color:  #919da3;

  background-color:
   #919da3;
   max-width: 600px; 
   width: 100%;

}

.title {
  margin-bottom: 20px;
}


.footer{
  padding: 1px !important; 

.footer {
  padding: 1px !important; /* Reduce el padding del footer */

  background-color: #b3c4cc;
  color: black;
}
</style>
