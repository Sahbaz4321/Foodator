package food_ordering


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.R
import kotlin.jvm.java

class Login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signup: TextView
    private lateinit var signin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signin = findViewById(R.id.button3)
        signup = findViewById(R.id.signup)

        val helper = Helper(this)

        signup.setOnClickListener {
            val intent = Intent(this@Login, SignUp::class.java)
            startActivity(intent)
        }

        signin.setOnClickListener {
            val uemail = email.text.toString()
            val upassword = password.text.toString()

            if (uemail.isEmpty() || upassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val validate = helper.checkemailandpassword(uemail, upassword)
//                if (validate) {
//                    Toast.makeText(this@Login, "Login Successfully", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this@Login, Home::class.java)
//                    startActivity(intent)
//                }
//
                if (validate) {
                    val username = helper.getUsernameByEmail(uemail) // helper se username fetch karega

                    // ✅ Save username in SharedPreferences
                    val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("username", username)
                    editor.putString("email", uemail)

                    editor.apply()

                    Toast.makeText(this@Login, "Login Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Login, Home::class.java)
                    startActivity(intent)
                    finish()
                }

                else {
                    Toast.makeText(this@Login, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
