package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ShoeViewBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.utils.navigateSafe
import com.udacity.shoestore.viewModels.ShoesViewModel

class ShoesListFragment : Fragment() {

    private val viewModel: ShoesViewModel by viewModels()
    private lateinit var binding: FragmentShoesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoes_list,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel =viewModel
        setHasOptionsMenu(true)
        binding.addShoeButton.setOnClickListener{
            navigateSafe(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailsFragment())
        }
        viewModel.shoesList.observe(viewLifecycleOwner) { shoesList ->
            for (shoe in shoesList) {
                addShoeToView(container, shoe)
            }
        }
        return binding.root
    }

    private fun addShoeToView(
        container: ViewGroup?,
        shoe: Shoe
    ) {
        val shoeBinding: ShoeViewBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.shoe_view, container, false
        )
        shoeBinding.shoe = shoe
        binding.shoeListContainer
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navigateSafe(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailsFragment())
        return true
    }
}