package com.cloudtec.promedio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double primeraNota;
    private double segundaNota;
    private double terceraNota;

    EditText txtPrimeraNota;
    EditText txtSegundaNota;
    EditText txtTerceraNota;

    TextView lblResultado;
    TextView lblMessage;

    Button btnCalcular;
    Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPrimeraNota = findViewById(R.id.txt_primera_nota);
        txtSegundaNota = findViewById(R.id.txt_segunda_nota);
        txtTerceraNota = findViewById(R.id.txt_tercera_nota);

        lblResultado = findViewById(R.id.lbl_resultado);
        lblMessage = findViewById(R.id.lbl_message);

        btnCalcular = findViewById(R.id.btn_calcular);
        btnLimpiar = findViewById(R.id.btn_limpiar);

        txtTerceraNota.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    esconderTeclado(txtTerceraNota);
                    calcularPromedio();
                }
                return false;
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                esconderTeclado(view);
                calcularPromedio();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarCampos();
            }
        });
    }

    private void esconderTeclado(View view) {
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void calcularPromedio() {
        lblResultado.setText("0");
        lblMessage.setText("");

        if (txtPrimeraNota.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese la primera nota", Toast.LENGTH_SHORT).show();
        } else if (txtSegundaNota.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese la segunda nota", Toast.LENGTH_SHORT).show();
        } else if (txtTerceraNota.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese la tercera nota", Toast.LENGTH_SHORT).show();
        } else {
            primeraNota = Double.parseDouble(txtPrimeraNota.getText().toString());
            segundaNota = Double.parseDouble(txtSegundaNota.getText().toString());
            terceraNota = Double.parseDouble(txtTerceraNota.getText().toString());

            double resultado = (primeraNota + segundaNota + terceraNota) / 3;
            resultado = Math.round(resultado);
            lblResultado.setText(String.valueOf(resultado));

            if (resultado > 17) {
                lblMessage.setText("Felicitaciones! Obtuviste una nota excelente");
            } else if (resultado > 13) {
                lblMessage.setText("Aprobaste! Puedes seguir mejorando");
            } else {
                lblMessage.setText("Esfuerzate más. No pudiste aprobar");
            }
        }

    }

    private void limpiarCampos() {
        txtPrimeraNota.setText("");
        txtSegundaNota.setText("");
        txtTerceraNota.setText("");
        lblMessage.setText("");
        lblResultado.setText("0");
    }
}
