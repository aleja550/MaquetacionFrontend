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

public class AlarmaFraseActivity extends AppCompatActivity {

    private TextView tvTitulo;
    private TextView tvHora;
    private TextView tvCategoria;
    private TextView tvFraseObjetivo;
    private EditText etFraseUsuario;
    private TextView tvContadorCaracteres;
    private Button btnConfirmar;
    
    private String fraseObjetivo = "Hoy voy a lograr mis metas";
    private String tituloRecordatorio = "Tomar Losartán 50mg";
    private String horaRecordatorio = "08:00 AM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_alarma_frase);
        
        inicializarVistas();
        configurarDatos();
        configurarEventos();
    }
    
    private void inicializarVistas() {
        tvTitulo = findViewById(R.id.tv_titulo_recordatorio);
        tvHora = findViewById(R.id.tv_hora_recordatorio);
        tvCategoria = findViewById(R.id.tv_categoria);
        tvFraseObjetivo = findViewById(R.id.tv_frase_objetivo);
        etFraseUsuario = findViewById(R.id.et_frase_usuario);
        tvContadorCaracteres = findViewById(R.id.tv_contador_caracteres);
        btnConfirmar = findViewById(R.id.btn_confirmar);
    }
    
    private void configurarDatos() {
        
        Intent intent = getIntent();
        if (intent.hasExtra("TITULO_RECORDATORIO")) {
            tituloRecordatorio = intent.getStringExtra("TITULO_RECORDATORIO");
        }
        if (intent.hasExtra("HORA_RECORDATORIO")) {
            horaRecordatorio = intent.getStringExtra("HORA_RECORDATORIO");
        }
        if (intent.hasExtra("FRASE_OBJETIVO")) {
            fraseObjetivo = intent.getStringExtra("FRASE_OBJETIVO");
        }
        
        
        tvTitulo.setText(tituloRecordatorio);
        tvHora.setText(horaRecordatorio);
        tvCategoria.setText("● Salud 💊");
        tvFraseObjetivo.setText(fraseObjetivo);
        
        
        actualizarContador(0);
    }
    
    private void configurarEventos() {
        
        btnConfirmar.setEnabled(false);
        btnConfirmar.setAlpha(0.5f);
        
        
        etFraseUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textoUsuario = s.toString();
                
                
                actualizarContador(textoUsuario.length());
                
                
                boolean coincide = textoUsuario.equals(fraseObjetivo);
                btnConfirmar.setEnabled(coincide);
                
                
                if (coincide) {
                    
                    btnConfirmar.setAlpha(1.0f);
                    btnConfirmar.setBackgroundResource(R.drawable.confirm_button_correct_background);
                } else {
                    
                    btnConfirmar.setAlpha(0.5f);
                    btnConfirmar.setBackgroundResource(R.drawable.confirm_button_background);
                }
                
                
                if (textoUsuario.isEmpty()) {
                    
                    etFraseUsuario.setTextColor(getResources().getColor(android.R.color.black));
                } else if (coincide) {
                    
                    etFraseUsuario.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    
                    etFraseUsuario.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                
            }
        });
        
        btnConfirmar.setOnClickListener(v -> {
            
            if (btnConfirmar.isEnabled()) {
                
                Intent homeIntent = new Intent(AlarmaFraseActivity.this, HomeScreenActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(homeIntent);
                finish();
            }
        });
    }
    
    private void actualizarContador(int caracteresActuales) {
        String contador = caracteresActuales + "/" + fraseObjetivo.length() + " caracteres";
        tvContadorCaracteres.setText(contador);
    }
    
    @Override
    public void onBackPressed() {
        
        
        return;
    }
}