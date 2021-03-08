package com.github.aleksandrgrebenkin.androidlevel3.presentation.view.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.androidlevel3.R
import com.github.aleksandrgrebenkin.androidlevel3.databinding.FragmentSearchBinding
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import com.github.aleksandrgrebenkin.androidlevel3.presentation.view.common.viewLifecycle
import com.github.aleksandrgrebenkin.androidlevel3.presentation.view.history.adapter.HistoryAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment(), HistoryAdapter.OnItemClickListener {
    private val historyModel: HistoryViewModel by viewModel()
    private var adapter: HistoryAdapter? = null
    private var binding: FragmentSearchBinding by viewLifecycle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        historyModel.subscribeHistoryWordList().observe(viewLifecycleOwner, { showList(it) })
        historyModel.subscribeError().observe(viewLifecycleOwner, { showError(it) })
        historyModel.getAll()
        return binding.root
    }

    override fun onResume() {
        setToolbar()
        super.onResume()
    }

    override fun onPause() {
        wipeToolbar()
        super.onPause()
    }

    private fun setToolbar() {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = getString(R.string.history)
        }
        setHasOptionsMenu(true)
    }

    private fun wipeToolbar() {
        setHasOptionsMenu(false)
    }

    private fun showList(words: List<Word>) {
        adapter?.setData(words) ?: initAdapter(words)
    }

    private fun initAdapter(words: List<Word>) {
        binding.wordsRv.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = HistoryAdapter(words, this)
        }
    }

    private fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        Log.e("HISTORY_FRAGMENT_ERROR", error)
    }

    override fun onItemClicked(word: Word) {
        goToWordScreen(word)
    }

    private fun goToWordScreen(word: Word) {
        val action = HistoryFragmentDirections.actionHistoryFragmentToMeaningFragment(word)
        findNavController().navigate(action)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}