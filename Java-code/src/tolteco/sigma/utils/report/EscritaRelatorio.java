package tolteco.sigma.utils.report;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.dao.jdbc.JDBCFinancaDAO;
import tolteco.sigma.model.entidades.Financa;

/**
 * Codigo de escrita de relatorios em PDF do SIGMA
 * @author Maycon
 */
public class EscritaRelatorio {
    private final StringBuilder QUERY = new StringBuilder();
    
    private String namefile = "Relatorio(";
    private String A = new SimpleDateFormat("dd/MM/YYYY").format(new Date());
    private String B = new SimpleDateFormat("dd-MM-YYYY").format(new Date());

    /**
     * 14/05 - Maycon
     * Escreve o Latex
     * @param tipo : tipo de relatorio solicitado
     * @return : true se tudo ocorreu bem
     * @throws DatabaseException 
     */
    private boolean writeReport(int tipo) throws DatabaseException {
        String t;
        if (tipo == 0){
            t = "Mensal";
        } else if (tipo == 1){
            t = "Anual";
        } else {
            t = "Nao definido";
        }
        JDBCFinancaDAO jfd = new JDBCFinancaDAO();
        namefile = namefile + B;
        namefile = namefile + ").tex";
        final File file = new File(namefile);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            return false;
        } //Finalização de criacao de arquivo

        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        } catch (IOException ex) {
            Logger.getLogger(EscritaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }//Finalizacao de inicializacao de escritor
        
        /**
         * 04/09 - Maycon
         * Executa metodo que busca os dados e os tras em um ArrayList para serem adicionados a escrita depois
         */
        List<Financa> data = jfd.toReport(tipo);
        if (data == null){
            return false;
        }

        QUERY.append("\\documentclass[11pt]{article}\n")
            .append("\\usepackage[brazilian]{babel}\n")
            .append("\\usepackage[utf8]{inputenc}\n")
            .append("\\usepackage[T1]{fontenc}\n")
            .append("\\usepackage{makeidx}\n")
            .append("\\usepackage{multirow}\n")
            .append("\\usepackage{subfigure}\n")
            .append("\\usepackage{multicol}\n")
            .append("\\usepackage{color}\n")
            .append("\\usepackage[dvipsnames,svgnames,table]{xcolor}\n")
            .append("\\usepackage{graphicx}\n")
            .append("\\usepackage{epstopdf}\n")
            .append("\\usepackage{ulem}\n")
            .append("\\usepackage{float}\n")
            .append("\\usepackage{enumerate}\n")
            .append("\\usepackage{hyperref}\n")
            .append("\\usepackage{amsmath}\n")
            .append("\\usepackage{amssymb,amsmath}\n")
            .append("\\usepackage{watermark}\n")
            .append("\\usepackage{eso-pic,calc}\n")
            .append("\\usepackage{fancyhdr}\n")
            .append("\\usepackage{verbatim}\n")
            .append("\\usepackage{tabularx}\n")
            .append("\\usepackage[none]{hyphenat}\n")
            .append("\\renewcommand{\\theenumi}{\\Alph{enumi}}\n")
            .append("\\makeatletter\n")
            .append("\\sloppy\n")
            .append("\\usepackage{geometry}\n")
            .append("\\geometry{a4paper,left=2cm,right=2cm,bottom=2cm,top=2cm,headsep=1.5cm}\n")
            .append("\n")
            .append("\\makeatletter\n")
            .append("	\\newenvironment{indentation}[3]%\n")
            .append("	{\\par\\setlength{\\parindent}{#3}\n")
            .append("	\\setlength{\\leftmargin}{#1}       \\setlength{\\rightmargin}{#2}%\n")
            .append("	\\advance\\linewidth -\\leftmargin       \\advance\\linewidth -\\rightmargin%\n")
            .append("	\\advance\\@totalleftmargin\\leftmargin  \\@setpar{{\\@@par}}%\n")
            .append("	\\parshape 1\\@totalleftmargin \\linewidth\\ignorespaces}{\\par}%\n")
            .append("\\makeatother \n")
            .append("\n")
            .append("% new LaTeX commands\n")
            .append("\n")
            .append("\n")
            .append("\\begin{document}\n")
            .append("\n")
            .append("\\begin{center}\n")
            .append("\\textbf{\\large R\\ E\\ L\\ A\\ T\\ Ó\\ R\\ I\\ O\\ \\ \\ S\\ I\\ G\\ M\\ A}\n")
            .append("\\end{center}\n")
            .append("\\begin{flushleft}\n")
            .append("\\line(1,0){485}\\\\\n")
            .append("Data de Emissão: ").append(A).append("\\\\\n")
            .append("Tipo de Relatório: ").append(t).append("\\\\\n")
            .append("\\line(1,0){485}\\\\\n")
            .append("\\end{flushleft}\n")
            .append("\n")
            .append("\\begin{center}\n")
            .append("\\textbf{Receitas}\n")
            .append("\\end{center}\n")
            .append("\n")
            .append("\\begin{table}[h]\n")
            .append("    \\begin{tabularx}{\\textwidth}{l l l l}\n")
            .append("    Data\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ & Situação\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ & Observacoes\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ & Valor \\\\\n")
            .append("		\\hline\n")
            .append("    04/01/2016 & Quitado & Conserto X & 485   \\\\\n")//Aqui vai comecar o agrupamento
            .append("    06/01/2016 & Quitado & exemplo de texto & 125   \\\\\n")
            .append("		07/01/2016 & Não Quitado & freio de charrete & 387  \\\\\n")
            .append("    17/01/2016 & Quitado & Bolinho de aipim & 387  \\\\\n")
            .append("    23/01/2016 & Não Quitado & Batata & 56    \\\\\n")
            .append("		& &\\textbf{Valor Total} & 1259\n")
            .append("    \\end{tabularx}\n")
            .append("\\end{table}\n")
            .append("\n")
            .append("\\begin{center}\n")
            .append("\\textbf{Despesas}\n")
            .append("\\end{center}\n")
            .append("\n")
            .append("\\end{document}");
        writer.println(QUERY.toString());
        writer.close(); //Close já dá writer.flush(); mas em alguns casos pode ser recomendado fazer separado.
        //Finalização de criação de comandos

        QUERY.setLength(0);
        
        /**
         * 14/05 - Maycon
         * Chama o compilador de Latex (Nada bonito, mas funciona que é uma belezura)
         */
        final File bat = new File("LatexCompiler.bat");
        try {
            Desktop dt = Desktop.getDesktop();
            dt.open(bat);
        } catch (Exception e) {
            return false;
        } //Finalização de execução de arquivo
        return true;
    }
}
