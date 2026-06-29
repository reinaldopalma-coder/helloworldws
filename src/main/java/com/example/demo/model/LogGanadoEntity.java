package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs_ganado")
public class LogGanadoEntity {

    @Id
    private Long id;
    private double temperatura;
    private double latitud;
    private double longitud;

    // Se asigna la fecha/hora en memoria al momento de crear la instancia en Java
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // Constructor vacío obligatorio para JPA
    public LogGanadoEntity() {}

    // Constructor para inicializar los datos desde el Servicio
    public LogGanadoEntity(Long id, double temperatura, double latitud, double longitud) {
        this.id = id;
        this.temperatura = temperatura;
        this.latitud = latitud;
        this.longitud = longitud;
        // No incluimos fechaCreacion aquí porque ya se inicializa arriba por defecto
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}