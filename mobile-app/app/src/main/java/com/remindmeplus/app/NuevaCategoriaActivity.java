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
    
    private String iconoSeleccionado = "🎯"; // Icono por defecto
    private String colorSeleccionado = "#10B981"; // Verde por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_nueva_categoria);
        
        inicializarVistas();
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
        
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> onBackPressed());
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
        
        // Seleccionar el primer ícono por defecto
        if (iconosContainer.getChildCount() > 0) {
            actualizarSeleccionIcono((TextView) iconosContainer.getChildAt(0));
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
        
        // Seleccionar el primer color por defecto
        if (coloresContainer.getChildCount() > 0) {
            actualizarSeleccionColor(coloresContainer.getChildAt(0));
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
        
        // Crear la nueva categoría
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