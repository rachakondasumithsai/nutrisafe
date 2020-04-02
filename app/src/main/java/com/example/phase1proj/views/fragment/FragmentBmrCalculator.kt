package com.example.phase1proj.views.fragment

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.phase1proj.R
import kotlinx.android.synthetic.main.fragment_bmr_calculator.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class FragmentBmrCalculator : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_bmr_calculator, container, false)
        activity?.title = "BMR Calculator"


        var weightEditText = view.findViewById(R.id.bmrweight) as EditText
        var heightEditText = view.findViewById(R.id.bmrheight) as EditText
        var errorMsg = view.findViewById(R.id.error_msg) as TextView
        var caloriePerDay = view.findViewById(R.id.textView7) as TextView
        var bmrvalue = view.bmrValue
        var bmrVal: Number = 0

        // Input filter to allow only two decimal values only
        val filter: InputFilter = inputFilter()

        // Assign those filters to the edit texts
        heightEditText.filters = arrayOf(filter)
        weightEditText.filters = arrayOf(filter)

        view.bmrfindbutton.setOnClickListener {

            var age = view.ageValue.text.toString()
            var height = heightEditText.text.toString()
            var weight = weightEditText.text.toString()

            if (age.isEmpty() || height.isEmpty() || weight.isEmpty() || !(view.male.isChecked || view.female.isChecked)) {

                bmrvalue.visibility = TextView.GONE
                caloriePerDay.visibility = TextView.GONE
                errorMsg.visibility = TextView.VISIBLE

            } else {
                if (age.toDouble() > 15 && age.toDouble() < 80) {


                    if (height == "." || weight == ".") {

                        bmrvalue.visibility = TextView.GONE
                        caloriePerDay.visibility = TextView.GONE
                        errorMsg.visibility = TextView.VISIBLE

                    } else {

                        if (view.male.isChecked) {
                            bmrVal =
                                13.397 * weight.toDouble() + 4.799 * height.toDouble() - 5.677 * age.toDouble() + 88.362
                        } else if (view.female.isChecked) {
                            bmrVal =
                                9.247 * weight.toDouble() + 3.098 * height.toDouble() - 4.330 * age.toDouble() + 447.593
                        }
                        bmrvalue.text = String.format("%.2f", bmrVal)
                        bmrvalue.visibility = TextView.VISIBLE
                        caloriePerDay.visibility = TextView.VISIBLE
                        errorMsg.visibility = TextView.GONE

                    }
                } else {
                    bmrvalue.visibility = TextView.GONE
                    caloriePerDay.visibility = TextView.GONE
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