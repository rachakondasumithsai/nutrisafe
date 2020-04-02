package com.example.phase1proj.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.phase1proj.R
import com.github.barteksc.pdfviewer.PDFView

class FragmentAbout : Fragment() {

    // Creates a pdf view object
    lateinit var pdfView: PDFView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Sets the title of the app bar for this fragment
        activity?.title = "About NutriSafe"

        var view = inflater.inflate(R.layout.fragment_about, container, false)

        // Find the pdf view that is present in the application
        pdfView = view.findViewById(R.id.pdfView) as PDFView

        // Load the pdf that is present in the assets folder and display the pdf in the app
        pdfView.fromAsset("about.pdf")
            .load()

        return view
    }

}
