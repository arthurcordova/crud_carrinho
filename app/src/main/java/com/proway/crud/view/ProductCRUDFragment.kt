package com.proway.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.proway.crud.R
import com.proway.crud.adapter.ProductAdapter
import com.proway.crud.databinding.ProductCrudFragmentBinding
import com.proway.crud.model.Category
import com.proway.crud.model.Product
import com.proway.crud.model.ProductWithCategory
import com.proway.crud.view_model.ProductCRUDViewModel

class ProductCRUDFragment : Fragment(R.layout.product_crud_fragment) {

    companion object {
        fun newInstance() = ProductCRUDFragment()
    }

    private lateinit var viewModel: ProductCRUDViewModel
    private lateinit var binding: ProductCrudFragmentBinding
    private lateinit var adapterSpinner: ArrayAdapter<String>

    private var selectedCategory: Category? = null
    private var selectedProduct: ProductWithCategory? = null

    private val adapter: ProductAdapter = ProductAdapter {
        setValueToFields(it)
    }

    private val observerProducts = Observer<List<ProductWithCategory>> {
        adapter.refresh(it)
    }

    private val observerCategories = Observer<List<Category>> {
        val listOf = it.map { category ->
            category.name
        }
        adapterSpinner.addAll(listOf)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProductCrudFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(ProductCRUDViewModel::class.java)

        setupRecyclerView()
        setupAutoComplete()
        setupForm()
        startObservers()
        initialData()
    }

    private fun setupAutoComplete() {
        adapterSpinner =
            ArrayAdapter<String>(requireContext(), R.layout.spinner_item_category)
        val autoCompleteBrand: AutoCompleteTextView? =
            binding.inputCategoryTextInputLayout.editText as? AutoCompleteTextView
        autoCompleteBrand?.setAdapter(adapterSpinner)
        autoCompleteBrand?.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position) as String
            viewModel.categories.value?.first { cat -> (cat.name.equals(selected, true)) }
                ?.let {
                    selectedCategory = it
                }
        }
    }

    fun setupRecyclerView() {
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.productsRecyclerView.adapter = adapter
    }

    fun startObservers() {
        viewModel.categories.observe(viewLifecycleOwner, observerCategories)
        viewModel.products.observe(viewLifecycleOwner, observerProducts)

    }

    fun initialData() {
        viewModel.getProducts()
        viewModel.getCategories()
        binding.inputIdTextInputLayout.visibility = View.GONE
    }

    fun setupForm() {
        binding.newButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            val priceStr = binding.inputPriceTextInputLayout.editText?.text ?: ""

            if (nameStr.isNotEmpty() && priceStr.isNotEmpty() && selectedCategory != null) {
                Product(
                    name = nameStr.toString(),
                    price = priceStr.toString().toDouble(),
                    categoryFK = selectedCategory!!.id
                ).let {
                    viewModel.insertProduct(it)
                    clearFields()
                }
            }
        }
        binding.deleteButton.setOnClickListener {
            selectedProduct?.product?.let {
                viewModel.deleteProduct(it)
                clearFields()
            }
        }
        binding.editButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            val priceStr = binding.inputPriceTextInputLayout.editText?.text ?: ""

            if (nameStr.isNotEmpty() && priceStr.isNotEmpty() && selectedCategory != null) {
                Product(
                    name = nameStr.toString(),
                    price = priceStr.toString().toDouble(),
                    categoryFK = selectedCategory!!.id
                ).let {
                    viewModel.updateProduct(it)
                    clearFields()
                }
            }
        }
    }

    fun clearFields() {
        binding.inputNameTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.editText?.setText("")
        binding.inputPriceTextInputLayout.editText?.setText("")
        binding.inputCategoryTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.visibility = View.GONE
        binding.newButton.visibility = View.VISIBLE

        selectedCategory = null
        selectedProduct = null
    }

    fun setValueToFields(productWithCategory: ProductWithCategory) {
        binding.inputIdTextInputLayout.editText?.setText(productWithCategory.product?.id.toString())
        binding.inputNameTextInputLayout.editText?.setText(productWithCategory.product?.name)
        binding.inputPriceTextInputLayout.editText?.setText(productWithCategory.product?.price.toString())
        binding.inputCategoryTextInputLayout.editText?.setText(productWithCategory.category?.name)

        binding.inputIdTextInputLayout.visibility = View.VISIBLE
        binding.newButton.visibility = View.GONE

        selectedProduct = productWithCategory
        selectedCategory = productWithCategory.category
    }


}