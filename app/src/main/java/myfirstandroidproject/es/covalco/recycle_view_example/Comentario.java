package myfirstandroidproject.es.covalco.recycle_view_example;

public class Comentario {
    int id;
    String nombre;
    String comentario;

    public String getNombre() {
        return nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public Comentario(int id, String nombre, String comentario) {
        this.id = id;
        this.nombre = nombre;
        this.comentario = comentario;
    }



    public int getId() {
        return id;
    }
}
