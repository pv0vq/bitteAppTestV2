package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentTestBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val fragmentManager: FragmentManager = supportFragmentManager
        val transient: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()
        // 새로운 프래그먼트를 화면에 추가
        transient.add(R.id.fragment_content, fragment)
        // 프래그먼트 출력
        transient.commit()


        // 업버튼 호출 여부
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    // 프래그먼트 객체
    class OneFragment: Fragment () {
        // 늦은 초기화
        lateinit var binding: FragmentTestBinding
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_test, container, false)
        }
 }

    // 업버튼 클릭시 실행
    override fun onSupportNavigateUp(): Boolean {
        Log.d("업버튼", "테스트")
        return super.onSupportNavigateUp()
    }

    // 메뉴 리스트 호출
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 상단 메뉴 생성
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem1: MenuItem? = menu?.add(0,0,0, "메뉴1") // 매겨변수(groupID, itemId, order, title)
        val menuItem2: MenuItem? = menu?.add(0,1,0, "메뉴1")
        return super.onCreateOptionsMenu(menu)
    }

    // 메뉴 리스트 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        0 -> {
            Log.d("메뉴아이템" , "메뉴아이템 0번")
            true
        }

        1 -> {
            Log.d("메뉴아이템" , "메뉴아이템 1번")
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

}