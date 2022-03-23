package com.example.rachasevo.ui.listado

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rachasevo.R
import com.example.rachasevo.baseDeDatos.BaseDeDatos
import com.example.rachasevo.databinding.FragmentListadoBinding
import com.example.rachasevo.baseDeDatos.model.Item

class ListadoFragment : Fragment(),EditCounter {

    private lateinit var binding:FragmentListadoBinding
    lateinit var items:List<Item>
    lateinit var db:RoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let{
            db = Room.databaseBuilder(it,BaseDeDatos::class.java,"listado").build()
        }

        items = db.
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListadoBinding.inflate(inflater,container,false)

        activity?.let { setAdapter(it) }
        binding.listadoRecycler.layoutManager = LinearLayoutManager(activity)

        onClick()
        return binding.root
    }

    private fun onClick(){
        binding.newItem.setOnClickListener { toaster("hola") }
    }

    private fun toaster(text:String) = Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()

    private fun setAdapter(contexto:Context){
        val adaptador = Adaptador(contexto, items,this)
        binding.listadoRecycler.adapter = adaptador
    }

    @SuppressLint("SetTextI18n")
    override fun editCounter(posicion: Int, ecuacion: Char, texto: TextView) {
        items[posicion].contador =
            if (ecuacion=='+')
                items[posicion].contador+1
            else
                items[posicion].contador-1

        texto.text = ""+items[posicion].contador
    }

    override fun onResume() {
        super.onResume()
        activity?.let { setAdapter(it) }
    }
}