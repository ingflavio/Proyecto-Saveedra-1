package com.springJWT.yop.Persistens;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        @NonNull
        private String username;
        @NonNull
        private String password;
        @NonNull
        private String correo;
        @Convert(converter = PermisosConverter.class)
        Set<Permisos> permisos;

        private String phone;
        private String cell;
        private String gender;
        private String firstName;
        private String lastName;
        private int number;
        private String name;
        private String city;
        private String state;
        private String country;
        private String postcode;
        private String latitude;
        private String longitude;
        private String offset;
        private String description;
        private Date date;
        private int ageUser;
        private Date dateRegister;
        private int ageRegister;


    }

