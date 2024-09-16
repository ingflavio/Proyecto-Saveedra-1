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
                  <label class="label">Telefono Celular</label>
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
                      placeholder="Resto del número"
                      v-model="profile.phoneNumber"
                      @input="limitPhoneNumber"
                      style="margin-left: 10px"
                    />
                  </div>
                </div>


                <div class="field">
                  <label class="label">Telefono Fijo</label>
                  <div class="control is-flex">
                    <div class="select">
                      <select v-model="profile.phonePrefixFijo">
                        <option value="0212">0212</option>
                        <option value="0241">0241</option>
                        <option value="0243">0243</option>
                        <option value="0261">0261</option>
                        <option value="0281">0281</option>
                        <option value="0283">0283</option>
                        <option value="0284">0284</option>
                        <option value="0285">0285</option>
                        <option value="0286">0286</option>
                        <option value="0287">0287</option>
                        <option value="0288">0288</option>
                        <option value="0289">0289</option>
                        <option value="0291">0291</option>
                        <option value="0293">0293</option>   

                        <option value="0294">0294</option>
                        <option value="0295">0295</option>
                        <option value="0296">0296</option>
                        <option value="0297">0297</option>
                        <option value="0412">0412</option>
                        <option value="0414">0414</option>
                        <option value="0416">0416</option>
                        <option value="0424">0424</option>
                        <option value="0426">0426</option>
                        </select>   

                    </div>
                    <input
                      class="input"
                      type="text"
                      placeholder="Resto del número"
                      v-model="profile.number"
                      @input="limitPhoneNumberFijo"
                      style="margin-left: 10px"
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
                  <label class="label">Número De Cedula</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.cedula"
                      @input="validateNumber('number')"
                    />
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
                      readonly
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
                  <label class="label">Foto de Perfil</label>
                  <div class="control">
                    <input
                      class="input"
                      type="file"
                      @change="handleFileUpload"
                    />
                  </div>
                  <div v-if="profile.profilePicture" class="preview">
                    <img :src="profile.profilePicture" alt="Vista previa de la foto" />
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
import { ref, onMounted ,onBeforeUnmount} from "vue";
import axios from "axios";
import L from "leaflet";


const actionTimes = ref({
  keypress: 0.2, // tiempo en segundos
  point: 1.1,
  handMove: 0.4,
  think: 1.2,
  mouseClick: 0.1,
  datePicker: 6.81,
  scroll: 3.96,
  draw: 1.2,
  systemResponse: 0, // Este será añadido dinámicamente
});

const actionLog = ref([]); // Para registrar las acciones
const startTime = ref(null); // Para registrar el tiempo de inicio

// Función para registrar una acción
const logAction = (actionType) => {
  const timestamp = new Date().getTime();
  actionLog.value.push({ actionType, timestamp });
};

// Función para calcular el tiempo total
const calculateTotalTime = () => {
  if (actionLog.value.length === 0 || !startTime.value) return 0;

  let totalTime = 0;
  for (let i = 0; i < actionLog.value.length - 1; i++) {
    const action = actionLog.value[i];
    const nextAction = actionLog.value[i + 1];
    const duration = (nextAction.timestamp - action.timestamp) / 1000; // Tiempo en segundos

    totalTime += actionTimes.value[action.actionType] || 0;
  }

  // Agregar el tiempo del último evento hasta el final del formulario
  if (actionLog.value.length > 0) {
    const lastAction = actionLog.value[actionLog.value.length - 1];
    const now = new Date().getTime();
    const duration = (now - lastAction.timestamp) / 1000; // Tiempo en segundos

    totalTime += actionTimes.value[lastAction.actionType] || 0;
  }

  // Agregar el tiempo desde el inicio del formulario hasta el final
  if (startTime.value) {
    const now = new Date().getTime();
    totalTime += (now - startTime.value) / 1000; // Tiempo en segundos
  }

  return totalTime;
};

// Event listeners para capturar las acciones
onMounted(() => {
  startTime.value = new Date().getTime(); // Registrar el tiempo de inicio
  document.addEventListener('click', onMouseClick);
  // Puedes agregar más listeners para otros tipos de acciones
});

onBeforeUnmount(() => {
  document.removeEventListener('click', onMouseClick);
  // Elimina otros listeners aquí
});

