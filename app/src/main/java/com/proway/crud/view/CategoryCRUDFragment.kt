package com.proway.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proway.crud.R
import com.proway.crud.databinding.CategoryCrudFragmentBinding
import com.proway.crud.view_model.MainViewModel

class CategoryCRUDFragment : Fragment(R.layout.category_crud_fragment) {

    companion object {
        fun newInstance() = CategoryCRUDFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: CategoryCrudFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CategoryCrudFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)



    }

}