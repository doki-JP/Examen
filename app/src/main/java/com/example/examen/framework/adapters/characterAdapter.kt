package com.example.examen.framework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examen.R
import com.example.examen.data.models.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characters: List<Character> = listOf()
    private var favoriteCharacters: MutableList<Character> = mutableListOf()
    private var showFavorites: Boolean = false

    fun setCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    fun filterFavorites(showFavorites: Boolean) {
        this.showFavorites = showFavorites
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = if (showFavorites) favoriteCharacters[position] else characters[position]
        holder.bind(character)
        holder.buttonFavorite.text = if (favoriteCharacters.contains(character)) "Favorito" else "Guardar como Favorito"
        holder.buttonFavorite.setOnClickListener {
            if (favoriteCharacters.contains(character)) {
                favoriteCharacters.remove(character)
                holder.buttonFavorite.text = "Guardar como Favorito"
            } else {
                favoriteCharacters.add(character)
                holder.buttonFavorite.text = "Favorito"
            }
        }
    }

    override fun getItemCount(): Int = if (showFavorites) favoriteCharacters.size else characters.size

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val speciesTextView: TextView = itemView.findViewById(R.id.speciesTextView)
        private val genderTextView: TextView = itemView.findViewById(R.id.genderTextView)
        private val kiTextView: TextView = itemView.findViewById(R.id.kiTextView)
        private val maxKiTextView: TextView = itemView.findViewById(R.id.maxKiTextView)
        private val affiliationTextView: TextView = itemView.findViewById(R.id.affiliationTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val buttonFavorite: Button = itemView.findViewById(R.id.buttonFavorite)

        fun bind(character: Character) {
            nameTextView.text = character.name
            speciesTextView.text = character.race
            genderTextView.text = character.gender
            kiTextView.text = character.ki
            maxKiTextView.text = character.maxKi
            affiliationTextView.text = character.affiliation
            descriptionTextView.text = character.description
            Glide.with(itemView.context).load(character.image).into(imageView)
        }
    }
}