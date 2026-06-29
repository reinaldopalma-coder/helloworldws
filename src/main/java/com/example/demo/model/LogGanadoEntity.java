package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs_ganado")
public class LogGanadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura AUTO_INCREMENT en MySQL
    private Long id;

    @Column(name = "dispositivo_id", nullable = false)
    private Long dispositivoId; // Aquí guardamos el id del dispositivo (ej. 100)

    private double temperatura;
    private double latitud;
    private double longitud;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public LogGanadoEntity() {}

    // Constructor actualizado para recibir el id del dispositivo
    public LogGanadoEntity(Long dispositivoId, double temperatura, double latitud, double longitud) {
        this.dispositivoId = dispositivoId;
        this.temperatura = temperatura;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDispositivoId() { return dispositivoId; }
    public void setDispositivoId(Long dispositivoId) { this.dispositivoId = dispositivoId; }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}