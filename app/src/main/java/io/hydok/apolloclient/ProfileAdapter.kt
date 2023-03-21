package io.hydok.apolloclient

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.hydok.apolloclient.databinding.ItemProfileBinding

class ProfileAdapter(private val items: List<GetCharactersQuery.Result?>) :
    RecyclerView.Adapter<ProfileAdapter.Holder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(ItemProfileBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = items.size

    inner class Holder(private val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            Glide.with(context).load(items[position]?.image).into(profileImage)
            profileName.text = items[position]?.name
        }
    }
}