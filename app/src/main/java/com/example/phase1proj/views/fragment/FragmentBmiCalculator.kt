package com.example.phase1proj.views.fragment

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.phase1proj.R
import kotlinx.android.synthetic.main.fragment_bmi_calculator.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class FragmentBmiCalculator : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_bmi_calculator, container, false)
        activity?.title = "BMI Calculator"

        var height = view.findViewById<TextView>(R.id.heightVal) //.toDouble()
        var weight = view.findViewById<TextView>(R.id.weight) //.toDouble()
        var bmivalue = view.findViewById(R.id.bmivalue) as TextView
        var errorMsg = view.findViewById(R.id.error_msg) as TextView

        val filter: InputFilter = inputFilter()

        // Input filters to take only two decimal values for edit text
        height.filters = arrayOf(filter)
        weight.filters = arrayOf(filter)

        view.bmifindbutton.setOnClickListener {

            var age = view.age.text.toString()  //.toInt()
            var heightVal = height.text.toString()
            var weightVal = weight.text.toString()

            if (age.isEmpty() || heightVal.isEmpty() || weightVal.isEmpty()) {

                bmivalue.visibility = TextView.GONE
                errorMsg.visibility = TextView.VISIBLE

            } else {

                if (age.toDouble() in 18.0..120.0) {

                    if (weightVal == "." || heightVal == ".") {

                        bmivalue.visibility = TextView.GONE
                        errorMsg.visibility = TextView.VISIBLE

                    } else {

                        var heightInDouble =
                            ((heightVal.toDouble() * heightVal.toDouble()) / 10000)
                        var bmi = (weightVal.toDouble()) / heightInDouble
                        bmivalue.text = String.format("%.2f", bmi)
                        bmivalue.visibility = TextView.VISIBLE
                        errorMsg.visibility = TextView.GONE
                    }

                } else {
                    bmivalue.visibility = TextView.GONE
                    errorMsg.visibility = TextView.VISIBLE

                }

            }
        }
        return view
    }

    private fun inputFilter(): InputFilter {
        return object : InputFilter {
            val maxDigitsBeforeDecimalPoint = 4
            val maxDigitsAfterDecimalPoint = 2

            private val mPattern: Pattern =
                Pattern.compile("[0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "}+((\\.[0-9]{0," + (maxDigitsAfterDecimalPoint) + "})?)||(\\.)?")

            override fun filter(
                source: CharSequence, start: Int, end: Int,
                dest: Spanned, dstart: Int, dend: Int
            ): CharSequence? {
                val builder = StringBuilder(dest)
                builder.replace(
                    dstart, dend, source
                        .subSequence(start, end).toString()
                )
                val matcher: Matcher = mPattern.matcher(builder.toString())
                return if (!matcher.matches()) {
                    if (source.isEmpty()) {
                        dest.subSequence(dstart, dend)
                    } else {
                        ""
                    }
                } else null
            }
        }
    }
}