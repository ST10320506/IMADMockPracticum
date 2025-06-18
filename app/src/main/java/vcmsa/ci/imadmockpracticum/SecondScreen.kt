package vcmsa.ci.imadmockpracticum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }// end of ViewCompat

        //Declaring GUI variables for Second Screen
        val tvListDisplay = findViewById<TextView>(R.id.tvListDisplay)
        val btnDisplayList = findViewById<Button>(R.id.btnDisplayList)
        val btnFilterList = findViewById<Button>(R.id.btnFilterList)
        val btnReturn = findViewById<Button>(R.id.btnReturn)

        val itemName = intent.getStringArrayListExtra("Item") ?: arrayListOf()
        val categories = intent.getStringArrayListExtra("Category") ?: arrayListOf()
        val quantities = intent.getIntegerArrayListExtra("Quantity") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("Comments") ?: arrayListOf()

        fun displayAll() {
            val output = StringBuilder()
            //Table headers with fixed-width columns
            output.append(String.format("%-5s%-5s%-5s%-5s%-20s\n", "No.", "Item", "Category", "Quantity", "Comment"))
            output.append("---------------------------------\n")

            for (i in itemName.indices) {
                output.append(
                    String.format(
                        "%-5s%-15s%-15s%-10s%-20s\n",
                        i + 1,
                        itemName[i],
                        categories[i],
                        quantities[i],
                        comments[i]
                    )
                )
            }
            tvListDisplay.text = output.toString()
        }

        fun displayFiltered() {
            val output = StringBuilder()
            for (i in itemName.indices) {
                if (quantities[i] >= 2) {
                    output.append("${itemName[i]} (quantity: ${quantities[i]})\n")
                }
            }
            tvListDisplay.text = output.toString()
        }

        btnDisplayList.setOnClickListener {
            displayAll()
        }

        btnFilterList.setOnClickListener {
            displayFiltered()
        }

        //Button to return to the Main Activity Screen
        btnReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }// end of onCreate
}//end of SecondScreen
