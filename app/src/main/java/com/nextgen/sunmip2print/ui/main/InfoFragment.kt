package com.nextgen.sunmip2print.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.nextgen.sunmip2print.R
import com.nextgen.sunmip2print.databinding.FragmentInfoBinding
import com.nextgen.sunmip2print.ui.vm.PrinterViewModel
import com.sunmi.printerx.PrinterSdk
import com.sunmi.printerx.PrinterSdk.Printer


class InfoFragment : Fragment() {
    private lateinit var dataBinding : FragmentInfoBinding
    private lateinit var arrayAdapter : ArrayAdapter<PrinterSdk.Printer>

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding =  DataBindingUtil.inflate( inflater, R.layout.fragment_info, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            val viewModel: PrinterViewModel by viewModels()
            dataBinding.model = viewModel
            dataBinding.lifecycleOwner = viewLifecycleOwner
            viewModel.showPrinters.observe(viewLifecycleOwner){
                it?.let {
                    arrayAdapter.clear()
                    arrayAdapter.addAll(it) //add all items
                }
            }

            arrayAdapter = ArrayAdapter(it, android.R.layout.simple_spinner_item, arrayListOf())
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dataBinding.spinner.let {
                it.adapter = arrayAdapter
                it.onItemSelectedListener = object : OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        viewModel.showPrinter(arrayAdapter.getItem(position) as Printer)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                }
            }


            viewModel.initPrinter(it)

        }
    }


}