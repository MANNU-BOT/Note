package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.view.*
import java.text.SimpleDateFormat
import java.util.*


class
MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    private val mNotes: List<note> =
    private val checkedIntArray: ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.notes.R.layout.activity_main)

        Realm.init(this)
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()


        load()

        addBtn.setOnClickListener {
            save()
            sheetOpen()
        }

        deleteSelectedButton.setOnClickListener {
            delete()
        }

        recycler()

        if(condition){
            deleteSelectedButton.setVisibility(View.INVISIBLE);
        }

    }

    fun save(){

    }
    fun load(){}
    fun delete(){

    }
    fun recycler(){}

    private fun sheetOpen() {
        val bottomSheetDialog =
            BottomSheetDialog(this@MainActivity, R.style.BottomSheetDialogTheme)
        val bottomSheetView: View = LayoutInflater.from(applicationContext).inflate(
            R.layout.layout_bottom_sheet,
            findViewById<LinearLayout>(R.id.bottomSheetContainer)
        )
        bottomSheetView.doBtn.setOnClickListener {
            val addEditText: EditText = bottomSheetView.findViewById(R.id.addTextView)
            val noteText = addEditText.text.toString().replace("  ".toRegex(), "")
            val date: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())


            if(noteText.isNotEmpty()){
                mNotes.add(mNotes.size, note(noteText, date, false))
                mAdapter.notifyItemInserted(mNotes.size)
                bottomSheetDialog.dismiss();
            }
            else{
                Toast.makeText(this, "Nothing To Add", Toast.LENGTH_SHORT).show();
            }

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        }

    }
}