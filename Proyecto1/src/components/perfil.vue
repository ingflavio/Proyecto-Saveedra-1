<template>
  <div class="containerContent is-relative">
    <section class="section register-section">
      <div class="container">
        <div class="columns is-centered">
          <div class="column is-half">
            <div class="box">
              <h1 class="title has-text-centered">Perfil del Usuario</h1>
              <form @submit.prevent="updateProfile">
                <div class="field">
                  <label class="label">Nombre de Usuario</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.username"
                      readonly
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Correo Electrónico</label>
                  <div class="control">
                    <input
                      class="input"
                      type="email"
                      v-model="profile.correo"
                      readonly
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Género</label>
                  <div class="control controlgender">
                    <label class="radio">
                      <input type="radio" v-model="profile.gender" value="M" />
                      Masculino
                    </label>
                    <label class="radio ml-5">
                      <input type="radio" v-model="profile.gender" value="F" />
                      Femenino
                    </label>
                  </div>
                </div>

                <div class="field">
                  <label class="label">Teléfono</label>
                  <div class="control is-flex">
                    <div class="select">
                      <select v-model="profile.phonePrefix">
                        <option value="0414">0414</option>
                        <option value="0424">0424</option>
                        <option value="0412">0412</option>
                        <option value="0416">0416</option>
                        <option value="0426">0426</option>
                      </select>
                    </div>
                    <input
                      class="input"
                      type="text"
                      placeholder="Resto del numero"
                      v-model="profile.phoneNumber"
                      style="margin-left: 10px"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Celular</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.cell"
                      @input="validateNumber('cell')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Nombre</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.firstName"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Apellido</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.lastName"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Número</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.number"
                      @input="validateNumber('number')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Nombre Completo</label>
                  <div class="control">
                    <input class="input" type="text" v-model="profile.name" />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Ubicación</label>
                  <div class="control">
                    <div id="map" style="height: 400px"></div>
                  </div>
                </div>

                <div class="field">
                  <label class="label">Ciudad</label>
                  <div class="control">
                    <input class="input" type="text" v-model="profile.city" />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Estado</label>
                  <div class="control">
                    <input class="input" type="text" v-model="profile.state" />
                  </div>
                </div>

                <div class="field">
                  <label class="label">País</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.country"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Código Postal</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.postcode"
                      @input="validateNumber('postcode')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Latitud</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.latitude"
                      @input="validateNumber('latitude')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Longitud</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.longitude"
                      @input="validateNumber('longitude')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Offset</label>
                  <div class="control">
                    <input class="input" type="text" v-model="profile.offset" />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Descripción</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.description"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Fecha de Nacimiento</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.date"
                      placeholder="DD/MM/YYYY"
                      @input="validateDate('date')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Edad del Usuario</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.ageUser"
                      @input="validateNumber('ageUser')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Fecha de Registro</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.dateRegister"
                      placeholder="DD/MM/YYYY"
                      @input="validateDate('dateRegister')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Edad al Registrar</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.ageRegister"
                      @input="validateNumber('ageRegister')"
                    />
                  </div>
                </div>

                <div class="field is-grouped is-grouped-centered">
                  <div class="control">
                    <button class="button is-link" type="submit">
                      Actualizar
                    </button>
                  </div>
                </div>
              </form>
              <p v-if="message" :class="messageClass">{{ message }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import L from "leaflet";

const profile = ref({
  username: "",
  correo: "",
  permisos: new Set(),
  phone: "",
  cell: "",
  gender: "",
  firstName: "",
  lastName: "",
  number: "",
  name: "",
  city: "",
  state: "",
  country: "",
  postcode: "",
  latitude: "",
  longitude: "",
  offset: "",
  description: "",
  date: "",
  ageUser: "",
  dateRegister: "",
  ageRegister: "",
});
const message = ref("");
const messageClass = ref("");
const map = ref(null);

const getProfile = async () => {
  try {
    const token = localStorage.getItem("token");
    if (!token) {
      throw new Error("Token no disponible");
    }
    const response = await axios.get("http://localhost:8080/api/Perfil", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    profile.value = response.data;
    if (map.value) {
      const { latitude, longitude } = profile.value;
      map.value.setView([latitude, longitude], 13);
      L.marker([latitude, longitude]).addTo(map.value);
    }
  } catch (error) {
    console.error("Error al obtener el perfil:", error);
    message.value =
      "Error al obtener el perfil. Por favor, inténtalo de nuevo.";
    messageClass.value = "error-message";
  }
};

const updateProfile = async () => {
  try {
    const token = localStorage.getItem("token");
    const response = await axios.put(
      "http://localhost:8080/api/Perfil",
      profile.value,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    message.value = "Perfil actualizado correctamente";
    messageClass.value = "success-message";
  } catch (error) {
    console.error("Error al actualizar el perfil:", error);
    message.value =
      "Error al actualizar el perfil. Por favor, inténtalo de nuevo.";
    messageClass.value = "error-message";
  }
};

const validateNumber = (field) => {
  profile.value[field] = profile.value[field].replace(/\D/g, "");
};

const validateDate = (field) => {
  const datePattern = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/;
  if (!datePattern.test(profile.value[field])) {
    message.value = "Formato de fecha inválido. Use DD/MM/YYYY";
    messageClass.value = "error-message";
  } else {
    message.value = "";
    messageClass.value = "";
  }
};

const reverseGeocode = async (lat, lng) => {
  try {
    const response = await axios.get(
      `https://nominatim.openstreetmap.org/reverse`,
      {
        params: {
          lat,
          lon: lng,
          format: "json",
        },
      }
    );
    const address = response.data.address;
    profile.value.country = address.country || "";
    profile.value.city = address.city || address.town || address.village || "";
    profile.value.state = address.state || "";
    profile.value.postcode = address.postcode || "";
    profile.value.street = address.road || "";
    profile.value.description = `${profile.value.city}, ${profile.value.country}`;

    const timezoneResponse = await axios.get(`http://worldtimeapi.org/api/ip`);
    profile.value.offset = timezoneResponse.data.utc_offset;
  } catch (error) {
    console.error("Error al obtener la dirección o la zona horaria:", error);
  }
};

onMounted(() => {
  map.value = L.map("map").setView([10.4806, -66.9036], 13); // Coordenadas iniciales
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "© OpenStreetMap contributors",
  }).addTo(map.value);

  map.value.on("click", async (e) => {
    const { lat, lng } = e.latlng;
    profile.value.latitude = lat;
    profile.value.longitude = lng;
    L.marker([lat, lng]).addTo(map.value);
    await reverseGeocode(lat, lng);
  });

  getProfile();
});


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
  background-color: #919da3;
}
.title {
  margin-bottom: 20px;
}
.footer {
  padding: 1px !important;
  background-color: #b3c4cc;
  color: black;
}

.success-message {
  color: green;
  font-size: 1.5em;
  font-weight: bold;
  margin-top: 20px;
  text-align: center;
}
.error-message {
  color: red;
  font-size: 1.5em;
  font-weight: bold;
  margin-top: 20px;
  text-align: center;
}

.controlgender {
  display: flex;
  justify-content: center;
}

.radio {
  color: white;
}
</style>
