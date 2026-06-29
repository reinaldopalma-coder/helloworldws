package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class LogGanado {
    
    @NotNull(message = "El ID del dispositivo es obligatorio")
    @JsonProperty("id") // Sigue leyendo "id" desde el JSON, pero lo mapea a la variable de abajo
    private Long dispositivoId;

    @NotNull(message = "La temperatura es obligatoria")
    private Double temperatura;

    @NotNull(message = "Las coordenadas son obligatorias")
    @Size(min = 2, max = 2, message = "Las coordenadas deben contener exactamente una latitud y una longitud")
    private List<Double> coordenadas;

    public LogGanado() {}

    // Getters y Setters actualizados
    public Long getDispositivoId() { return dispositivoId; }
    public void setDispositivoId(Long dispositivoId) { this.dispositivoId = dispositivoId; }
    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }
    public List<Double> getCoordenadas() { return coordenadas; }
    public void setCoordenadas(List<Double> coordenadas) { this.coordenadas = coordenadas; }
}