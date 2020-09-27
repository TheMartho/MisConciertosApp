package cl.inacap.misconciertos.dto;

import java.util.Date;

public class Concierto {
    private String nombreArtista;
    private Date fechaEvento;
    private String generoMusical;
    private int valorEntrada;
    private int calificacion;

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Fecha: "+fechaEvento+"\n"
                +"Nombre Del Artista: "+ nombreArtista+ "\n"
                +"Valor De Entrada: "+valorEntrada+"\n"
                +"Icono De Calificación: ";
    }
}
