# Alu-Cine 

**Alu-Cine** es una aplicación móvil nativa para Android que simula un sistema completo de reserva y venta de boletos de cine. Desarrollada como proyecto final para la clase de Sistemas de Simulación, esta app demuestra el manejo avanzado de interfaces modernas, gestión de estados persistentes y reglas de negocio dinámicas.

## Descripción del Proyecto

La aplicación permite a los usuarios explorar una cartelera de películas, consultar sinopsis y precios, seleccionar horarios específicos y elegir asientos en una sala virtual interactiva. El sistema bloquea en tiempo real los asientos previamente "vendidos" basándose en el horario seleccionado y genera un ticket de comprobante al finalizar el flujo de compra. Además, incluye un programa de lealtad simulado (Fan VIP).

## Características Principales

* **Splash Screen Animado:** Animaciones de físicas (Spring) y transiciones de opacidad (Fade-in) utilizando corrutinas.
* **Cartelera Dinámica:** Cuadrícula de películas escalable (`LazyVerticalGrid`).
* **Navegación de Nivel Superior (Top-Level):** Barra de navegación inferior que conserva el estado de las pantallas (`restoreState = true`).
* **Motor de Reserva Interactiva:**
  * Filtrado de ocupación de sala dependiendo del horario seleccionado.
  * Matriz de asientos con 3 estados dinámicos: Disponible (Azul), Seleccionado (Dorado) y Ocupado (Gris/Bloqueado).
  * Validaciones de reglas de negocio para evitar transacciones vacías o selección excesiva de lugares.
* **Gestión de Estado (State Management):** Uso de `ViewModel` para garantizar la persistencia de los asientos comprados durante el ciclo de vida de la aplicación.
* **Generación de Tickets:** Pase de parámetros a través del grafo de navegación (NavArguments) para construir recibos dinámicos.
* **UI sin dependencias externas:** Elementos complejos como el simulador de Código QR construidos 100% con componentes primitivos de Jetpack Compose.

## Tecnologías y Arquitectura

* **Lenguaje:** Kotlin
* **UI Toolkit:** Jetpack Compose (Material Design 3)
* **Navegación:** Jetpack Navigation Compose
* **Arquitectura:** Patrón MVVM (Modelo-Vista-ViewModel) simplificado para la gestión del estado reactivo de la UI.
* **IDE:** Android Studio

## Instalación y Ejecución

Para correr este proyecto en tu entorno local:

1. Clona este repositorio: `git clone https://github.com/TuUsuario/Alu-Cine.git`
2. Abre el proyecto en **Android Studio** (versión superior recomendada).
3. Sincroniza los archivos de Gradle.
4. Ejecuta la aplicación en un emulador o dispositivo físico con Android 8.0 (API 26) o superior.

## Autor y Créditos

* **Desarrollador:** César Muñoz Durón
* **Contexto Académico:** Proyecto desarrollado para la materia de Sistemas de Simulación. 
* **Agradecimientos Especiales:** Prof. Braulio Jesus Montoya Padilla
