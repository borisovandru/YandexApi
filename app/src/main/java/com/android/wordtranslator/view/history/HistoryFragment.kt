package com.android.wordtranslator.view.history

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.android.wordtranslator.R
import com.android.wordtranslator.databinding.FragmentHistoryBinding
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.storage.entity.WordTranslate
import com.android.wordtranslator.view.detail.DetailScreen
import com.android.wordtranslator.view.history.adapter.HistoryWordAdapter

class HistoryFragment : Fragment(R.layout.fragment_history), HistoryWordAdapter.Delegate {
    companion object {
        fun newInstance() = HistoryFragment()
    }

    private lateinit var binding: FragmentHistoryBinding
    private val model: HistoryViewModel by viewModel()
    private val historyWordAdapter by lazy { HistoryWordAdapter(this) }
    private val router: Router by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history_menu, menu)
    }

    private fun clearHistory() {
        model.clearHistory()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.clear -> {
                clearHistory()
                true
            }
            else -> false
        }

    private fun init() {
        model.translateLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            when (it) {
                is AppState.Success -> {
                    if (it.data == null || (it.data as List<WordTranslate>).isEmpty()) {
                        showErrorScreen(getString(R.string.empty_server_response_on_success))
                    } else {
                        showViewSuccess()
                        historyWordAdapter.setData(ArrayList(it.data))
                    }
                }
                is AppState.Loading -> {
                    showViewLoading()
                    with(binding) {
                        if (it.progress != null) {
                            progressBarHorizontal.isVisible = true
                            progressBarRound.isVisible = false
                            progressBarHorizontal.progress = it.progress
                        } else {
                            progressBarHorizontal.isVisible = false
                            progressBarRound.isVisible = true
                        }
                    }
                }
                is AppState.Error -> {
                    showErrorScreen(it.error.message)
                }
            }
        })
        model.clearLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            when (it) {
                is AppState.Success -> {
                    if ((it.data as Int) > 0) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.success_clear),
                            Toast.LENGTH_SHORT
                        ).show()
                        model.getData()
                    }
                }
                is AppState.Loading -> {
                    showViewLoading()
                    with(binding) {
                        if (it.progress != null) {
                            progressBarHorizontal.isVisible = true
                            progressBarRound.isVisible = false
                            progressBarHorizontal.progress = it.progress
                        } else {
                            progressBarHorizontal.isVisible = false
                            progressBarRound.isVisible = true
                        }
                    }
                }
                is AppState.Error -> {
                    binding.loadingFrame.isVisible = false
                    Toast.makeText(requireContext(), it.error.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
        model.getData()
        with(binding) {
            with(historyRv) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = historyWordAdapter
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
        }
    }

    private fun showViewSuccess() {
        with(binding) {
            successFrame.isVisible = true
            loadingFrame.isVisible = false
            errorFrame.isVisible = false
        }
    }

    private fun showViewLoading() {
        with(binding) {
            successFrame.isVisible = false
            loadingFrame.isVisible = true
            errorFrame.isVisible = false
        }
    }

    private fun showViewError() {
        with(binding) {
            successFrame.isVisible = false
            loadingFrame.isVisible = false
            errorFrame.isVisible = true
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData()
        }
    }

    override fun onItemPicked(word: WordTranslate) {
        router.navigateTo(DetailScreen(word = word))
    }
}