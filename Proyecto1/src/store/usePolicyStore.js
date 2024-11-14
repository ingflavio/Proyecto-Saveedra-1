// usePolicyStore.js
import { defineStore } from "pinia";

export const usePolicyStore = defineStore("policy", {
  state: () => ({
    privacyPolicy: `<div><h2>Política de Privacidad </h2>
<ol type="1">

<li><p>Recopilación de Información: Recopilamos información personal que nos proporcionas voluntariamente al registrarte, suscribirte a nuestro boletín, o interactuar con nuestras funciones multimedia.</p></li>

<li><p> Uso de la Información: Utilizamos la información recopilada para mejorar tu experiencia en nuestra página web y proporcionarte contenido relevante. No compartimos tu información personal con terceros sin tu consentimiento, excepto cuando sea necesario para cumplir con la ley.</p></li>

<li><p> Protección de Datos: Implementamos medidas de seguridad adecuadas para proteger tu información personal contra el acceso no autorizado, la alteración, divulgación o destrucción.</p></li>

<li><p> Cookies: Utilizamos cookies para mejorar la funcionalidad de nuestra página web y personalizar tu experiencia. Puedes configurar tu navegador para rechazarlas, pero esto puede afectar el funcionamiento de algunas partes de nuestra página web.</p></li>

<li><p> Enlaces a Terceros: Nuestra página web puede contener enlaces a sitios web de terceros. No somos responsables de las prácticas de privacidad de estos sitios y te recomendamos leer sus políticas de privacidad.</p></li>

<li><p> Cambios en la Política de Privacidad: Nos reservamos el derecho de actualizar esta política de privacidad en cualquier momento. Te notificaremos sobre cualquier cambio publicando la nueva política en nuestra página web.</p></li>

</ol></div>`,
  }),
  actions: {
    setPrivacyPolicy(newPolicy) {
      this.privacyPolicy = newPolicy;
    },
  },
});
