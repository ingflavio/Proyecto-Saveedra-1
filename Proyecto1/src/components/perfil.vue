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
                      @focus="onFieldFocus('username')"
                      @input="onFieldChange('username')"
                      @click="onFieldClick('username')"
                      @keydown="onKeyDown('username')"
                  
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
                      @focus="onFieldFocus('correo')"
                      @input="onFieldChange('correo')"
                      @click="onFieldClick('correo')"
                      @keydown="onKeyDown('username')"
                  
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Género</label>
                  <div class="control controlgender">
                    <label class="radio">
                      <input type="radio" v-model="profile.gender" value="M"
                      @click="onFieldClick('gender')" />
                      

                      Masculino
                    </label>
                    <label class="radio ml-5">
                      <input type="radio" v-model="profile.gender" value="F" 
                      @click="onFieldClick('gender')"
                      />
                      Femenino
                    </label>
                  </div>
                </div>

                <div class="field">
                  <label class="label">Telefono Celular</label>
                  <div class="control is-flex">
                    <div class="select">
                      <select v-model="profile.phonePrefix"
                      @focus="onFieldFocus('phoneNumber')" 
                      @click="onFieldClick('phoneNumber')"
                      >
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
                
                      @focus="onFieldFocus('phoneNumber')"
                      @input="handleNumberInput($event, 'phoneNumber')"
                      @click="onFieldClick('phoneNumber')"
                      @keydown="onKeyDown('phoneNumber')"
                      style="margin-left: 10px"
                    />
                  </div>
                </div>


                <div class="field">
                  <label class="label">Telefono Fijo</label>
                  <div class="control is-flex">
                    <div class="select">
                      <select v-model="profile.phonePrefixFijo"
                      @focus="onFieldFocus('number')" 
                      @click="onFieldClick('number')">
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
                   
                        </select>   

                    </div>
                    <input
                      class="input"
                      type="text"
                      placeholder="Resto del número"
                      v-model="profile.number"
                      @focus="onFieldFocus('number')"
                      @input="handleNumberInput($event, 'number')"
                      @click="onFieldClick('number')"
                      @keydown="onKeyDown('number')"
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
                      @focus="onFieldFocus('firstName')"
                      @input="onFieldChange('firstName')"
                      @click="onFieldClick('firstName')"
                      @keydown="onKeyDown('firstName')"
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
                      @focus="onFieldFocus('lastName')"
                      @input="onFieldChange('lastName')"
                      @click="onFieldClick('lastName')"
                      @keydown="onKeyDown('lastName')"
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
                      @input="handleNumber($event, 'cedula')"
                      @focus="onFieldFocus('cedula')"
                      @click="onFieldClick('cedula')"
                      @keydown="onKeyDown('cedula')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Ubicación</label>
                  <div class="control">
                    <div id="map"

                      @focus="onFieldFocus('ubicacion')"
                      @input="onFieldChange('ubicacion')"
                      @click="onFieldClick('ubicacion')"
                      @keydown="onKeyDown('ubicacion')"
              
                     style="height: 400px"></div>
                  </div>
                </div>

                <div class="field">
                  <label class="label">Ciudad</label>
                  <div class="control">
                    <input class="input" type="text" v-model="profile.city" 
                    @focus="onFieldFocus('city')"
                    @input="onFieldChange('city')"
                    @click="onFieldClick('city')"
                    @keydown="onKeyDown('city')"
                    
                    
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Estado</label>
                  <div class="control">
                    <input class="input" type="text" v-model="profile.state"
                    @focus="onFieldFocus('state')"
                    @input="onFieldChange('state')"
                    @click="onFieldClick('state')"
                    @keydown="onKeyDown('state')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">País</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.country"
                      @focus="onFieldFocus('country')"
                    @input="onFieldChange('country')"
                    @click="onFieldClick('country')"
                    @keydown="onKeyDown('country')"
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
                      @input="handleNumber($event, 'postcode')"
                      @focus="onFieldFocus('postcode')"
                      @click="onFieldClick('postcode')"
                      @keydown="onKeyDown('postcode')"
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
                      @input="handleNumber($event, 'latitude')"
                      @focus="onFieldFocus('latitude')"
                      @click="onFieldClick('latitude')"
                      @keydown="onKeyDown('latitude')"
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
                      @input="handleNumber($event, 'longitude')"
                      @focus="onFieldFocus('longitude')"
                      @click="onFieldClick('longitude')"
                      @keydown="onKeyDown('longitude')"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Offset</label>
                  <div class="control">
                    <input class="input" type="text" v-model="profile.offset" 
                    @input="handleNumber($event, 'offset')"
                    @focus="onFieldFocus('offset')"
                    @click="onFieldClick('offset')"
                    @keydown="onKeyDown('offset')"
                    
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Descripción</label>
                  <div class="control">
                    <input
                      class="input"
                      type="text"
                      v-model="profile.description"
                    @focus="onFieldFocus('description')"
                    @input="onFieldChange('description')"
                    @click="onFieldClick('description')"
                    @keydown="onKeyDown('description')"
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
      
                      @focus="onFieldFocus('date')"
                      @input="handleDate($event, 'date')"
                      @click="onFieldClick('date')"
                      @keydown="onKeyDown('date')"
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
                      @input="handleNumber($event, 'ageUser')"
                      @focus="onFieldFocus('ageUser')"
                      @click="onFieldClick('ageUser')"
                      @keydown="onKeyDown('ageUser')"
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
          
                      @focus="onFieldFocus('date')"
                      @input="handleDate($event, 'date')"
                      @click="onFieldClick('date')"
                      @keydown="onKeyDown('date')"
                    />
                
                  </div>
                  
                </div>
                <div class="field">
                  <label class="label">Foto de Perfil</label>
                  <div class="control">
                    <input
                      class="input"
                      type="file"
                      accept=".jpg, .jpeg, .png"
                      @change="handleFileUpload"
                      @focus="onFieldFocus('profilePicture')"
                      @input="onFieldChange('profilePicture')"
                      @click="onFieldClick('profilePicture')"
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
          <div class="column is-half">
            <div class="box">
              <h1 class="title has-text-centered">Registro de Acciones</h1>
              <table class="table is-fullwidth">
                <thead>
                  <tr>
                    <th>Título</th>
                    <th>K</th>
                    <th>P</th>
                    <th>H</th>
                    <th>M</th>
                    <th>B</th>
                    <th>Scrolling</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="action in actionTable" :key="action.operator">
                    <td>{{ action.title }}</td>
                    <td>{{ action.K }}</td>
                    <td>{{ action.P }}</td>
                    <td>{{ action.H }}</td>
                    <td>{{ action.M }}</td>
                    <td>{{ action.B }}</td>
                    <td>{{ action.Scrolling }}</td>
                  </tr>
                </tbody>
              </table>
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

