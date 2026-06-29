package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.LogGanado;
import com.example.demo.model.LogGanadoEntity;
import com.example.demo.repository.LogGanadoRepository;

@Service
public class LogGanadoService {

	@Autowired
	private LogGanadoRepository logGanadoRepository;

	@Transactional
	public LogGanadoEntity guardarLog(LogGanado log) {
		double latitud = 0.0;
		double longitud = 0.0;

		if (log.getCoordenadas() != null && log.getCoordenadas().size() >= 2) {
			latitud = log.getCoordenadas().get(0);
			longitud = log.getCoordenadas().get(1);
		}

		LogGanadoEntity entidad = new LogGanadoEntity(log.getDispositivoId(), // Pasamos el ID del dispositivo mapeado
				log.getTemperatura(), latitud, longitud);

		return logGanadoRepository.save(entidad);
	}

	public List<LogGanadoEntity> obtenerTodosLosLogs() {
		return logGanadoRepository.findAll();
	}
}
