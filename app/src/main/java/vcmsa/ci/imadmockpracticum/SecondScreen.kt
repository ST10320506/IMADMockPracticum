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

        //Declare GUI variables
        val tvListDisplay = findViewById<TextView>(R.id.tvListDisplay)
        val btnDisplayList = findViewById<Button>(R.id.btnDisplayList)
        val btnFilterList = findViewById<Button>(R.id.btnFilterList)
        val btnReturn = findViewById<Button>(R.id.btnReturn)

        //Parallel Array passed through Intent (from MainActivity)
        val itemNames = intent.getStringArrayListExtra("itemNames") ?: arrayListOf()
        val categories = intent.getStringArrayListExtra("categories") ?: arrayListOf()
        val quantities = intent.getIntegerArrayListExtra("quantities") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        //displays array on screen **
        fun displayList() {
            val output = StringBuilder()
            //Table Headings
            output.append(
                String.format(
                    "\n",
                    "Number ID",
                    "Item Name",
                    "Category",
                    "Quantity",
                    "Comment"
                )
            )
            output.append("---------------------------------/n")
            for (i in itemNames.indices) {
                output.append(
                    String.format(
                        "\n",
                        i + 1,
                        itemNames[i],
                        categories[i],
                        quantities[i],
                        comments[i]
                    )
                )
            }

            //Shows list
            tvListDisplay.text = output.toString()

            fun btnFilterList() {
                val output = StringBuilder()
                for (i in itemNames.indices) {
                    if (quantities[i] >= 2) {
                        output.append("${itemNames[i]} (Quantity: ${quantities[i]})\n")
                    }
                }
            }

            //Button to display list
            btnDisplayList.setOnClickListener {
                tvListDisplay.text = output.toString()
            }

            //Button to return to Main Activity Screen
            btnReturn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }// end of onCreate
    }//end of SecondScreen
}