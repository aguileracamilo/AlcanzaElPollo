package com.alcanzaelpollo.controlador;


import java.sql.*;
import java.util.function.DoubleBinaryOperator;

public class Conexion {
    Connection con;
    private final String DB="bdalgoritmos";
    private final String URL="jdbc:mysql://db4free.net:3306/dbalgoritmoss?";
    private final String USER="bdalgoritmos";
    private final String PASS="analisisdealgoritmos";

    public Conexion(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(URL,USER,PASS);
        }catch (Exception e){
            System.err.println("error: "+e);
        }
    }
    public  void guardarUsuario(String codigo,int pregunta1,int pregunta2,int pregunta3,int p[]){
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs;
        System.out.println(codigo);
        try {
            st = cn.con.createStatement();
            st.executeUpdate("insert into usuario(id) values (" + Integer.parseInt(codigo) + ");");
            st.executeUpdate("insert into encuesta(idUsuario,pregunta1,pregunta2,pregunta3) values ('"+Integer. parseInt(codigo)+"','"+pregunta1+"','"+pregunta2+"','"+pregunta3+"');");
            st.executeUpdate("insert into respuestas(idUsuario,Nivel1,Nivel2,Nivel3,Nivel4,Nivel5) values ('"+Integer. parseInt(codigo)+"','"+p[0]+"','"+p[1]+"','"+p[2]+"','"+p[3]+"','"+p[4]+"');");
            cn.con.close();
        }
        catch (Exception e){

        }
    }
    public ResultSet cargarInfo(){
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs = null;
        try {
            st = cn.con.createStatement();
            rs = st.executeQuery("select avg(r.nivel1) as nivel1,avg(r.nivel2) as nivel2,avg(r.nivel3) as nivel3,avg(r.nivel4) as nivel4,avg(r.nivel5) as nivel5,sum(nivel1) as suma1, sum(nivel2) as suma2,sum(nivel3) as suma3,sum(nivel4) as suma4,sum(nivel5) as suma5, count(nivel1) as contador from respuestas r ;");

        }
        catch (Exception e){

        }
        return rs;
    }
    public ResultSet cargarEncuestas(){
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs = null;
        try {
            st = cn.con.createStatement();
            rs = st.executeQuery("select avg(pregunta1) as pregunta1,avg(pregunta2) as pregunta2,avg(pregunta3) as pregunta3 from encuesta e ;");

        }
        catch (Exception e){

        }
        return rs;
    }
    public ResultSet listado(){
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs = null;
        try {
            st = cn.con.createStatement();
            rs = st.executeQuery("select (r.nivel1+r.nivel2+r.nivel3+r.nivel4+r.nivel5) as puntuacion, r.idUsuario as idUsuario from respuestas r ORDER by puntuacion DESC  ;");

        }
        catch (Exception e){

        }
        return rs;
    }
    public Double coeficiente(int pregunta,int nivel) throws SQLException {

       ResultSet nivelR= getNivel(nivel);
       ResultSet preguntaR= getPregunta(pregunta);

       Double coeficiente=0.0;
        Double numerador=0.0;
        Double denominador1=0.0;
        Double denominador2=0.0;
        nivelR.next();
        preguntaR.next();
        int preguntaV ;
        int nivelV;
        Double promedioPregunta = Double.parseDouble(preguntaR.getString("promedio")); ;
        Double promedioNivel=Double.parseDouble(nivelR.getString("promedio")) ;
        do {
            preguntaV= Integer.parseInt(preguntaR.getString("pregunta"));
            nivelV=Integer.parseInt(nivelR.getString("puntuacion"));
            System.out.println(promedioPregunta);
            System.out.println(promedioNivel);
            System.out.println(preguntaV);
            System.out.println(nivelV);

             numerador += (preguntaV-promedioPregunta)*(nivelV-promedioNivel);
            denominador1 += Math.pow((preguntaV-promedioPregunta), 2);
            denominador2 +=  Math.pow((nivelV-promedioNivel),2);
            preguntaR.next();
        } while(nivelR.next());

        coeficiente=numerador/(Math.sqrt((denominador1*denominador2)));
        return coeficiente;
    }




    public ResultSet getNivel(int nivel) throws SQLException {
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs = null;

        try {
            st = cn.con.createStatement();
            switch (nivel){

                case 1:rs = st.executeQuery("select r.nivel1 as puntuacion, (select avg(nivel1) from respuestas  ) as promedio from respuestas r;");
                    break;
                case 2:rs = st.executeQuery("select r.nivel2 as puntuacion, (select avg(nivel2) from respuestas  ) as promedio from respuestas r ;");
                    break;
                case 3:rs = st.executeQuery("select r.nivel3 as puntuacion, (select avg(nivel3) from respuestas  ) as promedio from respuestas r ;");
                    break;
                case 4:rs = st.executeQuery("select r.nivel4 as puntuacion, (select avg(nivel4) from respuestas  ) as promedio from respuestas r ;");
                    break;
                case 5:rs = st.executeQuery("select r.nivel5 as puntuacion,(select avg(nivel5) from respuestas  ) as promedio from respuestas r ;");
                    break;


            }
        }
        catch (Exception e){

        }

        return rs;
    }

    public ResultSet getPregunta(int pregunta){
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs = null;

        try {
            st = cn.con.createStatement();


            switch (pregunta){

                case 1:rs = st.executeQuery("select e.pregunta1 as pregunta,(select avg(pregunta1) from encuesta  ) as promedio from encuesta e ;");
                    break;
                case 2:rs = st.executeQuery("select e.pregunta2 as pregunta,(select avg(pregunta2) from encuesta  ) as promedio from encuesta e ;");
                    break;
                case 3:rs = st.executeQuery("select e.pregunta3 as pregunta,(select avg(pregunta3) from encuesta  ) as promedio from encuesta e ;");
                    break;
            }



        }
        catch (Exception e){

        }
        return rs;
    }
}