package com.example.empty_proyect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.nio.file.Files

class Buttons : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        //Instanciado de el Button personalizado
        val buttonChangeActivity = findViewById<Button>(R.id.buttonGradient)

        //Este evento lo que hará es pasar de una activity a otra
        buttonChangeActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //Instanciado del ImageButton
        val imageButton = findViewById<ImageButton>(R.id.imageButton)

        //Muestra un mesnaje e una pequeña ventana emergente
        imageButton.setOnClickListener {
            Toast.makeText(this, "ImageButton pulsada", Toast.LENGTH_SHORT).show()
        }
        //Instanciado del objeto ChipGroup que sirve para albergar una serie de objetos Chip
        val cgCondiciones = findViewById<ChipGroup>(R.id.cg_condiciones)

        //Instanciado del objeto Chip al que iremos añadiendo al ChipGroup mediante un bucle
        var chip: Chip
        for (i in 0 until cgCondiciones.childCount) {

            //Casteamos el objeto Chip a cada objeto View que obtendremos gracias al método getChildAt()
            chip = cgCondiciones.getChildAt(i) as Chip
            //Agregamos caracteristicas a cada objeto de tipo Chip
            chip.apply {
                this.textAlignment = View.TEXT_ALIGNMENT_CENTER //Alineación del texto al centro

                //Evento que hará que si pulsamos sobre el icono <x> se elimine dicho objeto Chip de la lista
                this.setOnCloseIconClickListener {
                    cgCondiciones.removeView(it)
                }
            }
            //Evento que mostrará en una ventana emergente un texto con el nombre descriptivo del objeto Chip
            chip.setOnClickListener {
                it as Chip //Casteo de view a Chip para poder acceder al .text
                Toast.makeText(this, "${it.text} pulsado", Toast.LENGTH_SHORT).show()
            }
            //Instanciado de un objeto Chip para añadirlo a la colección del ChipGroup
            val chipNew: Chip = Chip(this)
            chipNew.text = "Opcion ${i}"
            chipNew.chipIcon = ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground)
            chipNew.isCloseIconVisible = true
            chipNew.isClickable = true
            chipNew.isCheckable = true

            //Casteo de objeto Chip a View para poder añadirlo al ChipGroup
            cgCondiciones.addView(chipNew as View)
            //Mismo evento que en los anteriores, casteo de Chip a View para poder eliminarlo si presionas la x
            chipNew.setOnCloseIconClickListener {
                cgCondiciones.removeView(chipNew as View)
            }
        }

        //Instanciado del objeto RadioGroup
        val rGVacaciones = findViewById<RadioGroup>(R.id.rGVacaciones)

        //Instanciado de un objeto View en la posicion 1 de los hijos del RadioGroup
        val rbMontaña = rGVacaciones.getChildAt(1)

        //Activar el RadioButton
        rGVacaciones.check(rbMontaña.id)


        //Instanciamos un objeto de tipo CheckBox

        val cBSeguro = findViewById<CheckBox>(R.id.cBSeguro)
        //Lo habilitamos y lo marcamos
        cBSeguro.isEnabled = true
        cBSeguro.isChecked = true

        //Instanciado de un objeto de tipo ToggleButton
        val tBNormal = findViewById<ToggleButton>(R.id.tBNormal)
        //Marcamos el ToggleButton
        tBNormal.isChecked = true
        //Creamos un evento especial de los ToggleButton que evaluan si esta marcado o no
        tBNormal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Toggle Activado", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Toggle desactivado", Toast.LENGTH_SHORT).show()
        }

        //Instanciamos el objeto Switch
        val sBNormal = findViewById<Switch>(R.id.sBNormal)
        sBNormal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Switch Activadolf", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "Switch Desactivadolf", Toast.LENGTH_SHORT).show()
        }

        val fabButton= findViewById<FloatingActionButton>(R.id.fabButton)
        fabButton.setOnClickListener{
            Toast.makeText(this,"Floating Action Button presionado",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //Método que muestra un mensaje según este uno u otro RadioButton marcado
    fun eventsOnButtons(view: View) {
        if (view is RadioButton) {
            if (view.isChecked) {
                when (view.id) {
                    R.id.rBPlaya -> Toast.makeText(this, "Vamos a la playa", Toast.LENGTH_SHORT)
                        .show()

                    else -> Toast.makeText(this, "Vamos a la montaña", Toast.LENGTH_SHORT).show()
                }
            }
        }
        if (view is CheckBox) {
            when (view.id) {
                R.id.cBSeguro -> {
                    if (view.isChecked) {
                        Toast.makeText(this, "Seguro de viaje solicitado", Toast.LENGTH_SHORT)
                            .show()
                    } else
                        Toast.makeText(this, "Seguro de viaje anulado", Toast.LENGTH_SHORT).show()
                }

                R.id.cbCancelable -> {
                    if (view.isChecked) {
                        Toast.makeText(this, "Reserva cancelable solicitada", Toast.LENGTH_SHORT)
                            .show()

                    } else
                        Toast.makeText(this, "Reserva cancelable anulada", Toast.LENGTH_SHORT)
                            .show()
                }

            }
        }
    }
}

