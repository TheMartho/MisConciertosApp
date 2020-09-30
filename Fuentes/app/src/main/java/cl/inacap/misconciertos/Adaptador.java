package cl.inacap.misconciertos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import cl.inacap.misconciertos.dto.Concierto;

public class Adaptador extends ArrayAdapter<Concierto> {

    private List<Concierto> conciertos;
    private Context contexto;
    private int elLayout;

    public Adaptador(@NonNull Context context, int resource, List<Concierto> objects) {
        super(context, resource, objects);
        this.conciertos=objects;
        this.contexto=context;
        this.elLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null)
            view= LayoutInflater.from(contexto).inflate(elLayout, null);


        Concierto concierto = conciertos.get(position);

        TextView verFecha = view.findViewById(R.id.verFechaTxt);
        String formatoDeFecha = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
        String fecha = sdf.format(concierto.getFechaEvento());
        verFecha.setText(fecha);

        TextView verArtista = view.findViewById(R.id.verArtistaTxt);
        verArtista.setText(concierto.getNombreArtista());

        TextView verEntrada = view.findViewById(R.id.verEntradaTxt);
        verEntrada.setText(concierto.getValorEntrada()+"");


        ImageView vercalificacionImg = view.findViewById(R.id.calificacionImg);
        int calificacion = concierto.getCalificacion();

        if(calificacion>=1 && calificacion<=3){
            vercalificacionImg.setImageResource(R.drawable.cara_triste);
        }
        if(calificacion==4 || calificacion==5){
            vercalificacionImg.setImageResource(R.drawable.cara_seria);
        }
        if(calificacion==6 || calificacion==7){
            vercalificacionImg.setImageResource(R.drawable.cara_feliz);
        }

        return view;
    }
}
