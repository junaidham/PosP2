package com.nextgen.sunmip2print.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.nextgen.sunmip2print.R
import com.nextgen.sunmip2print.databinding.FragmentTicketBinding
import com.nextgen.sunmip2print.ui.vm.TicketViewModel


class TicketFragment : Fragment() {
    private lateinit var dataBinding : FragmentTicketBinding

    companion object {
        fun newInstance() = TicketFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ticket, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val viewModelPrint: TicketViewModel by viewModels()
        context?.let {
            dataBinding.model = viewModelPrint
        }

    }

}