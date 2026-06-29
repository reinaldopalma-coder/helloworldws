package com.example.demo.repository;

import com.example.demo.model.LogGanadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogGanadoRepository extends JpaRepository<LogGanadoEntity, Long> {
}
