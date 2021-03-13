package com.github.aleksandrgrebenkin.searchscreen.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.core.view.common.viewLifecycle
import com.github.aleksandrgrebenkin.core.viewmodel.SearchViewModel
import com.github.aleksandrgrebenkin.model.entity.Word
import com.github.aleksandrgrebenkin.searchscreen.R
import com.github.aleksandrgrebenkin.searchscreen.databinding.FragmentSearchBinding
import com.github.aleksandrgrebenkin.searchscreen.view.adapter.SearchAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {

    private val searchModel: SearchViewModel by viewModel()
    private var adapter: SearchAdapter? = null
    private var binding: FragmentSearchBinding by viewLifecycle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchModel.subscribeWordList().observe(viewLifecycleOwner, { showList(it) })
        searchModel.subscribeError().observe(viewLifecycleOwner, { showError(it) })
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
            it.setDisplayHomeAsUpEnabled(false)
            it.title = getString(R.string.search)
        }
        setHasOptionsMenu(true)
    }

    private fun wipeToolbar() {
        setHasOptionsMenu(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        setSearchView(searchView)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history -> goToHistoryScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setSearchView(searchView: SearchView) {
        val manager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(manager.getSearchableInfo(activity?.componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchModel.search(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.length > 2) {
                        searchModel.search(it)
                    }
                }
                return true
            }
        })
    }

    private fun showList(words: List<Word>) {
        adapter?.setData(words) ?: initAdapter(words)
    }

    private fun initAdapter(words: List<Word>) {
        binding.wordsRv.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = SearchAdapter(words, this)
        }
    }

    private fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        Log.e("SEARCH_FRAGMENT_ERROR", error)
    }

    override fun onItemClicked(word: Word) {
        goToWordScreen(word)
    }

    private fun goToWordScreen(word: Word) {
        val action = SearchFragmentDirections.actionSearchFragmentToMeaningFragment(word)
        findNavController().navigate(action)
    }

    private fun goToHistoryScreen() {
        val action = SearchFragmentDirections.actionSearchFragmentToHistoryFragment()
        findNavController().navigate(action)
    }
}