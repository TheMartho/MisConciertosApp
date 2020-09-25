package cl.inacap.misconciertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText calendarioTxt;
    private Calendar calendario = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calendarioTxt = findViewById(R.id.calendarioTxt);
        calendarioTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };



    private void actualizarInput() {
        String formatoDeFecha = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        calendarioTxt.setText(sdf.format(calendario.getTime()));
    }

}