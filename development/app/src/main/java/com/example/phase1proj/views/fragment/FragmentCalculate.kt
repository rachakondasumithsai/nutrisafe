package com.example.phase1proj.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.phase1proj.R
import com.example.phase1proj.views.fragment.FragmentBmiCalculator
import com.example.phase1proj.views.fragment.FragmentBmrCalculator
import kotlinx.android.synthetic.main.fragment_calculate.view.*


class FragmentCalculate : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_calculate, container, false)
        activity?.title = "Health Calculators"

        view.bmiButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(
                R.id.fragment_container,
                FragmentBmiCalculator()
            )?.addToBackStack(null)?.commit()
        }

        view.bmrButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(
                R.id.fragment_container,
                FragmentBmrCalculator()
            )?.addToBackStack(null)?.commit()
        }
        return view
    }
}