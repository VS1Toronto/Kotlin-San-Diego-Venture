package com.core.data_03.models

/**
 *  THIS CLASS HOLDS THE LIST OF PHOTOS
 *
 *  This class is needed because the data arrives as aan Array
 *  of photos and so this data class is used as a holder of photos
 */
data class ProductList(val ventures : List<Product>) {
}
