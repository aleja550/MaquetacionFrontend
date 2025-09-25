package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class AlarmaDesafioMatematicoActivity extends AppCompatActivity {

    private TextView tvTitulo;
    private TextView tvHora;
    private TextView tvCategoria;
    private TextView tvOperacionMatematica;
    private EditText etRespuestaUsuario;
    private Button btnConfirmar;
    
    private String tituloRecordatorio = "Estudiar UX Research";
    private String horaRecordatorio = "7:00 AM";
    private int numero1, numero2, respuestaCorrecta;
    private String operador = "+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_alarma_desafio_matematico);
        
        inicializarVistas();
        generarDesafioMatematico();
        configurarDatos();
        configurarEventos();
    }
    
    private void inicializarVistas() {
        tvTitulo = findViewById(R.id.tv_titulo_recordatorio);
        tvHora = findViewById(R.id.tv_hora_recordatorio);
        tvCategoria = findViewById(R.id.tv_categoria);
        tvOperacionMatematica = findViewById(R.id.tv_operacion_matematica);
        etRespuestaUsuario = findViewById(R.id.et_respuesta_usuario);
        btnConfirmar = findViewById(R.id.btn_confirmar);
    }
    
    private void generarDesafioMatematico() {
        Random random = new Random();
        
        // Generar números aleatorios entre 10 y 99 para hacer el desafío más interesante
        numero1 = random.nextInt(90) + 10; // 10-99
        numero2 = random.nextInt(90) + 10; // 10-99
        
        // Por simplicidad, usar solo suma. Puedes agregar más operadores después
        respuestaCorrecta = numero1 + numero2;
        
        // Configurar la operación en el TextView
        String operacion = numero1 + " " + operador + " " + numero2 + " = ?";
        tvOperacionMatematica.setText(operacion);
    }
    
    private void configurarDatos() {
        // Obtener datos del Intent si están disponibles
        Intent intent = getIntent();
        if (intent.hasExtra("TITULO_RECORDATORIO")) {
            tituloRecordatorio = intent.getStringExtra("TITULO_RECORDATORIO");
        }
        if (intent.hasExtra("HORA_RECORDATORIO")) {
            horaRecordatorio = intent.getStringExtra("HORA_RECORDATORIO");
        }
        
        // Configurar la información en las vistas
        tvTitulo.setText(tituloRecordatorio);
        tvHora.setText(horaRecordatorio);
        tvCategoria.setText("● ESTUDIOS");
    }
    
    private void configurarEventos() {
        // Inicialmente el botón está desactivado
        btnConfirmar.setEnabled(false);
        btnConfirmar.setAlpha(0.5f);
        
        // TextWatcher para validar la respuesta en tiempo real
        etRespuestaUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No necesario
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String respuestaTexto = s.toString().trim();
                
                // Validar si la respuesta es correcta
                boolean esCorrecta = false;
                if (!respuestaTexto.isEmpty()) {
                    try {
                        int respuestaUsuario = Integer.parseInt(respuestaTexto);
                        esCorrecta = respuestaUsuario == respuestaCorrecta;
                    } catch (NumberFormatException e) {
                        esCorrecta = false;
                    }
                }
                
                // Habilitar/deshabilitar botón y cambiar apariencia
                btnConfirmar.setEnabled(esCorrecta);
                
                // Cambiar apariencia del botón según respuesta
                if (esCorrecta) {
                    // Verde cuando es correcta
                    btnConfirmar.setAlpha(1.0f);
                    btnConfirmar.setBackgroundResource(R.drawable.confirm_button_correct_background);
                } else {
                    // Gris cuando es incorrecta
                    btnConfirmar.setAlpha(0.5f);
                    btnConfirmar.setBackgroundResource(R.drawable.confirm_button_background);
                }
                
                // Cambiar color del texto según sea correcta o incorrecta
                if (respuestaTexto.isEmpty()) {
                    etRespuestaUsuario.setTextColor(getResources().getColor(android.R.color.black));
                } else if (esCorrecta) {
                    etRespuestaUsuario.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    etRespuestaUsuario.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No necesario
            }
        });
        
        btnConfirmar.setOnClickListener(v -> {
            // Solo proceder si el botón está habilitado
            if (btnConfirmar.isEnabled()) {
                // Desafío completado exitosamente, volver al home
                Intent homeIntent = new Intent(AlarmaDesafioMatematicoActivity.this, HomeScreenActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(homeIntent);
                finish();
            }
        });
    }
    
    @Override
    public void onBackPressed() {
        // No permitir salir hasta que se resuelva el desafío correctamente
        // El usuario debe resolver la operación para poder continuar
        return;
    }
}