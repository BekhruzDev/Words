package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment() {
    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!! // also check for only '='!
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    //this is just like 'setContentView(binding.root) in the onCreate() method of MainActivity.kt
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null    //Just before destroying _binding = null and no property works
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    //lets create a function that switches the layout icon on and off
    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return //nothing
        menuItem.icon =
            if (isLinearLayoutManager) ContextCompat.getDrawable(
                this.requireContext(),
                R.drawable.ic_grid_layout
            )
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

}