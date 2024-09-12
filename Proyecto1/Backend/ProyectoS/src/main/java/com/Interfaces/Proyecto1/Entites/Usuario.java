package com.Interfaces.Proyecto1.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String correo;
    @NotNull
    private String password;


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
