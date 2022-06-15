package com.alcanzaelpollo.controlador;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ControladorReportes {

    private ObservableList<Calificacion> personData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Calificacion, String> columnaIdentificacion;

    @FXML
    private TableColumn<Calificacion, String> columnaPuntaje;

    @FXML
    private ComboBox<Integer> boxEncuestas;

    @FXML
    private ComboBox<Integer> boxRetos;

    @FXML
    private Label correctasReto1;

    @FXML
    private Label correctasReto2;

    @FXML
    private Label correctasReto3;

    @FXML
    private Label correctasReto4;

    @FXML
    private Label correctasReto5;

    @FXML
    private Label incorrectasReto1;

    @FXML
    private Label incorrectasReto2;

    @FXML
    private Label incorrectasReto3;

    @FXML
    private Label incorrectasReto4;

    @FXML
    private Label incorrectasReto5;

    @FXML
    private Label promedioEncuesta1;

    @FXML
    private Label promedioEncuesta2;

    @FXML
    private Label promedioEncuesta3;

    @FXML
    private Label promedioReto1;

    @FXML
    private Label promedioReto2;

    @FXML
    private Label promedioReto3;

    @FXML
    private Label promedioReto4;

    @FXML
    private Label promedioReto5;

    @FXML
    private Label resultadoCoeficiente;

    @FXML
    private TableView<Calificacion> tablaPuntajes;

    public void initialize() throws SQLException {
        Conexion c=new Conexion();
        ResultSet info=c.cargarInfo();

        if(info!=null){
            info.next();

            promedioReto1.setText( info.getString("nivel1"));
            promedioReto2.setText( info.getString("nivel2"));
            promedioReto3.setText( info.getString("nivel3"));
            promedioReto4.setText( info.getString("nivel4"));
            promedioReto5.setText( info.getString("nivel5"));

            correctasReto1.setText( info.getString("suma1"));
            correctasReto2.setText( info.getString("suma2"));
            correctasReto3.setText( info.getString("suma3"));
            correctasReto4.setText( info.getString("suma4"));
            correctasReto5.setText( info.getString("suma5"));

            int total=Integer.parseInt(info.getString("contador"));
            incorrectasReto1.setText( ""+(total-Integer.parseInt(info.getString("suma1"))));
            incorrectasReto2.setText( ""+(total-Integer.parseInt(info.getString("suma2"))));
            incorrectasReto3.setText( ""+(total-Integer.parseInt(info.getString("suma3"))));
            incorrectasReto4.setText( ""+(total-Integer.parseInt(info.getString("suma4"))));
            incorrectasReto5.setText( ""+(total-Integer.parseInt(info.getString("suma5"))));

            ResultSet info2=c.cargarEncuestas();
            info2.next();
            promedioEncuesta1.setText(info2.getString("pregunta1"));
            promedioEncuesta2.setText(info2.getString("pregunta2"));
            promedioEncuesta3.setText(info2.getString("pregunta3"));

            ResultSet info3=c.listado();
            String a;

            columnaIdentificacion.setCellValueFactory(cellData -> cellData.getValue().getId());
            columnaPuntaje.setCellValueFactory(cellData -> cellData.getValue().getPuntaje());

            while (info3.next()){
                personData.add(new Calificacion(info3.getString("idUsuario"),info3.getString("puntuacion")));
            }

            tablaPuntajes.setItems(personData);

        }
        ObservableList retos = FXCollections.observableArrayList();
        retos.add(1);
        retos.add(2);
        retos.add(3);
        retos.add(4);
        retos.add(5);
        boxRetos.setItems(retos);

        ObservableList encuestas = FXCollections.observableArrayList();
        encuestas.add(1);
        encuestas.add(2);
        encuestas.add(3);
        boxEncuestas.setItems(encuestas);
    }

    @FXML
    void calcularCoeficiente(ActionEvent event) throws SQLException {

      int pregunta= boxEncuestas.getValue();
      int nivel= boxRetos.getValue();
        Conexion c= new Conexion();
   Double coeficiente=  c.coeficiente(pregunta,nivel);

   resultadoCoeficiente.setText(coeficiente+"");



    }

    private class Calificacion{
        private StringProperty id;
        private StringProperty puntaje;

        public Calificacion(String id, String puntaje) {
            this.id = new SimpleStringProperty(id);
            this.puntaje = new SimpleStringProperty(puntaje);

        }

        public Calificacion() {
        }

        public StringProperty  getId() {
            return id;
        }

        public StringProperty getPuntaje() {
            return puntaje;
        }
    }
}
