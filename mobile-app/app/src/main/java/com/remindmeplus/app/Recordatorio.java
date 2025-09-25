package com.remindmeplus.app;

public class Recordatorio {
    private String titulo;
    private String fechaHora;
    private String categoria;
    private String antiPostponer;
    private String repetir;
    private String estado; // "Activo", "Próximo", "Atrasado", "Completado"

    public Recordatorio(String titulo, String fechaHora, String categoria, String antiPostponer, String repetir) {
        this.titulo = titulo;
        this.fechaHora = fechaHora;
        this.categoria = categoria;
        this.antiPostponer = antiPostponer;
        this.repetir = repetir;
        this.estado = "Activo"; // Por defecto
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getFechaHora() { return fechaHora; }
    public String getCategoria() { return categoria; }
    public String getAntiPostponer() { return antiPostponer; }
    public String getRepetir() { return repetir; }
    public String getEstado() { return estado; }

    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setAntiPostponer(String antiPostponer) { this.antiPostponer = antiPostponer; }
    public void setRepetir(String repetir) { this.repetir = repetir; }
    public void setEstado(String estado) { this.estado = estado; }

    // Helper methods
    public String getCategoriaColor() {
        switch (categoria.toLowerCase()) {
            case "salud":
                return "#4CAF50";    // Verde
            case "trabajo":
                return "#FF9800";    // Naranja
            case "estudio":
                return "#9C27B0";    // Morado
            case "ejercicio":
                return "#607D8B";    // Gris
            default:
                return "#4CAF50";
        }
    }

    public String getCategoriaEmoji() {
        switch (categoria.toLowerCase()) {
            case "salud":
                return "💊";
            case "trabajo":
                return "";
            case "estudio":
                return "";
            case "ejercicio":
                return "";
            default:
                return "";
        }
    }
}