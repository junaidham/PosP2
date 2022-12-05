package com.nextgen.sunmip2print.ui.vm

import android.graphics.BitmapFactory
import android.view.View
import androidx.lifecycle.ViewModel
import com.nextgen.sunmip2print.R
import com.sunmi.printerx.api.PrintResult
import com.sunmi.printerx.enums.*
import com.sunmi.printerx.style.*
import kotlin.math.roundToInt

class TicketViewModel  : ViewModel(){

    /**
     * Text printing example
     * Set inline size and inline text alignment through initLine
     * Directly print the entire line through printText
     * Control the content of each print by addText
     * Partial highlighting and text inversion require Sunmi 3.0 font support
     */
    fun printText() {
        selectPrinter?.lineApi()?.run {
            initLine(BaseStyle.getStyle())
            printText("This line will be printed directly", TextStyle.getStyle())
            addText("Different styles of content:", TextStyle.getStyle())
            addText("Bold", TextStyle.getStyle().enableBold(true))
            addText("Underline", TextStyle.getStyle().enableUnderline(true))
            addText("Strikethrough", TextStyle.getStyle().enableStrikethrough(true))
            addText("Tilt", TextStyle.getStyle().enableItalics(true))
            addText("\n", TextStyle.getStyle())
            autoOut()
        }
    }

    /**
     * Example of printing text by column
     *  | * | * | * |
     *  |*  | * |  *|
     *  |*  |*  |*  |
     */
    fun printTexts() {
        selectPrinter?.lineApi()?.run {
            val textStyle = TextStyle.getStyle().setAlign(Align.CENTER)
            printTexts(arrayOf("first column","second column","third column"), intArrayOf(1, 1, 1), arrayOf(textStyle, textStyle, textStyle))
            printTexts(arrayOf("first column","second column","third column"), intArrayOf(1, 1, 1),
                arrayOf(
                    TextStyle.getStyle().setAlign(Align.LEFT),
                    TextStyle.getStyle().setAlign(Align.CENTER),
                    TextStyle.getStyle().setAlign(Align.RIGHT)))
            val textStyle1 = TextStyle.getStyle().setAlign(Align.LEFT)
            printTexts(arrayOf("first column","second column","third column"), intArrayOf(1, 1, 1), arrayOf(textStyle1, textStyle1, textStyle1))
            autoOut()
        }
    }


    /**
     * Example of printing dividing lines
     * The dividing line includes blank lines, and the printed content of each line can be divided by blank lines
     */
    fun printLine() {
        selectPrinter?.lineApi()?.run {
            printDividingLine(DividingLine.EMPTY, 20)
            printDividingLine(DividingLine.DOTTED, 5)
            printDividingLine(DividingLine.EMPTY, 20)
            printDividingLine(DividingLine.SOLID, 10)
            autoOut()
        }
    }

