package com.springJWT.yop.Persistens;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Colores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Colores
    private String primary_color;
    private String secondary;
    private String accent;
    private String button;

    // Tamaños de fuente
    private Integer titleSize;
    private Integer subtitleSize;
    private Integer paragraphSize;

    // Fuentes de tipografía
    private String titleFontPath;
    private String paragraphFontPath;
}
