package com.example.retrofitappdogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitappdogs.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : DogsAdapter
    private var listaImagenes = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    buscarDogPorRaza(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun buscarDogPorRaza(raza:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java)
                .obtenerPerrosPorRaza("$raza/images")

            val resultado = call.body()

            runOnUiThread {
                if (call.isSuccessful){
                    if (resultado != null) {
                        listaImagenes.clear()
                        listaImagenes.addAll(resultado.listaImagenes)
                        adapter.notifyDataSetChanged()
                    }else{
                        //mostrar error
                    }
                }
            }
        }
    }


    fun initRecycler(){
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        adapter = DogsAdapter(listaImagenes)
        binding.rvDogs.adapter = adapter
    }

}