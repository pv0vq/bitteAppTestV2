package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        // 업버튼 호출 여부
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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