    /**
     * Combining the above to build a standard ticket content
     */
    fun printTicket(view: View) {
        selectPrinter?.lineApi()?.run {

            initLine(BaseStyle.getStyle().setAlign(Align.CENTER))
            printText("---------- Tafani Telecom --------", TextStyle.getStyle().setTextSize(22))


            //set logo
            val option: BitmapFactory.Options = BitmapFactory.Options().apply {
                inScaled = false
            }
            val bitmap = BitmapFactory.decodeResource(view.context.resources, R.drawable.logo, option)
            printBitmap(bitmap, BitmapStyle.getStyle().setAlign(Align.CENTER).setAlgorithm(ImageAlgorithm.BINARIZATION).setValue(120).setWidth(200).setHeight(100))
           // printBitmap(bitmap, BitmapStyle.getStyle().setAlign(Align.CENTER).setAlgorithm(ImageAlgorithm.DITHERING).setWidth(384).setHeight(150))


            val textStyleDLine = TextStyle.getStyle().setAlign(Align.DEFAULT).setTextSize(20)
            val textStyleD = TextStyle.getStyle().setAlign(Align.DEFAULT).setTextSize(18)
            val textStyleC = TextStyle.getStyle().setAlign(Align.CENTER).setTextSize(15)
            val textStyleL = TextStyle.getStyle().setAlign(Align.LEFT).setTextSize(10)
            val textStyleR = TextStyle.getStyle().setAlign(Align.RIGHT).setTextSize(15)
            printText("Address : G-44, Noida, U.P.,India ", textStyleD)
            printText("Date:05-12-2022                 Time:11:20", textStyleD) //42 chars
            printText("--------------------------------------", textStyleDLine) //38 chars

            printText("Merchant Name      :       Junaid Ahmad   " , textStyleD) //42 chars
            printText("Merchant ID        :       8802587111     ",  textStyleD) //42 chars
            printText("Service Type       :       Wallet Recharge",textStyleD) //42 chars

            //initLine(BaseStyle.getStyle().setAlign(Align.CENTER))
            printText("--------------------------------------", textStyleDLine) //38 chars
            printText("Txn Id             :       12052022001    ", textStyleD) //42 chars
            printText("Amount Dr.         :       UNTS 100       ", textStyleD) //42 chars
            printText("Bal. Amount        :       UNTS 1000      ", textStyleD) //42 chars
            printText("--------------------------------------", textStyleDLine) //38 chars

            //QR generate
            printText("\n", textStyleDLine)
            printQrCode("1234567890", QrStyle.getStyle().setDot(9).setAlign(Align.CENTER))

            printText("Thanks you for using this service", TextStyle.getStyle().setAlign(Align.CENTER).setTextSize(15))
            printText("www.tafani.com", TextStyle.getStyle().setAlign(Align.CENTER).setTextSize(20))

            printText("--------------------------------------", textStyleDLine) //38 chars

            autoOut()
        }
    }

    fun printTicket2(view: View) {
        selectPrinter?.lineApi()?.run {

            initLine(BaseStyle.getStyle().setAlign(Align.CENTER))
            printText("------------ Tafani Telecom ------------", TextStyle.getStyle().setTextSize(22))


            //set logo
            val option: BitmapFactory.Options = BitmapFactory.Options().apply {
                inScaled = false
            }
            val bitmap = BitmapFactory.decodeResource(view.context.resources, R.drawable.logo, option)
            printBitmap(bitmap, BitmapStyle.getStyle().setAlign(Align.CENTER).setAlgorithm(ImageAlgorithm.BINARIZATION).setValue(120).setWidth(300).setHeight(120))
            // printBitmap(bitmap, BitmapStyle.getStyle().setAlign(Align.CENTER).setAlgorithm(ImageAlgorithm.DITHERING).setWidth(384).setHeight(150))


            val textStyleDLine = TextStyle.getStyle().setAlign(Align.DEFAULT).setTextSize(20)
            val textStyleD = TextStyle.getStyle().setAlign(Align.DEFAULT).setTextSize(15)
            val textStyleC = TextStyle.getStyle().setAlign(Align.CENTER).setTextSize(12)
            val textStyleL = TextStyle.getStyle().setAlign(Align.LEFT).setTextSize(12)
            val textStyleR = TextStyle.getStyle().setAlign(Align.RIGHT).setTextSize(12)
            // printTexts(arrayOf("Merchant Type to Customer"), intArrayOf(1, 1, 1), arrayOf(textStyle, textStyle, textStyle))
            printText("Address : G-44, Noida, U.P.,India ", textStyleD)
            printText("----------------------------------------", textStyleDLine)

            printTexts(arrayOf("Date: 05-12-2022","Time : 11:20"), intArrayOf(1, 1), arrayOf(textStyleL, textStyleR))

            printTexts(arrayOf("Merchant Name : ",   "Junaid Ahmad"), intArrayOf(1,  1), arrayOf(textStyleL,  textStyleR))
            printTexts(arrayOf("Merchant ID : ",   "8802587111"), intArrayOf(1,  1), arrayOf(textStyleL,  textStyleR))
            printTexts(arrayOf("Service Type :"," Wallet Recharge"), intArrayOf(1, 1), arrayOf(textStyleL, textStyleR))

            //initLine(BaseStyle.getStyle().setAlign(Align.CENTER))
            printText("----------------------------------------", textStyleDLine)

            printTexts(arrayOf("Txn Id", ":" , "12052022001"), intArrayOf(1, 1, 1), arrayOf(textStyleL, textStyleC, textStyleR))
            printTexts(arrayOf("Amount Dr", ":" , "UNTS 100"), intArrayOf(1, 1, 1), arrayOf(textStyleL, textStyleC, textStyleR))
            printTexts(arrayOf("Bal. Amount", ":" , "UNTS 1000"), intArrayOf(1, 1, 1), arrayOf(textStyleL, textStyleC, textStyleR))

            printText("----------------------------------------", textStyleDLine)

            //QR generate
            printText("\n", textStyleDLine)
            printQrCode("1234567890", QrStyle.getStyle().setDot(9).setAlign(Align.CENTER))

            printText("Thanks you for using this service", TextStyle.getStyle().setAlign(Align.CENTER).setTextSize(20))
            printText("www.tafani.com", TextStyle.getStyle().setAlign(Align.CENTER).setTextSize(25))

            printText("----------------------------------------", textStyleDLine)

            autoOut()
        }
    }

