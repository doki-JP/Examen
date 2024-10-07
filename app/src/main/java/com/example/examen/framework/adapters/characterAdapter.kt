package com.example.examen.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examen.R
import com.example.examen.data.models.Character

class characterAdapter : RecyclerView.Adapter<characterAdapter.CharacterViewHolder>() {

    private var characters: List<Character> = listOf()

    fun setCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val speciesTextView: TextView = itemView.findViewById(R.id.speciesTextView)
        private val genderTextView: TextView = itemView.findViewById(R.id.genderTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(character: Character) {
            nameTextView.text = character.name
            speciesTextView.text = character.species
            genderTextView.text = character.gender
            Glide.with(itemView.context).load(character.image).into(imageView)
        }
    }
}