package com.example.RaktarkezeloProgram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RaktarkezeloProgram.domain.Pekaru;
@Repository
public interface PekaruRepository extends JpaRepository<Pekaru, Long>{

}
