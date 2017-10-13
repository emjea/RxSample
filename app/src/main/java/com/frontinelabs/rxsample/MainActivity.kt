package com.frontinelabs.rxsample

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fragment1: Fragment = ItemOneFragment()
    val fragment2: Fragment = ItemTwoFragment()
    val fragment3: Fragment = ItemThreeFragment()
    var activeFragment = fragment1
    val fm:FragmentManager = fragmentManager
    var current : Int = 1
    var currentFragment : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationView()
    }

    private fun setupNavigationView() {
        if (navigation != null) {

            // Select first menu item by default and show Fragment accordingly.
            val menu = navigation.menu
            selectFragment(menu.getItem(0),  "Home")

            // Set action to perform when any menu-item is selected.
            navigation.setOnNavigationItemSelectedListener { item ->
                Log.e("current item", current.toString())
                Log.e("item", item.toString())

                if(current.toString() == "Home")
                    currentFragment = 1
                else if (current.toString() == "Dashboard")
                    currentFragment = 2
                else
                    currentFragment = 3
                selectFragment(item, item.toString())
                false
            }
        }
    }

    /**
     * Perform action when any item is selected.

     * @param item Item that is selected.
     */
    private fun selectFragment(item: MenuItem, clickmenu : String) {

        item.isChecked = true

        when (item.itemId) {
            R.id.action_home ->
                // Action to perform when Home Menu item is selected.
                pushFragment(ItemOneFragment(), clickmenu)
            R.id.action_bag ->
                // Action to perform when Bag Menu item is selected.
                pushFragment(ItemTwoFragment(), clickmenu)
            R.id.action_account ->
                // Action to perform when Account Menu item is selected.
                pushFragment(ItemThreeFragment(), clickmenu)
        }
    }

    /**
     * Method to push any fragment into given id.

     * @param fragment An instance of Fragment to show into the given id.
     */
    private fun pushFragment(fragment: Fragment?, clickmenu : String) {
        if (fragment == null)
            return

        val fragmentManager = fragmentManager
        if (fragmentManager != null) {
            val ft = fragmentManager.beginTransaction()
            if (ft != null) {
                Log.e("current", currentFragment.toString())

                if(currentFragment == 1){
                    activeFragment = ItemOneFragment()
                }else if(currentFragment == 2){
                    activeFragment = ItemTwoFragment()
                }else{
                    activeFragment  = ItemThreeFragment()
                }

                ft.hide(activeFragment).add(R.id.frame_layout, fragment)
                ft.commit()
            }
        }
    }

}

