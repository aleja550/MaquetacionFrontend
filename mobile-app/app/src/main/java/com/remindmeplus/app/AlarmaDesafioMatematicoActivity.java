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
        
        
        numero1 = random.nextInt(90) + 10; 
        numero2 = random.nextInt(90) + 10; 
        
        
        respuestaCorrecta = numero1 + numero2;
        
        
        String operacion = numero1 + " " + operador + " " + numero2 + " = ?";
        tvOperacionMatematica.setText(operacion);
    }
    
    private void configurarDatos() {
        
        Intent intent = getIntent();
        if (intent.hasExtra("TITULO_RECORDATORIO")) {
            tituloRecordatorio = intent.getStringExtra("TITULO_RECORDATORIO");
        }
        if (intent.hasExtra("HORA_RECORDATORIO")) {
            horaRecordatorio = intent.getStringExtra("HORA_RECORDATORIO");
        }
        
        
        tvTitulo.setText(tituloRecordatorio);
        tvHora.setText(horaRecordatorio);
        tvCategoria.setText("● ESTUDIOS");
    }
    
    private void configurarEventos() {
        
        btnConfirmar.setEnabled(false);
        btnConfirmar.setAlpha(0.5f);
        
        
        etRespuestaUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String respuestaTexto = s.toString().trim();
                
                
                boolean esCorrecta = false;
                if (!respuestaTexto.isEmpty()) {
                    try {
                        int respuestaUsuario = Integer.parseInt(respuestaTexto);
                        esCorrecta = respuestaUsuario == respuestaCorrecta;
                    } catch (NumberFormatException e) {
                        esCorrecta = false;
                    }
                }
                
                
                btnConfirmar.setEnabled(esCorrecta);
                
                
                if (esCorrecta) {
                    
                    btnConfirmar.setAlpha(1.0f);
                    btnConfirmar.setBackgroundResource(R.drawable.confirm_button_correct_background);
                } else {
                    
                    btnConfirmar.setAlpha(0.5f);
                    btnConfirmar.setBackgroundResource(R.drawable.confirm_button_background);
                }
                
                
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
                
            }
        });
        
        btnConfirmar.setOnClickListener(v -> {
            
            if (btnConfirmar.isEnabled()) {
                
                Intent homeIntent = new Intent(AlarmaDesafioMatematicoActivity.this, HomeScreenActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(homeIntent);
                finish();
            }
        });
    }
    
    @Override
    public void onBackPressed() {
        
        
        return;
    }
}