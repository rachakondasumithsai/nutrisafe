package com.example.phase1proj.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.phase1proj.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


// This is the class which stores the overall framework of the application.
// It is therefore called the main activity.
// It contains the navigation drawer, bottom navigation and the container to store the
// fragments
class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    // Class attributes
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Assign the toolbar present in the view
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Assign the navigation drawer to the drawer layout variable
        drawerLayout = findViewById(R.id.drawerLayout)

        // Set the toggler to open/close the drawer
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open, R.string.close
        )

        // Add a drawer listener for toggling
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()

        // find the bottom navigation view
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // By default, the first fragment is the home screen
        val fragment = FragmentHome()
        val fragmentTransaction =
            supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, "Home")
        fragmentTransaction.commit()

        // Find the bottom navigation and by default, home is the first fragment
        val bottomNav =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            FragmentHome()
        ).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // Opens a fragment for a corresponding item in the drawer
        when (item.itemId) {
            R.id.home -> {
                val fragment = FragmentHome()
                val fragmentTransaction =
                    supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, fragment, "Home")
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.work -> {
                val fragment = FragmentWork()
                val fragmentTransaction =
                    supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, fragment, "Work")
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.school -> {
                val fragment = FragmentSchool()
                val fragmentTransaction =
                    supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, fragment, "School")
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.setting -> {
                val fragment = FragmentSetting()
                val fragmentTransaction =
                    supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, fragment, "Settings")
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.timeline -> {
                val fragment = FragmentTimeline()
                val fragmentTransaction =
                    supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, fragment, "Timeline")
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.logout -> {
                val fragment = FragmentLogout()
                val fragmentTransaction =
                    supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, fragment, "Logout")
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    // Used to close the drawer on clicking the hamburger icon
    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // open a fragment based on the item selected from the bottom navigation view
    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_home -> selectedFragment = FragmentHome()
                R.id.navigation_calculate -> selectedFragment = FragmentCalculate()
                R.id.navigation_blogs -> selectedFragment = FragmentBlogs()
                R.id.navigation_add_meal -> selectedFragment = FragmentAddMeal()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment!!
            ).addToBackStack(null).commit()
            true
        }
}