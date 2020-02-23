package com.example.peoplelist.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peoplelist.R
import com.example.peoplelist.presentation.model.UiPerson
import com.example.peoplelist.presentation.replace
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_person.view.*

class PeopleAdapter(
    private var items: List<UiPerson> = emptyList(),
    private val callback: PersonActionsCallback
) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<UiPerson>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun updateLike(personId: String) {
        val index = items.indexOfFirst { it.id == personId }
        val person = items[index]

        items = items.replace(person) { copy(hasLike = true) }
        notifyItemChanged(index)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(person: UiPerson) = with(view) {

            Picasso.with(context).load(person.urlPicture).into(imagePhoto)
            textName.text = person.name
            textEmail.text = person.email
            textGender.text = person.gender

            imageLike.setImageResource(getResourceIdByPreferences(person))
            imageLike.setOnClickListener { callback.atGivenLike(person.id) }
        }

        private fun getResourceIdByPreferences(person: UiPerson): Int {
            return if (person.hasLike) {
                R.drawable.ic_thumb_up
            } else {
                R.drawable.ic_thumb_up_outline
            }
        }
    }
}