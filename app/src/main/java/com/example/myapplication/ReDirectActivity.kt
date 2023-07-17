package com.example.myapplication
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRedirectBinding

class ReDirectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRedirectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val data = intent.getStringExtra("data")
        Log.d("업버튼", "테스트:? ${data}")
        binding.redirectData.text = data

        binding.delayTextView.setOnClickListener {
            intent.putExtra("result", "돌아왔다!")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}

