package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ConfiguracionActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_configuracion);

        drawerLayout = findViewById(R.id.drawer_layout);
        
        ImageView menuButton = findViewById(R.id.menu_button);
        
        LinearLayout cardCuenta = findViewById(R.id.card_cuenta);
        LinearLayout cardPrivacidad = findViewById(R.id.card_privacidad);
        LinearLayout cardNotificaciones = findViewById(R.id.card_notificaciones);
        
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
                startActivity(new Intent(ConfiguracionActivity.this, HomeScreenActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfiguracionActivity.this, GestionarRecordatoriosActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfiguracionActivity.this, GestionarCategoriasActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfiguracionActivity.this, EstadisticasActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfiguracionActivity.this, AyudaActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        cardCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        cardPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        cardNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}