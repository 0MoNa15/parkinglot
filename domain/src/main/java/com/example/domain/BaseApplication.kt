package com.example.domain

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Al ser el contenedor ppal, necesitamos que el gráfico de las dependencias permanezca
// y no se explote el app cuando se vaya a segundo plano, asegurando que las dependencias
// vivan hasta que todo_ el app sea destruida por completo

// Contenedor del gráfico de Hilt
//@HiltAndroidApp
class BaseApplication: Application() {

}