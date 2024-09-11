package com.Interfaces.Proyecto1.Repository;

import com.Interfaces.Proyecto1.Entites.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
}
