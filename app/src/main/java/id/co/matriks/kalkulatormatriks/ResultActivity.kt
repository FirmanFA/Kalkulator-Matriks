package id.co.matriks.kalkulatormatriks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val rowCount = intent.getIntExtra("rowCount",1)
        val matriksA = intent.getIntArrayExtra("matriksA")
        val matriksB = intent.getIntArrayExtra("matriksB")
        val resultLayout = findViewById<TableLayout>(R.id.resultLayout)
        val rowParams: TableRow.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.
                WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)
        val layoutParams: TableLayout.LayoutParams = TableLayout.LayoutParams(TableLayout.
                LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)
        val operasi = intent.getIntExtra("operasi",0)

        for(i in 1..rowCount){

            val resultRow = TableRow(this)
            resultRow.id = i

            for(j in 1..rowCount){

                val txtResult = EditText(this)
                txtResult.id = (i.toString()+j.toString()).toInt()
                var jumlah = 0
                when(operasi){
                    1 -> jumlah = (matriksA[(i.toString()+j.toString()).toInt()])+(matriksB[(
                            i.toString()+j.toString()).toInt()])
                    2 -> jumlah = (matriksA[(i.toString()+j.toString()).toInt()])-(matriksB[(
                            i.toString()+j.toString()).toInt()])
                    3 -> for(k in 1..rowCount){
                        jumlah += matriksA[(i.toString() + k.toString()).toInt()] * matriksB[(
                                k.toString() + j.toString()).toInt()]
                        }
                    4 -> for(k in 1..rowCount){
                        jumlah += matriksB[(i.toString() + k.toString()).toInt()] * matriksA[(
                                k.toString() + j.toString()).toInt()]
                        }
                }

                txtResult.setText(jumlah.toString())
                resultRow.addView(txtResult,rowParams)
            }

            resultLayout.addView(resultRow,layoutParams)
        }

        btnOperasi.text = "Result"

        btnA.setOnClickListener {
            resultLayout.removeAllViews()
            for(i in 1..rowCount){
                val resultRow = TableRow(this)
                resultRow.id = i
                for(j in 1..rowCount){
                    val txtResult = EditText(this)
                    txtResult.id = (i.toString()+j.toString()).toInt()
                    val plus = matriksA[(i.toString()+j.toString()).toInt()].toString()
                    txtResult.setText(plus)
                    resultRow.addView(txtResult,rowParams)
                }
                resultLayout.addView(resultRow,layoutParams)
            }
        }
        btnB.setOnClickListener {
            resultLayout.removeAllViews()
            for(i in 1..rowCount){
                val resultRow = TableRow(this)
                resultRow.id = i
                for(j in 1..rowCount){
                    val txtResult = EditText(this)
                    txtResult.id = (i.toString()+j.toString()).toInt()
                    val plus = matriksB[(i.toString()+j.toString()).toInt()].toString()
                    txtResult.setText(plus)
                    resultRow.addView(txtResult,rowParams)
                }
                resultLayout.addView(resultRow,layoutParams)
            }
        }

        btnOperasi.setOnClickListener {
            resultLayout.removeAllViews()
            for(i in 1..rowCount){

                val resultRow = TableRow(this)
                resultRow.id = i

                for(j in 1..rowCount){

                    val txtResult = EditText(this)
                    txtResult.id = (i.toString()+j.toString()).toInt()
                    var jumlah = 0
                    when(operasi){
                        1 -> jumlah = (matriksA[(i.toString()+j.toString()).toInt()])+(matriksB[(
                                i.toString()+j.toString()).toInt()])
                        2 -> jumlah = (matriksA[(i.toString()+j.toString()).toInt()])-(matriksB[(
                                i.toString()+j.toString()).toInt()])
                        3 -> for(k in 1..rowCount){
                                jumlah += matriksA[(i.toString() + k.toString()).toInt()] *
                                        matriksB[(k.toString() + j.toString()).toInt()]
                            }

                        4 -> for(k in 1..rowCount){
                                jumlah += matriksB[(i.toString() + k.toString()).toInt()] *
                                        matriksA[(k.toString() + j.toString()).toInt()]
                            }
                    }
                    txtResult.setText(jumlah.toString())
                    resultRow.addView(txtResult,rowParams)
                }
                resultLayout.addView(resultRow,layoutParams)
            }
        }
    }
}
