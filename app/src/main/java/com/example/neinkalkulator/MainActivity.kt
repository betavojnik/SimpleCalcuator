package com.example.neinkalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastDot = false
    var lastNumeric = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun funk(view: View) {

        idegas.append((view as Button).text)
        lastNumeric = true

    }

    fun cisti(view: View) {
        idegas.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun decimal(view: View) {
        if (lastDot == false && lastNumeric == true) {
            idegas.append(".")
            lastDot = true
        }
    }

    private fun sadrzi(a: String): Boolean {
        if (idegas.text.toString().startsWith("-")) {
            return false
        } else return a.contains("/") || a.contains("+") || a.contains("*") || a.contains("-")
    }

    fun operatorl(view: View) {

        if (lastNumeric && !sadrzi(idegas.text.toString())) {
            idegas.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }

    }


    fun jednako(view: View) {

        if (lastNumeric) { // Read the textView value
            var value = idegas.text.toString()
            var prefix = ""
            try {

                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1);
                }

                if (value.contains("/")) {

                    val splitedValue = value.split("/")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    idegas.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if (value.contains("*")) {

                    val splitedValue = value.split("*")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    idegas.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (value.contains("-")) {


                    val splitedValue = value.split("-")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    idegas.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (value.contains("+")) {

                    val splitedValue = value.split("+")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    idegas.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {

        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }
}
