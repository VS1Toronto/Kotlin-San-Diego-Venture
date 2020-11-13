package com.core.data_03

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.core.data_03.models.Product

/**
 *  In the Adapters Constructor a list of photos and a click listener is passed in
 */
class MainAdapter(var products:List<Product>, var clickListener : View.OnClickListener) : RecyclerView.Adapter<MainAdapter.PhotoHolder>() {

    override fun getItemCount(): Int {
        return products.size      //  Returns number of photos we currently have
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val photo = products[position]    //  Get the photo at the given position

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //  Commented out for Venture as its price is a String so that I can add a range of price on each side of a dash
        //
        //  var itemCost = String.format("%.2f", photo.price)
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        holder?.name?.text = photo.itemName   //  Get tags text field and set its text to our tags from the photo object

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //  Original code without two decimal points left for reference uses randomNumber as no cost field in web resource
        //
        //  holder?.cost?.text = "£" + randomNumber             //  ""  and using toString() method because these are integers
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //  Commented out as the original design did not have the price on the MainActivity screen
        //
        //  holder?.price?.text = "£" + itemCost.toString() //  ""  and using toString() method because these are integers
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (photo.image.isNotEmpty()) {
            Glide.with(holder?.name?.context)
                .load("https://leedavidsoncontentmanagementsystem1.com/core_data/3_San_Diego_Venture_Core/images/" + photo.image)
                .into(holder?.photo_item)
        }
    }


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): PhotoHolder {
        return PhotoHolder(LayoutInflater.from(p0?.context).inflate(R.layout.photo_item, p0, false))
    }


    //  This function gets a specific photo and is used by WomensWearActivity
    //
    fun getPhoto(adapterPosition : Int) : Product{
        return products[adapterPosition]
    }


    inner class PhotoHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var name : TextView

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //  Commented out as the original design did not have the price on the MainActivity screen
        //
        //var price : TextView
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        var photo_item  : ImageView

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this
            name = itemView.findViewById(R.id.name) as TextView

            //  Commented out as the original design did not have the price on the MainActivity screen
            //
            //  price = itemView.findViewById(R.id.cost) as TextView

            photo_item = itemView.findViewById(R.id.photo_item) as ImageView
        }
    }
}