const lastScrollTime = ref(0); // Variable para el tiempo del último scroll
const hasSwitchedToKeyboard = ref({});
const hasThoughtField = ref({});
const lastSelectedField = ref(null); 

const onKeyDown = (field) => {
  if (!hasSwitchedToKeyboard.value[field]) {
    logAction(field, 'H'); 
  
    hasSwitchedToKeyboard.value[field] = true; 
    hasThoughtField.value[field]=true
  }
};

const onFieldClick = (field) => {
  logAction(field, 'B'); 
  logAction(field, 'M'); 
  hasSwitchedToKeyboard.value[field] = false; 
  hasThoughtField.value[field]=false
  lastSelectedField.value = field; 
};

const onScroll = () => {
  const now = Date.now();
  if (now - lastScrollTime.value > 2500) { // 2.5 segundos
    const field = lastSelectedField.value || 'scroll';
    logAction(field, 'Scrolling');
    lastScrollTime.value = now;
  }
};

onMounted(() => {
  startTime.value = new Date().getTime();
  const container = document.querySelector('.containerContent');
  container.addEventListener('scroll', onScroll);
  document.addEventListener('click', onMouseClick);
  document.addEventListener('keydown', onKeyDown);
});

onBeforeUnmount(() => {
  const container = document.querySelector('.containerContent');
  container.removeEventListener('scroll', onScroll);
  document.removeEventListener('click', onMouseClick);
  document.removeEventListener('keydown', onKeyDown);
  const mapContainer = document.getElementById('map');
  mapContainer.removeEventListener('scroll', onScroll);
});



