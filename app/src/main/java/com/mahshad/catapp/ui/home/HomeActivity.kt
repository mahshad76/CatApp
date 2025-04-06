package com.mahshad.catapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Breed
import com.google.android.material.navigation.NavigationView
import com.mahshad.catapp.R
import com.mahshad.catapp.data.network.RetrofitClient
import com.mahshad.catapp.ui.detail.DetailActivity
import com.mahshad.catapp.ui.viewpager.ViewPagerActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val TAG = "TagHomeActivity"
        const val KEY_ID = "id"
    }

    /// reference to the navigation drawer, tool bar, and navigation view
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    //search about bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //search about this
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        navigationView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_home)
        //search about this
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        setupRecyclerView()
        val call = RetrofitClient.apiService.getBreeds()
        Log.i(TAG, "beforeCall: ${System.currentTimeMillis()}")
        call.enqueue(object : Callback<List<Breed>> {
            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                if (response.isSuccessful) {
                    val breeds = response.body()
                    itemAdapter = ItemAdapter(
                        breeds = breeds ?: emptyList(),
                        listener = object : OnItemClickListener {
                            override fun onItemClick(position: Int, id: String?) {
                                navigateToDetail(id = id)
                            }
                        })
                    recyclerView.adapter = itemAdapter
                } else {
                    Log.e(TAG, "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
        Log.i(TAG, "afterCall: ${System.currentTimeMillis()}")
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun navigateToDetail(id: String?) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(KEY_ID, id)
        }
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_info) {
            val intent = Intent(this, ViewPagerActivity::class.java)
            startActivity(intent)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
