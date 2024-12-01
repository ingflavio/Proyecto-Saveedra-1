package com.springJWT.yop.Repository;

import com.springJWT.yop.Persistens.Colores;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColoresRepository extends CrudRepository<Colores, Long> {
}
