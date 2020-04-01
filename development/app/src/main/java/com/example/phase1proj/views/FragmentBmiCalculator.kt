package com.example.phase1proj.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.phase1proj.R
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.fragment_bmi_calculator.*
import kotlinx.android.synthetic.main.fragment_bmi_calculator.view.*
import kotlinx.android.synthetic.main.popup_window.view.*


class FragmentBmiCalculator : Fragment()  {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_bmi_calculator, container, false)
        activity?.title = "BMI Calculator"
        view.bmifindbutton.setOnClickListener{
            var age = view.age.text.toString()  //.toInt()
            var height = view.heightVal.text.toString() //.toDouble()
            var weight = view.weight.text.toString() //.toDouble()
            var bmivalue = view.bmivalue
            if(age.isEmpty() || height.isEmpty()||weight.isEmpty()){
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
                    bmilayout, // Location to display popup window
                    Gravity.CENTER, // Exact position of layout to display popup
                    0, // X offset
                    0 // Y offset
                )
            } else {
                if(age.toInt()>18){
                    var heightVal = ((height.toDouble() * height.toDouble())/10000)
                    var bmi = (weight.toDouble())/ heightVal
                    bmivalue.setText(String.format("%.2f",bmi))
                }
            }
        }
        return view
    }
}