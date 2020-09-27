package cl.inacap.misconciertos.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.misconciertos.dto.Concierto;

public class ConciertoDAO {

    private List<Concierto> conciertos = new ArrayList<>();

    public void add(Concierto c) {
        conciertos.add(c);
    }

    public List<Concierto> getAll() {
        return conciertos;
    }

}
