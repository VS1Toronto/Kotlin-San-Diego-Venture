package com.core.data_03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.core.data_03.models.Product
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        //----------------------------------------------------------------------------------------------------------------------------------------------------------------
        //  Find Image that user clicked and use Glide to load that
        //  image into the imageView assosiated with this
        //  DetailActivity in the activity_detail.xml
        //
        val imageView = findViewById(R.id.imageView_2) as ImageView
        val photo = intent.getSerializableExtra(PHOTO) as Product?


        photo?.image.let {
            Glide.with(this).load("https://leedavidsoncontentmanagementsystem1.com/core_data/3_San_Diego_Venture_Core/images/" + photo?.image)
                .into(imageView)
        }


        //---------------------------------------------------------------------------------------------------------------------------------------------------------
        //  PRODUCT NAME
        //
        //  With the correct photo being sent from the MainAdapter getPhoto() method
        //  using adapterPosition this is taken advantage of and the textViews in turn
        //  have their values assigned from the photo object here
        //
        photo?.itemName.let {
            val textView: TextView = findViewById(R.id.productName) as TextView
            textView.text = photo?.itemName
        }
        //---------------------------------------------------------------------------------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------------------------------------------------------------------------------
        //  DESCRIPTION
        //
        //  With the correct photo being sent from the MainAdapter getPhoto() method
        //  using adapterPosition this is taken advantage of and the textViews in turn
        //  have their values assigned from the photo object here
        //
        photo?.description.let {
            val textView: TextView = findViewById(R.id.productDescription) as TextView
            textView.text = photo?.description
        }
        //---------------------------------------------------------------------------------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------------------------------------------------------------------------------
        //  DESCRIPTION
        //
        //  With the correct photo being sent from the MainAdapter getPhoto() method
        //  using adapterPosition this is taken advantage of and the textViews in turn
        //  have their values assigned from the photo object here
        //
        photo?.address.let {
            val textView: TextView = findViewById(R.id.productAddress) as TextView
            textView.text = photo?.address
        }
        //---------------------------------------------------------------------------------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------------------------------------------------------------------------------
        //  PHONE
        //
        //  With the correct photo being sent from the MainAdapter getPhoto() method
        //  using adapterPosition this is taken advantage of and the textViews in turn
        //  have their values assigned from the photo object here
        //
        photo?.phone.let {
            val textView: TextView = findViewById(R.id.productPhone) as TextView
            textView.text = photo?.phone
        }
        //---------------------------------------------------------------------------------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------------------------------------------------------------------------------
        //  WEBSITE
        //
        //  With the correct photo being sent from the MainAdapter getPhoto() method
        //  using adapterPosition this is taken advantage of and the textViews in turn
        //  have their values assigned from the photo object here
        //
        photo?.website.let {
            val textView: TextView = findViewById(R.id.productWebsite) as TextView
            textView.text = photo?.website
        }
        //---------------------------------------------------------------------------------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------------------------------------------------------------------------------
        //  PRICE
        //
        //  With the correct photo being sent from the MainAdapter getPhoto() method
        //  using adapterPosition this is taken advantage of and the textViews in turn
        //  have their values assigned from the photo object here
        //
        photo?.price.let {
            val textView: TextView = findViewById(R.id.productPrice) as TextView
            textView.text = photo?.price
        }
        //---------------------------------------------------------------------------------------------------------------------------------------------------------


        imageView.setOnClickListener {
            finish()
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    companion object {
        val PHOTO ="PHOTO"  //  This is used as a key to pass in photos
    }

}

