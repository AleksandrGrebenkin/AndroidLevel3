package com.github.aleksandrgrebenkin.androidlevel3.presentation.ui.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.androidlevel3.R
import com.github.aleksandrgrebenkin.androidlevel3.databinding.ActivityMainBinding
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word
import com.github.aleksandrgrebenkin.androidlevel3.presentation.presenter.MainPresenter
import com.github.aleksandrgrebenkin.androidlevel3.presentation.presenter.MainView
import com.github.aleksandrgrebenkin.androidlevel3.presentation.ui.adapter.MainAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(uiScheduler = AndroidSchedulers.mainThread())
    private var adapter: MainAdapter? = null
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
    }

    override fun showList(words: List<Word>) {
        adapter?.setData(words) ?: initAdapter(words)
    }

    private fun initAdapter(words: List<Word>) {
        binding.wordsRv.layoutManager = LinearLayoutManager(applicationContext)
        binding.wordsRv.adapter = MainAdapter(words)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    presenter.search(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}