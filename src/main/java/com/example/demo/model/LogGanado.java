package com.example.demo.model;

import java.util.List;

public class LogGanado {
	private Long id;
    private double temperatura;
    private List<Double> coordenadas; // Almacena [latitud, longitud]

    // Constructor vacío obligatorio
    public LogGanado() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public List<Double> getCoordenadas() { return coordenadas; }
    public void setCoordenadas(List<Double> coordenadas) { this.coordenadas = coordenadas; }
}