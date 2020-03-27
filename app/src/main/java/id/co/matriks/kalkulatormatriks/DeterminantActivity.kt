package id.co.matriks.kalkulatormatriks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_determinant.*
import org.ejml.simple.SimpleMatrix

class DeterminantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_determinant)
        val indexMatriks = 11
        val matriksA = intent.getIntArrayExtra("matriksA")
        val rowCount = intent.getIntExtra("rowCount",1)
        val matriksB = intent.getIntArrayExtra("matriksB")
        val jenisDet = intent.getIntExtra("determinan",0)
        val resultLayout = findViewById<TableLayout>(R.id.resultLayout)
        val rowParams: TableRow.LayoutParams = TableRow.LayoutParams(200,200)
        val layoutParams: TableLayout.LayoutParams = TableLayout.LayoutParams(TableLayout.
                LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)
        val btn = Button(this)

        btn.id = indexMatriks
        if(jenisDet == 1){
            btn.text = determinan(matriksA,rowCount).toInt().toString()
            btnA.setOnClickListener {
                resultLayout.removeAllViews()
                showMatriks(rowCount,matriksA,layoutParams)
            }
        }else{
            btn.text = determinan(matriksB,rowCount).toInt().toString()
            btnA.setOnClickListener {
                resultLayout.removeAllViews()
                showMatriks(rowCount,matriksB,layoutParams)
            }
        }

        val rowDet = TableRow(this)
        rowDet.id = "1".toInt()
        rowDet.addView(btn,rowParams)
        resultLayout.addView(rowDet,layoutParams)

        btnOperasi.setOnClickListener {
            resultLayout.removeAllViews()
            val btnNew = Button(this)
            btnNew.id = indexMatriks
            if(jenisDet == 1){
                btnNew.text = determinan(matriksA,rowCount).toInt().toString()
            }else{
                btnNew.text = determinan(matriksB,rowCount).toInt().toString()
            }
            val rowDetNew = TableRow(this)
            rowDetNew.id = "1".toInt()
            rowDetNew.addView(btnNew,rowParams)
            resultLayout.addView(rowDetNew,layoutParams)
        }
    }

    fun showMatriks(rowCount: Int,matriks: IntArray,layoutParams:TableLayout.LayoutParams){
        val rowParams: TableRow.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.
                WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT)
        for(i in 1..rowCount){

            val resultRow = TableRow(this)
            resultRow.id = i

            for(j in 1..rowCount){

                val txtResult = EditText(this)

                txtResult.id = (i.toString()+j.toString()).toInt()
                val plus = matriks[(i.toString()+j.toString()).toInt()].toString()
                txtResult.setText(plus)

                resultRow.addView(txtResult,rowParams)
            }

            resultLayout.addView(resultRow,layoutParams)
        }
    }

    fun determinan(matriks:IntArray,rowCount:Int):Double{
        val hasilDet:Double
        if(rowCount == 1){
            hasilDet = matriks[11].toDouble()
        }else if(rowCount == 2){
            hasilDet = ((matriks[11]*matriks[22])-(matriks[21]*matriks[12])).toDouble()
        }else if(rowCount ==3){
            hasilDet = ((matriks[11]*matriks[22]*matriks[33])+(matriks[12]*matriks[23]*matriks[31])+
                    (matriks[13]*matriks[21]*matriks[32])-(matriks[13]*matriks[22]*matriks[31])-
                    (matriks[11]*matriks[23]*matriks[32])-(matriks[12]*matriks[21]*matriks[33])).
                    toDouble()
        }else{
            val matriksEJML = DoubleArray(rowCount*rowCount)
            val mat2d = Array(rowCount){IntArray(rowCount)}
            val temp = ArrayList<Int>()
            for(i in 0 until rowCount){
                for(j in 0 until rowCount){
                    val indeks:String = (i+1).toString()+(j+1).toString()
                    mat2d[i][j] = matriks[indeks.toInt()]
                    temp.add(mat2d[i][j])
                }
            }

            for(i in 0 until rowCount*rowCount){
                matriksEJML[i] = temp[i].toDouble()
            }

            val A = SimpleMatrix(rowCount,rowCount,true, matriksEJML)
            hasilDet = A.determinant()
        }

        return hasilDet
    }
}
