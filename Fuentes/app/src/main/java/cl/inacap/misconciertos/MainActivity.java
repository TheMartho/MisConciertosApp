package cl.inacap.misconciertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cl.inacap.misconciertos.dto.Concierto;

public class MainActivity extends AppCompatActivity {


    private EditText valorEntradaTxt;
    private  EditText artistaTxt;
    private Button registrarBtn;
    private Calendar calendario = Calendar.getInstance();
    private EditText calendarioTxt;
    private Spinner spinnerGenero;
    private Spinner spinnerCalificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GENERA EL CONTENIDO DEL SPINNER DE GENEROS
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);
        String [] generos = {"Rock","Jazz","Pop","Reguetoon","Salsa","Metal"};
        spinnerGenero.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,generos));

        //GENERA EL CONTENIDO DEL SPINNER DE CALIFICACION
        spinnerCalificacion = (Spinner) findViewById(R.id.spinnerCalificacion);
        String [] valores ={"1", "2", "3", "4", "5", "6", "7"};
        spinnerCalificacion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));

        //GENERA EL CALENDARIO PARA PODER ELEGIR UNA FECHA
        calendarioTxt = findViewById(R.id.calendarioTxt);
        calendarioTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        this.valorEntradaTxt = findViewById(R.id.valorEntradaTxt);
        this.artistaTxt = findViewById(R.id.artistaTxt);
        this.registrarBtn = findViewById(R.id.registrarBtn);

        this.registrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:OBTENER DATOS, CREAR OBJETO Y GUARDAR EN DAO
                //TODO:GUARDAR FECHA EN FORMATO ESPECIFICADO
                String generoStr;
                String calificacionStr;
                calificacionStr=spinnerCalificacion.getSelectedItem().toString();
                generoStr=spinnerGenero.getSelectedItem().toString();

                Concierto concierto = new Concierto();
                concierto.setNombreArtista("RELLENAR");
                concierto.setValorEntrada(2);
                concierto.setCalificacion(Integer.parseInt(calificacionStr));
                concierto.setFechaEvento(calendario.getTime());
                concierto.setGeneroMusical(generoStr);
                Toast.makeText(MainActivity.this
                        ,"Fecha: "+concierto.getFechaEvento() + "  Genero: "+ concierto.getGeneroMusical()
                        ,Toast.LENGTH_SHORT).show();
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