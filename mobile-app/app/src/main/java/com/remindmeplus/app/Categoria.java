package com.remindmeplus.app;

public class Categoria {
    private String nombre;
    private String emoji;
    private String color;
    private String descripcion;

    public Categoria(String nombre, String emoji, String color, String descripcion) {
        this.nombre = nombre;
        this.emoji = emoji;
        this.color = color;
        this.descripcion = descripcion;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getEmoji() { return emoji; }
    public String getColor() { return color; }
    public String getDescripcion() { return descripcion; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setColor(String color) { this.color = color; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // Método para obtener el texto completo con emoji (si tiene)
    public String getTextoCompleto() {
        if (emoji != null && !emoji.trim().isEmpty()) {
            return nombre + " " + emoji;
        } else {
            return nombre;
        }
    }

    @Override
    public String toString() {
        return getTextoCompleto();
    }
}