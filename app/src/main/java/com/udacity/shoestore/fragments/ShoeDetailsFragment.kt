package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.utils.navigateSafe
import com.udacity.shoestore.viewModels.ShoeDetailsViewModel
import com.udacity.shoestore.viewModels.ShoesViewModel

class ShoeDetailsFragment : Fragment() {

    private val shoesViewModel: ShoesViewModel by activityViewModels()
    private lateinit var detailViewModel: ShoeDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )
        detailViewModel = ViewModelProvider(this)[ShoeDetailsViewModel::class.java]
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this

        binding.buttonCancel.setOnClickListener { navigateSafe(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoesListFragment()) }

        binding.buttonSave.setOnClickListener {
            if (detailViewModel.validateFields()) {
                val shoe = detailViewModel.buildShoe()
                shoesViewModel.addShoe(shoe)
                navigateSafe(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoesListFragment())
            } else {
                Toast.makeText(context, getString(R.string.empty_fields_warning), Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}