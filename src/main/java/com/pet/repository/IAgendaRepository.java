package com.pet.repository;

import com.pet.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgendaRepository extends JpaRepository<Agenda, Long> {
}
