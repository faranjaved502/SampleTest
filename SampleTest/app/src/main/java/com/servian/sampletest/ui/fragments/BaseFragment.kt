package com.servian.sampletest.ui.fragments

import androidx.fragment.app.Fragment
import com.servian.sampletest.ui.dialog.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null

    fun showProgressDialog() {
        progressDialog = ProgressDialog(requireActivity())
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    fun hideDialog() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
        }
    }
}