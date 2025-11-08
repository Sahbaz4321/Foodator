package food_ordering


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.R

import androidx.recyclerview.widget.RecyclerView

class Home : AppCompatActivity() {
    private lateinit var categoryRecycler: RecyclerView
    private lateinit var popularRecycler: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        categoryRecycler = findViewById(R.id.categoryRecycler)
        popularRecycler = findViewById(R.id.popularRecycler)

        setupCategoryRecycler()
        setupPopularRecycler()
        val btn = findViewById<LinearLayout>(R.id.profile)
        btn.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")

        val hiText = findViewById<TextView>(R.id.hitext)
        hiText.text = "Hi $username"


    }

    private fun setupCategoryRecycler() {
        val categoryList = listOf(
            Category("Pizza", R.drawable.pizza1),
            Category("Burger", R.drawable.burger),
            Category("Hotdog", R.drawable.burger_large),
            Category("Drink", R.drawable.cat_1)
        )
        categoryRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryRecycler.adapter = CategoryAdapter(categoryList)
    }

    private fun setupPopularRecycler() {
        val popularList = listOf(
            PopularItem("Pepperoni Pizza", "$9.76", R.drawable.pizza1),
            PopularItem("Cheese Burger", "$8.79", R.drawable.cat_3),
            PopularItem("Vegetable Pizza", "$8.59", R.drawable.cat_4)
        )
        popularRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popularRecycler.adapter = PopularAdapter(popularList)
    }
}
