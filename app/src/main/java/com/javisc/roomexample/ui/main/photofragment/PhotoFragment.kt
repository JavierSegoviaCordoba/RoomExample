package com.javisc.roomexample.ui.main.photofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.javisc.roomexample.R
import com.javisc.roomexample.ui.ScreenState
import com.javisc.roomexample.ui.extension.snackbarShortOnDismissed
import kotlinx.android.synthetic.main.photo_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : Fragment() {

    private val viewModel: PhotoViewModel by viewModel()
    private var counter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStates(view)
        setupButtons()
        setupRecyclerView()
    }

    private fun setupStates(view: View) {
        swipeRefreshLayout.isEnabled = false
        viewModel.screenState.observe(this, Observer { screenState ->
            when (screenState) {
                is ScreenState.LOADING -> swipeRefreshLayout.isRefreshing = true
                is ScreenState.ERROR ->
                    view.snackbarShortOnDismissed(screenState.message) { viewModel.finishState() }
                is ScreenState.FINISHED -> swipeRefreshLayout.isRefreshing = false
                is ScreenState.SUCCESS -> swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun setupButtons() {
        buttonAddItem.setOnClickListener { viewModel.getPhoto(counter + 1) }
        buttonClear.setOnClickListener { viewModel.clear() }
    }

    private fun setupRecyclerView() {
        val photoAdapter = PhotoAdapter().apply {
            onClick = { photo ->
                Toast.makeText(context, photo.title, Toast.LENGTH_LONG).show()
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoAdapter
        }

        viewModel.photoList.observe(this, Observer { photoList ->
            counter = photoList.size
            photoAdapter.submitList(photoList)
            recyclerView.smoothScrollToPosition(photoAdapter.itemCount)
        })
    }

}