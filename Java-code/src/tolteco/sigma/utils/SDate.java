/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Métodos para mainpulação de padrões de Data usados no sistema.
 *
 * Opera sobre a classe depreciada "Date" por causa de dependências de plugins
 * utilizados.
 *
 * @author JFPS
 */
public class SDate {
    public static final DateFormat DATE_FORMAT_SIGMA  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final DateFormat DATE_FORMAT_NOTIME = new SimpleDateFormat("yyyy/MM/dd");
    //public static final DateFormat DATE_FORMAT_JCALENDAR = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    
    /**
     * Transforma uma String no formato: "yyyy-MM-dd HH:mm:ss".
     *
     * Formatador explícito: {@link #DATE_FORMAT_SIGMA}.
     *
     * @param dataString Data no formato especificado.
     * @return Objeto de data.
     * @throws java.text.ParseException Caso não esteja no padrão.
     */
    public static Date sigmaDateFormat(String dataString) throws ParseException {
        return SDate.DATE_FORMAT_SIGMA.parse(dataString);
    }

    /**
     * Método oposto do {@link #sigmaDateFormat(java.lang.String)}, isto é,
     * transforma uma Data numa String no formato específicado lá.
     *
     * Formatador explícito: {@link #DATE_FORMAT_SIGMA}.
     *
     * @param data para converter.
     * @return String no formato: "yyyy-MM-dd HH:mm:ss".
     */
    public static String sigmaDateFormat(Date data) {
        return SDate.DATE_FORMAT_SIGMA.format(data);
    }

    /**
     * Transforma uma String no formato: "yyyy-MM-dd". 
     *
     * Formatador explícito: {@link #DATE_FORMAT_NOTIME}.
     *
     * @param dataString Data no formato especificado.
     * @return Objeto de data.
     * @throws java.text.ParseException Caso não esteja no padrão.
     */
    public static Date sigmaSmallDateFormat(String dataString) throws ParseException {
        return SDate.DATE_FORMAT_NOTIME.parse(dataString);
    }

    /**
     * Método oposto do {@link #sigmaDateSmallFormat(java.lang.String)}, isto é,
     * transforma uma Data numa String no formato específicado lá.
     *
     * Formatador explícito: {@link #DATE_FORMAT_NOTIME}.
     *
     * @param data para converter.
     * @return String no formato: "yyyy-MM-dd".
     */
    public static String sigmaSmallDateFormat(Date data) {
        return SDate.DATE_FORMAT_NOTIME.format(data);
    }
    
    /**
     * 20/02/2016 Método para traduzir data (Dia, Mês). jDayChooser retorna
     * dados em inglês, estes são armazenados no banco. No momento de "parsing",
     * deve-ser tê-los de acordo com o locale BR (em pt-br)
     *
     * @author Juliano Felipe
     *
     * @param data armazenada no banco de dados. Formato: SimpleDateFormat("E
     * MMM dd HH:mm:ss z yyyy") Exemplo: "Sáb Dez 20 22:23:58 BRST 2016"
     *
     * @return String com dia e mês traduzidos.
     */
    @Deprecated
    public static String TranslateString(String data) {
        String StringData = data;
        String[] split = StringData.split(" ", 3); //split by spaces
        String day = split[0];
        String month = split[1];
        switch (day) {
            //Inglês - Portugês
            case "Sun": day = "Dom"; break;
            case "Mon": day = "Seg"; break;
            case "Tue": day = "Ter"; break;
            case "Wed": day = "Qua"; break;
            case "Thu": day = "Qui"; break;
            case "Fri": day = "Sex"; break;
            case "Sat": day = "Sáb"; break;
            //Portugês - Inglês
            case "Dom": day = "Sun"; break;
            case "Seg": day = "Mon"; break;
            case "Ter": day = "Tue"; break;
            case "Qua": day = "Wed"; break;
            case "Qui": day = "Thu"; break;
            case "Sex": day = "Fri"; break;
            case "Sáb": day = "Sat"; break;
            default:
                throw new IllegalArgumentException(
                        "Argumento \"" + day + "\" da String \""
                        + StringData + "\" é inválido.");
        }
        switch (month) {
            //Inglês - Portugês
            case "Feb": month = "Fev"; break;
            case "Apr": month = "Abr"; break;
            case "May": month = "Mai"; break;
            case "Aug": month = "Ago"; break;
            case "Sep": month = "Set"; break;
            case "Oct": month = "Out"; break;
            case "Dec": month = "Dez"; break;
            //Portugês - Inglês
            case "Jan":                break;
            case "Fev": month = "Feb"; break;
            case "Mar":                break;
            case "Abr": month = "Apr"; break;
            case "Mai": month = "May"; break;
            case "Jun":                break;
            case "Jul":                break;
            case "Ago": month = "Aug"; break;
            case "Set": month = "Sep"; break;
            case "Out": month = "Oct"; break;
            case "Nov":                break;
            case "Dez": month = "Dec"; break;
            default:
                throw new IllegalArgumentException(
                        "Argumento \"" + month + "\" da String \""
                        + StringData + "\" é inválido.");
        }
        data = day + " " + month + " " + split[2];
        return data;
    }

    /**
     * 20/02/2016 Método para transformar String de data para o tipo
     * "java.util.Date" (ignora qualquer coisa que não seja o dia, mês ou ano;
     * setando o tempo para meia-noite).
     *
     * @author Juliano Felipe
     *
     * @param data do pacote Util do java.
     *
     * @return Date com hh:mm:ss setados em 0.
     *
     * @throws Exception de string fora do formato esperado para conversão.
     */
    @Deprecated
    public static Date StringToDate(String data) throws Exception {
        String[] split = data.split(" ", 6); //split by spaces
        String day = split[2];
        String month = split[1];
        String year = split[5];
        data = day + month + year;
        Date date = DATE_FORMAT_SIGMA.parse(data);
        return date;
    }

    /**
     * 20/02/2016 Método para transformar tipo "java.util.Date" para String de
     * data (ignora qualquer coisa que não seja o dia, mês ou ano). Usada pelos
     * métodos de vizualização, não salvando esse formato de strings no banco de
     * dados.
     *
     * @author Juliano Felipe
     *
     * @param data armazenada no banco de dados
     *
     * @return String no formato "dd/mm/aaaa".
     * 
     * @deprecated Utiliza-se o método {@link #sigmaDateFormat(java.util.Date)}.
     */
    @Deprecated
    public static String DateToString(Date data) {
        String StringData = data.toString();
        String[] split = StringData.split(" ", 6); //split by spaces
        String day = split[2];
        String month = split[1];
        String year = split[5];
        switch (month) {
            case "Jan": month = "01"; break;
            case "Feb": month = "02"; break;
            case "Mar": month = "03"; break;
            case "Apr": month = "04"; break;
            case "May": month = "05"; break;
            case "Jun": month = "06"; break;
            case "Jul": month = "07"; break;
            case "Aug": month = "08"; break;
            case "Sep": month = "09"; break;
            case "Oct": month = "10"; break;
            case "Nov": month = "11"; break;
            case "Dec": month = "12"; break;
        }
        return day + "/" + month + "/" + year;
    }
    
    public static Date addActualTime(final Date fromJChooser){
        Date horaAtual = new Date();
        fromJChooser.setHours(horaAtual.getHours());
        fromJChooser.setMinutes(horaAtual.getMinutes());
        fromJChooser.setSeconds(horaAtual.getSeconds());
        return fromJChooser;
    }
}
