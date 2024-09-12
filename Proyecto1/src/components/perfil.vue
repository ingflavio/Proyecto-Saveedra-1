<template>
    <div>
      <h1>Perfil del Usuario</h1>
      <p>Nombre de Usuario: {{ profile.username }}</p>
      <p>Correo Electrónico: {{ profile.correo }}</p>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const profile = ref({ username: '', correo: '' });
  
  const getProfile = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.get('http://localhost:8080/api/Perfil', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      profile.value = response.data;
    } catch (error) {
      console.error('Error al obtener el perfil:', error);
      // Manejar el error
    }
  };
  
  onMounted(() => {
    getProfile();
  });
  </script>
  