package mm.sergi.portfolio

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.*
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList


class MainActivity : AppCompatActivity(), CoinsAdapter.CoinsAdapterListener {

    override fun onContactSelected(coin: Coin?) {
        Toast.makeText(applicationContext, "Selected: " + coin!!.getName(), Toast.LENGTH_LONG).show()
    }

    private var recyclerView: RecyclerView? = null
    private var coinList: MutableList<Coin>? = null
    private var mAdapter: CoinsAdapter? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // toolbar fancy stuff
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Cryptos")

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView?
        coinList = ArrayList()
        mAdapter = CoinsAdapter(this, coinList, this)

        // white background notification bar
        whiteNotificationBar(this!!.recyclerView!!)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.setLayoutManager(mLayoutManager)
        recyclerView!!.setItemAnimator(DefaultItemAnimator())
        recyclerView!!.addItemDecoration(MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36))
        recyclerView!!.setAdapter(mAdapter)

        fetchCoins()
    }

     fun fetchCoins() {
         val client = OkHttpClient()
         val request: Request

         request = Request.Builder().url("https://api.coinmarketcap.com/v1/ticker/?convert=EUR&limit=0").get().build()

         client.newCall(request).enqueue(object : Callback {
             override fun onFailure(call: Call, e: IOException) {
                 e.printStackTrace()
             }

             @Throws(IOException::class)
             override fun onResponse(call: Call, response: Response) {
                 if (!response.isSuccessful()) {
                     throw IOException("Unexpected code " + response)
                 } else {
                     val response = response.body()!!.string()
                     try {
                         val json = JSONArray(response)

                         for (e in 0 until json.length()) {

                             val coinString:String = json.get(e).toString()
                             val gson = Gson()
                             val coin : Coin = gson.fromJson(coinString, Coin::class.java)
                             //coinList!!.add(coin)

                             Log.e(Integer.toString(e), coin.name)

                         }

                         val items = Gson().fromJson<List<Coin>>(json.toString(), object : TypeToken<List<Coin>>() {}.type)

                         // adding contacts to contacts list
                         coinList!!.clear()
                         coinList!!.addAll(items)

                         runOnUiThread {
                             //stuff that updates ui
                             // refreshing recycler view
                             mAdapter!!.notifyDataSetChanged()
                         }

                     } catch (e: JSONException) {
                         e.printStackTrace()
                     }

                 }
             }
         })
     }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
                .actionView as SearchView
        searchView!!.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        searchView!!.setMaxWidth(Integer.MAX_VALUE)

        // listening to search query text change
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                mAdapter!!.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                mAdapter!!.getFilter().filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        // close search view on back button pressed
        if (!searchView!!.isIconified()) {
            searchView!!.setIconified(true)
            return
        }
        super.onBackPressed()
    }

    private fun whiteNotificationBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.WHITE
        }
    }
}
