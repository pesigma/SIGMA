/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;
import Controles.ErrorPane;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entidade financa, possui métodos e valores para o objeto finança
 * @author Maycon
 */
public class Financa{
    
    //Atributos
    Date data;
    String obs;
    boolean sit, tipo;
    double valor;
    int rowid;

    //Construtores
    public Financa(boolean tipo, Date data, double valor, String obs, boolean sit) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.obs = obs;
        this.sit = sit;
    }
    
    public Financa(int rowid, boolean tipo, Date data, double valor, String obs, boolean sit) {
        this.rowid = rowid;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.obs = obs;
        this.sit = sit;
    }
    
    public Financa() {

    }
    
    //Métodos
    public int getRowId() {
        return rowid;
    }
    
    public boolean getTipo() {
        return tipo;
    }

    public Date getData() {
        return data;
    }

    public String getobs() {
        return obs;
    }

    public boolean isSit() {
        return sit;
    }

    public double getValor() {
        return valor;
    }

    public void setRowId(int rowid){
        this.rowid = rowid;
    }
    
    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDesc(String obs) {
        this.obs = obs;
    }

    public void setSit(boolean sit) {
        this.sit = sit;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    /** 20/02/2016
    * Método para transformar tipo "java.util.Date" para
    * String de data (ignora qualquer coisa que não seja
    * o dia, mês ou ano). Usada pelos métodos de vizualização,
    * não salvando esse formato de strings no banco de dados.
    * @author Juliano Felipe
    * 
    * @param data armazenada no banco de dados
    * 
    * @return String no formato "dd/mm/aaaa".
    */
    public String DateToString (Date data){
        String StringData = data.toString();
        String[] split = StringData.split(" ",6);     //split by spaces        
        String day = split[2]; 
        String month = split[1];
        String year = split[5];
        
        switch (month) {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";               
                break;
            case "Mar":
                month = "03";                
                break;
            case "Apr":
                month = "04";                
                break;
            case "May":
                month = "05";                
                break;
            case "Jun":
                month = "06";                
                break;
            case "Jul":
                month = "07";                
                break;
            case "Aug":
                month = "08";                
                break;
            case "Sep":
                month = "09";                
                break;
            case "Oct":
                month = "10";                
                break;
            case "Nov":
                month = "11";                
                break;
            case "Dec":
                month = "12";                
                break;
            }
        
        return day + "/" + month + "/" + year;
    }
    
    /** 20/02/2016
    * Método para transformar String de data para o tipo
    * "java.util.Date" (ignora qualquer coisa que não seja
    * o dia, mês ou ano; setando o tempo para meia-noite).
    * @author Juliano Felipe
    * 
    * @param data do pacote Util do java.
    * 
    * @return Date com hh:mm:ss setados em 0.
    * 
    * @throws Exception de string fora do formato esperado 
    * para conversão.
    */
    public Date StringToDate (String data) throws Exception{
        String[] split = data.split(" ",6);     //split by spaces        
        String day = split[2]; 
        String month = split[1];
        String year = split[5];
        data = day+month+year;
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        return date;
    }
    
    /** 20/02/2016
    * Método para traduzir data (Dia, Mês).
    * jDayChooser retorna dados em inglês, estes são
    * armazenados no banco. No momento de "parsing",
    * deve-ser tê-los de acordo com o locale BR (em pt-br)
    * @author Juliano Felipe
    * 
    * @param data armazenada no banco de dados.
    * Formato: SimpleDateFormat("E MMM dd HH:mm:ss z yyyy")
    * Exemplo: "Sáb Dez 20 22:23:58 BRST 2016"
    * 
    * @return String com dia e mês traduzidos.
    */
    public String TranslateString (String data){
        String StringData = data;
        String[] split = StringData.split(" ",3);     //split by spaces        
        String day = split[0]; 
        String month = split[1];
        
        switch (day) {
            //Inglês - Portugês
            case "Sun":
                day = "Dom";               
                break;
            case "Mon":
                day = "Seg";                
                break;
            case "Tue":
                day = "Ter";                
                break;
            case "Wed":
                day = "Qua";                
                break;
            case "Thu":
                day = "Qui";                
                break;
            case "Fri":
                day = "Sex";                
                break;
            case "Sat":
                day = "Sáb";                
                break;
            //Portugês - Inglês    
            case "Dom":
                day = "Sun";               
                break;
            case "Seg":
                day = "Mon";                
                break;
            case "Ter":
                day = "Tue";                
                break;
            case "Qua":
                day = "Wed";                
                break;
            case "Qui":
                day = "Thu";                
                break;
            case "Sex":
                day = "Fri";                
                break;
            case "Sáb":
                day = "Sat";                
                break;
            default:
                ErrorPane err = new ErrorPane();
                err.Error("Entidade finança", "Erro tradução de data.", "03-02-01.", "String do dia inválida.");
                break;
        }
        
        switch (month) { 
            //Inglês - Portugês
            case "Feb": //Os meses não listados permanecem iguais
                month = "Fev";               
                break;
            case "Apr":
                month = "Abr";                
                break;
            case "May":
                month = "Mai";                
                break;
            case "Aug":
                month = "Ago";                
                break;
            case "Sep":
                month = "Set";                
                break;
            case "Oct":
                month = "Out";                
                break;
            case "Dec":
                month = "Dez";                
                break;
                //Portugês - Inglês
            case "Jan":
                break;
            case "Fev": 
                month = "Feb";               
                break;
            case "Mar":
                break;
            case "Abr":
                month = "Apr";                
                break;
            case "Mai":
                month = "May";                
                break;
            case "Jun":
                break;
            case "Jul":
                break;
            case "Ago":
                month = "Aug";                
                break;
            case "Set":
                month = "Sep";                
                break;
            case "Out":
                month = "Oct";                
                break;
            case "Nov":
                break;
            case "Dez":
                month = "Dec";                
                break;
            default:
                ErrorPane err = new ErrorPane();
                err.Error("Entidade finança", "Erro tradução de data.", "03-02-02.", "String do mês inválida.");
                break;
        }
        
        data = day + " " + month + " " + split[2];        
        return data;
    }
}
