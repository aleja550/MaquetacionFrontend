package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class GestionarCategoriasActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout categoriasContainer;
    private CategoriasManager categoriasManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Hide the default ActionBar to avoid double header
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_gestionar_categorias);

        drawerLayout = findViewById(R.id.drawer_layout);
        categoriasContainer = findViewById(R.id.categorias_container);
        categoriasManager = CategoriasManager.getInstance();
        
        ImageView menuButton = findViewById(R.id.menu_button);
        EditText searchBar = findViewById(R.id.search_bar);
        FloatingActionButton fabAddCategory = findViewById(R.id.fab_add_category);
        
        // Navigation menu items
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navRecordatorios = findViewById(R.id.nav_recordatorios);
        LinearLayout navCategorias = findViewById(R.id.nav_categorias);
        LinearLayout navEstadisticas = findViewById(R.id.nav_estadisticas);
        LinearLayout navConfiguracion = findViewById(R.id.nav_configuracion);
        LinearLayout navAyuda = findViewById(R.id.nav_ayuda);
        
        // Edit buttons
        ImageView editSalud = findViewById(R.id.edit_salud);
        ImageView deleteSalud = findViewById(R.id.delete_salud);
        ImageView editEjercicio = findViewById(R.id.edit_ejercicio);
        ImageView deleteEjercicio = findViewById(R.id.delete_ejercicio);
        ImageView editTrabajo = findViewById(R.id.edit_trabajo);
        ImageView deleteTrabajo = findViewById(R.id.delete_trabajo);
        ImageView editEstudio = findViewById(R.id.edit_estudio);
        ImageView deleteEstudio = findViewById(R.id.delete_estudio);

        // Search functionality
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Usar el manager para filtrar
                categoriasManager.filtrarCategorias(s.toString());
                // Actualizar la vista
                updateCategoriasView();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        fabAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(GestionarCategoriasActivity.this, NuevaCategoriaActivity.class);
                // startActivity(intent);
            }
        });

        // Navigation menu listeners
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionarCategoriasActivity.this, HomeScreenActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionarCategoriasActivity.this, GestionarRecordatoriosActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ya estamos en Categorías, solo cerramos el drawer
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionarCategoriasActivity.this, EstadisticasActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(GestionarCategoriasActivity.this, ConfiguracionActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(GestionarCategoriasActivity.this, AyudaActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Edit listeners
        editSalud.setOnClickListener(v -> {
            // TODO: Editar categoría Salud
        });
        
        deleteSalud.setOnClickListener(v -> {
            // TODO: Eliminar categoría Salud
        });

        editEjercicio.setOnClickListener(v -> {
            // TODO: Editar categoría Ejercicio
        });
        
        deleteEjercicio.setOnClickListener(v -> {
            // TODO: Eliminar categoría Ejercicio
        });

        editTrabajo.setOnClickListener(v -> {
            // TODO: Editar categoría Trabajo
        });
        
        deleteTrabajo.setOnClickListener(v -> {
            // TODO: Eliminar categoría Trabajo
        });

        editEstudio.setOnClickListener(v -> {
            // TODO: Editar categoría Estudio
        });
        
        deleteEstudio.setOnClickListener(v -> {
            // TODO: Eliminar categoría Estudio
        });

        // Inicializar la vista con todas las categorías
        updateCategoriasView();
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

    private void updateCategoriasView() {
        if (categoriasContainer == null) return;

        // Obtener las categorías filtradas del manager
        List<Categoria> categoriasFiltradas = categoriasManager.getCategoriasFiltradas();
        
        // Ocultar todas las tarjetas primero
        hideAllCategoryCards();
        
        // Mostrar solo las tarjetas de categorías que coinciden con el filtro
        for (Categoria categoria : categoriasFiltradas) {
            showCategoryCard(categoria.getNombre());
        }
    }

    private void hideAllCategoryCards() {
        for (int i = 0; i < categoriasContainer.getChildCount(); i++) {
            View child = categoriasContainer.getChildAt(i);
            if (child instanceof LinearLayout) {
                // Omitir el header "MIS CATEGORÍAS"
                if (child != categoriasContainer.getChildAt(0)) {
                    child.setVisibility(View.GONE);
                }
            }
        }
    }

    private void showCategoryCard(String categoriaNombre) {
        for (int i = 0; i < categoriasContainer.getChildCount(); i++) {
            View child = categoriasContainer.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout categoryCard = (LinearLayout) child;
                
                // Buscar el TextView con el nombre de la categoría
                TextView categoryNameView = findCategoryTitleInCard(categoryCard);
                if (categoryNameView != null) {
                    String cardCategoryName = categoryNameView.getText().toString();
                    
                    // Extraer solo el nombre de la categoría (sin emoji para comparar)
                    String cleanCardName = extractCleanCategoryName(cardCategoryName);
                    String cleanSearchName = extractCleanCategoryName(categoriaNombre);
                    
                    // Si coincide el nombre de la categoría, mostrar la tarjeta
                    if (cleanCardName.toLowerCase().equals(cleanSearchName.toLowerCase())) {
                        categoryCard.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    private String extractCleanCategoryName(String categoryText) {
        // Remover emojis y espacios extra para obtener solo el nombre
        return categoryText
            .replace("💊", "")
            .replace("🏃", "")
            .replace("💼", "")
            .replace("📚", "")
            .trim();
    }

    private TextView findCategoryTitleInCard(LinearLayout card) {
        // Recursively find the category title TextView in the card (same logic as recordatorios)
        return findCategoryTitleTextView(card);
    }
    
    private TextView findCategoryTitleTextView(View parent) {
        if (parent instanceof TextView) {
            TextView textView = (TextView) parent;
            String text = textView.getText().toString();
            
            // The category title is the TextView that contains the category name
            // "Salud 💊" (con ícono) o "Ejercicio", "Trabajo", "Estudio" (sin íconos)
            if ((text.contains("Salud") && text.contains("💊")) ||
                text.equals("Ejercicio") ||
                text.equals("Trabajo") ||
                text.equals("Estudio")) {
                return textView;
            }
        } else if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            for (int i = 0; i < group.getChildCount(); i++) {
                TextView result = findCategoryTitleTextView(group.getChildAt(i));
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

}