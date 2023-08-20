package com.example.empty_proyect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

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
        imageButton.setOnClickListener{
            Toast.makeText(this,"ImageButton pulsada",Toast.LENGTH_SHORT).show()
        }
        //Instanciado del objeto ChipGroup que sirve para albergar una serie de objetos Chip
        val cgCondiciones = findViewById<ChipGroup>(R.id.cg_condiciones)

        //Instanciado del objeto Chip al que iremos añadiendo al ChipGroup mediante un bucle
        var chip: Chip
        for (i in 0 until cgCondiciones.childCount){

            //Casteamos el objeto Chip a cada objeto View que obtendremos gracias al método getChildAt()
            chip = cgCondiciones.getChildAt(i) as Chip
            //Agregamos caracteristicas a cada objeto de tipo Chip
            chip.apply {
                this.textAlignment = View.TEXT_ALIGNMENT_CENTER //Alineación del texto al centro

                //Evento que hará que si pulsamos sobre el icono <x> se elimine dicho objeto Chip de la lista
                this.setOnCloseIconClickListener{
                    cgCondiciones.removeView(it)
                }
            }
            //Evento que mostrará en una ventana emergente un texto con el nombre descriptivo del objeto Chip
            chip.setOnClickListener{it as Chip //Casteo de view a Chip para poder acceder al .text
                Toast.makeText(this,"${it.text} pulsado",Toast.LENGTH_SHORT).show()
            }
            //Instanciado de un objeto Chip para añadirlo a la colección del ChipGroup
            val chipNew:Chip = Chip(this)
            chipNew.text ="Opcion ${i}"
            chipNew.chipIcon = ContextCompat.getDrawable(this,R.drawable.ic_launcher_foreground)
            chipNew.isCloseIconVisible = true
            chipNew.isClickable = true
            chipNew.isCheckable = true

            //Casteo de objeto Chip a View para poder añadirlo al ChipGroup
            cgCondiciones.addView(chipNew as View)
            //Mismo evento que en los anteriores, casteo de Chip a View para poder eliminarlo si presionas la x
            chipNew.setOnCloseIconClickListener{
                cgCondiciones.removeView(chipNew as View)
            }
        }

        val rGVacaciones = findViewById<RadioGroup>(R.id.rGVacaciones)

        val rbMontaña = rGVacaciones.getChildAt(1)

        rGVacaciones.check(rbMontaña.id)

            }


    fun onRadioButtonClicked (view: View){
        if (view is RadioButton){
           if(view.isChecked){
               when (view.id){
                   R.id.rBPlaya -> Toast.makeText(this,"Vamos a la playa",Toast.LENGTH_SHORT).show()
                       else-> Toast.makeText(this,"Vamos a la montaña",Toast.LENGTH_SHORT).show()
               }
           }
        }
    }
}
