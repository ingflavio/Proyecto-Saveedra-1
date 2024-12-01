package com.springJWT.yop.Service;

import com.springJWT.yop.Persistens.Colores;
import java.util.Optional;

public interface ServiceColores {

    Optional<Colores> findById(Long id);

    Colores guardar(Colores colores);

    Colores actualizarColores(Long id, Colores colores);

    void eliminarColores(Long id);
}
