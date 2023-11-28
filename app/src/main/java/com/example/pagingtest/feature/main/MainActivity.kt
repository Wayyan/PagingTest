package com.example.pagingtest.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingtest.base.core.BaseActivity
import com.example.pagingtest.databinding.ActivityMainBinding
import com.example.pagingtest.extension.showPromptDialog
import com.example.pagingtest.feature.main.paging.BeerAdapter
import com.example.pagingtest.feature.main.uiModel.MainAlertState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    private val beerAdapter by lazy { BeerAdapter() }

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpRecyclerView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { state ->
                        state.beers?.let {
                            beerAdapter.submitData(it)
                        }
                    }
                }

                launch {
                    viewModel.state.collectLatest { state ->
                        state.alertState.let {
                            when (it) {
                                null -> {
                                    binding.errorPromptLayout.visibility = View.GONE
                                }

                                is MainAlertState.PromptLoadingBeersError -> {
                                    if (it.isImportant) {
                                        showPromptDialog(it.message, "Retry") {
                                            beerAdapter.retry()
                                            viewModel.dismissedAlert()
                                        }
                                    } else {
                                        binding.tvErrorDescription.text = it.message
                                        binding.errorPromptLayout.visibility = View.VISIBLE
                                    }
                                }
                            }
                        }
                    }
                }

                launch {
                    beerAdapter.loadStateFlow.collect {
                        binding.prependProgress.isVisible =
                            (it.mediator?.prepend is LoadState.Loading) || (it.mediator?.refresh is LoadState.Loading)

                        binding.appendProgress.isVisible = it.mediator?.append is LoadState.Loading

                        (it.mediator?.refresh as? LoadState.Error)?.error?.let { e ->
                            viewModel.errorLoadingBeers(e, true)
                        }

                        (it.mediator?.append as? LoadState.Error)?.error?.let { e ->
                            viewModel.errorLoadingBeers(e)
                        }

                        (it.mediator?.prepend as? LoadState.Error)?.error?.let { e ->
                            viewModel.errorLoadingBeers(e)
                        }
                    }
                }
            }
        }

        initUiListener()
        viewModel.loadBeers()
    }

    private fun initUiListener(){
        binding.imgRetry.setOnClickListener {
            beerAdapter.retry()
            viewModel.dismissedAlert()
        }
    }

    private fun setUpRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        binding.beerList.adapter = beerAdapter
        binding.beerList.layoutManager = gridLayoutManager

    }
}