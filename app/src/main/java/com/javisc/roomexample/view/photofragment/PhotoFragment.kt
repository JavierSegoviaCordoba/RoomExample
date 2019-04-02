package com.javisc.roomexample.view.photofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.javisc.roomexample.R
import com.javisc.roomexample.util.ScreenState
import kotlinx.android.synthetic.main.photo_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotoFragment : Fragment() {

    private val viewModel: PhotoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupStates()
        setupButtons()
        setupRecyclerView()

    }

    private fun setupStates() {
        swipeRefreshLayout.isEnabled = false
        viewModel.screenState.observe(this, Observer { screenState ->
            when (screenState) {
                is ScreenState.LOADING -> swipeRefreshLayout.isRefreshing = true
                is ScreenState.ERROR -> {
                    swipeRefreshLayout.isRefreshing = false
                    view?.let { view ->
                        Snackbar.make(view, screenState.message, Snackbar.LENGTH_SHORT).show()
                    }
                }
                is ScreenState.SUCCESS -> {
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        })
    }

    private fun setupButtons() {
        var counter = 0L

        buttonAddItem.setOnClickListener {
            counter++
            viewModel.getPhoto(counter)
        }

        buttonClear.setOnClickListener { viewModel.clear() }
    }

    private fun setupRecyclerView() {
        val photoAdapter = PhotoAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoAdapter
        }

        viewModel.photoList.observe(this, Observer {
            photoAdapter.submitList(it)
            recyclerView.smoothScrollToPosition(photoAdapter.itemCount)
        })
    }

}
