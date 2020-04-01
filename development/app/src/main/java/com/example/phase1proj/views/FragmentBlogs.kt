package com.example.phase1proj.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.phase1proj.R
import kotlinx.android.synthetic.main.fragment_blogs.*

class FragmentBlogs : Fragment() {

    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.title = "Nutrition Blogs"
        return inflater.inflate(R.layout.fragment_blogs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Implementations of listeners for all five ImageButtons. Each listener will call-
        // openLink() method where the link for blog will be opened in a browser
        healthlineimage.setOnClickListener {
            url = healthlineimage.contentDescription.toString()
            openLink(view = View(this.context))
        }
        mynewrootsimage.setOnClickListener {
            url = mynewrootsimage.contentDescription.toString()
            openLink(view = View(this.context))
        }
        ohsheglowsimage.setOnClickListener {
            url = ohsheglowsimage.contentDescription.toString()
            openLink(view = View(this.context))
        }
        runningonrealfoodimage.setOnClickListener {
            url = runningonrealfoodimage.contentDescription.toString()
            openLink(view = View(this.context))
        }
        theroastedrootimage.setOnClickListener {
            url = theroastedrootimage.contentDescription.toString()
            openLink(view = View(this.context))
        }
    }

    //Function to open Blog url in a browser
    private fun openLink(view: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)

    }

}
