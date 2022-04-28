package com.example.project1.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.R
import com.example.project1.base.ParentFragment
import com.example.project1.ui.main.MainViewModel
import org.koin.android.ext.android.inject

class HistoryFragment : ParentFragment() {

    // вью модель управление данными
    private val viewModel: MainViewModel by inject()

    private lateinit var tvList: TextView
    private lateinit var rvHistory: RecyclerView
    private lateinit var btnClear: Button

    private val historyAdapter by lazy {
        HistoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    //нп
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setAdapter()
        setData()
    }

    // подготовка вьюшек
    override fun bindViews(view: View) = with(view) {
        tvList = view.findViewById(R.id.tv_list)
        rvHistory = findViewById(R.id.rvMovies)
        btnClear = view.findViewById(R.id.btn_clear)

        btnClear.setOnClickListener{
            viewModel.clearHistory()
            historyAdapter.clearAll()
        }

        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvHistory.layoutManager = linearLayoutManager
        }

    override fun setData() {
        observeViewModel()
    }

    // адаптер для листа ресайкл вью
    private fun setAdapter() {
        rvHistory.adapter = historyAdapter
    }

    // observing viewModel передача инфы для листа
    private fun observeViewModel() {
        viewModel.readAllData.observe(viewLifecycleOwner,
            Observer {
                historyAdapter.addItems(it)
            })
    }

}