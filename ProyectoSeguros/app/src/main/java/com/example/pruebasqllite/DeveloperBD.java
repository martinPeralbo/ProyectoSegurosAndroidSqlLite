package com.example.pruebasqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DeveloperBD extends SQLiteOpenHelper {
    private  static  final  String NOMBRE_BD= "seguros";
    private static final int Version_BD=1;
    private  static  final  String TABLA_CLIENTES= "CREATE TABLE CLIENTES(DNI TEXT PRIMARY KEY, NOMBRE TEXT, MAIL TEXT,TELEFONO TEXT,ACTIVO INTEGER)";
    private static final String TABLA_SEGUROS = "CREATE TABLE SEGUROS(ID INTEGER PRIMARY KEY , NOMBRE TEXT, TIPOSEGURO TEXT,ACTIVO INTEGER)";
    private static final String TABLA_VENDEDORES= "CREATE TABLE VENDEDORES(ID_VENDEDOR INTEGER PRIMARY KEY, NOMBRE TEXT, PASSWORD TEXT, ACTIVO INTEGER, ADMIN INTEGER)";
    private static final String TABLA_CONTRATO="CREATE TABLE CONTRATO(ID_VENDEDOR INTEGER,ID INTEGER, DNI TEXT, FOREIGN KEY (ID) REFERENCES SEGUROS(ID),FOREIGN KEY(DNI) REFERENCES CLIENTES (DNI), FOREIGN KEY(ID_VENDEDOR) REFERENCES VENDEDORES(ID_VENDEDOR), PRIMARY KEY(ID_VENDEDOR,ID,DNI))";
   public DeveloperBD(Context context){
       super(context,NOMBRE_BD,null,Version_BD);
       //context.deleteDatabase("seguros");

   }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_CLIENTES);
        db.execSQL(TABLA_SEGUROS);
        db.execSQL(TABLA_VENDEDORES);
        db.execSQL(TABLA_CONTRATO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CLIENTES);
        db.execSQL(TABLA_CLIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_SEGUROS);
        db.execSQL(TABLA_SEGUROS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_VENDEDORES);
        db.execSQL(TABLA_VENDEDORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CONTRATO);
        db.execSQL(TABLA_CONTRATO);

    }
    //Cliente
    public boolean  agregarCliente(String dni, String nombre , String mail,String telefono,int activo  ){
       SQLiteDatabase bd = getWritableDatabase();
       //Si lo busca y no lo encuentra le permitimos insertar
        Cliente c = cargarCliente(dni);

       if(bd!= null){
           if(c == null ) {
               bd.execSQL("INSERT INTO CLIENTES VALUES ('" + dni + "','" + nombre + "','" + mail + "','" + telefono + "','" + activo + "')");
               return  true;
           }
       }
       return false;
    }
    //Los booleanos en sqllite no son soportados por eso utilizamos un int con valor 1 o 0 para señalar si esta activo en la base de datos
    public Cliente cargarCliente(String dni){

        Cliente c =null;
        SQLiteDatabase bd = getReadableDatabase();

        if(bd!= null){
            //Cargamos la tabla y buscamos
            Cursor cursor= bd.rawQuery("SELECT * FROM CLIENTES ",null);

            String dniSacado=null;

                if(cursor.moveToFirst()){
                    do{

                        Object[] objeto= {cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4)};

                        if(objeto[0].toString().equals(dni)) {
                            c= new Cliente(objeto[0].toString(),objeto[1].toString(),objeto[2].toString(),objeto[3].toString(),((Integer)objeto[4])>0);

                        }
                        dniSacado=objeto[0].toString();

                    }while (cursor.moveToNext() && c==null);
            }
        }
        return c;
    }


    public ArrayList<Cliente> obtenerClientes(){
        Cliente c =null;
        SQLiteDatabase bd = getReadableDatabase();
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        if(bd!= null){
            //Cargamos la tabla y buscamos
            Cursor cursor= bd.rawQuery("SELECT * FROM CLIENTES ",null);

            if (cursor.moveToFirst()){
                do {

                    String dni = cursor.getString(0);
                    String nombre = cursor.getString(1);
                    String mail = cursor.getString(2);
                    String mophone = cursor.getString(3);
                    int activo = cursor.getInt(4);

                    lista.add(new Cliente(dni,nombre,mail,mophone,activo>0));
                } while(cursor.moveToNext());
            }

        }
        return lista;
    }
    public boolean actualizarCliente(Cliente c){
        ContentValues data=new ContentValues();
        data.put("DNI",c.dni);
        data.put("NOMBRE",c.nombre);
        data.put("MAIL",c.mail);
        data.put("TELEFONO",c.mophone);
        data.put("ACTIVO",c.activo);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("CLIENTES", data, "DNI=?" , new String[]{c.dni.toString()});
        return true;
    }
    public boolean desactivarCliente(Cliente c){
        ContentValues data=new ContentValues();

        data.put("ACTIVO",0);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("CLIENTES", data, "DNI=?" , new String[]{c.dni.toString()});
        return true;
    }

    public boolean activarCliente(Cliente c){
        ContentValues data=new ContentValues();

        data.put("ACTIVO",1);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("CLIENTES", data, "DNI=?" , new String[]{c.dni.toString()});
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //Seguro
    public boolean  agregarSeguro(int id,String nombre , Seguro.TipoSeguro tipoSeguro,int activo  ){
        SQLiteDatabase bd = getWritableDatabase();
        //Si lo busca y no lo encuentra le permitimos insertar
        Seguro c = cargarSeguro(id);
        //falta poner variables
       // Log.v("abc2",Integer.getInteger(tipoSeguro.toString()));

        if(bd!= null){
            if(c == null ) {
                bd.execSQL("INSERT INTO SEGUROS VALUES ('" + id + "','" + nombre + "','" + tipoSeguro + "','" + activo + "')");
                return  true;
            }
        }
        return false;
    }
    //Los booleanos en sqllite no son soportados por eso utilizamos un int con valor 1 o 0 para señalar si esta activo en la base de datos
    public Seguro cargarSeguro(int id){

        Seguro c =null;
        SQLiteDatabase bd = getReadableDatabase();

        if(bd!= null){
            //Cargamos la tabla y buscamos
            Cursor cursor= bd.rawQuery("SELECT * FROM SEGUROS ",null);

            int idSacado=0;

            if(cursor.moveToFirst()){
                do{

                    Object[] objeto= {cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)};

                    if((int)objeto[0]==id) {
                        String valor=objeto[2].toString();
                        int posicion=0;
                        switch (valor){

                            case "VIVIENDA":
                                posicion=0;
                                break;
                            case "VIDA":
                                posicion=1;
                                break;
                            case "COCHE":
                                posicion=2;
                                break;
                        }

                        Seguro.TipoSeguro t = Seguro.TipoSeguro.values()[posicion];
                        c= new Seguro((int)objeto[0],objeto[1].toString(), t,((Integer)objeto[3])>0);

                    }
                    idSacado= (int) objeto[0];

                }while (cursor.moveToNext() && c==null);
            }
        }
        return c;
    }


    public ArrayList<Seguro> obtenerSeguros(){
        Seguro c =null;
        SQLiteDatabase bd = getReadableDatabase();
        ArrayList<Seguro> lista = new ArrayList<Seguro>();
        if(bd!= null){
            //Cargamos la tabla y buscamos
            Cursor cursor= bd.rawQuery("SELECT * FROM SEGUROS ",null);

            if (cursor.moveToFirst()){
                do {

                    int id = cursor.getInt(0);
                    String nombre = cursor.getString(1);

                        String valor=cursor.getString(2).toString();
                        int posicion=0;
                        switch (valor){

                            case "VIVIENDA":
                                posicion=0;
                                break;
                            case "VIDA":
                                posicion=1;
                                break;
                            case "COCHE":
                                posicion=2;
                                break;
                        }
                    Seguro.TipoSeguro t = Seguro.TipoSeguro.values()[posicion];
                    int activo = cursor.getInt(3);

                    lista.add(new Seguro(id,nombre,t,activo>0));
                } while(cursor.moveToNext());
            }

        }
        return lista;
    }


    public boolean actualizarSeguro(Seguro c){
        ContentValues data=new ContentValues();
        data.put("ID",c.id);
        data.put("NOMBRE",c.nombre);
        data.put("TIPOSEGURO",c.getTipoSeguro().toString());
        data.put("ACTIVO",c.activo);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("SEGUROS", data, "ID=?" , new String[]{String.valueOf(c.id)});
        return true;
    }
    public boolean desactivarSeguro(Seguro c){
        ContentValues data=new ContentValues();

        data.put("ACTIVO",0);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("SEGUROS", data, "ID=?" , new String[]{String.valueOf(c.id)});
        return true;
    }

    public boolean activarSeguro(Seguro c){
        ContentValues data=new ContentValues();

        data.put("ACTIVO",1);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("SEGUROS", data, "ID=?" , new String[]{String.valueOf(c.id)});
        return true;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Vendedor
    //METODOS PARA LOS VENDEDORES

    public boolean AgregarVendedor(int id_vendedor,String nombre,String pass ,int activo, int admin){

        SQLiteDatabase bd = getWritableDatabase();

        Vendedor v = CargarVendedor(id_vendedor);

        if(bd!= null){
            if(v == null ) {
                bd.execSQL("INSERT INTO VENDEDORES VALUES ('" + id_vendedor + "','" + nombre + "','" + pass + "','" + activo +"','"+ admin +"')");
                return  true;
            }

        }
        return false;

    }

    public Vendedor CargarVendedor(int id_vendedor){

        Vendedor v = null;
        SQLiteDatabase bd = getReadableDatabase();

        if(bd!= null){


            Cursor cs = bd.rawQuery("SELECT * FROM VENDEDORES", null);

            String id_vendedor_obtenido = null;

            if(cs.moveToFirst()){

                do{

                    Object[] objeto= {cs.getInt(0),cs.getString(1),cs.getString(2),cs.getInt(3),cs.getInt(4)};

                    if(objeto[0].toString().equals(String.valueOf(id_vendedor))) {
                        v = new Vendedor((int)objeto[0],objeto[1].toString(),objeto[2].toString(),((Integer)objeto[3])>0, ((Integer)objeto[4])>0);
                    }
                    id_vendedor_obtenido=objeto[0].toString();

                }while (cs.moveToNext() && v==null);


            }

        }
        return v;
    }

    public ArrayList<Vendedor> obtenerVendedores(){

        Vendedor v = null;
        SQLiteDatabase bd = getReadableDatabase();
        ArrayList<Vendedor> lista = new ArrayList<Vendedor>();

        if(bd!= null){
            //Cargamos la tabla y buscamos
            Cursor cs= bd.rawQuery("SELECT * FROM VENDEDORES",null);

            if (cs.moveToFirst()){
                do {

                    int id_vendedor = cs.getInt(0);
                    String nombre = cs.getString(1);
                    String pass = cs.getString(2);
                    int activo = cs.getInt(3);
                    int admin = cs.getInt(4);

                    lista.add(new Vendedor(id_vendedor,nombre,pass,activo>0,admin>0));
                } while(cs.moveToNext());
            }

        }
        return lista;
    }


    public boolean actualizarVendedor(Vendedor v){
        ContentValues data=new ContentValues();
        data.put("ID_VENDEDOR",v.id);
        data.put("NOMBRE",v.nombre);
        data.put("PASSWORD",v.pass);
        data.put("ACTIVO",v.activo);
        data.put("ADMIN",v.admin);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("VENDEDORES", data, "ID_VENDEDOR="+v.id,null );
        return true;
    }

    public boolean desactivarVendedor(Vendedor v){
        ContentValues data=new ContentValues();

        data.put("ACTIVO",0);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("VENDEDORES", data, "ID_VENDEDOR="+v.id,null );
        return true;
    }

    public boolean activarVendedor(Vendedor v){
        ContentValues data=new ContentValues();

        data.put("ACTIVO",1);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("VENDEDORES", data, "ID_VENDEDOR="+v.id,null );
        return true;
    }

    public boolean desactivarAdmin(Vendedor v){
        ContentValues data=new ContentValues();

        data.put("ADMIN",0);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("VENDEDORES", data, "ID_VENDEDOR="+v.id,null );
        return true;
    }

    public boolean activarAdmin(Vendedor v){
        ContentValues data=new ContentValues();

        data.put("ADMIN",1);

        SQLiteDatabase bd = getWritableDatabase();
        bd.update("VENDEDORES", data, "ID_VENDEDOR="+v.id,null );
        return true;
    }


//METODOS PARA CONTRATO

    public boolean AgregarContrato(int id_vendedor,int id, String DNI){

        SQLiteDatabase bd = getWritableDatabase();

        Contrato c = CargarContrato(id_vendedor,id,DNI);

        if(bd!= null){
            if(c == null ) {
                bd.execSQL("INSERT INTO CONTRATO VALUES ('" + id_vendedor + "','" + id + "','" + DNI + "')");
                return  true;
            }

        }
        return false;

    }

    public Contrato CargarContrato(int id_vendedor, int id, String DNI){

        Contrato c = null;
        SQLiteDatabase bd = getReadableDatabase();

        if(bd!= null){


            Cursor cs = bd.rawQuery("SELECT * FROM CONTRATO", null);

            String id_vendedor_obtenido = null;

            if(cs.moveToFirst()){

                do{

                    Object[] objeto= {cs.getInt(0),cs.getInt(1),cs.getString(2)};

                    if(objeto[0].toString().equals(String.valueOf(id_vendedor)) && objeto[1].toString().equals(String.valueOf(id)) && objeto[2].toString().equals(DNI)) {
                        c = new Contrato((int)objeto[0],(int)objeto[1],objeto[2].toString());
                    }
                    id_vendedor_obtenido=objeto[0].toString();

                }while (cs.moveToNext() && c==null);


            }

        }
        return c;
    }

    public ArrayList<Contrato> obtenerContratos(){

        Contrato c = null;
        SQLiteDatabase bd = getReadableDatabase();
        ArrayList<Contrato> lista = new ArrayList<Contrato>();

        if(bd!= null){
            //Cargamos la tabla y buscamos
            Cursor cs= bd.rawQuery("SELECT * FROM CONTRATO",null);

            if (cs.moveToFirst()){
                do {

                    int id_vendedor = cs.getInt(0);
                    int id = cs.getInt(1);
                    String dni = cs.getString(2);


                    lista.add(new Contrato(id_vendedor,id,dni));
                } while(cs.moveToNext());
            }

        }
        return lista;
    }


    public boolean actualizarContrato(Contrato c){
        ContentValues data=new ContentValues();
        data.put("ID_VENDEDOR",c.id_vendedor);
        data.put("ID",c.id);
        data.put("DNI",c.dni);


        SQLiteDatabase bd = getWritableDatabase();
        bd.update("CONTRATO", data, "ID_VENDEDOR="+c.id_vendedor +" && " + "ID"+c.id + " && " +"DNI"+c.dni,null );
        return true;
    }


}
