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
        
        // Hide the default ActionBar to avoid double header
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_configuracion);

        drawerLayout = findViewById(R.id.drawer_layout);
        
        ImageView menuButton = findViewById(R.id.menu_button);
        
        // Cards de configuración
        LinearLayout cardCuenta = findViewById(R.id.card_cuenta);
        LinearLayout cardPrivacidad = findViewById(R.id.card_privacidad);
        LinearLayout cardNotificaciones = findViewById(R.id.card_notificaciones);
        
        // Navigation menu items
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

        // Navigation menu listeners
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
                // Ya estamos en Configuración, solo cerramos el drawer
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

        // Listeners para los cards de configuración
        cardCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implementar navegación a pantalla de Cuenta
                // startActivity(new Intent(ConfiguracionActivity.this, CuentaActivity.class));
            }
        });

        cardPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implementar navegación a pantalla de Privacidad
                // startActivity(new Intent(ConfiguracionActivity.this, PrivacidadActivity.class));
            }
        });

        cardNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implementar navegación a pantalla de Notificaciones
                // startActivity(new Intent(ConfiguracionActivity.this, NotificacionesActivity.class));
            }
        });
    }
}