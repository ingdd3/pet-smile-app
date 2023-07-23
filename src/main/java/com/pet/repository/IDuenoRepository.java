package com.pet.repository;

import com.pet.model.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDuenoRepository extends JpaRepository<Dueno, Long> {
}
