package com.example.yumemi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumemi.app.ui.adapter.ContributorsAdapter
import com.example.yumemi.app.R
import com.example.yumemi.domain.model.Contributor
import com.example.yumemi.domain.result.GithubRespFail
import com.example.yumemi.domain.result.GithubRespOK
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private fun setupRecyclerView(contributors: List<Contributor>) {
        val viewManager = LinearLayoutManager(requireContext())
        val contributorsAdapter = ContributorsAdapter(this, contributors.toTypedArray())
        recyclerViewContributors.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = contributorsAdapter
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(requireActivity(), HomeViewModelFactory(requireActivity()))
                .get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.requestContributors()
        homeViewModel.loading.observe(viewLifecycleOwner, Observer {
            progressBarLoading.visibility = View.VISIBLE
        })
        homeViewModel.contributors.observe(viewLifecycleOwner, Observer { respObj ->
            progressBarLoading.visibility = View.INVISIBLE
            when (respObj) {
                is GithubRespOK -> {
                    textViewStatus.text = ""
                    setupRecyclerView(respObj.contributors)
                }
                is GithubRespFail -> textViewStatus.text = resources.getString(
                    R.string.format_network_error, respObj.msg)
            }
        })
        return root
    }
}
