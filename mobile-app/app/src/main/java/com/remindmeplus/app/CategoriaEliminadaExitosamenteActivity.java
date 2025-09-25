package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriaEliminadaExitosamenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_categoria_eliminada_exitosamente);
        
        // Configurar botones
        Button btnVerCategorias = findViewById(R.id.btn_ver_categorias);
        Button btnCrearOtra = findViewById(R.id.btn_crear_otra);
        
        btnVerCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a GestionarCategoriasActivity y cerrar las demás actividades
                Intent intent = new Intent(CategoriaEliminadaExitosamenteActivity.this, GestionarCategoriasActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
        
        btnCrearOtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a crear nueva categoría
                Intent intent = new Intent(CategoriaEliminadaExitosamenteActivity.this, NuevaCategoriaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    
    @Override
    public void onBackPressed() {
        // Al presionar back, ir a gestionar categorías
        Intent intent = new Intent(this, GestionarCategoriasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}