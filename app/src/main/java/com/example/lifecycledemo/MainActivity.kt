package com.example.lifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.lifecycledemo.databinding.MainActivityBinding
import com.example.lifecycledemo.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        lifecycleScope.launch(IO) {
            Log.i("MyTag"," thread is ${Thread.currentThread().name}")
        }

        lifecycleScope.launchWhenCreated {
            // 액티비티나 프래그먼트 수명 주기 동안
            // 한 번만 발생해야 하는 장기 실행 작업 있는 경우
            // launchWhenCreated 사용
            // ...
        }

        lifecycleScope.launchWhenStarted {
            // launchWhenStarted는 activity나 fragment가
            // 시작될 때 코루틴이 시작됨
            // ...
        }

        lifecycleScope.launchWhenResumed {
            // launchWhenResumed는 앱이 실행되고 실행된 직후
            // 장기 실행 작업을 시작해야 하는 경우
            // ...
        }
    }
}