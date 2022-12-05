package com.nextgen.sunmip2print.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.nextgen.sunmip2print.MainActivity
import com.nextgen.sunmip2print.R
import com.nextgen.sunmip2print.databinding.FragmentMainBinding
import com.nextgen.sunmip2print.ui.vm.PrinterViewModel


class MainFragment : Fragment() {

    private lateinit var dataBinding : FragmentMainBinding




    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.info.setOnClickListener {
            (activity as MainActivity).switchFragment(InfoFragment.newInstance(),
                addToBackStack = true
            )
        }
        dataBinding.ticket.setOnClickListener {
            (activity as MainActivity).switchFragment(TicketFragment.newInstance(),
                addToBackStack = true
            )
        }



        val viewModelPrinterInfo: PrinterViewModel by viewModels()
        context?.let { viewModelPrinterInfo.initPrinter(it) }

    }



}

