/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.model.entidades;

import java.util.Comparator;

/**
 * Entidade cliente, possui métodos e valores para o objeto cliente
 * @author Maycon
 */
public class Cliente implements Comparable{
    //Atributos
    private String nome,
                sobrenome,
                obs, 
                end, 
                tel, 
                cpf;
    private int clienteId,
                userId; //Quem adicionou o cliente

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    
    public Cliente(String nome, String sobrenome, String obs, String end, String tel, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.obs = obs;
        this.end = end;
        this.tel = tel;
        this.cpf = cpf;
    }

    public Cliente(String nome, String sobrenome, String obs, String end, String tel, String cpf, int userId) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.obs = obs;
        this.end = end;
        this.tel = tel;
        this.cpf = cpf;
        this.userId = userId;
    }
    
    public Cliente (int rowid){
        this.clienteId = rowid;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
        
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {    
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    // </editor-fold>
    
    @Override
    public String toString() {
        return "Cliente{" + "Nome: " + nome + ", Sobrenome: " + sobrenome + ", Obs.: " + obs + ", Endereço: " + end + ", Telefone: " + tel + ", CPF: " + cpf + ", Rowid: " + clienteId + '}' + "\n";
    }
    
    //<editor-fold defaultstate="collapsed" desc="Comparators">
    
    /**
     * Compara this com o, para ordenar em ordem
     * lexicográfica.
     * @param o String para comparar.
     * @return  0 se iguais, 1 se this for "maior",
     *          e -1 se this for "menor".
     * 
     * Caso a ordenação bugue com acentos, Tente esses links.
     * @see http://stackoverflow.com/questions/12927913/how-to-compare-non-english-characters-with-accents/12927962#12927962
     * @see http://www.javapractices.com/topic/TopicAction.do?Id=207
     */
    @Override
    public int compareTo(Object o) {
        String str=null;
        if (o instanceof String) str = (String) o;
        else throw new ClassCastException ("Objeto a ser comparado não é uma String!");
        return this.compareTo(str);
    }
    
    public static Comparator<String> nomeComparator (){
        return (String o1, String o2) -> {
            if (o1== null || o2 == null)
                return 0;
            return o1.compareTo(o2);
        };
    }
    
    public static Comparator<String> sobrenomeComparator (){
        return Cliente.nomeComparator();
    }
    
    public static Comparator<String> obsComparator (){
        return Cliente.nomeComparator();
    }
    
    public static Comparator<String> enderecoComparator (){
        return Cliente.nomeComparator();
    }
    
    public static Comparator<String> telefoneComparator (){
        return Cliente.nomeComparator();
    }
    
    public static Comparator<String> cpfComparator (){
        return Cliente.nomeComparator();
    }
    
    //</editor-fold>
}
