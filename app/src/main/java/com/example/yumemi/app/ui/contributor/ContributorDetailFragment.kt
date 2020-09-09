package com.example.yumemi.app.ui.contributor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yumemi.app.R
import kotlinx.android.synthetic.main.fragment_contributor_details.*
import kotlinx.android.synthetic.main.item_contributor.*
import kotlinx.android.synthetic.main.item_contributor.imageViewAvatar
import kotlinx.android.synthetic.main.item_contributor.textViewLogin

class ContributorDetailFragment : Fragment() {

    val args: ContributorDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_contributor_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(args.avatarUrl).into(imageViewAvatar)
        textViewLogin.text = args.login
        textViewContributions.text = args.contributions.toString()
    }
}
