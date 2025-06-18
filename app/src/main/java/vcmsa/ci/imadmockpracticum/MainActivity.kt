package vcmsa.ci.imadmockpracticum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Declare Parallel Arrays
    val itemNames = ArrayList<String>()
    val categories = ArrayList<String>()
    val quantities = ArrayList<Int>()
    val comments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }// end of ViewCompat

        //Declaring GUI buttons
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnNavigate = findViewById<Button>(R.id.btnNavigate)
        val btnExit = findViewById<Button>(R.id.btnExit)

        //Call the button functions
        btnAdd.setOnClickListener {
            //Display addDialogBox
            val intent = Intent(this, SecondScreen::class.java)
            intent.putStringArrayListExtra("itemNames", itemNames)
            intent.putStringArrayListExtra("categories", categories)
            intent.putIntegerArrayListExtra("quantities", ArrayList(quantities))
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }
    }// end of onCreate
}//end of MainActivity