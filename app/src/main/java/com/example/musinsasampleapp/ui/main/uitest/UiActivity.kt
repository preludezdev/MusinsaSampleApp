package com.example.musinsasampleapp.ui.main.uitest


import android.os.Bundle
import android.view.Menu
import androidx.core.content.ContextCompat
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseActivity
import com.example.musinsasampleapp.data.vo.MeetingRoomInfo
import com.example.musinsasampleapp.databinding.ActivityUiBinding
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream


class UiActivity : BaseActivity<ActivityUiBinding>(R.layout.activity_ui) {

    private lateinit var data: List<MeetingRoomInfo>
    private val rvBriefAdapter by lazy { RvBriefAdapter() }
    private val rvDetailAdapter by lazy { RvDetailAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initRecyclerView()
        loadJsonData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ui, menu)
        return true
    }

    private fun initToolbar() {
        with(binding.toolbar) {
            title = resources.getString(R.string.menu_title)
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            setSupportActionBar(this)
        }
    }

    private fun initRecyclerView() {
        binding.rvBriefStatus.adapter = rvBriefAdapter
        binding.rvDetailStatus.adapter = rvDetailAdapter
    }

    private fun loadJsonData() {
        val jsonStringFile = inputStreamToString(assets.open("MUSINSA.json"))
        data = Gson().fromJson(jsonStringFile, Array<MeetingRoomInfo>::class.java).toList()

        binding.tvCurrPossibleRoomVal.text = data.size.toString()
        rvBriefAdapter.setData(data.map { it.name })
        rvDetailAdapter.setData(data)
    }

    private fun inputStreamToString(inputStream: InputStream): String {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            ""
        }
    }

}
