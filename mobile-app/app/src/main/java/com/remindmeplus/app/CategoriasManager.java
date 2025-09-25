package com.remindmeplus.app;

import java.util.ArrayList;
import java.util.List;

public class CategoriasManager {
    private static CategoriasManager instance;
    private List<Categoria> categorias;
    private List<Categoria> categoriasFiltradas;

    private CategoriasManager() {
        categorias = new ArrayList<>();
        categoriasFiltradas = new ArrayList<>();
        // Agregar las categorías predeterminadas
        inicializarCategoriasDefault();
    }

    public static CategoriasManager getInstance() {
        if (instance == null) {
            instance = new CategoriasManager();
        }
        return instance;
    }

    private void inicializarCategoriasDefault() {
        // Solo Salud tiene ícono según el mockup
        categorias.add(new Categoria("Salud", "💊", "#4CAF50", "Relajante • Suave"));
        categorias.add(new Categoria("Ejercicio", "", "#607D8B", "Energética • Motivadora"));
        categorias.add(new Categoria("Trabajo", "", "#FF9800", "Profesional • Productiva"));
        categorias.add(new Categoria("Estudio", "", "#9C27B0", "Concentración • Mental"));
        
        // Inicialmente, todas las categorías están en la lista filtrada
        categoriasFiltradas.addAll(categorias);
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
        categoriasFiltradas.add(categoria);
    }

    public List<Categoria> getCategorias() {
        return new ArrayList<>(categorias);
    }

    public List<Categoria> getCategoriasFiltradas() {
        return new ArrayList<>(categoriasFiltradas);
    }

    public void filtrarCategorias(String query) {
        categoriasFiltradas.clear();
        
        if (query == null || query.trim().isEmpty()) {
            // Si no hay query, mostrar todas las categorías
            categoriasFiltradas.addAll(categorias);
        } else {
            String queryLower = query.toLowerCase().trim();
            for (Categoria categoria : categorias) {
                // Buscar en el nombre de la categoría
                if (categoria.getNombre().toLowerCase().contains(queryLower)) {
                    categoriasFiltradas.add(categoria);
                }
            }
        }
    }

    public void eliminarCategoria(int position) {
        if (position >= 0 && position < categorias.size()) {
            Categoria categoria = categorias.get(position);
            categorias.remove(position);
            categoriasFiltradas.remove(categoria);
        }
    }

    public boolean eliminarCategoria(Categoria categoria) {
        boolean removed = categorias.remove(categoria);
        if (removed) {
            categoriasFiltradas.remove(categoria);
        }
        return removed;
    }

    public boolean actualizarCategoria(Categoria categoriaOriginal, String nuevoNombre, String nuevoEmoji, String nuevoColor, String nuevaDescripcion) {
        // Buscar la categoría original en la lista
        int index = categorias.indexOf(categoriaOriginal);
        if (index != -1) {
            // Actualizar los datos de la categoría existente
            categoriaOriginal.setNombre(nuevoNombre);
            categoriaOriginal.setEmoji(nuevoEmoji);
            categoriaOriginal.setColor(nuevoColor);
            categoriaOriginal.setDescripcion(nuevaDescripcion);
            
            // Actualizar también en la lista filtrada si está presente
            if (categoriasFiltradas.contains(categoriaOriginal)) {
                int filteredIndex = categoriasFiltradas.indexOf(categoriaOriginal);
                if (filteredIndex != -1) {
                    categoriasFiltradas.set(filteredIndex, categoriaOriginal);
                }
            }
            
            return true;
        }
        return false;
    }

    public int getTotalCategorias() {
        return categorias.size();
    }
}