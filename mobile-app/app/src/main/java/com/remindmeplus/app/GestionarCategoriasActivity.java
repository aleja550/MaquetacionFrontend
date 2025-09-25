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
        
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navRecordatorios = findViewById(R.id.nav_recordatorios);
        LinearLayout navCategorias = findViewById(R.id.nav_categorias);
        LinearLayout navEstadisticas = findViewById(R.id.nav_estadisticas);
        LinearLayout navConfiguracion = findViewById(R.id.nav_configuracion);
        LinearLayout navAyuda = findViewById(R.id.nav_ayuda);
        
        ImageView editSalud = findViewById(R.id.edit_salud);
        ImageView deleteSalud = findViewById(R.id.delete_salud);
        ImageView editEjercicio = findViewById(R.id.edit_ejercicio);
        ImageView deleteEjercicio = findViewById(R.id.delete_ejercicio);
        ImageView editTrabajo = findViewById(R.id.edit_trabajo);
        ImageView deleteTrabajo = findViewById(R.id.delete_trabajo);
        ImageView editEstudio = findViewById(R.id.edit_estudio);
        ImageView deleteEstudio = findViewById(R.id.delete_estudio);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                categoriasManager.filtrarCategorias(s.toString());
                updateCategoriasView();
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

        fabAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestionarCategoriasActivity.this, NuevaCategoriaActivity.class);
                startActivity(intent);
            }
        });

        
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
                
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        editSalud.setOnClickListener(v -> {
        });
        
        deleteSalud.setOnClickListener(v -> {
        });

        editEjercicio.setOnClickListener(v -> {
        });
        
        deleteEjercicio.setOnClickListener(v -> {
        });

        editTrabajo.setOnClickListener(v -> {
        });
        
        deleteTrabajo.setOnClickListener(v -> {
        });

        editEstudio.setOnClickListener(v -> {
        });
        
        deleteEstudio.setOnClickListener(v -> {
        });

        
        updateCategoriasView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        
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

        
        List<Categoria> categoriasFiltradas = categoriasManager.getCategoriasFiltradas();
        
        
        clearCategoryCards();
        
        
        for (Categoria categoria : categoriasFiltradas) {
            createCategoryCard(categoria);
        }
    }

    private void clearCategoryCards() {
        
        int childCount = categoriasContainer.getChildCount();
        for (int i = childCount - 1; i > 0; i--) {
            categoriasContainer.removeViewAt(i);
        }
    }

    private void createCategoryCard(Categoria categoria) {
        LinearLayout categoryCard = (LinearLayout) getLayoutInflater().inflate(R.layout.item_categoria_card, null);
        configurarCategoryCard(categoryCard, categoria);
        categoriasContainer.addView(categoryCard);
    }

    private void configurarCategoryCard(LinearLayout categoryCard, Categoria categoria) {
        View colorIndicator = categoryCard.findViewById(R.id.color_indicator);
        TextView tvCategoryName = categoryCard.findViewById(R.id.tv_category_name);
        TextView tvCategoryDescription = categoryCard.findViewById(R.id.tv_category_description);
        TextView tvRemindersCount = categoryCard.findViewById(R.id.tv_reminders_count);
        ImageView btnEdit = categoryCard.findViewById(R.id.btn_edit_category);
        ImageView btnDelete = categoryCard.findViewById(R.id.btn_delete_category);

        
        if (categoria.getColor() != null) {
            try {
                int colorInt = android.graphics.Color.parseColor(categoria.getColor());
                colorIndicator.setBackgroundColor(colorInt);
            } catch (IllegalArgumentException e) {
                colorIndicator.setBackgroundColor(android.graphics.Color.parseColor("#10B981"));
            }
        }

        
        String categoryText = categoria.getTextoCompleto();
        tvCategoryName.setText(categoryText);
        
        
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

        
        tvRemindersCount.setText("0 recordatorios activos");

        
        btnEdit.setOnClickListener(v -> {
            
            Intent intent = new Intent(GestionarCategoriasActivity.this, NuevaCategoriaActivity.class);
            intent.putExtra("MODO_EDICION", true);
            intent.putExtra("CATEGORIA_NOMBRE", categoria.getNombre());
            intent.putExtra("CATEGORIA_ICONO", categoria.getEmoji());
            intent.putExtra("CATEGORIA_COLOR", categoria.getColor());
            intent.putExtra("CATEGORIA_DESCRIPCION", categoria.getDescripcion());
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            
            CategoriasManager.getInstance().eliminarCategoria(categoria);
            
            
            Intent intent = new Intent(GestionarCategoriasActivity.this, CategoriaEliminadaExitosamenteActivity.class);
            startActivity(intent);
        });
    }

    private void showCategoryCard(String categoriaNombre) {
        for (int i = 0; i < categoriasContainer.getChildCount(); i++) {
            View child = categoriasContainer.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout categoryCard = (LinearLayout) child;
                
                
                TextView categoryNameView = findCategoryTitleInCard(categoryCard);
                if (categoryNameView != null) {
                    String cardCategoryName = categoryNameView.getText().toString();
                    
                    
                    String cleanCardName = extractCleanCategoryName(cardCategoryName);
                    String cleanSearchName = extractCleanCategoryName(categoriaNombre);
                    
                    
                    if (cleanCardName.toLowerCase().equals(cleanSearchName.toLowerCase())) {
                        categoryCard.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    private String extractCleanCategoryName(String categoryText) {
        
        return categoryText
            .replace("💊", "")
            .replace("🏃", "")
            .replace("💼", "")
            .replace("📚", "")
            .trim();
    }

    private TextView findCategoryTitleInCard(LinearLayout card) {
        
        return findCategoryTitleTextView(card);
    }
    
    private TextView findCategoryTitleTextView(View parent) {
        if (parent instanceof TextView) {
            TextView textView = (TextView) parent;
            String text = textView.getText().toString();
            
            
            
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