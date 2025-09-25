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
                Intent intent = new Intent(GestionarCategoriasActivity.this, NuevaCategoriaActivity.class);
                startActivity(intent);
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
    protected void onResume() {
        super.onResume();
        // Actualizar la vista cuando se regrese de crear nueva categoría
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
        
        // Limpiar el container (excepto el header)
        clearCategoryCards();
        
        // Crear tarjetas dinámicamente para cada categoría
        for (Categoria categoria : categoriasFiltradas) {
            createCategoryCard(categoria);
        }
    }

    private void clearCategoryCards() {
        // Mantener solo el header "MIS CATEGORÍAS" (primer hijo)
        int childCount = categoriasContainer.getChildCount();
        for (int i = childCount - 1; i > 0; i--) {
            categoriasContainer.removeViewAt(i);
        }
    }

    private void createCategoryCard(Categoria categoria) {
        // Inflar el layout de la tarjeta de categoría
        LinearLayout categoryCard = (LinearLayout) getLayoutInflater().inflate(R.layout.item_categoria_card, null);
        
        // Configurar la información de la categoría
        configurarCategoryCard(categoryCard, categoria);
        
        // Agregar al container
        categoriasContainer.addView(categoryCard);
    }

    private void configurarCategoryCard(LinearLayout categoryCard, Categoria categoria) {
        // Obtener referencias a las vistas
        View colorIndicator = categoryCard.findViewById(R.id.color_indicator);
        TextView tvCategoryName = categoryCard.findViewById(R.id.tv_category_name);
        TextView tvCategoryDescription = categoryCard.findViewById(R.id.tv_category_description);
        TextView tvRemindersCount = categoryCard.findViewById(R.id.tv_reminders_count);
        ImageView btnEdit = categoryCard.findViewById(R.id.btn_edit_category);
        ImageView btnDelete = categoryCard.findViewById(R.id.btn_delete_category);

        // Configurar color del indicador
        if (categoria.getColor() != null) {
            try {
                int colorInt = android.graphics.Color.parseColor(categoria.getColor());
                colorIndicator.setBackgroundColor(colorInt);
            } catch (IllegalArgumentException e) {
                colorIndicator.setBackgroundColor(android.graphics.Color.parseColor("#10B981"));
            }
        }

        // Configurar nombre de la categoría con icono
        String categoryText = categoria.getTextoCompleto();
        tvCategoryName.setText(categoryText);
        
        // Configurar background del chip con el color de la categoría
        if (categoria.getColor() != null) {
            try {
                int colorInt = android.graphics.Color.parseColor(categoria.getColor());
                android.graphics.drawable.GradientDrawable drawable = new android.graphics.drawable.GradientDrawable();
                drawable.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
                drawable.setColor(colorInt);
                drawable.setCornerRadius(12f * getResources().getDisplayMetrics().density);
                tvCategoryName.setBackground(drawable);
            } catch (IllegalArgumentException e) {
                tvCategoryName.setBackgroundResource(R.drawable.category_chip_dynamic);
            }
        }

        // Configurar descripción basada en vibración
        String descripcion = "Relajante";
        if (categoria.getDescripcion() != null) {
            if (categoria.getDescripcion().contains("Suave")) {
                descripcion += " • Suave";
            } else if (categoria.getDescripcion().contains("Normal")) {
                descripcion += " • Normal";
            } else if (categoria.getDescripcion().contains("Energética")) {
                descripcion = "Energética • Fuerte";
            }
        }
        tvCategoryDescription.setText(descripcion);

        // Configurar contador de recordatorios (por ahora 0)
        tvRemindersCount.setText("0 recordatorios activos");

        // Configurar listeners de botones
        btnEdit.setOnClickListener(v -> {
            // TODO: Implementar editar categoría
        });

        btnDelete.setOnClickListener(v -> {
            // Eliminar categoría del manager
            CategoriasManager.getInstance().eliminarCategoria(categoria);
            
            // Navegar a pantalla de eliminación exitosa
            Intent intent = new Intent(GestionarCategoriasActivity.this, CategoriaEliminadaExitosamenteActivity.class);
            startActivity(intent);
        });
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