package com.nextgen.sunmip2print

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.nextgen.sunmip2print.ui.main.MainFragment
import com.nextgen.sunmip2print.ui.vm.PrinterViewModel

class MainActivity : AppCompatActivity() {


    private val handler by lazy {
        Handler(mainLooper)
    }

    private val viewModel: PrinterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            switchFragment(MainFragment.newInstance(), false)
        }
    }

    fun switchFragment(fragment: Fragment, addToBackStack: Boolean = true, clearStack: Boolean = true ){
        handler.post {
            if (clearStack) {
                if (lifecycle.currentState != Lifecycle.State.RESUMED) {
                    fixBug()
                }

                var r = supportFragmentManager.popBackStackImmediate(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }

            var transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment, fragment::class.java.name)
            if (addToBackStack) {
                transaction.addToBackStack(fragment::class.java.name)
            }
            transaction.commitAllowingStateLoss()
            supportFragmentManager.executePendingTransactions()

        }
    }

    fun fixBug() {
        try {
            var aClass = FragmentManager::class.java
            var method = aClass.getDeclaredMethod("noteStateNotSaved")
            method.setAccessible(true)
            method.invoke(supportFragmentManager)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}