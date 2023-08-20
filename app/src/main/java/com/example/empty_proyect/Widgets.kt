package com.example.empty_proyect

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class Widgets:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widgets)

        //RECORDAR QUE PARA PODER OPERAR CON COSAS DE INTERNET HAY QUE HABILITAR UNA ETIQUETA EN AndoridManifest.xml
        //   <uses-permission android:name="android.permission.INTERNET"/>

        //Instanciamos dos objetos uno de WebView y otro de WebSettings para poder activar javaScript
        val webView = findViewById<WebView>(R.id.webView)
        val webSettings : WebSettings = webView.settings
        webSettings.javaScriptEnabled=true

        //Aquí configuramos el poder navegar mediante el navegador de nuestra app, no una exterior como Google Chrome o Firefox
        webView.webViewClient = WebViewClient()

        //Cargamos la pagina web por defecto para poder mostrarla en la app y navegar sobre ella
        webView.loadUrl("https://www.google.com")


        //Instanciamos un objeto de tipo VideoView para un video en la web
        val videoWeb = findViewById<VideoView>(R.id.videoViewWeb)

        //Instanciamos un objeto de tipo MediaController para poder operar con el video
        val mediaController = MediaController(this)

        //Le agregamos la propiedad de anchura a la misma anchura que el objeto VideoView
        mediaController.setAnchorView(videoWeb)

        //Le establecemos un color del background de color rojo para diferenciarlo del segundo objeto
        mediaController.setBackgroundColor(Color.RED)
        //Le agregamos un path que va a ser la ubicación online del video
        videoWeb.setVideoPath("https://jotajotavm.com/img/video.mp4")
        //Añadimos el mediacontroller al video
        videoWeb.setMediaController(mediaController)


        //Instanciamos un objeto de tipo VideoView para un video local
        val videoLocal = findViewById<VideoView>(R.id.videoViewLocal)

        //Instanciamos un objeto de tipo MediaController para poder operar con el video
        val mediaController2 = MediaController(this)

        //Establecemos la ubicación de dentro de android a String para pasarlo como parámetro
        // en el método setVideoPath
        val localPath = "android.resource://$packageName/${R.raw.video}"

        //Le agregamos la propiedad de anchura a la misma anchura que el objeto VideoView
        mediaController2.setAnchorView(videoLocal)
        //Le establecemos un color del background de color azul para diferenciarlo del primer objeto
        mediaController2.setBackgroundColor(Color.BLUE)

        //Le agregamos un path que va a ser la ubicación local que esta en la variable
        // localPath
        videoLocal.setVideoPath(localPath)

        //Añadimos el mediacontroller al video
        videoLocal.setMediaController(mediaController2)




    }





}