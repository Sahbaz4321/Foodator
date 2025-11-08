package food_ordering

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myproject.R

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // 🔹 Get username & email from SharedPreferences
        val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        val email = sharedPref.getString("email", "user@example.com")

        // 🔹 Find TextViews and set values
        val nameText = findViewById<TextView>(R.id.textView)
        val emailText = findViewById<TextView>(R.id.textView2)

        nameText.text = username
        emailText.text = email

        val btn=findViewById<Button>(R.id.button6)
        btn.setOnClickListener {
            val intent= Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}
