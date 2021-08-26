package com.proway.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.proway.crud.R
import com.proway.crud.adapter.CategoryAdapter
import com.proway.crud.databinding.CategoryCrudFragmentBinding
import com.proway.crud.model.Category
import com.proway.crud.view_model.MainViewModel

class CategoryCRUDFragment : Fragment(R.layout.category_crud_fragment) {

    companion object {
        fun newInstance() = CategoryCRUDFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: CategoryCrudFragmentBinding
    private var selectedCategory: Category? = null

    private val adapter = CategoryAdapter {
        selectedCategory = it
        binding.inputIdTextInputLayout.visibility = VISIBLE
        binding.newButton.visibility = GONE
        setValueToFields(category = it)
    }

    private val observerCategories = Observer<List<Category>> {
        adapter.refresh(it)
        clearFields()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CategoryCrudFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.injectContextAndStartDAO(requireContext())

        settingRecyclerView()
        settingForm()

        viewModel.categories.observe(viewLifecycleOwner, observerCategories)
        initialData()
    }

    fun initialData() {
        viewModel.getCategories()
        binding.inputIdTextInputLayout.visibility = GONE
    }

    fun settingRecyclerView() {
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.categoriesRecyclerView.adapter = adapter
    }

    fun settingForm() {
        binding.newButton.setOnClickListener {
            binding.inputNameTextInputLayout.editText?.let { edit ->
                if (edit.text.isNotEmpty()) {
                    Category(name = edit.text.toString()).let { category ->
                        viewModel.insertCategory(category)
                        clearFields()
                    }
                }
            }
        }
        binding.deleteButton.setOnClickListener {
            selectedCategory?.let {
                viewModel.deleteCategory(it)
            }
        }
        binding.editButton.setOnClickListener {
            selectedCategory?.let { selectedCategory ->

                binding.inputNameTextInputLayout.editText?.let { edit ->
                    if (edit.text.isNotEmpty()) {
                        Category(
                            id = selectedCategory.id,
                            name = edit.text.toString()
                        ).let { category ->
                            viewModel.updateCategory(category)
                        }
                    }
                }
            }
        }
    }

    fun clearFields() {
        binding.inputNameTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.visibility = GONE
        binding.newButton.visibility = VISIBLE
    }

    fun setValueToFields(category: Category) {
        binding.inputIdTextInputLayout.editText?.setText(category.id.toString())
        binding.inputNameTextInputLayout.editText?.setText(category.name)
    }
}