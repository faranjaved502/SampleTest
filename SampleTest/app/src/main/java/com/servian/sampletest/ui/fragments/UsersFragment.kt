package com.servian.sampletest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servian.sampletest.ui.adapters.UsersRecyclerViewAdapter
import com.servian.sampletest.databinding.FragmentUsersBinding
import com.servian.sampletest.model.User
import com.servian.sampletest.ui.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.servian.sampletest.utils.gone
import com.servian.sampletest.utils.visible
import com.servian.sampletest.ui.viewmodels.UserViewModelFactory
import com.servian.sampletest.utils.Status


@AndroidEntryPoint
class UsersFragment : BaseFragment() {

    lateinit var viewModel: UserViewModel

    @Inject
    lateinit var viewModelFactory: UserViewModelFactory

    private lateinit var binding: FragmentUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(layoutInflater)
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
                            setupList(binding.rvUsers, response.data.toList())
                        else {
                            binding.rvUsers.gone()
                            binding.tvFailure.visible()
                        }
                    }
                }
                Status.ERROR -> {
                    hideDialog()
                    binding.rvUsers.gone()
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
        userList: List<User>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = UsersRecyclerViewAdapter(userList) { id ->
                val action =
                    UsersFragmentDirections.actionUsersFragmentToAlbumListFragment(id)
                findNavController().navigate(action)
            }
        }
    }

    private fun setupViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

    private fun fetchResponse() {
        viewModel.userList()
        showProgressDialog()
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsersFragment().apply {}
    }
}