package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentWordListBinding


class WordListFragment : Fragment() {
    companion object{
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://dictionary.cambridge.org/dictionary/english/"
    }
    private var _binding: FragmentWordListBinding? = null
    private val binding = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(   //layout inflation of fragment_word_list.xml
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //binding access to views
        val letterId = activity?.intent?.extras?.getString(WordListFragment.LETTER).toString()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WordAdapter(letterId, this.requireContext())

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //title = getString(R.string.detail_prefix) + " " + letterId
}
