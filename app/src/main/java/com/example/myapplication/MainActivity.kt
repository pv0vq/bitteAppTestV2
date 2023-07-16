package com.example.myapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentOneBinding
import com.example.myapplication.databinding.FragmentTestBinding
import com.example.myapplication.databinding.ItemMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    //프래그먼트 스와이프
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments = listOf(OneFragment(), TwoFragment(),ThreeFragment()
            )
        }

        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // 드로어 레이아웃과 토클 버튼 연동
        // 토글 레이아웃 연동
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened,
            R.string.drawer_closed)
        // 업버튼 호출 여부
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 업버튼을 토글버튼으로 변환
        toggle.syncState()


//        val fragmentManager: FragmentManager = supportFragmentManager
//        val transient: FragmentTransaction = fragmentManager.beginTransaction()
//        val fragment = RecyclerViewActivity.OneFragment()
//        // 새로운 프래그먼트를 화면에 추가
//        transient.add(R.id.fragment_content, fragment)
//        // 프래그먼트 출력
//        transient.commit()


        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter


        // tap 선언
        TabLayoutMediator(binding.tabs, binding.viewpager){ tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()

    }

//    class RecyclerViewActivity : AppCompatActivity () {
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            val binding = FragmentOneBinding.inflate(layoutInflater)
//            setContentView(binding.root)
//            val dates = mutableListOf<String>()
//
//            for (i in 1..10) {
//                dates.add("item ${i}")
//            }
//            // 항목 배치
//            binding.recyclerView.layoutManager = LinearLayoutManager(this)
//            // 항목 구성 (데이터)
//            binding.recyclerView.adapter = MyAdapter(dates)
//            binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
//        }
//    }

    // recyclerview 레이아웃 연동
    class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    // recyclerview 객체 생성
    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        // 항목 개수를 판단하려고 자동으로 호출
        // 0을 반환하면 아무것도 안나옴
        override fun getItemCount(): Int {
            return datas.size;
        }

        // 항목의 뷰를 가지는 뷰 홀더를 준비하려고 자동으로 호출
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent,
                false))

        // 뷰 홀더의 뷰에 데이터를 출력하려고 자동으로 호출
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.d("리스트 아이템", "리스트 아아템 클릭 시")
            val binding = (holder as MyViewHolder).binding
            // 뷰 데이터 출력
            binding.itemData.text = datas[position]

            // 아이템 클릭 이벤트
            binding.itemRoot.setOnClickListener{
                Log.d("리스트 아이템", "리스트 아아템 클릭 시1")
            }

        }

    }

    // 아이템 데커레이션
    // 리사이클 뷰를 꾸밀 경우 사용한다.
    class MyDecoration(val context: Context): RecyclerView.ItemDecoration () {
        // 항목이 배치되기 전에 호출
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
//            c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_background),0f,0f,null)
        }

        // 항목이 모두 배치된 이후 호출
        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)

//            // 뷰 크기 계산
            val width = parent.width
            val height = parent.height

            // 이미지 크기 계산
            // 리스트 뒤에 배경 화면
//            val dr: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
//            val drWidth = dr?.intrinsicWidth
//            val drHeight = dr?.intrinsicHeight
//
//            // 이미지가 그려질 위치 계싼
//            val left = width/2 - drWidth?.div(2) as Int
//            val top = height/2 - drHeight?.div(2) as Int
//
//            c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo),
//                left.toFloat(), top.toFloat(), null)
        }

        // 개별 항목을 꾸밀 떄 호출
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent.getChildAdapterPosition(view)+1
            if(index % 3 == 0)
                outRect.set(10, 10, 10, 60)
            else
                outRect.set(10, 10, 10, 0)

            view.setBackgroundColor(Color.parseColor("#FFFFFF"))
            ViewCompat.setElevation(view, 20.0f)
        }
    }

    // 프래그먼트 객체
    // 리스트
    class OneFragment: Fragment () {
        // 늦은 초기화
        lateinit var binding: FragmentTestBinding
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val binding = FragmentOneBinding.inflate(inflater, container, false)
            val dates = mutableListOf<String>()

            // 아이템 추가
            for (i in 1..10) {
                dates.add("item ${i}")
            }
            // 항목 배치
             binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            // binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
            // 항목 구성 (데이터)
            binding.recyclerView.adapter = MyAdapter(dates)
            binding.recyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            // 아이템 테커레이션
            binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))
            return  binding.root
        }
 }

    class TwoFragment: Fragment () {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_two, container, false)
        }
    }

    class ThreeFragment: Fragment () {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_three, container, false)
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
//        val menuItem1: MenuItem? = menu?.add(0,0,0, "메뉴1") // 매겨변수(groupID, itemId, order, title)
//        val menuItem2: MenuItem? = menu?.add(0,1,0, "메뉴1")
//        return super.onCreateOptionsMenu(menu)

        // menuItem 객체 생성
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        // 검색 메뉴 이벤트
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어 변경 이벤트
                Log.d("검색어 이벤트", "search text(change): $newText")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어 submit 이벤트
                Log.d("검색어 이벤트", "search text(submit): $query")
                return true
            }
        })
        return true
    }

    // 메뉴 리스트 이벤트
//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
//        0 -> {
//            Log.d("메뉴아이템" , "메뉴아이템 0번")
//            true
//        }
//
//        1 -> {
//            Log.d("메뉴아이템" , "메뉴아이템 1번")
//            true
//        }
//
//        else -> super.onOptionsItemSelected(item)
//    }


    // 토글 버튼 및 드로습
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 토클 버튼을 클릭 시 이벤트
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}