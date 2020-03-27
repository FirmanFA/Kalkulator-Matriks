package id.co.matriks.kalkulatormatriks;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class DetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det);

        int indeksMatriks = 11;
        int[] matriksA = getIntent().getIntArrayExtra("matriksA");
        int[][] matriksTemp;
        int rowCount = getIntent().getIntExtra("rowCount",1);

        Log.i("rowcount",Integer.toString(rowCount));
        TableLayout resultLayout = findViewById(R.id.resultLayout);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(200,200);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);


        matriksTemp = new int[rowCount+1][rowCount+1];
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<rowCount;j++){
                int indeks = Integer.parseInt((Integer.toString(i+1))+(Integer.toString(j+1)));
                 matriksTemp[i][j] = matriksA[indeks];
                Log.i("isitemp",Integer.toString(matriksTemp[i][j]));
                Log.i("isiA",Integer.toString(matriksA[indeks]));
            }
        }

        Button btn = new Button(this);
        btn.setId(indeksMatriks);
        String determinant = Integer.toString(determinan(matriksTemp,3));
        btn.setText(determinant);
        Log.i("det",determinant);
        TableRow tableRow = new TableRow(this);
        tableRow.setId(Integer.parseInt("1"));
        tableRow.addView(btn,rowParams);
        resultLayout.addView(tableRow,layoutParams);

    }

    int determinan(int[][] matriks,int rowCount){
        int[][] newMatriks = new int[rowCount-1][];
        int det = 0;

        for(int skipColumn=0;skipColumn<rowCount;skipColumn++){
            for(int k=0;k<(3-1);k++){
                newMatriks[k] = new int[rowCount-1];
            }

            for(int i=1;i<rowCount;i++){
                int skipRow = 0;

                for(int j=0;j<rowCount;j++){
                    if(j==skipColumn)
                        continue;
                    newMatriks[i-1][skipRow] = matriks[i][j];
                    skipRow++;
                }
            }
            int neg1PowerOfSkipPlus1;
            if(skipColumn%2==0){
                neg1PowerOfSkipPlus1 = 1;
            }else{
                neg1PowerOfSkipPlus1 = -1;
            }

            det += neg1PowerOfSkipPlus1 * matriks[0][skipColumn] * determinan(newMatriks,rowCount-1);

        }

        return det;
    }

}
