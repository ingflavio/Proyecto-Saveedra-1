<script>
document.addEventListener("DOMContentLoaded", () => {
  const $navbarBurgers = Array.prototype.slice.call(
    document.querySelectorAll(".navbar-burger"),
    0
  );

  $navbarBurgers.forEach((el) => {
    el.addEventListener("click", () => {
      const target = el.dataset.target;
      const $target = document.getElementById(target);

      el.classList.toggle("is-active");
      $target.classList.toggle("is-active");
    });
  });
});
</script>

<template>
  <nav class="navbar is-transparent">
    <div class="navbar-brand">
      <div
        class="navbar-burger js-burger"
        data-target="navbarExampleTransparentExample"
      >
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>

    <div id="navbarExampleTransparentExample" class="navbar-menu">
      <div class="navbar-start">
        <a class="navbar-item" href="#"> Inicio </a>
        <a class="navbar-item" href="#"> Galería </a>
      </div>
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="field is-grouped">
            <p class="control">
              <a class="button buttonNav" href="#">
                <span>Iniciar Sesión</span>
              </a>
            </p>
            <p class="control">
              <a class="button buttonNav" href="#">
                <span>Registrarte</span>
              </a>
            </p>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<style>
.navbar {
  background-color: #48cae4 !important;
  min-height: 30px;
  max-height: 120px;
}

.navbar-menu {
  background-color: #48cae4 !important;
}

.navbar-item {
  color: black !important;
}
.buttonNav{
    background-color: #b3c4cc;
    color: black;
    --bulma-button-border-style: none;
}

.buttonNav:hover{
    background-color: #919da3;
}

strong {
  color: black !important;
}

@media only screen and (max-width: 1023px) {
  .field.is-grouped {
    justify-content: center;
  }
}
</style>
