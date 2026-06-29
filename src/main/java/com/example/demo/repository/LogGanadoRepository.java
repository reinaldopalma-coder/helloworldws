package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LogGanadoEntity;

@Repository
public interface LogGanadoRepository extends JpaRepository<LogGanadoEntity, Long> {
	// Spring genera el SQL "SELECT * FROM logs_ganado WHERE fecha_creacion BETWEEN
	// ? AND ?" automáticamente
	List<LogGanadoEntity> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);
}
