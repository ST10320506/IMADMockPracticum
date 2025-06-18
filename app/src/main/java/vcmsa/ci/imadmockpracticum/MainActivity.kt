package vcmsa.ci.imadmockpracticum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Declare Parallel Arrays
    val itemName = ArrayList<String>()
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
        //Exit button exits app
        btnExit.setOnClickListener {
            finish()
        }
        //Navigate button navigates to the second screen
        btnNavigate.setOnClickListener {
            //Intent is created to allow user to navigate to the second screen
            val intent = Intent(this, SecondScreen::class.java)
            intent.putStringArrayListExtra("Item", itemName)
            intent.putStringArrayListExtra("Category", categories)
            intent.putIntegerArrayListExtra("Quantity", ArrayList(quantities))
            intent.putStringArrayListExtra("Comments", comments)
            startActivity(intent)
        }

        //**
        btnAdd.setOnClickListener {
            //Display addDialogBox
            showAddDialog()
        }

    }// end of onCreate

    private fun showAddDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Item Details")
        val layout = layoutInflater.inflate(R.layout.dialog_layout, null)
        builder.setView(layout)

        val inputItem = layout.findViewById<android.widget.EditText>(R.id.edtItemName)
        val inputCategory = layout.findViewById<android.widget.EditText>(R.id.edtCategory)
        val inputQuantity = layout.findViewById<android.widget.EditText>(R.id.edtQuantity)
        val inputComments = layout.findViewById<android.widget.EditText>(R.id.edtComments)

        builder.setPositiveButton("Add") { _, _ ->
            val item = inputItem.text.toString()
            val category = inputCategory.text.toString()
            val quantity = inputQuantity.text.toString().toIntOrNull()
            val comment = inputComments.text.toString()

            if (item.isEmpty() || category.isEmpty() || quantity == null) {
                Toast.makeText(this, "Please enter details in all fields.", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            itemName.add(item)
            categories.add(category)
            quantities.add(quantity)
            comments.add(comment)
            Toast.makeText(this, "Item added successfully!", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancel", null)
        builder.show()
    }// end of showAddDialog private function
}//end of MainActivity