package com.example.empty_proyect

import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaActionSound
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Constante de un objeto de tipo MediaPlayer que sirve para poder crear sonidos, reproducirlos, detenerlos, etc
        val mediaPlayer = MediaPlayer.create(this, R.raw.sound)

        //Constante del Label definido en el documento XML, lo instanciamos como objeto gracias a la id definida en el documento XML
        val ejemploLabel: TextView = findViewById(R.id.ejemploLabel)

        //Contador para mostrar valores dinámicos al evento definido posteriormente.
        var contador: Int = 1

        //Ejecutamos el método apply para poder agregar o manipular atributos del objeto de forma más ordenada y concisa.
        ejemploLabel.apply {
            this.text = "Views"

            setBackgroundColor(Color.CYAN)
            setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
            setTextSize(3, 15f)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            this.setTextColor(Color.BLACK)
        }
        /*
         Agregamos el evento de pulsado, si un usuario pulsa sobre este label se reproducirá el sonido
         y aparecerá una ventana emergente con el conteo de las veces que el usuario ha pulsado el label.
         */

        ejemploLabel.setOnClickListener {
            mediaPlayer.start()

            Toast.makeText(this, "Has eruptadolf ${contador++} veces.", Toast.LENGTH_SHORT).show()
        }

        //Instanciado de un campo de texto editable definido en el documento XML.
        val ejemploEditText: EditText = findViewById(R.id.ejemploEditText)

        //De nuevo ejecutados el método apply para poder trabajar sobre el objeto.
        ejemploEditText.apply {
            //Añadimos un evento que recogera todas las veces que el texto cambie (aumente o disminuya su longitud)
            addTextChangedListener {
                //Esta condición evalúa si el texto tiene 0 caracteres, se debe haber introducido 1 anteriormente para poder detectar que su longitud final equivale a a 0
                if (ejemploEditText.length() == 0) {
                    //Mostramos mensaje de error al tener 0 caracteres el valor introducido en el campo de texto editable
                    ejemploEditText.setError("Campo vacío")
                }
                /*
            Puedes seleccionar los caracteres que veas conveniente para poder personalizar por ejemplo el background de lo seleccionado de un color determinado
            en el documento XML con la propiedad  android:textColorHighlight="@color/green"
           */


            }

        }

        //Instanciamos el campo de texto editable de autocompletado
        val autoCompleteTextView:AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)

        //Creamos un array a partir de los recursos que tenemos almacenados en un documento XML.
        val countries:Array<String> = resources.getStringArray(R.array.countries_array)

        //Creamos un adaptador para mostrar el autocompletado de los paises con un diseño específico que escogemos en el constructor de ArrayAdapter
        val adapterView:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,countries)

        //Añadimos al campo de texto editable de autocompletado nuestro adaptadorView para poder mostrar la configuración anteriormente definida
        autoCompleteTextView.setAdapter(adapterView)


        //Instanciamos el campo de texto editable de autocompletado múltimple (sirve para ayudar al usuario con varios valores en vez de uno solo)
        val multiAutoCompleteTextView: MultiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView)

        //Añadimos el adaptador anteriormente definido junto al array de countries
        multiAutoCompleteTextView.setAdapter(adapterView)

        //Establecemos un delimitador para que el compilador sepa cada cuanto ayudar al usuario con el autocompletado (autocompletado múltiple)
        multiAutoCompleteTextView.setTokenizer(CommaTokenizer())


    }
}