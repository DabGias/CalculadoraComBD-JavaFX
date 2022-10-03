package br.com.fiap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import br.com.fiap.dao.ConnectionFactory;

public class PrimaryController {
    @FXML
    private TextField txtFieldOp;
    @FXML
    private Label labelOp;

    private int num1;

    public void digitarOp(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String num = txtFieldOp.getText() + btn.getText();
        txtFieldOp.setText(num);
    }

    public void calc() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        int resultado = 0;
        int num2 = Integer.valueOf(txtFieldOp.getText());
        String op = labelOp.getText() + num2;

        if (labelOp.getText().contains("+")) {
            resultado = num1 + num2;
        } else if (labelOp.getText().contains("-")) {
            resultado = num1 - num2;
        } else if (labelOp.getText().contains("*")) {
            resultado = num1 * num2;
        } else if (labelOp.getText().contains("/")) {
            resultado = num1 / num2;
        }

        PreparedStatement stm = con.prepareStatement("INSERT INTO CALC_DDD(op, resultado) VALUES(?, ?)");
        stm.setString(1, op);
        stm.setInt(2, resultado);

        stm.execute();
        con.close();

        labelOp.setText("");
        txtFieldOp.setText(String.valueOf(resultado));
    }

    public void somar() {
        num1 = Integer.valueOf(txtFieldOp.getText());
        labelOp.setText(num1 + " + ");
        txtFieldOp.setText("");
    }

    public void subtrair() {
        num1 = Integer.valueOf(txtFieldOp.getText());
        labelOp.setText(num1 + " - ");
        txtFieldOp.setText("");
    }

    public void mult() {
        num1 = Integer.valueOf(txtFieldOp.getText());
        labelOp.setText(num1 + " * ");
        txtFieldOp.setText("");
    }

    public void div() {
        num1 = Integer.valueOf(txtFieldOp.getText());
        labelOp.setText(num1 + " / ");
        txtFieldOp.setText("");
    }

    public void raiz() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        num1 = Integer.valueOf(txtFieldOp.getText());
        String op = String.valueOf(num1);
        int resultado = (int) Math.sqrt(num1);

        PreparedStatement stm = con.prepareStatement("INSERT INTO CALC_DDD(op, resultado) VALUES(?, ?)");
        stm.setString(1, op);
        stm.setInt(2, resultado);

        stm.execute();
        con.close();

        txtFieldOp.setText(String.valueOf(Math.sqrt(num1)));
    }

    public void quad() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        num1 = Integer.valueOf(txtFieldOp.getText());
        String op = num1 + " * " + num1;
        int resultado = num1 * num1;

        PreparedStatement stm = con.prepareStatement("INSERT INTO CALC_DDD(op, resultado) VALUES(?, ?)");
        stm.setString(1, op);
        stm.setInt(2, resultado);

        stm.execute();
        con.close();

        txtFieldOp.setText(String.valueOf(num1 * num1));
    }

    public void apagar() {
        txtFieldOp.setText("");
    }
}
