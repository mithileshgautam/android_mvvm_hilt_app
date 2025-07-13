package com.example.productbrowser.repository

import com.example.productbrowser.database.ProductDao
import com.example.productbrowser.network.FakeStoreApi
import com.example.productbrowser.uistate.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val api: FakeStoreApi,
    private val dao: ProductDao
){

    fun getProducts() = flow {
        emit(Resource.Loading())
        try{
            val products = api.getProducts()
            dao.insertAll(products)
            emit(Resource.Success(dao.getAllProducts()))
        }catch (e: Exception){
            val cashed = dao.getAllProducts()
            if(cashed.isNotEmpty()){
                emit(Resource.Success(cashed))

            }else{
                emit(Resource.Error("Failed to load products: ${e.localizedMessage}"))
            }
        }
    }
}