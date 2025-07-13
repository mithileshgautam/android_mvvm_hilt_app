package com.example.productbrowser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productbrowser.R
import com.example.productbrowser.model.Product

class ProductAdapter: ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallBack()) {

    class ProductViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(product: Product){
            view.findViewById<TextView>(R.id.titleText).text = product.title
            view.findViewById<TextView>(R.id.priceText).text = "${product.price}"
            view.findViewById<TextView>(R.id.categoryText).text = product.category
            Glide.with(view).load(product.image).into(view.findViewById(R.id.imageView))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallBack : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(old: Product, new: Product) = old.id == new.id
        override fun areContentsTheSame(old: Product, new: Product) = old == new
    }
}