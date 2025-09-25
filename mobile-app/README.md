# 📱 RemindMe+ - Aplicación Móvil Android

## 🎯 **Descripción**
**RemindMe+** es una aplicación móvil nativa para Android desarrollada como proyecto académico para el curso de **UX mejoramiento de la experiencia de usuario** en la Universidad de los Andes. La aplicación permite gestionar recordatorios personales con una interfaz intuitiva basada en Material Design.mindMe+ - Aplicación Móvil Android

## 📱 **Descripción**
Aplicación móvil de recordatorios inteligentes desarrollada en Android nativo con Java. Incluye desafíos matemáticos para asegurar que el usuario esté despierto al recibir las alarmas.

## 🎯 **Características Principales**
- ✅ **9 pantallas navegables** con Material Design
- ✅ **Gestión de recordatorios** con categorías personalizables
- ✅ **Desafíos matemáticos** para detener alarmas
- ✅ **Estadísticas** de uso y rendimiento
- ✅ **Configuración** personalizable de dificultad y preferencias
- ✅ **Navegación drawer** con menú lateral
- ✅ **Componentes interactivos** (switches, spinners, date/time pickers)

## 🏗️ **Estructura del Proyecto**

```
mobile-app/
├── app/
│   ├── src/main/
│   │   ├── java/com/remindmeplus/app/
│   │   │   ├── HomeScreenActivity.java
│   │   │   ├── GestionarRecordatoriosActivity.java
│   │   │   ├── NuevoRecordatorioActivity.java
│   │   │   ├── GestionarCategoriasActivity.java
│   │   │   ├── NuevaCategoriaActivity.java
│   │   │   ├── AlarmaDesafioMatematicoActivity.java
│   │   │   ├── ConfiguracionActivity.java
│   │   │   ├── EstadisticasActivity.java
│   │   │   └── AyudaActivity.java
│   │   ├── res/
│   │   │   ├── layout/ (9 archivos XML de pantallas)
│   │   │   ├── values/ (colors, strings, styles, arrays)
│   │   │   ├── drawable/ (botones y recursos gráficos)
│   │   │   └── menu/ (menú de navegación)
│   │   └── AndroidManifest.xml
│   ├── build.gradle (configuración del módulo app)
│   └── proguard-rules.pro
├── gradle/wrapper/ (wrapper de Gradle)
├── build.gradle (configuración del proyecto)
├── settings.gradle (configuración del workspace)
├── gradlew (script de ejecución Unix/Mac)
└── README.md (este archivo)
```

## 🎨 **Pantallas Implementadas**

### 1. **Pantalla Principal (HomeScreen)**
- Dashboard con resumen de recordatorios
- Menú lateral de navegación
- Botón flotante para agregar recordatorios

### 2. **Gestionar Recordatorios**
- Lista de recordatorios existentes
- Opciones de editar/eliminar
- Filtros por categoría

### 3. **Nuevo Recordatorio**
- Formulario completo con validaciones
- Selección de fecha y hora
- Opciones de repetición y desafío matemático

### 4. **Gestionar Categorías**
- Lista de categorías con colores
- Contador de recordatorios por categoría
- Navegación a nueva categoría

### 5. **Nueva Categoría**
- Selector de colores interactivo
- Vista previa en tiempo real
- Validación de nombre

### 6. **Alarma Desafío Matemático**
- Problemas matemáticos aleatorios
- Cronómetro con tiempo límite
- Opciones de posponer o detener

### 7. **Configuración**
- Ajustes de notificaciones y sonido
- Configuración de dificultad de desafíos
- Opciones de exportar/importar datos

### 8. **Estadísticas**
- Métricas de uso y rendimiento
- Gráficos de precisión en desafíos
- Estadísticas por categorías

### 9. **Ayuda**
- Preguntas frecuentes
- Información de contacto
- Versión de la aplicación

## 🔧 **Tecnologías Utilizadas**
- **Lenguaje:** Java
- **Platform:** Android SDK (API 21+)
- **UI Framework:** Material Design Components
- **IDE:** Android Studio
- **Build System:** Gradle

## 📋 **Componentes Interactivos**
- **Navigation Drawer:** Menú lateral deslizante
- **FloatingActionButton:** Botones de acción principales
- **RecyclerView:** Listas dinámicas y eficientes
- **CardView:** Tarjetas con Material Design
- **Switches:** Configuraciones on/off
- **Spinners:** Listas desplegables
- **SeekBar:** Control deslizante para valores numéricos
- **DatePicker/TimePicker:** Selección de fecha y hora
- **TextInputLayout:** Campos de texto con validación

## 🚀 **Cómo Compilar**

### **Método 1: Android Studio (Recomendado)**
1. Abrir Android Studio
2. File > Open > Seleccionar carpeta `mobile-app`
3. Build > Build Bundle(s) / APK(s) > Build APK(s)
4. El APK se generará en: `app/build/outputs/apk/debug/`

### **Método 2: Línea de comandos**
```bash
cd mobile-app
./gradlew assembleDebug
```

**Nota:** Requiere Java 17 o anterior para compilación exitosa.

## 📱 **Instalación**
```bash
# Instalar APK en dispositivo conectado
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 🎯 **Estado del Proyecto**
- ✅ **Maquetación completa:** 9 pantallas implementadas
- ✅ **Navegación funcional:** Todos los intents configurados
- ✅ **Componentes interactivos:** Switches, spinners, etc. operativos
- ✅ **Material Design:** Colores, estilos y temas aplicados
- ✅ **Estructura lista:** Para compilación y despliegue
- ⚠️ **No funcional:** Solo UI/UX (según requisitos universitarios)

## 📞 **Información del Proyecto**
- **Nombre:** RemindMe+
- **Package:** com.remindmeplus.app
- **Versión:** 1.0.0
- **Target SDK:** 33
- **Min SDK:** 21

---
**Desarrollado para:** Universidad de los Andes - UX mejoramiento de la experiencia de usuario  
**Fecha:** Septiembre 2025