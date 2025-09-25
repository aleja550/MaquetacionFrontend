package com.remindmeplus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomeScreenActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Hide the default ActionBar to use custom header
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_home_screen);

        drawerLayout = findViewById(R.id.drawer_layout);

        ImageView menuButton = findViewById(R.id.menu_button);
        ImageView notificationButton = findViewById(R.id.notification_button);
        EditText searchBar = findViewById(R.id.search_bar);
        TextView verTodos = findViewById(R.id.ver_todos);
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);

        // Navigation menu items
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navRecordatorios = findViewById(R.id.nav_recordatorios);
        LinearLayout navCategorias = findViewById(R.id.nav_categorias);
        LinearLayout navEstadisticas = findViewById(R.id.nav_estadisticas);
        LinearLayout navConfiguracion = findViewById(R.id.nav_configuracion);
        LinearLayout navAyuda = findViewById(R.id.nav_ayuda);

        // Search functionality
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterRecordatorios(s.toString());
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

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Mostrar notificaciones
            }
        });

        verTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, GestionarRecordatoriosActivity.class);
                startActivity(intent);
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, NuevoRecordatorioActivity.class);
                startActivity(intent);
            }
        });

        // Navigation menu listeners
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ya estamos en Home, solo cerramos el drawer
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, GestionarRecordatoriosActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, GestionarCategoriasActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, EstadisticasActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, ConfiguracionActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, AyudaActivity.class));
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

    private void filterRecordatorios(String query) {
        // Find all reminder cards in the home screen by searching through the layout hierarchy
        LinearLayout mainContent = findMainContentContainer();
        if (mainContent == null) return;

        filterRemindersRecursively(mainContent, query.toLowerCase().trim());
    }

    private LinearLayout findMainContentContainer() {
        // Look for the main ScrollView container in the home screen
        View rootLayout = findViewById(R.id.drawer_layout);
        return findScrollViewContent(rootLayout);
    }

    private LinearLayout findScrollViewContent(View parent) {
        if (parent instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) parent;
            if (scrollView.getChildCount() > 0) {
                View child = scrollView.getChildAt(0);
                if (child instanceof LinearLayout) {
                    return (LinearLayout) child;
                }
            }
        } else if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            for (int i = 0; i < group.getChildCount(); i++) {
                LinearLayout result = findScrollViewContent(group.getChildAt(i));
                if (result != null) return result;
            }
        }
        return null;
    }

    private void filterRemindersRecursively(ViewGroup parent, String query) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout layout = (LinearLayout) child;
                
                // Check if this looks like a reminder card
                if (isReminderCard(layout)) {
                    TextView titleView = findReminderTitle(layout);
                    if (titleView != null) {
                        String title = titleView.getText().toString().toLowerCase();
                        if (query.isEmpty() || title.contains(query)) {
                            layout.setVisibility(View.VISIBLE);
                        } else {
                            layout.setVisibility(View.GONE);
                        }
                    }
                } else {
                    // Continue searching recursively
                    filterRemindersRecursively(layout, query);
                }
            }
        }
    }

    private boolean isReminderCard(LinearLayout layout) {
        // A reminder card typically has specific characteristics
        try {
            // Look for elements that suggest this is a reminder card
            return layout.getChildCount() > 1 && hasReminderCharacteristics((ViewGroup) layout);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean hasReminderCharacteristics(ViewGroup layout) {
        // Check if the layout contains TextViews with reminder-like content
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof TextView) {
                String text = ((TextView) child).getText().toString();
                // Look for typical reminder titles
                if (text.contains("Tomar") || text.contains("Hacer") || text.contains("Recordar") || 
                    text.contains("Ejercicio") || text.length() > 10) {
                    return true;
                }
            } else if (child instanceof ViewGroup) {
                if (hasReminderCharacteristics((ViewGroup) child)) {
                    return true;
                }
            }
        }
        return false;
    }

    private TextView findReminderTitle(ViewGroup parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof TextView) {
                TextView textView = (TextView) child;
                String text = textView.getText().toString();
                
                // The title is the substantial text that's not a status or category
                if (!text.startsWith("●") && !text.startsWith("•") && 
                    !text.contains("💊") && !text.contains("🏥") && 
                    !text.contains("💼") && !text.contains("📚") && 
                    !text.contains("🏃") && text.length() > 3 &&
                    !text.equals("Activo") && !text.equals("Completado") && 
                    !text.equals("Atrasado") && 
                    !text.contains("Salud 💊") && !text.contains("Ejercicio 🏃") && 
                    !text.contains("Trabajo 💼") && !text.contains("Estudio 📚") &&
                    !text.equals("Ver todos")) {
                    return textView;
                }
            } else if (child instanceof ViewGroup) {
                TextView result = findReminderTitle((ViewGroup) child);
                if (result != null) return result;
            }
        }
        return null;
    }



}