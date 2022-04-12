package com.example.recyclerview_newsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.sharememes.MySingleton

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter:NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView:RecyclerView=findViewById(R.id.recyclerView)

        recyclerView.layoutManager=LinearLayoutManager(this)

        //adapter binding to recycler view
        fetchData()
        mAdapter=NewsListAdapter(this)
        recyclerView.adapter=mAdapter

    }

    private fun fetchData(){
//        val list= ArrayList<String>()
//        for(i in 0 until 100){
//            list.add("item $i")
//        }
//        return list
    var url=
//        "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=275a7bb5f97e45ffa0c8432bdfffa345"
"https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val newsJsonArray=response.getJSONArray("articles")
                val newsArray=ArrayList<News>()
                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject=newsJsonArray.getJSONObject(i)
                    Log.d("response",response.getString("status"))
                    val news=News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            { error ->
                // TODO: Handle error
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onItemClicked(item: News) {
//        Toast.makeText(this,"clicked on item $item",Toast.LENGTH_SHORT).show()

//        String url = ¨https://paul.kinlan.me/¨;
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//        CustomTabsIntent customTabsIntent = builder.build();
//        customTabsIntent.launchUrl(this, Uri.parse(url));

        val builder=CustomTabsIntent.Builder()
        val customTabsIntent=builder.build()
        customTabsIntent.launchUrl(this, Uri.parse((item.url)))



    }
}