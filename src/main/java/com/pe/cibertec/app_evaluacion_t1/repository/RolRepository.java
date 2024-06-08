package com.pe.cibertec.app_evaluacion_t1.repository;

import com.pe.cibertec.app_evaluacion_t1.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nomrol);
}
