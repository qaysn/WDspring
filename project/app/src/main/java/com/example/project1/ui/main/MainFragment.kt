package com.example.project1.ui.main

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.project1.R
import com.example.project1.base.ParentFragment
import com.example.project1.data.models.CatInfo
import com.example.project1.data.models.CatInfoRoom
import com.example.project1.room.CatsDatabase
import com.example.project1.room.CatsRepository
import com.example.project1.utilities.AppPreferences
import com.google.android.material.bottomappbar.BottomAppBar
import org.koin.android.ext.android.inject
import java.lang.StringBuilder

class MainFragment  : ParentFragment() {

    private val viewModel: MainViewModel by inject()
    private val catsViewModel: CatsViewModel by inject()

    private lateinit var progressBar: ProgressBar

// как в HistoryFragment
    private lateinit var tvTest: TextView
    private lateinit var btnLoad: Button
    private lateinit var ivPhoto: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatabaseRoom()
        bindViews(view)
        setData()
    }

    override fun bindViews(view: View) = with(view) {
        tvTest = view.findViewById(R.id.tv_test)
        btnLoad = view.findViewById(R.id.btn_load)
        ivPhoto = view.findViewById(R.id.iv_photo)
        progressBar = view.findViewById(R.id.progress_bar)

        btnLoad.setOnClickListener{
            catsViewModel.getRandomCat()
        }

    }

    override fun setData() {
        observeViewModel()
        observeCatsViewModel()
    }

    fun initDatabaseRoom(){

    }

    // добавление в дб айтема
    fun addToDatabase(item: CatInfo?){
        if(item != null){
            viewModel.addToDatabase(item)
        }
    }
    private fun observeCatsViewModel() {
        catsViewModel.getRandomCat()

        catsViewModel.liveData.observe(
            viewLifecycleOwner,
            Observer {result ->
                // обработка запроса по типу ERROR, Success
                when (result) {
                    is CatsViewModel.State.ShowLoading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is CatsViewModel.State.HideLoading -> {
                        progressBar.visibility = View.GONE
                    }
                    is CatsViewModel.State.Result -> {
                        val catInfo = result.catsInfo?.get(0)
                        val imageUrl = catInfo?.url

                        // добавление в дб айтема
                        addToDatabase(catInfo)
                        if (imageUrl != null) {
                            setImage(imageUrl)
                        }
                        Log.i("MSG", "CATEGORY: " + catInfo?.categories)

                        var categoryInfo = "None"
                        if(catInfo?.categories != null) categoryInfo = catInfo.categories.get(0).name
                        val strBuilder = StringBuilder()
                            .append("id: " + catInfo?.id + "\n")
                            .append("url: " + catInfo?.id + "\n")
                            .append("WxH: " + catInfo?.width + ":" + catInfo?.height + "\n")
                            .append("category: $categoryInfo")
                        tvTest.text = strBuilder
                    }
                    is CatsViewModel.State.Error -> {
                        Log.i("MSG", "ERROR: " + result.error)
                    }
                    is CatsViewModel.State.IntError -> {
                        Log.i("MSG", "ERROR:2 " + result.error)
                    }
                }
            }
        )
    }

    fun setImage(imageUrl: String){
        // глайд для загрузки картинки с инета и тут прогрессБар при загрузке
        val circularProgressDrawable = CircularProgressDrawable(ivPhoto.context)
        circularProgressDrawable.strokeWidth = 7f
        circularProgressDrawable.centerRadius = 70f
        circularProgressDrawable.start()

        Glide.with(this)
            .load(imageUrl)
            .placeholder(circularProgressDrawable)
            .into(ivPhoto)
    }

    private fun observeViewModel() {
        viewModel.open.observe(
            viewLifecycleOwner,
            Observer {
                tvTest.text = "TESTED FROM VM"
            }
        )
    }


}