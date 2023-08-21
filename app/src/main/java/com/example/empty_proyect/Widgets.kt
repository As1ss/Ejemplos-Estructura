package com.example.empty_proyect

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ListView
import android.widget.MediaController
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.SearchView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.empty_proyect.databinding.ActivityMainBinding
import com.example.empty_proyect.databinding.ActivityWidgetsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.tankery.lib.circularseekbar.CircularSeekBar
import org.w3c.dom.Text
import java.lang.Thread.sleep
import java.util.Calendar
import kotlin.concurrent.thread

class Widgets:AppCompatActivity() {
    lateinit var activity:AppCompatActivity
    lateinit var binding: ActivityWidgetsBinding//Este objeto es para acceder directamente a los objetos del XML
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity=this
        //Hacemos esto para poder acceder a los documentos XML en esta activity sin necesidad de instanciarlo en variables
        binding=ActivityWidgetsBinding.inflate(layoutInflater)

       // setContentView(R.layout.activity_widgets) Comentamos esto para activar la manera de mostrar la IU mediante el Biding
          setContentView(binding.root)





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


        //Instanciado del objeto ViewCalendar y TextView
        val calendarView = findViewById<CalendarView>(R.id.cVEjemplo)
        val textCalendar = findViewById<TextView>(R.id.textViewCalendar)
        //Creamos un evento en el objeto ViewCalendar para capturar en un string la fecha seleccionada
        //Añadimos +1 a la variable month porque los meses los captura como si fuera un array
        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val date = "$day/${month+1}/$year"
            //Añadimos el texto al textVIew que se modificará de forma dinámica al escoger una fecha
            textCalendar.text = "Fecha seleccionada: $date"
        }

        //Instanciamos un objeto de tipo Calendar para poder operar y predefinir una fecha
        val fechaPredefinida = Calendar.getInstance()
        //Añadimos una fecha predefinida con el método set y disminuyendo 1 al parametro month
        fechaPredefinida.set(2026,5-1,8)
        //Establecemos como fecha predefinida esta fecha en milisegundos?
        calendarView.date = fechaPredefinida.timeInMillis
        //Esto sirve para que el primer día de la semana sea el Lunes , no el Domingo
        calendarView.firstDayOfWeek += 1


        //Instanciamos los dos ojbetos de tipo ProgressBar
        val pbDeterminado = findViewById<ProgressBar>(R.id.pBHorizontalDeterminate)
        //Establecemos la medida maxima del progresBar
        pbDeterminado.max= 300
        //Establecemos la medida inicial del progresBar
        pbDeterminado.progress=0

        //Misma metodología para el objeto de progresBar.
        // Este objeto también tendrá un progressBar secundario como el búfer de un video de Youtube.
        val pBSecundario = findViewById<ProgressBar>(R.id.pBSecundario)
        pBSecundario.max=300
        pBSecundario.progress=0
        pBSecundario.secondaryProgress=0


        //Instanciamos el objeto SeekBar
        val sbNormal = findViewById<SeekBar>(R.id.sBNormal)
        //Este evento necesitamos un objeto de tipo SeekBar para poder controlar las acciones del usuario
        sbNormal.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //Si el usuario es el que ha ejecutado el cambio del progress del SeekBar, muestra el mesnaje
              if (fromUser){
                  Toast.makeText(activity,"El usuario ha accionado el SeekBar",
                      Toast.LENGTH_LONG).show()
              }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        //Instanciado del ojbeto RatingBar y un TextView que va a mostrar el numero de puntuación
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val tvRatingBar = findViewById<TextView>(R.id.tVRating)

        //Inicializamos el número de puntuación a 3.5
        ratingBar.rating = 3.5f
        //Creamos un evento al pulsar en la RatingBar, omitiendo el ultimo parámetro booleano de accion del usuario
        //para poder actualizar el texto del TextView dependiendo de cuanta puntuación este accionando el usuario
        ratingBar.setOnRatingBarChangeListener { ratingBar, ratingStar, _ ->
            tvRatingBar.text = "$ratingStar/${ratingBar.numStars}"
        }



        //Instanciado de un ojbeto NumberPick y un TextView para recoger los cambios de los valores
        val numberPicker = findViewById<NumberPicker>(R.id.nPEjemplo)
       // val tvNumberPicker = findViewById<TextView>(R.id.tVNumberPicker) COMENTAMOS ESTO PARA ACCEDER DESDE EL BINDING

       numberPicker.apply {
            this.minValue=0 //Valor mínimo del NumberPick
            this.maxValue=59 //Valor máximo del NumberPick
            this.value=5 //Valor predefinido del NumberPIck
            this.wrapSelectorWheel=true //Activamos el objeto NumberPick de tipo rueda
            this.setFormatter { i->String.format("%02d",i) } //Formateamos el texto para que se nos muestre números de dos dígitos con 0 a la izq
        }


        //Creamos evento para capturar en el textView los valores que hemos escogido en primera instancia y acto después
        numberPicker.setOnValueChangedListener{numberPicker,oldVal,newVal->
            //Accedemos al objeto TextView desde el binding directamente en vez de instanciar un objeto en una variable
            binding.tVNumberPicker.text = "Numberpicker: antes ${String.format("%02d",oldVal)} / " +
                    "ahora ${String.format("%02d",newVal)}"
        }

        val sBCircular = findViewById<CircularSeekBar>(R.id.sBCircular)
        sBCircular.progress = 20f
        sBCircular.max = 100f










        //Esto es la invocación de coroutinas (hilos) para poder operar con los progressBar de forma asíncrona y no secuencial
        //Para poder acceder a esta coroutina debemos ir a build.gradle e implementar esto
        //  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
        // Sincronizamos el gradle en la parte superior de la ventana y listo
        GlobalScope.launch {
            //Iniciamos las funciones para simular una progresión del ProgressBar
            progressManager(pbDeterminado)
            progressManager(pBSecundario)
            //Esta función aumenta el progress del SeekBar
            seekProgressManager(sbNormal)
            seekCircularProgressManager(sBCircular)

        }

    }

    private fun seekCircularProgressManager(sBCircular: CircularSeekBar) {
        while(true){
            sleep(100L)
            sBCircular.progress+=35f
            println("Hola")
        }
    }

    //Esta función hara que aumente de forma permanente el progress del SeekBar
    private fun seekProgressManager(sb: SeekBar) {
        while(true){
            sleep(100L)
            sb.incrementProgressBy(5)
        }
    }


    //Este método se encarga de simular el funcionamiento de un progressBar
    private fun progressManager(pb: ProgressBar) {


       while(pb.progress<pb.max){
           //Esto sirve pra que espere 100 milisegundos por añadido del progreessbar (para que no sea instantáneo)
           sleep(100L)
           pb.incrementProgressBy(5)
           //Aquí evaluamos la id para determinar si tiene progressBar secundario (búfer)
           if (pb.id==R.id.pBSecundario){
               //Lo incrementamos más rápido para simular un búfer de un video
               pb.incrementSecondaryProgressBy(5*2)
           }
       }
    }


}