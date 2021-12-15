package com.example.retrofitappdogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitappdogs.databinding.DogItemBinding
import com.squareup.picasso.Picasso

class DogsAdapter(var listaImagenes:List<String>):RecyclerView.Adapter<DogsAdapter.Holder>() {

    class Holder(var view: View):RecyclerView.ViewHolder(view){
        val binding:DogItemBinding = DogItemBinding.bind(view)

        fun bind(imagen:String){
            Picasso.get().load(imagen).into(binding.ivDog);
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return Holder(layoutInflater.inflate(R.layout.dog_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listaImagenes[position])
    }

    override fun getItemCount(): Int {
        return listaImagenes.size
    }

}