const handleNumberInput = (event, fieldName) => {
  onFieldChange(fieldName);
  limitPhoneNumber(fieldName);
};

const handleDate= (event, fieldName) => {
  onFieldChange(fieldName);
  validateDate(fieldName);

};


const handleNumber = (event, fieldName) => {
  onFieldChange(fieldName);
  validateNumber(fieldName);
};



const actionTable = ref([
  { operator: 'username', title: 'Nombre de Usuario', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'correo', title: 'Correo Electrónico', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'gender', title: 'Género', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'phoneNumber', title: 'Teléfono Celular', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'number', title: 'Teléfono Fijo', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },


  { operator: 'firstName', title: 'Nombre', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'lastName', title: 'Apellido', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'cedula', title: 'Número de Cédula', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'ubicacion', title: 'Ubicacion', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'city', title: 'Ciudad', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'state', title: 'Estado', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'country', title: 'País', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'postcode', title: 'Código Postal', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'latitude', title: 'Latitud', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'longitude', title: 'Longitud', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'offset', title: 'Offset', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'description', title: 'Descripción', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'date', title: 'Fecha', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'ageUser', title: 'Edad del Usuario', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'dateRegister', title: 'Fecha de Registro', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
  { operator: 'profilePicture', title: 'Foto de Perfil', K: 0, P: 0, H: 0, M: 0, B: 0, Scrolling: 0 },
]);

const actionTimes = {
  K: 0.2,
  P: 1.1,
  H: 0.4,
  M: 1.2,
  B: 0.1,
  Scrolling: 3.96,
};

const calculateTotalTime = () => {
  let totalTime = 0;
  actionTable.value.forEach((action) => {
    for (const key in actionTimes) {
      totalTime += action[key] * actionTimes[key];
    }
  });
  return totalTime.toFixed(2); // Redondear a 2 decimales
};

const showSuccessMessage = () => {
  const totalTime = calculateTotalTime();
  message.value = (`¡Formulario enviado correctamente! Tiempo total transcurrido: ${totalTime} segundos.`);
  messageClass.value="success-message";
};

const actionLog = ref([]);
const startTime = ref(null);

const logAction = (field, actionType) => {
  const timestamp = new Date().getTime();
  
  actionLog.value.push({ field, actionType, timestamp });
  updateActionTable(field, actionType); // Asegúrate de que 'field' y 'actionType' se pasen correctamente
};


const updateActionTable = (field, actionType) => {
 
  const action = actionTable.value.find(item => item.operator === field);
  if (action && actionType in action) {  // Validar que el actionType sea una clave válida en la tabla
    action[actionType]++;
 
  } else {
    console.error(`No se encontró el campo ${field} o el tipo de acción ${actionType} no es válido en actionTable.`);
  }
};



const onFieldFocus = (field) => {
  logAction(field, 'P'); 
  lastSelectedField.value = field;
};

const onMouseClick = () => {
  logAction('Campo Relacionado', 'B'); 
  
};


const onFieldChange = (field) => {
  logAction(field, 'K'); 
};




const profile = ref({
  
  username: "",
  correo: "",
  phonePrefixFijo: "0241",
  phonePrefix: "0414",
  phoneNumber: "",  
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

const limitPhoneNumber = (field) => {
  profile.value[field] = profile.value[field].replace(/\D/g, "").slice(0,7);


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
    profile.value.dateRegister = response.data.usuarioRegistro; 
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
    showSuccessMessage();

   
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
    if (field === 'date') {
      profile.value.ageUser = calculateAge(profile.value[field]);
    }
  }
};

const calculateAge = (birthDate) => {
  const today = new Date();
  const birthDateParts = birthDate.split('/');
  const birthDay = parseInt(birthDateParts[0], 10);
  const birthMonth = parseInt(birthDateParts[1], 10) - 1; 
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

  const mapContainer = document.getElementById('map');
  mapContainer.addEventListener('scroll', onScroll);

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



.containerContent {
  height: 1000px; /* o cualquier altura adecuada */
  overflow-y: scroll;
}
</style>
