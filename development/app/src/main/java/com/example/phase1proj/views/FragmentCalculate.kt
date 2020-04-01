package com.example.phase1proj.views

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.phase1proj.R
import kotlinx.android.synthetic.main.fragment_calculate.*
import kotlinx.android.synthetic.main.fragment_calculate.view.*


class FragmentCalculate : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
        var view = inflater.inflate(R.layout.fragment_calculate, container, false)
        activity?.title = "Health Calculators"


        view.bmiButton.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(
                R.id.fragment_container,
                FragmentBmiCalculator()
            )?.addToBackStack(null)?.commit()
        }

        view.bmrButton.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(
                R.id.fragment_container,
                FragmentBmrCalculator()
            )?.addToBackStack(null)?.commit()
        }
        return view
=======

        activity?.title = "Health Calculators"
        return inflater.inflate(R.layout.fragment_calculate, container, false)
>>>>>>> 034f7e99161f5eb1457ec6ed17bba5bcf1c169b5
    }
}