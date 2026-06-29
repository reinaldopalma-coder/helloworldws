package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Log;
import com.example.demo.model.LogGanado;
import com.example.demo.model.LogGanadoEntity;
import com.example.demo.service.LogGanadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class LogController {

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@Autowired
	private LogGanadoService logGanadoService;

	@PostMapping("/log")
	public ResponseEntity<Log> crearLog(@RequestBody Log log) {

		logger.info("El endpoint /api/log ha sido invocado");
		// logger.warn("Esto es un mensaje de advertencia");
		logger.info("Se recibieron los siguientes datos:");
		logger.info("	ID: " + log.getId());
		logger.info("	Date: " + log.getDate());
		// logger.error("Esto es un mensaje de error");

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/logGanado")
	public ResponseEntity<LogGanadoEntity> crearLogGanado(@Valid @RequestBody LogGanado log) {
		logger.info("El endpoint /api/logGanado ha sido invocado.");

		LogGanadoEntity resultado = logGanadoService.guardarLog(log);

		logger.info("Registro de telemetría guardado exitosamente con ID: {}", resultado.getId());
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/logGanado")
	public ResponseEntity<List<LogGanadoEntity>> listarLogs(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {

		List<LogGanadoEntity> historial;

		// Si vienen ambas fechas, filtramos. Si no, traemos todo.
		if (desde != null && hasta != null) {
			historial = logGanadoService.obtenerLogsPorRango(desde, hasta);
		} else {
			historial = logGanadoService.obtenerTodosLosLogs();
		}

		return ResponseEntity.ok(historial);
	}

}