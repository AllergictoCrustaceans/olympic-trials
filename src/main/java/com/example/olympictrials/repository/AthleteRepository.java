package com.example.olympictrials.repository;

import com.example.olympictrials.AthleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<AthleteEntity, Long> {

}
