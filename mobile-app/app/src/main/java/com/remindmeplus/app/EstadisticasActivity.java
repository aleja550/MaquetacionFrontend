package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class EstadisticasActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        // Setup back button
        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.nav_home) {
            startActivity(new Intent(this, HomeScreenActivity.class));
        } else if (id == R.id.nav_recordatorios) {
            startActivity(new Intent(this, GestionarRecordatoriosActivity.class));
        } else if (id == R.id.nav_categorias) {
            startActivity(new Intent(this, GestionarCategoriasActivity.class));
        } else if (id == R.id.nav_configuracion) {
            startActivity(new Intent(this, ConfiguracionActivity.class));
        } else if (id == R.id.nav_ayuda) {
            startActivity(new Intent(this, AyudaActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}