    /**
     * You can turn on the transaction mode when you need to monitor the print results
     * After it is turned on, the result can be obtained by listening to the callback through printTrans every time the content is printed
     */
    fun printTicketAndListen(view: View) {
        //The transaction mode can be turned on when the print result needs to be monitored
        selectPrinter?.lineApi()?.enableTransMode(true)

        // Print receipt normally
        printTicket(view)
        //Transaction monitoring results
        selectPrinter?.lineApi()?.printTrans(object : PrintResult() {
            override fun onResult(resultCode: Int, message: String?) {
                if(resultCode == 0) {
                    //print complete
                } else {
                    //print failed
                    println(selectPrinter?.queryApi()?.status)
                }
            }

        })
    }


    /**
     * Print barcode example
     * Demonstrate printing a few sections of barcode content
     * If the content of the barcode is too long, it will cause abnormal display. At this time, you need to customize the width of the barcode according to your needs
     * (Customized barcode width may affect the barcode recognition effect)
     */
    fun printBar() {
        selectPrinter?.lineApi()?.run {
            val barcodeStyle = BarcodeStyle.getStyle().setAlign(Align.CENTER).setDotWidth(2).setBarHeight(100).setReadable(
                HumanReadable.POS_TWO)
            printBarCode("0123456789", barcodeStyle)
            printBarCode("0123456789ABCDEFG", barcodeStyle)
            barcodeStyle.setWidth(384)
            printBarCode("0123456789ABCDEFG", barcodeStyle)
            autoOut()
        }
    }

    /**
     * Print Qr code example
     */
    fun printQr() {
        selectPrinter?.lineApi()?.run {
            printQrCode("http://www.sunmi.com", QrStyle.getStyle().setAlign(Align.CENTER)
                .setDot(9).setErrorLevel(ErrorLevel.L))
            initLine(BaseStyle.getStyle().setAlign(Align.CENTER))
            printText("http://www.sunmi.com", TextStyle.getStyle().enableBold(true))
            autoOut()
        }
    }

    /**
     * Example of printing Logo
     * Use binarization to process pictures and dithering algorithms to process pictures respectively
     * Due to the orange font on the transparent background of the logo itself, different
     * effects can be presented by setting the threshold after binarization
     */
    fun printBitmap(view: View) {
        selectPrinter?.lineApi()?.run {
            val option: BitmapFactory.Options = BitmapFactory.Options().apply {
                inScaled = false
            }
            val bitmap = BitmapFactory.decodeResource(view.context.resources, R.drawable.nexgen_logo, option)
            printBitmap(bitmap, BitmapStyle.getStyle().setAlign(Align.CENTER).setAlgorithm(
                ImageAlgorithm.BINARIZATION).setValue(120).setWidth(384).setHeight(150))
            printBitmap(bitmap, BitmapStyle.getStyle().setAlign(Align.CENTER).setAlgorithm(
                ImageAlgorithm.DITHERING).setWidth(384).setHeight(150))
            autoOut()
        }
    }




}