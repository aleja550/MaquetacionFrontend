package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NuevaCategoriaActivity extends AppCompatActivity {

    private EditText etNombre;
    private LinearLayout iconosContainer;
    private LinearLayout coloresContainer;
    private RadioGroup vibracionGroup;
    private Button btnCrearCategoria;
    private TextView headerTitle;
    
    private String iconoSeleccionado = "🎯"; // Icono por defecto
    private String colorSeleccionado = "#10B981"; // Verde por defecto
    
    // Variables para modo edición
    private boolean modoEdicion = false;
    private Categoria categoriaAEditar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_nueva_categoria);
        
        inicializarVistas();
        verificarModoEdicion();
        configurarIconos();
        configurarColores();
        configurarEventos();
    }
    
    private void inicializarVistas() {
        etNombre = findViewById(R.id.et_nombre);
        iconosContainer = findViewById(R.id.iconos_container);
        coloresContainer = findViewById(R.id.colores_container);
        vibracionGroup = findViewById(R.id.vibracion_group);
        btnCrearCategoria = findViewById(R.id.btn_crear_categoria);
        headerTitle = findViewById(R.id.header_title);
        
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> onBackPressed());
    }
    
    private void verificarModoEdicion() {
        Intent intent = getIntent();
        modoEdicion = intent.getBooleanExtra("MODO_EDICION", false);
        
        if (modoEdicion) {
            // Cambiar título del header
            if (headerTitle != null) {
                headerTitle.setText("EDITAR CATEGORÍA");
            }
            
            // Cambiar texto del botón
            btnCrearCategoria.setText("GUARDAR CAMBIOS");
            
            // Obtener datos de la categoría a editar
            String nombreCategoria = intent.getStringExtra("CATEGORIA_NOMBRE");
            String iconoCategoria = intent.getStringExtra("CATEGORIA_ICONO");
            String colorCategoria = intent.getStringExtra("CATEGORIA_COLOR");
            String descripcionCategoria = intent.getStringExtra("CATEGORIA_DESCRIPCION");
            
            // Buscar la categoría original para poder actualizarla
            for (Categoria categoria : CategoriasManager.getInstance().getCategorias()) {
                if (categoria.getNombre().equals(nombreCategoria)) {
                    categoriaAEditar = categoria;
                    break;
                }
            }
            
            // Rellenar campos con datos existentes
            if (nombreCategoria != null) {
                etNombre.setText(nombreCategoria);
            }
            if (iconoCategoria != null && !iconoCategoria.isEmpty()) {
                iconoSeleccionado = iconoCategoria;
            }
            if (colorCategoria != null && !colorCategoria.isEmpty()) {
                colorSeleccionado = colorCategoria;
            }
        }
    }
    
    private void configurarIconos() {
        String[] iconos = {"🎯", "🖤", "🟡", "🏃", "🟠", "🌿", "🏔️", "🏢"};
        
        for (String icono : iconos) {
            TextView iconoView = new TextView(this);
            iconoView.setText(icono);
            iconoView.setTextSize(24);
            iconoView.setPadding(24, 24, 24, 24);
            iconoView.setBackgroundResource(R.drawable.icono_selector_background);
            
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                getResources().getDimensionPixelSize(R.dimen.icono_size),
                getResources().getDimensionPixelSize(R.dimen.icono_size)
            );
            params.setMargins(8, 8, 8, 8);
            iconoView.setLayoutParams(params);
            
            iconoView.setOnClickListener(v -> {
                iconoSeleccionado = icono;
                actualizarSeleccionIcono(iconoView);
            });
            
            iconosContainer.addView(iconoView);
        }
        
        // Seleccionar el ícono correspondiente (por defecto el primero)
        TextView iconoASeleccionar = null;
        for (int i = 0; i < iconosContainer.getChildCount(); i++) {
            TextView iconoView = (TextView) iconosContainer.getChildAt(i);
            if (iconoView.getText().toString().equals(iconoSeleccionado)) {
                iconoASeleccionar = iconoView;
                break;
            }
        }
        
        // Si no se encontró el ícono específico, seleccionar el primero
        if (iconoASeleccionar == null && iconosContainer.getChildCount() > 0) {
            iconoASeleccionar = (TextView) iconosContainer.getChildAt(0);
        }
        
        if (iconoASeleccionar != null) {
            actualizarSeleccionIcono(iconoASeleccionar);
        }
    }
    
    private void configurarColores() {
        String[] colores = {"#10B981", "#4285F4", "#EC4899", "#F97316", "#EF4444", "#8B5CF6"};
        
        for (String color : colores) {
            View colorView = new View(this);
            colorView.setBackgroundColor(android.graphics.Color.parseColor(color));
            
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                getResources().getDimensionPixelSize(R.dimen.color_size),
                getResources().getDimensionPixelSize(R.dimen.color_size)
            );
            params.setMargins(12, 12, 12, 12);
            colorView.setLayoutParams(params);
            
            colorView.setOnClickListener(v -> {
                colorSeleccionado = color;
                actualizarSeleccionColor(colorView);
            });
            
            coloresContainer.addView(colorView);
        }
        
        // Seleccionar el color correspondiente (por defecto el primero)
        View colorASeleccionar = null;
        for (int i = 0; i < coloresContainer.getChildCount(); i++) {
            View colorView = coloresContainer.getChildAt(i);
            android.graphics.drawable.ColorDrawable colorDrawable = (android.graphics.drawable.ColorDrawable) colorView.getBackground();
            int colorInt = colorDrawable.getColor();
            String colorHex = String.format("#%06X", (0xFFFFFF & colorInt));
            
            if (colorHex.equalsIgnoreCase(colorSeleccionado)) {
                colorASeleccionar = colorView;
                break;
            }
        }
        
        // Si no se encontró el color específico, seleccionar el primero
        if (colorASeleccionar == null && coloresContainer.getChildCount() > 0) {
            colorASeleccionar = coloresContainer.getChildAt(0);
        }
        
        if (colorASeleccionar != null) {
            actualizarSeleccionColor(colorASeleccionar);
        }
    }
    
    private void configurarEventos() {
        // Inicialmente el botón está desactivado
        btnCrearCategoria.setEnabled(false);
        btnCrearCategoria.setAlpha(0.5f);
        
        // Agregar TextWatcher para validar el nombre
        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No necesario
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Validar si hay texto
                boolean hasText = s != null && s.toString().trim().length() > 0;
                btnCrearCategoria.setEnabled(hasText);
                btnCrearCategoria.setAlpha(hasText ? 1.0f : 0.5f);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No necesario
            }
        });
        
        btnCrearCategoria.setOnClickListener(v -> crearCategoria());
    }
    
    private void actualizarSeleccionIcono(TextView iconoSeleccionado) {
        // Resetear todos los iconos
        for (int i = 0; i < iconosContainer.getChildCount(); i++) {
            TextView icono = (TextView) iconosContainer.getChildAt(i);
            icono.setBackgroundResource(R.drawable.icono_selector_background);
        }
        
        // Marcar el seleccionado
        iconoSeleccionado.setBackgroundResource(R.drawable.icono_selector_selected);
    }
    
    private void actualizarSeleccionColor(View colorSeleccionado) {
        // Resetear todos los colores
        for (int i = 0; i < coloresContainer.getChildCount(); i++) {
            View color = coloresContainer.getChildAt(i);
            color.setScaleX(1.0f);
            color.setScaleY(1.0f);
        }
        
        // Marcar el seleccionado
        colorSeleccionado.setScaleX(1.2f);
        colorSeleccionado.setScaleY(1.2f);
    }
    
    private void crearCategoria() {
        String nombre = etNombre.getText().toString().trim();
        
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un nombre para la categoría", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Obtener tipo de vibración seleccionado
        RadioButton vibracionSeleccionada = findViewById(vibracionGroup.getCheckedRadioButtonId());
        String tipoVibracion = vibracionSeleccionada != null ? vibracionSeleccionada.getText().toString() : "Suave";
        
        if (modoEdicion && categoriaAEditar != null) {
            // Modo edición: actualizar categoría existente
            boolean actualizado = CategoriasManager.getInstance().actualizarCategoria(
                categoriaAEditar,
                nombre,
                iconoSeleccionado,
                colorSeleccionado,
                tipoVibracion
            );
            
            if (actualizado) {
                // Mostrar toast y volver a gestionar categorías
                Toast.makeText(this, "Categoría editada correctamente", Toast.LENGTH_SHORT).show();
                
                Intent gestionarIntent = new Intent(this, GestionarCategoriasActivity.class);
                gestionarIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(gestionarIntent);
                finish();
            }
        } else {
            // Modo crear: nueva categoría
            Categoria nuevaCategoria = new Categoria(nombre, iconoSeleccionado, colorSeleccionado, tipoVibracion);
            
            // Agregar al manager
            CategoriasManager.getInstance().agregarCategoria(nuevaCategoria);
            
            // Navegar a pantalla de éxito
            Intent intent = new Intent(this, CategoriaCreadaExitosamenteActivity.class);
            intent.putExtra("CATEGORIA_NOMBRE", nombre);
            intent.putExtra("CATEGORIA_ICONO", iconoSeleccionado);
            intent.putExtra("CATEGORIA_COLOR", colorSeleccionado);
            intent.putExtra("CATEGORIA_VIBRACION", tipoVibracion);
            startActivity(intent);
            
            // Cerrar esta actividad
            finish();
        }
    }
}