package id.co.matriks.kalkulatormatriks

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_matriks_b.*

class MatriksBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matriks_b)

        addMinOrdo()
    }

    fun addMinOrdo(){
        val matriksA = intent.getIntArrayExtra("matriksA")
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMin = findViewById<Button>(R.id.btnMin)
        var indexMatriks = 11
        var rowCount = intent.getIntExtra("rowCount",1)
        val matriksLayout = findViewById<TableLayout>(R.id.matriksLayout)
        val rowParams: TableRow.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.
                WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)
        val layoutParams: TableLayout.LayoutParams = TableLayout.LayoutParams(TableLayout.
                LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)

        var tempIndex:Int

        btnOrdo.text = rowCount.toString()+"x"+rowCount.toString()

        if(rowCount!=1){
            for(i in 1..rowCount){

                val matriksRow = TableRow(this)
                matriksRow.id = i

                for(j in 1..rowCount){

                    val btnMatriks = EditText(this)
                    btnMatriks.id = (i.toString()+j.toString()).toInt()
                    btnMatriks.setText("0")
                    matriksRow.addView(btnMatriks,rowParams)
                }

                matriksLayout.addView(matriksRow,layoutParams)
            }

        }else{
            val matriksRow = TableRow(this)
            val btnMatriks = EditText(this)
            btnMatriks.id = indexMatriks
            matriksRow.id = (1).toInt()
            btnMatriks.layoutParams = rowParams
            btnMatriks.setText("0")
            matriksRow.addView(btnMatriks)
            matriksLayout.addView(matriksRow,layoutParams)
        }

        btnPlus.setOnClickListener {
            if(rowCount!=10){
                for(i in 1..rowCount){
                    val indexI = (i).toString()
                    val indexJ = (rowCount+1).toString()
                    tempIndex = (indexI+indexJ).toInt()
                    val settedRow = findViewById<TableRow>(i)
                    val settedBtn = EditText(this)
                    settedBtn.id = tempIndex
                    settedBtn.setText("0")
                    settedRow.addView(settedBtn,rowParams)
                }
                rowCount+=1
                val newRow = TableRow(this)
                newRow.id = rowCount
                Log.i("rowcount",newRow.id.toString())
                for(i in 1..rowCount){
                    val newBtn = EditText(this)
                    val indexI = (rowCount).toString()
                    val indexJ = (i).toString()
                    tempIndex = (indexI+indexJ).toInt()
                    newBtn.id = tempIndex
                    newBtn.setText("0")
                    newRow.addView(newBtn,rowParams)
                }
                matriksLayout.addView(newRow,layoutParams)
                btnOrdo.text = rowCount.toString()+"x"+rowCount.toString()
                setSharedPreference("simpanA",false)
            }
        }

        btnMin.setOnClickListener {
            if(rowCount!=1){
                Log.i("rowcount",rowCount.toString())
                matriksLayout.removeView(findViewById(rowCount))
                rowCount-=1
                for(i in 1..rowCount){
                    val deletedRow = findViewById<TableRow>(i)
                    val indexI = (i).toString()
                    val indexJ = (rowCount+1).toString()
                    val deletedIndex = (indexI+indexJ).toInt()
                    deletedRow.removeView(findViewById(deletedIndex))
                }
                btnOrdo.text = rowCount.toString()+"x"+rowCount.toString()
                setSharedPreference("simpanA",false)
            }

        }

        btnSimpan.setOnClickListener {

            val matriksB = IntArray(((rowCount+1).toString()+(rowCount+1).toString()).toInt())

            for (i in 1..rowCount){
                for(j in 1..rowCount){
                    val indexI = i.toString()
                    val indexJ = j.toString()
                    val index = (indexI+indexJ).toInt()
                    val isiMatriks = findViewById<EditText>(index)
                    matriksB[index] = isiMatriks.text.toString().toInt()
                    Log.i("Isi matriks",matriksB[index].toString())
                }
            }
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("rowCount",rowCount)
            setSharedPreference("simpanB",true)
            intent.putExtra("matriksB",matriksB)
            intent.putExtra("matriksA",matriksA)
            startActivity(intent)
            finish()
        }
        btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun setSharedPreference(name: String,simpan:Boolean){
        val editor = getSharedPreferences("simpan",Context.MODE_PRIVATE).edit()
        editor.putBoolean(name,simpan)
        editor.apply()
    }

    fun getShared(name:String):Boolean{
        val editor = getSharedPreferences("simpan",Context.MODE_PRIVATE)
        return editor.getBoolean(name,false)
    }
}

