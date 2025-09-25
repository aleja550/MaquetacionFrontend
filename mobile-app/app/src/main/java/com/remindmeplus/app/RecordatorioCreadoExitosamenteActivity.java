package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecordatorioCreadoExitosamenteActivity extends AppCompatActivity {

    private TextView detailTitulo, detailFecha, detailCategoria, detailAntiPostponer, detailRepetir;
    private Button btnVerRecordatorios, btnCrearOtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_recordatorio_creado_exitosamente);

        initViews();
        loadReminderDetails();
        setupClickListeners();
    }

    private void initViews() {
        detailTitulo = findViewById(R.id.detail_titulo);
        detailFecha = findViewById(R.id.detail_fecha);
        detailCategoria = findViewById(R.id.detail_categoria);
        detailAntiPostponer = findViewById(R.id.detail_anti_postponer);
        detailRepetir = findViewById(R.id.detail_repetir);
        
        btnVerRecordatorios = findViewById(R.id.btn_ver_recordatorios);
        btnCrearOtro = findViewById(R.id.btn_crear_otro);
    }

    private void loadReminderDetails() {
        
        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String fecha = intent.getStringExtra("fecha");
        String categoria = intent.getStringExtra("categoria");
        String antiPostponer = intent.getStringExtra("antiPostponer");
        String repetir = intent.getStringExtra("repetir");

        
        if (titulo != null) detailTitulo.setText(titulo);
        if (fecha != null) detailFecha.setText(fecha);
        if (categoria != null) detailCategoria.setText(categoria);
        if (antiPostponer != null) detailAntiPostponer.setText(antiPostponer);
        if (repetir != null) detailRepetir.setText(repetir);
    }

    private void setupClickListeners() {
        btnVerRecordatorios.setOnClickListener(v -> {
            Intent intent = new Intent(this, GestionarRecordatoriosActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        btnCrearOtro.setOnClickListener(v -> {
            Intent intent = new Intent(this, NuevoRecordatorioActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        
        Intent intent = new Intent(this, GestionarRecordatoriosActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}