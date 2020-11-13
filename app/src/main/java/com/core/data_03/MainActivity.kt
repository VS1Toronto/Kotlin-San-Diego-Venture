package com.core.data_03

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.core.data_03.api.ProductRetreiver
import com.core.data_03.models.Product
import com.core.data_03.models.ProductList


import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val facebookURL     =   "https://en-gb.facebook.com/"
    private val twitterURL      =   "https://twitter.com/login?lang=en"
    private val yahooNewsURL    =   "https://uk.news.yahoo.com/"
    private val googleURL       =   "https://www.google.co.uk/?gfe_rd=cr&ei=JlbKVp3yHPDS8AfU8KeYCg&gws_rd=ssl"
    private val googleMapsURL   =   "https://www.google.co.uk/maps"



    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //  EMAIL
    //
    //  This is to demonstrate sending email from the FAB button
    //
    private val email = "SanDiegoVentureApp@hotmail.com"
    //-------------------------------------------------------------------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //  ANIMATION
    //
    //  STEP 1
    //
    //  These variables are used to run the animation on the Main Activity
    //
    private var view : ImageView? = null
    private val frameAnimation : AnimationDrawable? = null
    //-------------------------------------------------------------------------------------------------------------------------------------------------


    //  These variables are assosiated with the Recycler View in activity_main.xml
    //
    var photos : List<Product>? = null
    var mainAdapter : MainAdapter? = null
    lateinit var recyclerView_1 : RecyclerView


    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //  ON CREATE
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        //---------------------------------------------------------------------------------------------------------------------------------------------
        //  EMAIL
        //
        //  Send an email to     SanDiegoHomesApp@hotmail.com     from the floating access button
        //
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {

            val addresses = arrayOf<String>(email)
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, addresses)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Information request")
            intent.putExtra(Intent.EXTRA_TEXT, "Please send some information!")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        //---------------------------------------------------------------------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------------------------------------------------------------------
        //  SETTINGS PREFERENCES STEP 1
        //
        val settings = PreferenceManager.getDefaultSharedPreferences(this)

        //  This is checking if the checkbox in the SETTINGS option on the toolbar has been checked
        //
        val grid = settings.getBoolean(getString(R.string.pref_display_grid), false)
        //---------------------------------------------------------------------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------------------------------------------------------------------
        //  Use Rest API to get photos List and send them to RecyclerView_1 im xml file assosiated with WomensWearActivity

        //  Setup Recycler View
        //
        recyclerView_1 = findViewById(R.id.recyclerView_1) as RecyclerView
        recyclerView_1.layoutManager =
            LinearLayoutManager(this)
        //---------------------------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------------------------------
        //  SETTING MAIN ACTIVITY IMAGE
        //
        //  STEP 1
        //
        //  This is the call to set the background image in the Main Activity
        //
        mainActivityImage();
        //---------------------------------------------------------------------------------------------------------------------------------------------


        //---------------------------------------------------------------------------------------------------------------------------------------------
        //  SETTINGS PREFERENCES STEP 2
        //
        if (grid) {
            recyclerView_1.setLayoutManager(
                GridLayoutManager(
                    this,
                    3
                )
            )
        }
        //---------------------------------------------------------------------------------------------------------------------------------------------

        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //  Create Instance of our REST API Class
        //
        var retriever = ProductRetreiver()    //  Variable that can make our API call
        val callback = object : Callback<ProductList> {      //  This callback is needed by the Retreiver Class

            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                response?.isSuccessful.let {
                    this@MainActivity.photos = response?.body()?.ventures  //  The @ symbol is how you reference your outer Activity in Kotlin - THIS "Products" is the name of the array on the JSON RESOURCE WEB FEED
                    mainAdapter = MainAdapter(this@MainActivity.photos!!,
                        this@MainActivity)
                    recyclerView_1.adapter = mainAdapter
                }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Log.e("MainActivity", "Problems calling API", t)
            }

        }

        retriever.getProducts(callback)
        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    }
    //  END ON CREATE
    //-------------------------------------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //  SETTING MAIN ACTIVITY IMAGE
    //
    //  STEP 2
    //
    private fun mainActivityImage() {

        //  Type casting the Image View
        //
        view = findViewById<View>(R.id.imageView2) as ImageView

        //  Setting Main Activity Image as the background of the image view
        //
        view!!.setBackgroundResource(R.drawable.image1)
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------


    //  Create new Intent to start the DetailActivity passing
    //  the Photo object via the MainAdapters PhotoHolder method
    //
    override fun onClick(view: View?) {
        val intent = Intent(this, DetailActivity::class.java)
        val holder = view?.tag as MainAdapter.PhotoHolder
        intent.putExtra(
            DetailActivity.PHOTO,
            mainAdapter?.getPhoto(holder.adapterPosition)

        )
        startActivity(intent)
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //---------------------------------------------------------------------------------------------------------------------------------------
    //  2   USER CLICKING THE ACTION BAR MENU SIGN IN CAUSES AN INTENT TO START SigninActivity
    //
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {


            //  SETTINGS OPTION FOR PREFERENCES
            //
            //  1   USER CHOOSES MENU CHOICE on Toolbar named SETTINGS while on MAINACTIVITY
            //  2   THAT LOADS PREFSACTIVITY
            //  3   PREFSACTIVITY LOADS SETTINGS FRAGMENT
            //  4   SETTINGS FRAGMENT LOADS SETTINGS
            //
            R.id.action_settings -> {
                val settingsIntent = Intent(this, PrefsActivity::class.java)
                startActivity(settingsIntent)
                return true
            }

            R.id.action_facebook -> {
                val webLinkIntent1 = Intent(Intent.ACTION_VIEW, Uri.parse(facebookURL));
                if (webLinkIntent1.resolveActivity(getPackageManager()) != null) {
                    startActivity(webLinkIntent1);
                }
                return true;
            }

            R.id.action_twitter -> {
                val webLinkIntent2 = Intent(Intent.ACTION_VIEW, Uri.parse(twitterURL));
                if (webLinkIntent2.resolveActivity(getPackageManager()) != null) {
                    startActivity(webLinkIntent2);
                }
                return true;
            }
            R.id.action_yahooNews -> {
                val webLinkIntent3 = Intent(Intent.ACTION_VIEW, Uri.parse(yahooNewsURL));
                if (webLinkIntent3.resolveActivity(getPackageManager()) != null) {
                    startActivity(webLinkIntent3);
                }
                return true;
            }
            R.id.action_google -> {
                val webLinkIntent4 = Intent(Intent.ACTION_VIEW, Uri.parse(googleURL));
                if (webLinkIntent4.resolveActivity(getPackageManager()) != null) {
                    startActivity(webLinkIntent4);
                }
                return true;
            }

            R.id.action_googleMaps -> {
                val webLinkIntent5 = Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsURL));
                if (webLinkIntent5.resolveActivity(getPackageManager()) != null) {
                    startActivity(webLinkIntent5);
                }
                return true;
            }

        }
        return super.onOptionsItemSelected(item)
    }
    //---------------------------------------------------------------------------------------------------------------------------------------


}