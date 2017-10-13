package com.frontinelabs.rxsample

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {
    val fragment1: Fragment = ItemOneFragment()
    val fragment2: Fragment = ItemTwoFragment()
    val fragment3: Fragment = ItemThreeFragment()
    val fragment4: Fragment = ItemFourFragment()
    val fragment5: Fragment = ItemFiveFragment()

    var active = fragment1
    val fm = fragmentManager
    var count : Int = 0
    var count2 : Int = 0
    var count3 : Int = 0
    var count4 : Int = 0
    var count5 : Int = 0

    var isAddFragment1 : Boolean  = false
    var isAddFragment2 : Boolean  = false
    var isAddFragment3 : Boolean  = false
    var isAddFragment4 : Boolean  = false
    var isAddFragment5 : Boolean  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

       // fm.beginTransaction().add(R.id.frame_layout, fragment3, "3").commit()
       // fm.beginTransaction().add(R.id.frame_layout, fragment2, "2").commit()
       // fm.beginTransaction().add(R.id.frame_layout, fragment1, "1").commit()
        val f = fm.findFragmentById(R.id.frame_layout)

        bottomBar.setOnTabSelectListener { tabId ->
            if (tabId == R.id.tab_favorites) {
                count++
                if(!isAddFragment1){
                    fm.beginTransaction().hide(active).add(R.id.frame_layout, fragment1, "1").show(fragment1).commit()
                    isAddFragment1 = true
                }else{
                    fm.beginTransaction().hide(active).show(fragment1).commit()
                }
                active = fragment1
            } else if (tabId == R.id.tab_nearby) {
                count2++
                Log.e("counter", count.toString())
                if(!isAddFragment2){
                    fm.beginTransaction().hide(active).add(R.id.frame_layout, fragment2, "2").show(fragment2).commit()
                    isAddFragment2 = true
                }else{
                    fm.beginTransaction().hide(active).show(fragment2).commit()
                }
                active = fragment2
            } else if (tabId == R.id.tab_friends) {
                count3++
                if(!isAddFragment3){
                    fm.beginTransaction().hide(active).add(R.id.frame_layout, fragment3, "3").show(fragment3).commit()
                    isAddFragment3 = true
                }else{
                    fm.beginTransaction().hide(active).show(fragment3).commit()
                }
                active = fragment3
            }else if (tabId == R.id.tab_transaksi) {
                count4++
                if(!isAddFragment4){
                    fm.beginTransaction().hide(active).add(R.id.frame_layout, fragment4, "4").show(fragment4).commit()
                    isAddFragment4 = true
                }else{
                    fm.beginTransaction().hide(active).show(fragment4).commit()
                }
                active = fragment4
            }else if (tabId == R.id.tab_account) {
                count5++
                if(!isAddFragment5){
                    fm.beginTransaction().hide(active).add(R.id.frame_layout, fragment5, "5").show(fragment5).commit()
                    isAddFragment5 = true
                }else{
                    fm.beginTransaction().hide(active).show(fragment5).commit()
                }
                active = fragment5
            }
        }
    }


}
