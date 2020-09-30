package cl.inacap.misconciertos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.inacap.misconciertos.dao.ConciertosDAO;
import cl.inacap.misconciertos.dto.Concierto;

public class MainActivity extends AppCompatActivity {
    private ConciertosDAO cDAO = new ConciertosDAO();
    private ListView listaConciertos;
    private EditText valorEntradaTxt;
    private  EditText artistaTxt;
    private Button registrarBtn;
    private Calendar calendario = Calendar.getInstance();
    private EditText calendarioTxt;
    private Spinner spinnerGenero;
    private Spinner spinnerCalificacion;
    private List <Concierto> conciertos;
    Adaptador miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.valorEntradaTxt = findViewById(R.id.valorEntradaTxt);
        this.artistaTxt = findViewById(R.id.artistaTxt);
        this.registrarBtn = findViewById(R.id.registrarBtn);


        listaConciertos = findViewById(R.id.conciertosLv);


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




        //ACCION DEL BOTON REGISTRAR
        this.registrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String generoStr;
                String calificacionStr;
                String artistaStr = artistaTxt.getText().toString().trim();
                int valorEntrada = 0;
                List<String> errores = new ArrayList<>();

                if (artistaStr.isEmpty()){
                    errores.add("Debe Ingresar El Nombre Del Artista");
                }

                try {
                    valorEntrada=Integer.parseInt(valorEntradaTxt.getText().toString().trim());
                    if (valorEntrada<0){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                errores.add("Debe Ingresar Un Valor Mayor que 0");
                }

                calificacionStr=spinnerCalificacion.getSelectedItem().toString();
                generoStr=spinnerGenero.getSelectedItem().toString();



                if(errores.isEmpty()){
                    Concierto concierto = new Concierto();
                    concierto.setNombreArtista(artistaStr);
                    concierto.setValorEntrada(valorEntrada);
                    concierto.setCalificacion(Integer.parseInt(calificacionStr));
                    concierto.setFechaEvento(calendario.getTime());
                    concierto.setGeneroMusical(generoStr);
                    cDAO.add(concierto);
                    conciertos = cDAO.getAll();
                    miAdaptador = new Adaptador(MainActivity.this,R.layout.list_item,conciertos);
                    listaConciertos.setAdapter(miAdaptador);
                    miAdaptador.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this
                            ,"El Concierto Se A Registrado Exitosamente"
                            ,Toast.LENGTH_SHORT).show();


                }else{
                    mostrarErrores(errores);
                }


            }

        });



    }



    private void mostrarErrores(List<String> errores){
        //1. Generar una cadena de texto con los errores
        String mensaje="";
        for(String e:errores){
            mensaje+= "-" + e + "\n";
        }
        //2. Mostrar un mensaje de alerta
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder
                .setTitle("Error De Validacion")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",null)
                .create()
                .show();
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