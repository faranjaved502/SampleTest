package com.servian.sampletest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servian.sampletest.databinding.FragmentAlbumListBinding
import com.servian.sampletest.model.Album
import com.servian.sampletest.ui.adapters.AlbumRecyclerViewAdapter
import com.servian.sampletest.ui.viewmodels.AlbumViewModel
import com.servian.sampletest.ui.viewmodels.AlbumViewModelFactory
import com.servian.sampletest.utils.Status
import com.servian.sampletest.utils.gone
import com.servian.sampletest.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumListFragment : BaseFragment() {

    lateinit var viewModel : AlbumViewModel

    @Inject
    lateinit var viewModelFactory : AlbumViewModelFactory

    private lateinit var binding : FragmentAlbumListBinding
    private val args : AlbumListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlbumListBinding.inflate(layoutInflater)
        setupViewModels()
        fetchResponse()
        observerData()
        return binding.root
    }


    private fun observerData() {
        viewModel.response.observe(this as LifecycleOwner, { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    hideDialog()
                    response.let {
                        if (!response.data.isNullOrEmpty())
                            setupList(binding.rvAlbum, viewModel.filerAlbumListAccordingToID(args.userId,response.data.toList()))
                        else {
                            binding.rvAlbum.gone()
                            binding.tvFailure.visible()
                        }
                    }
                }
                Status.ERROR -> {
                    hideDialog()
                    binding.rvAlbum.gone()
                    binding.tvFailure.visible()
                }
                Status.LOADING -> {
                    showProgressDialog()
                }
            }
        })
    }


    private fun setupList(
        view: View?,
        albumList: List<Album>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = AlbumRecyclerViewAdapter(albumList) { album ->
                 val action =
                     AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(album)
                 findNavController().navigate(action)
            }
        }
    }

    private fun setupViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(AlbumViewModel::class.java)
    }

    private fun fetchResponse() {
        viewModel.albumList()
        showProgressDialog()
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            AlbumListFragment().apply {}
    }
}