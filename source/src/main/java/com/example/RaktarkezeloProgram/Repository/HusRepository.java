package com.example.RaktarkezeloProgram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RaktarkezeloProgram.domain.Hus;
@Repository
public interface HusRepository extends JpaRepository<Hus, Long> {

}
