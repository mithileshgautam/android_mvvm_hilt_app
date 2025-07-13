package com.example.productbrowser.view.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productbrowser.adapter.ProductAdapter
import com.example.productbrowser.databinding.ActivityProductListBinding
import com.example.productbrowser.uistate.Resource
import com.example.productbrowser.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityProductListBinding
    private val adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(!isNetworkAvailable(this)){
            showMessage()
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.productState.collect { state ->
                    when (state) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.submitList(state.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.errorText.visibility = View.VISIBLE
                            binding.errorText.text = state.message
                        }
                    }
                }
            }
        }
    }
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun showMessage() {
        Toast.makeText(this, "You are offline", Toast.LENGTH_LONG).show()
    }
}