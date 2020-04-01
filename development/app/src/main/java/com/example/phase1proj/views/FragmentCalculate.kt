package com.example.phase1proj.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.phase1proj.R

class FragmentCalculate : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.title = "Health Calculators"
        return inflater.inflate(R.layout.fragment_calculate, container, false)
    }
}