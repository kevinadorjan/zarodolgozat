package com.example.RaktarkezeloProgram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RaktarkezeloProgram.domain.Csomagolt;
@Repository
public interface CsomagoltRepository extends JpaRepository<Csomagolt, Long>{

}
