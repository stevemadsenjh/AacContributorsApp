package com.example.yumemi.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yumemi.app.R
import com.example.yumemi.app.ui.home.HomeFragmentDirections
import com.example.yumemi.domain.model.Contributor

class ContributorsAdapter(val fragment: Fragment, private val myDataset: Array<Contributor>) :
    RecyclerView.Adapter<ContributorsAdapter.MyViewHolder>() {

    class MyViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val imageViewAvatar = root.findViewById<ImageView>(R.id.imageViewAvatar)
        val textViewLogin = root.findViewById<TextView>(R.id.textViewLogin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contributor, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(root)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(fragment.requireContext())
            .load(myDataset[position].avatar_url).into(holder.imageViewAvatar)
        holder.textViewLogin.text = myDataset[position].login
        holder.itemView.setOnClickListener {
            fragment.findNavController().navigate(
                HomeFragmentDirections.actionNavHomeToNavContributorDetail(
                    myDataset[position].avatar_url,
                    myDataset[position].login,
                    myDataset[position].contributions,
                    myDataset[position].html_url
                )
            )
        }
    }

    override fun getItemCount() = myDataset.size
}
