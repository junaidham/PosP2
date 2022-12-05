package com.nextgen.sunmip2print.ui.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunmi.printerx.PrinterSdk
import com.sunmi.printerx.PrinterSdk.Printer
import com.sunmi.printerx.enums.PrinterInfo
import com.sunmi.printerx.enums.PrinterType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var selectPrinter: Printer? = null

class PrinterViewModel : ViewModel() {
    private var printer: PrinterSdk.Printer? = null

    var showPrinters = MutableLiveData<MutableList<Printer>?>()

    var printerStatus = MutableLiveData<String>()

    var printerName = MutableLiveData<String>()

    var printerType = MutableLiveData<String>()

    var printerPaper = MutableLiveData<String>()

    fun initPrinter(context: Context) {
        PrinterSdk.getInstance().getPrinter(context, object : PrinterSdk.PrinterListen {

            override fun onDefPrinter(printer: PrinterSdk.Printer?) {
                printer?.let {
                    if(selectPrinter == null) {
                        selectPrinter = it
                    }
                }
            }

            override fun onPrinters(printers: MutableList<PrinterSdk.Printer>?) {
                showPrinters.postValue(printers)
            }

        })
    }

    fun changeSelectPrinter() {
        selectPrinter = printer
    }


    /**
     * Get status and other information
     * Because it is a blocking operation, it is best to obtain it in a coroutine or thread
     */
    fun showPrinter(printer: Printer) {
        this.printer = printer
        viewModelScope.launch(Dispatchers.IO) {
            //printer.queryApi().getStatus().name()
            printerStatus.postValue(printer.queryApi().status.name)
            printerName.postValue(printer.queryApi().getInfo(PrinterInfo.NAME))
            printerType.postValue(printer.queryApi().getInfo(PrinterInfo.TYPE))
            printerPaper.postValue(printer.queryApi().getInfo(PrinterInfo.PAPER))
            // If you need to judge the type of printing paper, you can refer to the following methods:
            //checkPrinterPaper(printer)
            // Other information
            // is only supported by SUNMI built-in printers and not displayed on the UI, you can choose to display it according to business needs
            //如 printer.queryApi().getInfo(PrinterInfo.DENSITY) ...
        }
    }

    /**
     * Generally printing small tickets may require printing paper judgment to determine the small ticket layout
     * You can refer to this method for judgment
     */
    fun checkPrinterPaper(printer: Printer) {
        printer?.let {
            val paper = it.queryApi().getInfo(PrinterInfo.PAPER)
            val printerType = it.queryApi().getInfo(PrinterInfo.TYPE)
            when(paper) {
                "58mm" -> println("Printer paper 58mm")
                "80mm" -> println("Printer paper 80mm")
                else -> {
                    if(printerType == PrinterType.THERMAL.toString())  {
                        println("Printer paper 58mm")
                    } else {
                        println("Non-thermal printer")
                    }
                }
            }
        }
    }



}