package com.example.RaktarkezeloProgram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RaktarkezeloProgram.domain.Ital;
@Repository
public interface ItalRepository extends JpaRepository<Ital, Long>{

}
