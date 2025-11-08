package food_ordering

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.R

class splash1: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.splash)

        val btn =findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            val intent= Intent(this, Home::class.java)
            startActivity(intent)

        }

    }
}