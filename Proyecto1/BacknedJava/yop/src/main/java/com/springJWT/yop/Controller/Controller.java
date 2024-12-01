package com.springJWT.yop.Controller;

import com.springJWT.yop.Controller.Dtos.AuthLoginRequest;
import com.springJWT.yop.Controller.Dtos.AuthRequest;

import com.springJWT.yop.Controller.Dtos.UsuarioResponse;
import com.springJWT.yop.Persistens.Colores;
import com.springJWT.yop.Persistens.Permisos;
import com.springJWT.yop.Persistens.Usuario;
import com.springJWT.yop.Service.impl.ServiceColoresImpl;
import com.springJWT.yop.Service.impl.ServiceUsuarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {


    @Autowired
    private ServiceUsuarioImpl usuarioimpl;

    @Autowired
    private ServiceColoresImpl coloresImpl;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest userLoginRequest) {
        System.out.println("xzczxc0" + userLoginRequest.getUsername());
        return new ResponseEntity<>(usuarioimpl.Login(userLoginRequest), HttpStatus.OK);

    }

    @PostMapping("/Register")
    public ResponseEntity<?> guardar(@RequestBody AuthRequest userEntity) {
        LocalDate fechaActual = LocalDate.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formato);

        Optional<Usuario> usuario1 = usuarioimpl.findbyName(userEntity.getUsername());
        if (usuario1.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioimpl.Guardar(Usuario.builder()
                    .username(userEntity.getUsername())
                    .correo(userEntity.getCorreo())
                    .password(userEntity.getPassword())
                    .permisos(Collections.singleton(Permisos.READ))
                    .dateRegister(fechaFormateada)
                    .build()));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario Ya Registrado");
        }

    }


    @GetMapping("/Perfil")
    public ResponseEntity<?> perfil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Usuario> usuario = usuarioimpl.findbyName(username);
        if (usuario.isPresent()) {
            UsuarioResponse response = new UsuarioResponse(usuario.get().getUsername(), usuario.get().getCorreo(), usuario.get().getDateRegister());
            return ResponseEntity.ok(response);

        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario No existe");

    }

    @PutMapping("/Perfil")
    public ResponseEntity<?> Actualizar(@RequestParam("username") String username,
                                        @RequestParam("correo") String correo,
                                        @RequestParam("phone") String phone,
                                        @RequestParam("number") String number,
                                        @RequestParam("gender") String gender,
                                        @RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("cedula") String cedula,
                                        @RequestParam("city") String city,
                                        @RequestParam("state") String state,
                                        @RequestParam("country") String country,
                                        @RequestParam("postcode") String postcode,
                                        @RequestParam("latitude") String latitude,
                                        @RequestParam("longitude") String longitude,
                                        @RequestParam("offset") String offset,
                                        @RequestParam("description") String description,
                                        @RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy") Date date,
                                        @RequestParam("ageUser") int ageUser,
                                        @RequestParam("dateRegister") String dateRegister,
                                        @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUsername = authentication.getName();
        Optional<Usuario> UsuarioEntity = usuarioimpl.findbyName(username);
        if (UsuarioEntity.isPresent()) {
            try {
                Usuario usuario = UsuarioEntity.get();
                usuario.setCorreo(correo);
                usuario.setPhone(phone);
                usuario.setCell(number);
                usuario.setCedula(cedula);
                usuario.setPermisos(Collections.singleton(Permisos.READ));
                usuario.setGender(gender);
                usuario.setFirstName(firstName);
                usuario.setLastName(lastName);
                usuario.setCity(city);
                usuario.setState(state);
                usuario.setCountry(country);
                usuario.setPostcode(postcode);
                usuario.setLatitude(latitude);
                usuario.setLongitude(longitude);
                usuario.setOffset(offset);
                usuario.setDescription(description);
                usuario.setDate(date);
                usuario.setAgeUser(ageUser);
                usuario.setDateRegister(dateRegister);
                if (profilePicture != null && !profilePicture.isEmpty()) {
                    usuario.setFoto(profilePicture.getBytes());
                }
                return ResponseEntity.status(HttpStatus.OK).body(usuarioimpl.Guardar(usuario));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario No existe");
        }
    }


    // Rutas para colores
    @PostMapping("/Colores")
    public ResponseEntity<?> guardarColores(@RequestBody Colores colores) {
        Colores savedColores = coloresImpl.guardar(colores);
        System.out.println("Colores guardados exitosamente: " + savedColores);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedColores);
    }

    @GetMapping("/Colores/{id}")
    public ResponseEntity<?> obtenerColores(@PathVariable Long id) {
        Optional<Colores> colores = coloresImpl.findById(id);
        if (colores.isPresent()) {
            System.out.println("Colores obtenidos: " + colores.get());
            return ResponseEntity.ok(colores.get());
        } else {
            System.out.println("Colores no encontrados para el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colores no encontrados");
        }
    }

    @PutMapping("/Colores/{id}")
    public ResponseEntity<?> actualizarColores(@PathVariable Long id, @RequestBody Colores colores) {
        Colores coloresActualizados = coloresImpl.actualizarColores(id, colores);
        if (coloresActualizados != null) {
            return ResponseEntity.ok(coloresActualizados);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colores no encontrados");
        }
    }

    @DeleteMapping("/Colores/{id}")
    public ResponseEntity<?> eliminarColores(@PathVariable Long id) {
        coloresImpl.eliminarColores(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping("/uploadFont")
    public ResponseEntity<?> uploadFont(@RequestParam("key") String key, @RequestParam("fontPath") String fontPath) {
        System.out.println("Se llamó el endpoint de subir tipografía");

        if (fontPath == null || fontPath.isEmpty()) {
            System.out.println("No se recibió la ruta del archivo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor selecciona un archivo y envíalo");
        }
        try {
            Colores colores = coloresImpl.findById(1L).orElse(new Colores());

            if (key.equals("title")) {
                colores.setTitleFontPath(fontPath);
            } else if (key.equals("paragraph")) {
                colores.setParagraphFontPath(fontPath);
            }

            coloresImpl.guardar(colores);

            System.out.println("Ruta de la fuente guardada correctamente: " + fontPath);
            return ResponseEntity.status(HttpStatus.OK).body("Ruta de la fuente guardada exitosamente");

        } catch (Exception e) {
            System.out.println("Error al guardar la ruta de la fuente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la ruta de la fuente");
        }
    }



}
