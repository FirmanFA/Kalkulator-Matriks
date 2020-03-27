package id.co.matriks.kalkulatormatriks

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rowCount = intent.getIntExtra("rowCount",1)
        val simpanB = getShared("simpanB")
        val simpanA = getShared("simpanA")
        var matriksB = IntArray(((rowCount+1).toString()+(rowCount+1).toString()).toInt())
        var matriksA = IntArray(((rowCount+1).toString()+(rowCount+1).toString()).toInt())
        btnMatriksA.setOnClickListener {
            val intent = Intent(this,MatriksAActivity::class.java)
            intent.putExtra("rowCount",rowCount)
            intent.putExtra("matriksB",matriksB)
            startActivity(intent)
            finish()
        }
        btnMatriksB.setOnClickListener {
            val intent = Intent(this,MatriksBActivity::class.java)
            intent.putExtra("rowCount",rowCount)
            intent.putExtra("matriksA",matriksA)
            startActivity(intent)
            finish()
        }

        if(simpanA){
            matriksA = intent.getIntArrayExtra("matriksA")
        }
        if(simpanB){
            matriksB = intent.getIntArrayExtra("matriksB")
        }

        val simpanC = getShared("simpanC")
        if(!simpanC){
            showDialog()
        }

        btnAPlusB.setOnClickListener {operasi(simpanA,simpanB,1,matriksA,matriksB)}

        btnAMinB.setOnClickListener{operasi(simpanA,simpanB,2,matriksA,matriksB)}

        btnATimesB.setOnClickListener {operasi(simpanA,simpanB,3,matriksA,matriksB)}

        btnBTimesA.setOnClickListener { operasi(simpanA,simpanB,4,matriksA,matriksB)}

        btnDetA.setOnClickListener { determinan(simpanA,1,matriksA,matriksB)}

        btnDetB.setOnClickListener { determinan(simpanB,2,matriksA,matriksB)}

    }

    fun operasi(simpanA: Boolean,simpanB:Boolean,jenisOperasi:Int,matriksA:IntArray,matriksB:IntArray){
        val rowCount = intent.getIntExtra("rowCount",1)
        if(!simpanA){
            val toast = Toast.makeText(this,"Masukan Matriks A Terlebih Dahulu",Toast.LENGTH_SHORT)
            toast.show()
        }else if(!simpanB){
            val toast = Toast.makeText(this,"Masukan Matriks B Terlebih Dahulu",Toast.LENGTH_SHORT)
            toast.show()
        }else{
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("operasi",jenisOperasi)
            intent.putExtra("rowCount",rowCount)
            intent.putExtra("matriksA",matriksA)
            intent.putExtra("matriksB",matriksB)
            startActivity(intent)
        }
    }

    fun determinan(simpan:Boolean, matriks:Int,matriksA:IntArray,matriksB:IntArray){
        val rowCount = intent.getIntExtra("rowCount",1)
        if(!simpan){
            val toast = Toast.makeText(this,"Masukan Matriks Terlebih Dahulu",Toast.LENGTH_SHORT)
            toast.show()
        }else{
            val intent = Intent(this,DeterminantActivity::class.java)
            intent.putExtra("determinan",matriks)
            intent.putExtra("rowCount",rowCount)
            intent.putExtra("matriksA",matriksA)
            intent.putExtra("matriksB",matriksB)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setSharedPreference("simpanA",false)
        setSharedPreference("simpanB",false)
        finish()
    }

    fun setSharedPreference(name: String,simpan:Boolean){
        val editor = getSharedPreferences("simpan", Context.MODE_PRIVATE).edit()
        editor.putBoolean(name,simpan)
        editor.apply()
    }

    fun getShared(name:String):Boolean{
        val editor = getSharedPreferences("simpan", Context.MODE_PRIVATE)
        return editor.getBoolean(name,false)
    }

    fun showDialog(){
        setSharedPreference("simpanC",true)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pemberitahuan Awal")
        builder.setMessage("Mohon maaf, dikarenakan masih adanya beberapa bug, jika pada saat " +
                "menggunakan aplikasi ini mengalami force close, bisa dengan menghapus data aplikasi " +
                "maka akan kembali seperti normal. Atau bisa dengan membuka aplikasi secara terus " +
                "sampai muncul dialog untuk membersihkan memori(tidak semua hp memiliki fungsi tsb)")
                .setCancelable(false)
                .setPositiveButton("SAYA MENGERTI") { dialog, _ ->
                    dialog.cancel()
                }
        val dialog = builder.create()
        dialog.show()
    }

}

