package com.github.aleksandrgrebenkin.meaningscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.core.view.common.viewLifecycle
import com.github.aleksandrgrebenkin.core.viewmodel.HistoryViewModel
import com.github.aleksandrgrebenkin.meaningscreen.R
import com.github.aleksandrgrebenkin.meaningscreen.databinding.FragmentMeaningBinding
import com.github.aleksandrgrebenkin.meaningscreen.view.adapter.MeaningAdapter
import com.github.aleksandrgrebenkin.model.entity.Meaning
import org.koin.android.viewmodel.ext.android.viewModel


class MeaningFragment : Fragment() {
    private val historyModel: HistoryViewModel by viewModel()
    private var binding: FragmentMeaningBinding by viewLifecycle()
    private val args: MeaningFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeaningBinding.inflate(inflater, container, false)
        historyModel.save(args.word)
        initAdapter(args.word.meanings)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setToolbar()
    }

    override fun onPause() {
        wipeToolbar()
        super.onPause()
    }

    private fun setToolbar() {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = args.word.text
        }
        setHasOptionsMenu(true)
    }

    private fun wipeToolbar() {
        setHasOptionsMenu(false)
    }

    private fun initAdapter(meanings: List<Meaning>) {
        binding.meaningsRv.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = MeaningAdapter(meanings)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}