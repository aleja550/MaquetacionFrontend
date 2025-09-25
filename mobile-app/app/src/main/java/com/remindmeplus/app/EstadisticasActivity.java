package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class EstadisticasActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_estadisticas);

        drawerLayout = findViewById(R.id.drawer_layout);
        
        ImageView menuButton = findViewById(R.id.menu_button);
        
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navRecordatorios = findViewById(R.id.nav_recordatorios);
        LinearLayout navCategorias = findViewById(R.id.nav_categorias);
        LinearLayout navEstadisticas = findViewById(R.id.nav_estadisticas);
        LinearLayout navConfiguracion = findViewById(R.id.nav_configuracion);
        LinearLayout navAyuda = findViewById(R.id.nav_ayuda);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EstadisticasActivity.this, HomeScreenActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EstadisticasActivity.this, GestionarRecordatoriosActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EstadisticasActivity.this, GestionarCategoriasActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EstadisticasActivity.this, ConfiguracionActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EstadisticasActivity.this, AyudaActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}