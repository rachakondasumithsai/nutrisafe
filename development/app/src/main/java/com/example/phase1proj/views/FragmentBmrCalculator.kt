package com.example.phase1proj.views

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import com.example.phase1proj.R
import kotlinx.android.synthetic.main.fragment_bmi_calculator.*
import kotlinx.android.synthetic.main.fragment_bmr_calculator.*
import kotlinx.android.synthetic.main.fragment_bmr_calculator.view.*
import kotlinx.android.synthetic.main.popup_window.view.*

class FragmentBmrCalculator: Fragment () {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_bmr_calculator, container, false)
        activity?.title = "BMR Calculator"
        view.bmrfindbutton.setOnClickListener{
            var age = view.ageValue.text.toString()
            var weight = view.bmrweight.text.toString()
            var height = view.bmrheight.text.toString()
            var bmrvalue = view.bmrValue
            var bmrVal: Number =0
            if(age.isEmpty() || height.isEmpty()||weight.isEmpty()|| !(view.male.isChecked || view.female.isChecked)){
                val view1 = inflater.inflate(R.layout.popup_window,null)
                val popupWindow = PopupWindow(
                    view1, // Custom view to show in popup window
                    LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                    LinearLayout.LayoutParams.WRAP_CONTENT // Window height
                )
                view1.msgview.setOnClickListener{
                    view1.msgview.setTextColor(Color.RED)
                }
                view1.button_popup.setOnClickListener{
                    // Dismiss the popup window
                    popupWindow.dismiss()
                }
                popupWindow.showAtLocation(
                    bmr, // Location to display popup window
                    Gravity.CENTER, // Exact position of layout to display popup
                    0, // X offset
                    0 // Y offset
                )
            } else {
                if (age.toDouble() > 15 && age.toDouble() < 80) {
                    if (view.male.isChecked) {
                        bmrVal =
                            13.397 * weight.toDouble() + 4.799 * height.toDouble() - 5.677 * age.toDouble() + 88.362
                    } else if (view.female.isChecked) {
                        bmrVal =
                            9.247 * weight.toDouble() + 3.098 * height.toDouble() - 4.330 * age.toDouble() + 447.593
                    }
                    bmrvalue.setText(String.format("%.2f", bmrVal))
                }
            }
        }
        return view
    }
}