package myfirstandroidproject.es.covalco.recycle_view_example;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.*;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.ArrayList;

public class CrudActivity extends Activity implements View.OnClickListener,
                                        AdapterView.OnItemSelectedListener {

    //Declaramos los elementos de la interfaz
    private Button btnCrear;
    private Button btnVer;
    private Button btnEliminar;
    private EditText editNombre;
    private EditText editComentario;
    private EditText txtNombre;
    private EditText txtComentario;

    //Declaración del spinner y su Adapter
    private Spinner spinComentarios;
    private ArrayAdapter spinnerAdapter;

    // Lista de comentarios y comentario actual
    private ArrayList<Comentario> lista;
    private Comentario comentario;

    //Controlador de bases de datos
    private MyOpenHelper db;

    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // Inicializamos los elementos de la Interface
        editNombre =(EditText) findViewById(R.id.editNombre);
        editComentario =(EditText) findViewById(R.id.editComentario);
        txtNombre =(EditText) findViewById(R.id.txtNombre);
        txtComentario =(EditText) findViewById(R.id.txtComentario);

        //Los elementos del panel inferior no seran editables
        txtNombre.setEnabled(false);
        txtComentario.setEnabled(false);

        btnCrear = (Button)findViewById(R.id.btnCrear);
        btnVer   = (Button)findViewById(R.id.btnVer);
        btnEliminar   = (Button)findViewById(R.id.btnEliminar);

        btnCrear.setOnClickListener(this);
        btnVer.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

        //Iniciamos el controlador de la base de datos
        db= new MyOpenHelper(this);

        //Iniciamos el spinner y la lista de comentarios
        spinComentarios= (Spinner) findViewById(R.id.spinComentarios);
        lista= db.getComments();

        //Creamos el adapter y lo asociamos al spinner
        spinnerAdapter= new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, lista);
        spinComentarios.setAdapter(spinnerAdapter);
        spinComentarios.setOnItemSelectedListener(this);

        //añadimos la validación al formulario
        addValidationToView();

    }

    @Override
    public void onClick(View view) {
    //Acciones de cada botón
        switch (view.getId()) {
            case R.id.btnCrear:
                if (awesomeValidation.validate()){
                    //Insertamos un nuevo elemento en base de datos
                    db.insertar(editNombre.getText().toString(), editComentario.getText().toString());
                    //actualizamos la lista de comentarios
                    lista= db.getComments();
                    //actualizamos el adapter y lo asociamos de nuevo al spinner
                    spinnerAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista);
                    spinComentarios.setAdapter(spinnerAdapter);

                    //mostrem el missatge
                    Toast.makeText(this, "Lo has hecho genial", Toast.LENGTH_SHORT).show();

                    //limpiamos el formulario
                    editNombre.setText("");
                    editComentario.setText("");
                }
                break;
            case R.id.btnVer:
                awesomeValidation.clear();
                //si hay algun comentario selcccionado mostramos sus valores en la parte inferior
                if(comentario!=null){
                    txtNombre.setText(comentario.getNombre());
                    txtComentario.setText(comentario.getComentario());
                }
                break;

            case R.id.btnEliminar:
                awesomeValidation.clear();
                //si hay algun comentario selecionado lo borramos de la base de datos y actualizamos el Spinner
                if(comentario!=null){
                    db.borrar(comentario.getId());
                    lista= db.getComments();
                    spinnerAdapter= new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, lista);
                    spinComentarios.setAdapter(spinnerAdapter);
                    //limpiamos los datos del panel inferior
                    txtNombre.setText("");
                    txtComentario.setText("");
                    //Eliminamos el comentario actual puesto que ya no existe en la base de datos
                    comentario= null;
                }
                break;
        }

    }

    private void addValidationToView() {
        awesomeValidation.addValidation(this, R.id.editNombre, RegexTemplate.NOT_EMPTY, R.string.invalid_nombre);
        awesomeValidation.addValidation(this, R.id.editComentario, RegexTemplate.NOT_EMPTY, R.string.invalid_comentario);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() ==R.id.spinComentarios){
            //Si hay elementos en la base de datos, establecemos el comentario actual a partir del indice del elemsnto selccionado en el spinner
            if(lista.size()>0){
                comentario = lista.get(position);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
