package food_ordering

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Helper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "User_registration"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "student_record"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = """
            CREATE TABLE $TABLE_NAME (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                Username TEXT,
                Email TEXT,
                Password TEXT
            )
        """.trimIndent()
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insert_record(username: String, email: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("Username", username)
            put("Email", email)
            put("Password", password)
        }

        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }

    fun check_email(email: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE Email=?",
            arrayOf(email)
        )
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun checkemailandpassword(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE Email=? AND Password=?",
            arrayOf(email, password)
        )
        val valid = cursor.count > 0
        cursor.close()
        db.close()
        return valid
    }

    // ✅ Fixed function — correct table and column names
//    fun getUsernameByEmail(email: String): String {
//        val db = this.readableDatabase
//        var username = ""
//        val cursor = db.rawQuery(
//            "SELECT Username FROM $TABLE_NAME WHERE Email=?",
//            arrayOf(email)
//        )
//        if (cursor.moveToFirst()) {
//            username = cursor.getString(0)
//        }
//        cursor.close()
//        db.close()
//        return username
//    }
    fun getUsernameByEmail(email: String): String {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT Username FROM student_record WHERE Email=?", arrayOf(email))
        var username = ""
        if (cursor.moveToFirst()) {
            username = cursor.getString(0)
        }
        cursor.close()
        db.close()
        return username
    }

}
