package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecordatorioEliminadoExitosamenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_recordatorio_eliminado_exitosamente);

        String titulo = getIntent().getStringExtra("titulo");
        
        TextView titleText = findViewById(R.id.deleted_reminder_title);
        if (titulo != null && !titulo.isEmpty()) {
            titleText.setText(titulo);
        }

        Button btnVolver = findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, GestionarRecordatoriosActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GestionarRecordatoriosActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}