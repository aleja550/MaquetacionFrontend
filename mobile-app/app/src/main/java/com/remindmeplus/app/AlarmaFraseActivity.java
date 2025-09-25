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
        // Obtener datos del Intent si están disponibles
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
        
        // Configurar la información en las vistas
        tvTitulo.setText(tituloRecordatorio);
        tvHora.setText(horaRecordatorio);
        tvCategoria.setText("● Salud 💊");
        tvFraseObjetivo.setText(fraseObjetivo);
        
        // Inicializar contador
        actualizarContador(0);
    }
    
    private void configurarEventos() {
        // Inicialmente el botón está desactivado
        btnConfirmar.setEnabled(false);
        btnConfirmar.setAlpha(0.5f);
        
        // TextWatcher para validar la frase en tiempo real
        etFraseUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No necesario
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textoUsuario = s.toString();
                
                // Actualizar contador de caracteres
                actualizarContador(textoUsuario.length());
                
                // Validar si coincide exactamente con la frase objetivo
                boolean coincide = textoUsuario.equals(fraseObjetivo);
                btnConfirmar.setEnabled(coincide);
                
                // Cambiar apariencia del botón según coincidencia
                if (coincide) {
                    // Verde cuando coincide exactamente
                    btnConfirmar.setAlpha(1.0f);
                    btnConfirmar.setBackgroundResource(R.drawable.confirm_button_correct_background);
                } else {
                    // Gris cuando no coincide
                    btnConfirmar.setAlpha(0.5f);
                    btnConfirmar.setBackgroundResource(R.drawable.confirm_button_background);
                }
                
                // Cambiar color del texto según coincidencia
                if (textoUsuario.isEmpty()) {
                    // Texto normal cuando está vacío
                    etFraseUsuario.setTextColor(getResources().getColor(android.R.color.black));
                } else if (coincide) {
                    // Verde cuando coincide exactamente
                    etFraseUsuario.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    // Rojo cuando no coincide
                    etFraseUsuario.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
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
                // Alarma confirmada exitosamente, regresar al home
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
        // No permitir salir hasta que se escriba la frase correctamente
        // El usuario debe completar el desafío para poder continuar
        return;
    }
}