// Ejemplo de cómo puedes registrar el tiempo de clic del ratón
const onMouseClick = () => {
  logAction('mouseClick');
};





const profile = ref({
  
  username: "",
  correo: "",
  phonePrefixFijo: "0241",
  phonePrefix: "0414",
  phoneNumber: "",  // Número sin prefijo
  number: "",

  cedula: "",
  gender: "",
  firstName: "",
  lastName: "",
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
  profilePicture: ""
});

const message = ref("");
const messageClass = ref("");
const map = ref(null);

const limitPhoneNumber = (event) => {
  const value = event.target.value;
  if (value.length > 7) {
    profile.value.phoneNumber = value.slice(0, 7);
  } else {
    profile.value.phoneNumber = value;
  }
};

const limitPhoneNumberFijo = (event) => {
  const value = event.target.value;
  if (value.length > 7) {
    profile.value.number = value.slice(0, 7);
  } else {
    profile.value.number = value;
  }
};


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
    profile.value.dateRegister = response.data.usuarioRegistro; // Asigna el valor aquí
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
  const estimatedTime = calculateTotalTime();
  console.log('Tiempo estimado para completar el formulario:', estimatedTime, 'segundos');

  try {
    const token = localStorage.getItem("token");
    const formData = new FormData();

    // Concatenate phone prefix and number
    profile.value.phone = `${profile.value.phonePrefix}${profile.value.phoneNumber}`;
    profile.value.number = `${profile.value.phonePrefixFijo}${profile.value.number}`;

    for (const key in profile.value) {
      if (profile.value[key] instanceof Set) {
        formData.append(key, JSON.stringify([...profile.value[key]]));
      } else {
        formData.append(key, profile.value[key]);
      }
    }
    if (profile.value.profilePicture) {
      formData.append("profilePicture", profile.value.profilePicture);
    }
    const response = await axios.put(
      "http://localhost:8080/api/Perfil",
      formData,
      {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "multipart/form-data",
        },
      }
    );

    // Mostrar el tiempo estimado en el mensaje de éxito
    message.value = `Perfil actualizado correctamente. Tiempo estimado para completar el formulario: ${estimatedTime.toFixed(2)} segundos.`;
    messageClass.value = "success-message";
  } catch (error) {
    console.error("Error al actualizar el perfil:", error);
    message.value =
      "Error al actualizar el perfil. Por favor, inténtalo de nuevo.";
    messageClass.value = "error-message";
  }
};

const validatePhoneNumber = () => {
  if (profile.value.phoneNumber.length !== 7) {
    message.value = "El número de teléfono debe tener exactamente 7 caracteres.";
    messageClass.value = "error-message";
    return false; // Indica que la validación ha fallado
  }
  message.value = ""; // Limpia el mensaje de error si la validación es exitosa
  messageClass.value = "";
  return true; // Indica que la validación ha pasado
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
    if (field === 'date') {
      profile.value.ageUser = calculateAge(profile.value[field]);
    }
  }
};

const calculateAge = (birthDate) => {
  const today = new Date();
  const birthDateParts = birthDate.split('/');
  const birthDay = parseInt(birthDateParts[0], 10);
  const birthMonth = parseInt(birthDateParts[1], 10) - 1; // Los meses en JavaScript son 0-11
  const birthYear = parseInt(birthDateParts[2], 10);

  const birthDateObj = new Date(birthYear, birthMonth, birthDay);
  let age = today.getFullYear() - birthDateObj.getFullYear();
  const monthDifference = today.getMonth() - birthDateObj.getMonth();

  if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < birthDateObj.getDate())) {
    age--;
  }

  return age;
};

const handleFileUpload = (event) => {
  const file = event.target.files[0];
  const validExtensions = ['image/jpeg', 'image/png', 'image/jpg'];

  if (file && validExtensions.includes(file.type)) {
    const reader = new FileReader();
    reader.onload = (e) => {
      profile.value.profilePicture = e.target.result;
    };
    reader.readAsDataURL(file);
    message.value = "";
    messageClass.value = "";
  } else {
    message.value = "Formato de archivo no válido. Solo se permiten archivos .jpg, .jpeg, y .png.";
    messageClass.value = "error-message";
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


.preview img {
  

  max-width: 150px; /* Ancho máximo */
  max-height: 150px; /* Alto máximo */
  width: auto;
  height: auto;
  margin-top: 10px;

}
</style>
