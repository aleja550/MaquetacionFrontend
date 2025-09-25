package com.remindmeplus.app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class NuevoRecordatorioActivity extends AppCompatActivity {

    private EditText editTitulo;
    private TextView textFechaHora, textCategoria, textRepetir;
    private TextView asteriscoTitulo, asteriscoFecha;
    private LinearLayout layoutFechaHora, layoutCategoria, layoutRepetir;
    private LinearLayout radioMatematica, radioFrase, radioAleatorio;
    private RadioButton rbMatematica, rbFrase, rbAleatorio;
    private Button btnGuardar;
    private ImageView btnBack;

    // Validation flags
    private boolean tituloValid = false;
    private boolean fechaValid = false;
    private String selectedFechaHora = "";

    // Selected values
    private String selectedCategoria = "Salud";
    private String selectedRepetir = "Una vez";
    private String selectedAntiPostponer = "Resolver operación matemática";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Hide the default ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_nuevo_recordatorio);

        initViews();
        setupValidation();
        setupClickListeners();
        updateButtonState();
    }

    private void initViews() {
        editTitulo = findViewById(R.id.edit_titulo);
        textFechaHora = findViewById(R.id.text_fecha_hora);
        textCategoria = findViewById(R.id.text_categoria);
        textRepetir = findViewById(R.id.text_repetir);
        
        asteriscoTitulo = findViewById(R.id.asterisco_titulo);
        asteriscoFecha = findViewById(R.id.asterisco_fecha);
        
        layoutFechaHora = findViewById(R.id.layout_fecha_hora);
        layoutCategoria = findViewById(R.id.layout_categoria);
        layoutRepetir = findViewById(R.id.layout_repetir);
        
        radioMatematica = findViewById(R.id.radio_matematica);
        radioFrase = findViewById(R.id.radio_frase);
        radioAleatorio = findViewById(R.id.radio_aleatorio);
        
        rbMatematica = findViewById(R.id.rb_matematica);
        rbFrase = findViewById(R.id.rb_frase);
        rbAleatorio = findViewById(R.id.rb_aleatorio);
        
        btnGuardar = findViewById(R.id.btn_guardar);
        btnBack = findViewById(R.id.btn_back);
    }

    private void setupValidation() {
        // Título validation
        editTitulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tituloValid = s.toString().trim().length() > 0;
                asteriscoTitulo.setVisibility(tituloValid ? View.GONE : View.VISIBLE);
                updateButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void updateButtonState() {
        boolean allValid = tituloValid && fechaValid;
        
        btnGuardar.setEnabled(allValid);
        if (allValid) {
            btnGuardar.setBackgroundColor(0xFF4A90E2); // Blue
            btnGuardar.setTextColor(0xFFFFFFFF); // White
        } else {
            btnGuardar.setBackgroundColor(0xFFCCCCCC); // Gray
            btnGuardar.setTextColor(0xFF666666); // Dark gray
        }
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());
        
        layoutFechaHora.setOnClickListener(v -> showDateTimePicker());
        layoutCategoria.setOnClickListener(v -> showCategoriaSelector());
        layoutRepetir.setOnClickListener(v -> showRepetirSelector());
        
        // Radio buttons functionality
        radioMatematica.setOnClickListener(v -> selectRadioOption(1));
        radioFrase.setOnClickListener(v -> selectRadioOption(2));
        radioAleatorio.setOnClickListener(v -> selectRadioOption(3));
        
        rbMatematica.setOnClickListener(v -> selectRadioOption(1));
        rbFrase.setOnClickListener(v -> selectRadioOption(2));
        rbAleatorio.setOnClickListener(v -> selectRadioOption(3));
        
        btnGuardar.setOnClickListener(v -> guardarRecordatorio());
    }

    private void selectRadioOption(int option) {
        // Reset all radio buttons
        rbMatematica.setChecked(false);
        rbFrase.setChecked(false);
        rbAleatorio.setChecked(false);
        
        // Reset all backgrounds
        radioMatematica.setBackgroundColor(0xFFFFFFFF); // White
        radioFrase.setBackgroundColor(0xFFFFFFFF);
        radioAleatorio.setBackgroundColor(0xFFFFFFFF);
        
        // Set selected option
        switch (option) {
            case 1:
                rbMatematica.setChecked(true);
                radioMatematica.setBackgroundColor(0xFFE3F2FD); // Light blue
                selectedAntiPostponer = "Resolver operación matemática";
                break;
            case 2:
                rbFrase.setChecked(true);
                radioFrase.setBackgroundColor(0xFFE3F2FD);
                selectedAntiPostponer = "Escribir frase motivacional";
                break;
            case 3:
                rbAleatorio.setChecked(true);
                radioAleatorio.setBackgroundColor(0xFFE3F2FD);
                selectedAntiPostponer = "Aleatorio (recomendado)";
                break;
        }
    }

    private void showDateTimePicker() {
        Calendar calendar = Calendar.getInstance();
        
        // First show date picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    // Then show time picker
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                            (timeView, hourOfDay, minute) -> {
                                selectedFechaHora = String.format("%02d/%02d/%d - %02d:%02d", 
                                    dayOfMonth, month + 1, year, hourOfDay, minute);
                                textFechaHora.setText(selectedFechaHora);
                                textFechaHora.setTextColor(0xFF333333); // Change to normal color
                                fechaValid = true;
                                asteriscoFecha.setVisibility(View.GONE);
                                updateButtonState();
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true);
                    timePickerDialog.show();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showCategoriaSelector() {
        String[] categorias = {
            "Salud",
            "Trabajo", 
            "Estudio",
            "Ejercicio"
        };
        
        int selectedIndex = 0;
        for (int i = 0; i < categorias.length; i++) {
            if (categorias[i].equals(selectedCategoria)) {
                selectedIndex = i;
                break;
            }
        }
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar Categoría");
        builder.setSingleChoiceItems(categorias, selectedIndex, (dialog, which) -> {
            selectedCategoria = categorias[which];
            textCategoria.setText(selectedCategoria);
            dialog.dismiss();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void showRepetirSelector() {
        String[] opciones = {
            "Una vez",
            "Diariamente",
            "Semanalmente",
            "Mensualmente"
        };
        
        int selectedIndex = 0;
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(selectedRepetir)) {
                selectedIndex = i;
                break;
            }
        }
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Repetir");
        builder.setSingleChoiceItems(opciones, selectedIndex, (dialog, which) -> {
            selectedRepetir = opciones[which];
            textRepetir.setText(selectedRepetir);
            dialog.dismiss();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void guardarRecordatorio() {
        String titulo = editTitulo.getText().toString().trim();
        
        // Create new recordatorio and add to manager
        Recordatorio nuevoRecordatorio = new Recordatorio(
            titulo, 
            selectedFechaHora, 
            selectedCategoria, 
            selectedAntiPostponer, 
            selectedRepetir
        );
        
        // Add to manager
        RecordatoriosManager.getInstance().agregarRecordatorio(nuevoRecordatorio);
        
        // Create intent for success activity
        Intent intent = new Intent(this, RecordatorioCreadoExitosamenteActivity.class);
        intent.putExtra("titulo", titulo);
        intent.putExtra("fecha", selectedFechaHora);
        intent.putExtra("categoria", selectedCategoria);
        intent.putExtra("antiPostponer", selectedAntiPostponer);
        intent.putExtra("repetir", selectedRepetir);
        
        startActivity(intent);
        finish();
    }
}