package com.remindmeplus.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class GestionarRecordatoriosActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout recordatoriosContainer;
    private TextView statTotal, statActivos, statProximos, statCompletados;
    private String currentFilter = "Todos";
    private String currentSearchQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_gestionar_recordatorios);

        drawerLayout = findViewById(R.id.drawer_layout);
        recordatoriosContainer = findViewById(R.id.reminders_container);
        
        statTotal = findViewById(R.id.stat_total);
        statActivos = findViewById(R.id.stat_activos);  
        statProximos = findViewById(R.id.stat_proximos);
        statCompletados = findViewById(R.id.stat_completados);
        
        ImageView menuButton = findViewById(R.id.menu_button);
        EditText searchBar = findViewById(R.id.search_edittext);
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        
        
        LinearLayout tabTodos = findViewById(R.id.tab_todos);
        LinearLayout tabHoy = findViewById(R.id.tab_hoy);
        LinearLayout tabAtrasados = findViewById(R.id.tab_atrasados);
        LinearLayout tabCompletados = findViewById(R.id.tab_completados);
        
        
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navRecordatorios = findViewById(R.id.nav_recordatorios);
        LinearLayout navCategorias = findViewById(R.id.nav_categorias);
        LinearLayout navEstadisticas = findViewById(R.id.nav_estadisticas);
        LinearLayout navConfiguracion = findViewById(R.id.nav_configuracion);
        LinearLayout navAyuda = findViewById(R.id.nav_ayuda);

        
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentSearchQuery = s.toString();
                applyFiltersAndSearch();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestionarRecordatoriosActivity.this, NuevoRecordatorioActivity.class);
                startActivity(intent);
            }
        });

        
        tabTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFilter = "Todos";
                selectTab(tabTodos, tabHoy, tabAtrasados, tabCompletados);
                applyFiltersAndSearch();
            }
        });

        tabHoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFilter = "Hoy";
                selectTab(tabHoy, tabTodos, tabAtrasados, tabCompletados);
                applyFiltersAndSearch();
            }
        });

        tabAtrasados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFilter = "Atrasados";
                selectTab(tabAtrasados, tabTodos, tabHoy, tabCompletados);
                applyFiltersAndSearch();
            }
        });

        tabCompletados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFilter = "Completados";
                selectTab(tabCompletados, tabTodos, tabHoy, tabAtrasados);
                applyFiltersAndSearch();
            }
        });

        
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionarRecordatoriosActivity.this, HomeScreenActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionarRecordatoriosActivity.this, GestionarCategoriasActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionarRecordatoriosActivity.this, EstadisticasActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionarRecordatoriosActivity.this, ConfiguracionActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionarRecordatoriosActivity.this, AyudaActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        
        loadRecordatorios();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        loadRecordatorios();
    }
    
    private void loadRecordatorios() {
        updateStatistics();
        updateRecordatoriosList();
    }
    
    private void updateStatistics() {
        RecordatoriosManager manager = RecordatoriosManager.getInstance();
        statTotal.setText(String.valueOf(manager.getTotalCount()));
        statActivos.setText(String.valueOf(manager.getActivosCount()));
        statProximos.setText(String.valueOf(manager.getProximosCount()));
        statCompletados.setText(String.valueOf(manager.getCompletadosCount()));
    }
    
    private void updateRecordatoriosList() {
        applyFiltersAndSearch();
    }

    private void updateRecordatoriosList(List<Recordatorio> recordatorios) {
        recordatoriosContainer.removeAllViews();
        
        for (Recordatorio recordatorio : recordatorios) {
            View recordatorioView = createRecordatorioView(recordatorio);
            recordatoriosContainer.addView(recordatorioView);
        }
    }
    
    private View createRecordatorioView(Recordatorio recordatorio) {
        LinearLayout mainLayout = new LinearLayout(this);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        mainParams.bottomMargin = (int) (8 * getResources().getDisplayMetrics().density);
        mainLayout.setLayoutParams(mainParams);
        mainLayout.setOrientation(LinearLayout.HORIZONTAL);
        mainLayout.setGravity(android.view.Gravity.TOP);
        int padding = (int) (16 * getResources().getDisplayMetrics().density);
        mainLayout.setPadding(padding, padding, padding, padding);
        String categoria = recordatorio.getCategoria().toLowerCase();
        switch (categoria) {
            case "salud":
                mainLayout.setBackgroundResource(R.drawable.card_border_salud);
                break;
            case "trabajo":
                mainLayout.setBackgroundResource(R.drawable.card_border_trabajo);
                break;
            case "estudio":
                mainLayout.setBackgroundResource(R.drawable.card_border_estudio);
                break;
            case "ejercicio":
                mainLayout.setBackgroundResource(R.drawable.card_border_ejercicio);
                break;
            default:
                mainLayout.setBackgroundResource(R.drawable.card_border_salud);
                break;
        }
        
        
        LinearLayout contentLayout = new LinearLayout(this);
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(
            0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f
        );
        contentLayout.setLayoutParams(contentParams);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        TextView categoryLabel = new TextView(this);
        String categoriaText = recordatorio.getCategoria();
        String emoji = recordatorio.getCategoriaEmoji();
        if (!emoji.isEmpty()) {
            categoriaText += " " + emoji;
        }
        categoryLabel.setText(categoriaText);
        categoryLabel.setTextSize(12);
        categoryLabel.setTextColor(Color.WHITE);
        switch (categoria) {
            case "salud":
                categoryLabel.setBackgroundResource(R.drawable.category_chip_salud);
                break;
            case "trabajo":
                categoryLabel.setBackgroundResource(R.drawable.category_chip_trabajo);
                break;
            case "estudio":
                categoryLabel.setBackgroundResource(R.drawable.category_chip_estudio);
                break;
            case "ejercicio":
                categoryLabel.setBackgroundResource(R.drawable.category_chip_ejercicio);
                break;
            default:
                categoryLabel.setBackgroundResource(R.drawable.category_chip_salud);
                break;
        }
        
        categoryLabel.setPadding(8, 2, 8, 2);
        LinearLayout.LayoutParams catParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        catParams.bottomMargin = (int) (4 * getResources().getDisplayMetrics().density);
        categoryLabel.setLayoutParams(catParams);
        contentLayout.addView(categoryLabel);
        TextView titleLabel = new TextView(this);
        titleLabel.setText(recordatorio.getTitulo());
        titleLabel.setTextSize(16);
        titleLabel.setTextColor(Color.parseColor("#333333"));
        titleLabel.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        titleParams.bottomMargin = (int) (4 * getResources().getDisplayMetrics().density);
        titleLabel.setLayoutParams(titleParams);
        contentLayout.addView(titleLabel);
        TextView statusLabel = new TextView(this);
        statusLabel.setText("● " + recordatorio.getEstado());
        statusLabel.setTextSize(12);
        statusLabel.setTextColor(getStatusColor(recordatorio.getEstado()));
        contentLayout.addView(statusLabel);
        
        mainLayout.addView(contentLayout);
        
        
        LinearLayout actionsLayout = new LinearLayout(this);
        actionsLayout.setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        actionsLayout.setOrientation(LinearLayout.VERTICAL);
        actionsLayout.setGravity(android.view.Gravity.CENTER);
        
        
        LinearLayout iconsLayout = new LinearLayout(this);
        LinearLayout.LayoutParams iconsParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        iconsParams.bottomMargin = (int) (4 * getResources().getDisplayMetrics().density);
        iconsLayout.setLayoutParams(iconsParams);
        iconsLayout.setOrientation(LinearLayout.HORIZONTAL);
        
        
        ImageView editIcon = new ImageView(this);
        editIcon.setImageResource(R.drawable.ic_edit);
        editIcon.setColorFilter(Color.parseColor("#666666"));
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(
            (int) (20 * getResources().getDisplayMetrics().density),
            (int) (20 * getResources().getDisplayMetrics().density)
        );
        editParams.rightMargin = (int) (8 * getResources().getDisplayMetrics().density);
        editIcon.setLayoutParams(editParams);
        
        
        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarRecordatorio(recordatorio);
            }
        });
        
        iconsLayout.addView(editIcon);
        
        
        ImageView deleteIcon = new ImageView(this);
        deleteIcon.setImageResource(R.drawable.ic_delete);
        deleteIcon.setColorFilter(Color.parseColor("#F44336"));
        deleteIcon.setLayoutParams(new LinearLayout.LayoutParams(
            (int) (20 * getResources().getDisplayMetrics().density),
            (int) (20 * getResources().getDisplayMetrics().density)
        ));
        
        
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarRecordatorio(recordatorio);
            }
        });
        
        iconsLayout.addView(deleteIcon);
        
        actionsLayout.addView(iconsLayout);
        
        
        TextView timeLabel = new TextView(this);
        String hora = RecordatoriosManager.getInstance().extraerHora(recordatorio.getFechaHora());
        timeLabel.setText(hora);
        timeLabel.setTextSize(12);
        timeLabel.setTextColor(getTimeColor(recordatorio.getEstado()));
        actionsLayout.addView(timeLabel);
        
        mainLayout.addView(actionsLayout);
        
        return mainLayout;
    }
    
    private int getStatusColor(String estado) {
        switch (estado) {
            case "Activo":
                return Color.parseColor("#4CAF50");
            case "Próximo":
                return Color.parseColor("#FF9800");
            case "Atrasado":
                return Color.parseColor("#F44336");
            case "Completado":
                return Color.parseColor("#666666");
            default:
                return Color.parseColor("#666666");
        }
    }
    
    private int getTimeColor(String estado) {
        if ("Atrasado".equals(estado)) {
            return Color.parseColor("#F44336");
        }
        return Color.parseColor("#666666");
    }

    private void selectTab(LinearLayout selectedTab, LinearLayout... otherTabs) {
        
        selectedTab.setBackgroundResource(R.drawable.activate_button_background);
        TextView selectedText = (TextView) selectedTab.getChildAt(0);
        selectedText.setTextColor(0xFFFFFFFF);
        
        
        for (LinearLayout tab : otherTabs) {
            tab.setBackgroundColor(0x00000000); 
            TextView tabText = (TextView) tab.getChildAt(0);
            tabText.setTextColor(0xFF666666);
        }
    }

    private void applyFiltersAndSearch() {
        RecordatoriosManager manager = RecordatoriosManager.getInstance();
        List<Recordatorio> recordatoriosFiltrados;

        
        switch (currentFilter) {
            case "Hoy":
                recordatoriosFiltrados = manager.getRecordatoriosHoy();
                break;
            case "Atrasados":
                recordatoriosFiltrados = manager.getRecordatoriosAtrasados();
                break;
            case "Completados":
                recordatoriosFiltrados = manager.getRecordatoriosCompletados();
                break;
            case "Todos":
            default:
                recordatoriosFiltrados = manager.getRecordatorios();
                break;
        }

        
        if (!currentSearchQuery.trim().isEmpty()) {
            List<Recordatorio> recordatoriosBuscados = new ArrayList<>();
            String searchLower = currentSearchQuery.toLowerCase().trim();
            
            for (Recordatorio recordatorio : recordatoriosFiltrados) {
                if (recordatorio.getTitulo().toLowerCase().contains(searchLower) ||
                    recordatorio.getCategoria().toLowerCase().contains(searchLower)) {
                    recordatoriosBuscados.add(recordatorio);
                }
            }
            recordatoriosFiltrados = recordatoriosBuscados;
        }

        updateRecordatoriosList(recordatoriosFiltrados);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    
    private void editarRecordatorio(Recordatorio recordatorio) {
        Intent intent = new Intent(this, NuevoRecordatorioActivity.class);
        intent.putExtra("MODO_EDICION", true);
        intent.putExtra("RECORDATORIO_ID", recordatorio.getId());
        intent.putExtra("RECORDATORIO_TITULO", recordatorio.getTitulo());
        intent.putExtra("RECORDATORIO_FECHA_HORA", recordatorio.getFechaHora());
        intent.putExtra("RECORDATORIO_CATEGORIA", recordatorio.getCategoria());
        intent.putExtra("RECORDATORIO_REPETIR", recordatorio.getRepetir());
        intent.putExtra("RECORDATORIO_ANTI_POSTPONER", recordatorio.getAntiPostponer());
        startActivity(intent);
    }

    private void eliminarRecordatorio(Recordatorio recordatorio) {
        
        RecordatoriosManager manager = RecordatoriosManager.getInstance();
        boolean removed = manager.eliminarRecordatorio(recordatorio);
        
        if (removed) {
            
            Intent intent = new Intent(this, RecordatorioEliminadoExitosamenteActivity.class);
            intent.putExtra("titulo", recordatorio.getTitulo());
            startActivity(intent);
        }
    }

}