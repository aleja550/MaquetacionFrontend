package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriaCreadaExitosamenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_categoria_creada_exitosamente);
        
        
        Intent intent = getIntent();
        String nombreCategoria = intent.getStringExtra("CATEGORIA_NOMBRE");
        String iconoCategoria = intent.getStringExtra("CATEGORIA_ICONO");
        String colorCategoria = intent.getStringExtra("CATEGORIA_COLOR");
        String vibracionCategoria = intent.getStringExtra("CATEGORIA_VIBRACION");
        
        
        configurarInformacionCategoria(nombreCategoria, iconoCategoria, colorCategoria, vibracionCategoria);
        
        
        Button btnVerCategorias = findViewById(R.id.btn_ver_categorias);
        Button btnCrearOtra = findViewById(R.id.btn_crear_otra);
        
        btnVerCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(CategoriaCreadaExitosamenteActivity.this, GestionarCategoriasActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
        
        btnCrearOtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(CategoriaCreadaExitosamenteActivity.this, NuevaCategoriaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    
    private void configurarInformacionCategoria(String nombre, String icono, String color, String vibracion) {
        TextView tvNombreCategoria = findViewById(R.id.tv_nombre_categoria);
        TextView tvIconoCategoria = findViewById(R.id.tv_icono_categoria);
        View viewColorCategoria = findViewById(R.id.view_color_categoria);
        TextView tvVibracionCategoria = findViewById(R.id.tv_vibracion_categoria);
        
        
        if (nombre != null) {
            tvNombreCategoria.setText(nombre.toUpperCase());
        }
        
        
        if (icono != null) {
            tvIconoCategoria.setText(icono);
        }
        
        
        if (color != null) {
            try {
                int colorInt = android.graphics.Color.parseColor(color);
                viewColorCategoria.setBackgroundColor(colorInt);
            } catch (IllegalArgumentException e) {
                
                viewColorCategoria.setBackgroundColor(android.graphics.Color.parseColor("#10B981"));
            }
        }
        
        
        if (vibracion != null) {
            if (vibracion.contains("Suave")) {
                tvVibracionCategoria.setText("Vibración: Suave");
            } else if (vibracion.contains("Energética")) {
                tvVibracionCategoria.setText("Vibración: Energética");
            } else {
                tvVibracionCategoria.setText("Vibración: Normal");
            }
        }
    }
    
    @Override
    public void onBackPressed() {
        
        Intent intent = new Intent(this, GestionarCategoriasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}