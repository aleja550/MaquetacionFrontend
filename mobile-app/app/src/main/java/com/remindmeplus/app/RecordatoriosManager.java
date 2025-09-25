package com.remindmeplus.app;

import java.util.ArrayList;
import java.util.List;

public class RecordatoriosManager {
    private static RecordatoriosManager instance;
    private List<Recordatorio> recordatorios;

    private RecordatoriosManager() {
        recordatorios = new ArrayList<>();
        // Agregar algunos recordatorios de ejemplo
        inicializarRecordatoriosEjemplo();
    }

    public static RecordatoriosManager getInstance() {
        if (instance == null) {
            instance = new RecordatoriosManager();
        }
        return instance;
    }

    private void inicializarRecordatoriosEjemplo() {
        recordatorios.add(new Recordatorio("Tomar Losartán 50mg", "08:00 AM", "Salud", "Matemática", "Diariamente"));
        recordatorios.add(new Recordatorio("Reunión Sprint Planning", "10:30 AM", "Trabajo", "Frase", "Una vez"));
        recordatorios.add(new Recordatorio("Estudiar UX research", "07:00 AM", "Estudio", "Aleatorio", "Diariamente"));
        recordatorios.add(new Recordatorio("Cardio 1 hora", "06:00 AM", "Ejercicio", "Matemática", "Semanalmente"));
        
        // Establecer estados para el ejemplo
        recordatorios.get(0).setEstado("Activo");
        recordatorios.get(1).setEstado("Próximo");
        recordatorios.get(2).setEstado("Atrasado");
        recordatorios.get(3).setEstado("Completado");
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        recordatorios.add(recordatorio);
    }

    public List<Recordatorio> getRecordatorios() {
        return new ArrayList<>(recordatorios);
    }

    public void eliminarRecordatorio(int position) {
        if (position >= 0 && position < recordatorios.size()) {
            recordatorios.remove(position);
        }
    }

    public boolean eliminarRecordatorio(Recordatorio recordatorio) {
        return recordatorios.remove(recordatorio);
    }

    public void actualizarRecordatorio(int position, Recordatorio recordatorio) {
        if (position >= 0 && position < recordatorios.size()) {
            recordatorios.set(position, recordatorio);
        }
    }

    public boolean actualizarRecordatorio(int id, String titulo, String fechaHora, String categoria, String antiPostponer, String repetir) {
        for (Recordatorio recordatorio : recordatorios) {
            if (recordatorio.getId() == id) {
                recordatorio.setTitulo(titulo);
                recordatorio.setFechaHora(fechaHora);
                recordatorio.setCategoria(categoria);
                recordatorio.setAntiPostponer(antiPostponer);
                recordatorio.setRepetir(repetir);
                return true;
            }
        }
        return false;
    }

    public int getTotalCount() {
        return recordatorios.size();
    }

    public int getActivosCount() {
        int count = 0;
        for (Recordatorio r : recordatorios) {
            if ("Activo".equals(r.getEstado())) count++;
        }
        return count;
    }

    public int getProximosCount() {
        int count = 0;
        for (Recordatorio r : recordatorios) {
            if ("Próximo".equals(r.getEstado())) count++;
        }
        return count;
    }

    public int getCompletadosCount() {
        int count = 0;
        for (Recordatorio r : recordatorios) {
            if ("Completado".equals(r.getEstado())) count++;
        }
        return count;
    }

    // Extraer solo la hora de la fecha completa para mostrar
    public String extraerHora(String fechaHora) {
        if (fechaHora.contains(" - ")) {
            String[] parts = fechaHora.split(" - ");
            if (parts.length > 1) {
                return parts[1];
            }
        }
        return fechaHora;
    }

    // Métodos de filtrado por estado
    public List<Recordatorio> getRecordatoriosPorEstado(String estado) {
        List<Recordatorio> filtrados = new ArrayList<>();
        for (Recordatorio recordatorio : recordatorios) {
            if (estado.equals(recordatorio.getEstado())) {
                filtrados.add(recordatorio);
            }
        }
        return filtrados;
    }

    public List<Recordatorio> getRecordatoriosHoy() {
        // Para este ejemplo, asumimos que "Hoy" incluye Activos y Próximos
        List<Recordatorio> hoy = new ArrayList<>();
        for (Recordatorio recordatorio : recordatorios) {
            String estado = recordatorio.getEstado();
            if ("Activo".equals(estado) || "Próximo".equals(estado)) {
                hoy.add(recordatorio);
            }
        }
        return hoy;
    }

    public List<Recordatorio> getRecordatoriosAtrasados() {
        return getRecordatoriosPorEstado("Atrasado");
    }

    public List<Recordatorio> getRecordatoriosCompletados() {
        return getRecordatoriosPorEstado("Completado");
    }

    public int getAtrasadosCount() {
        int count = 0;
        for (Recordatorio r : recordatorios) {
            if ("Atrasado".equals(r.getEstado())) count++;
        }
        return count;
    }
}