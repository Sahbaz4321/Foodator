//package food_ordering
//
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.WindowManager
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.myproject.R
//
//class SignUp : AppCompatActivity() {
//
//    private lateinit var username: EditText
//    private lateinit var email: EditText
//    private lateinit var password: EditText
//    private lateinit var login: TextView
//    private lateinit var signup: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.signup)
//        supportActionBar?.hide()
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
//
//        username = findViewById(R.id.username)
//        email = findViewById(R.id.email)
//        password = findViewById(R.id.password)
//        signup = findViewById(R.id.button3)
//        login = findViewById(R.id.login)
//
//        val helper = Helper(this)
//
//        // 🔹 Go to Login Page
//        login.setOnClickListener {
//            val intent = Intent(this@SignUp, Login::class.java)
//            startActivity(intent)
//        }
//
//        // 🔹 Sign Up logic
//        signup.setOnClickListener {
//            val uname = username.text.toString()
//            val uemail = email.text.toString()
//            val upassword = password.text.toString()
//
//            if (uname.isEmpty() || uemail.isEmpty() || upassword.isEmpty()) {
//                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
//            } else {
//                val isEmailExists = helper.check_email(uemail)
//                if (!isEmailExists) {
//                    val insertSuccess = helper.insert_record(uname, uemail, upassword)
//                    if (insertSuccess) {
//                        Toast.makeText(this, "Record Saved Successfully", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this@SignUp, Home::class.java)
//                        startActivity(intent)
//                    } else {
//                        Toast.makeText(this, "Failed to Save Record", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Toast.makeText(this, "Please choose another email", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//}


package food_ordering

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.R

class SignUp : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var login: TextView
    private lateinit var signup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signup = findViewById(R.id.button3)
        login = findViewById(R.id.login)

        val helper = Helper(this)

        // 🔹 Go to Login Page
        login.setOnClickListener {
            val intent = Intent(this@SignUp, Login::class.java)
            startActivity(intent)
        }

        // 🔹 Sign Up logic
        signup.setOnClickListener {
            val uname = username.text.toString()
            val uemail = email.text.toString()
            val upassword = password.text.toString()

            if (uname.isEmpty() || uemail.isEmpty() || upassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val isEmailExists = helper.check_email(uemail)
                if (!isEmailExists) {
                    val insertSuccess = helper.insert_record(uname, uemail, upassword)
                    if (insertSuccess) {
                        Toast.makeText(this, "Record Saved Successfully", Toast.LENGTH_SHORT).show()

                        // ✅ Save username in SharedPreferences
                        val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("username", uname)
                        editor.putString("email", uemail)

                        editor.apply()

                        // ✅ Go to Home Screen
                        val intent = Intent(this@SignUp, Home::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to Save Record", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Please choose another email", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

