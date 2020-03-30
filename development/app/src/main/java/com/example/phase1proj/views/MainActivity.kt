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

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open, R.string.close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val fragment = FragmentHome()
        val fragmentTransaction =
            supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, "Home")
        fragmentTransaction.commit()
        val bottomNav =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            FragmentHome()
        ).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home) {
            val fragment = FragmentHome()
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment, "Home")
            fragmentTransaction.commit()
        } else if (id == R.id.work) {
            val fragment = FragmentWork()
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment, "Work")
            fragmentTransaction.commit()
        } else if (id == R.id.school) {
            val fragment = FragmentSchool()
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment, "School")
            fragmentTransaction.commit()
        } else if (id == R.id.setting) {
            val fragment = FragmentSetting()
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment, "Settings")
            fragmentTransaction.commit()
        } else if (id == R.id.timeline) {
            val fragment = FragmentTimeline()
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment, "Timeline")
            fragmentTransaction.commit()
        } else if (id == R.id.logout) {
            val fragment = FragmentLogout()
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment, "Logout")
            fragmentTransaction.commit()
        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

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
            ).commit()
            true
        }
}