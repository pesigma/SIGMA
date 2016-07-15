/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * Modelo de tabela para Clientes.
 * 
 * @author Juliano Felipe
 */
public class ClienteTableModel extends DefaultTableModel{
    List<Cliente> data = new ArrayList(1);
    Object[] headers = {"ID", "Nome", "Sobrenome", "Endereço", "Telefone", "CPF", "Observações"};

    public ClienteTableModel(ArrayList<Cliente> data) {
        this.data = new ArrayList<>(data);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Cliente temp = data.get(row);
        
        switch (column){
            case 0: return temp.getRowid();
            case 1: return temp.getNome();
            case 2: return temp.getSobrenome();
            case 3: return temp.getEnd();
            case 4: return temp.getTel();
            case 5: return temp.getCpf();
            case 6: return temp.getObs();
            default: throw new ArrayIndexOutOfBoundsException("Coluna inexistente. Impossível recuperar tal dado.");
        }
    }

    @Override
    public String getColumnName(int column) {
        return (String) headers[column];
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public void removeRow(int row) {
        data.remove(row);
    }

    public void insertRow(int row, Cliente toAdd) {
        data.add(row, toAdd);
    }

    public void addRow(Cliente toAdd) {
        data.add(toAdd);
    }

    @Override
    public Vector getDataVector() {
        return new Vector(data);
    }
    
    public static void main(String[] args) {
        ArrayList<Cliente> dts = new ArrayList<>();
        Cliente t  = new Cliente("N",  "S", "Obs", "Rua", "666T", "101");
        Cliente t1 = new Cliente("N1", "S", "Obs", "Rua", "666T", "101");
        Cliente t2 = new Cliente("N2", "S", "Obs", "Rua", "666T", "101");
        Cliente t3 = new Cliente("N3", "S", "Obs", "Rua", "666T", "101");
        
        dts.add(t); dts.add(t1); dts.add(t2); dts.add(t3);
        System.out.println(dts.size());
        ClienteTableModel adds = new ClienteTableModel(dts);
        
        Cliente t0  = new Cliente("N0",  "S", "Obs", "Rua", "666T", "101");
        adds.insertRow(1, t0);
        
    }
}
