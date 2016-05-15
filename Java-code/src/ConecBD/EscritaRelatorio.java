/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConecBD;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maycon
 */
public class EscritaRelatorio {

    private String namefile = "Relatorio(";
    private String A = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    private String B = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    public EscritaRelatorio() {
        writeReport();
    }

    /**
     * 14/05 - Maycon
     * Escreve o Latex
     */
    public boolean writeReport() {
        namefile = namefile + B;
        namefile = namefile + ").tex";
        final File file = new File(namefile);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            return false;
        } //Finalização de criação de arquivo

        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        } catch (IOException ex) {
            Logger.getLogger(EscritaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }//Finalização de inicialização de escritor

        writer.println("\\documentclass[11pt]{article}\n"
                + "\\usepackage[brazilian]{babel}\n"
                + "\\usepackage[utf8]{inputenc}\n"
                + "\\usepackage[T1]{fontenc}\n"
                + "\\usepackage{makeidx}\n"
                + "\\usepackage{multirow}\n"
                + "\\usepackage{subfigure}\n"
                + "\\usepackage{multicol}\n"
                + "\\usepackage{color}\n"
                + "\\usepackage[dvipsnames,svgnames,table]{xcolor}\n"
                + "\\usepackage{graphicx}\n"
                + "\\usepackage{epstopdf}\n"
                + "\\usepackage{ulem}\n"
                + "\\usepackage{float}\n"
                + "\\usepackage{enumerate}\n"
                + "\\usepackage{hyperref}\n"
                + "\\usepackage{amsmath}\n"
                + "\\usepackage{amssymb,amsmath}\n"
                + "\\usepackage{watermark}\n"
                + "\\usepackage{eso-pic,calc}\n"
                + "\\usepackage{fancyhdr}\n"
                + "\\usepackage{verbatim}\n"
                + "\\usepackage{tabularx}\n"
                + "\\usepackage[none]{hyphenat}\n"
                + "\\renewcommand{\\theenumi}{\\Alph{enumi}}\n"
                + "\\makeatletter\n"
                + "\\sloppy\n"
                + "\\usepackage{geometry}\n"
                + "\\geometry{a4paper,left=2cm,right=2cm,bottom=2cm,top=2cm,headsep=1.5cm}\n"
                + "\n"
                + "\\makeatletter\n"
                + "	\\newenvironment{indentation}[3]%\n"
                + "	{\\par\\setlength{\\parindent}{#3}\n"
                + "	\\setlength{\\leftmargin}{#1}       \\setlength{\\rightmargin}{#2}%\n"
                + "	\\advance\\linewidth -\\leftmargin       \\advance\\linewidth -\\rightmargin%\n"
                + "	\\advance\\@totalleftmargin\\leftmargin  \\@setpar{{\\@@par}}%\n"
                + "	\\parshape 1\\@totalleftmargin \\linewidth\\ignorespaces}{\\par}%\n"
                + "\\makeatother \n"
                + "\n"
                + "% new LaTeX commands\n"
                + "\n"
                + "\n"
                + "\\begin{document}\n"
                + "\n"
                + "\\begin{center}\n"
                + "\\textbf{\\large R\\ E\\ L\\ A\\ T\\ Ó\\ R\\ I\\ O\\ \\ \\ S\\ I\\ G\\ M\\ A}\n"
                + "\\end{center}\n"
                + "\\begin{flushleft}\n"
                + "\\line(1,0){485}\\\\\n"
                + "Data de Emissão: " + A + "\\\\\n"
                + "Tipo de Relatório: \n"
                + "\\line(1,0){485}\\\\\n"
                + "\\end{flushleft}\n"
                + "\n"
                + "\\begin{center}\n"
                + "\\textbf{Receitas}\n"
                + "\\end{center}\n"
                + "\n"
                + "\\begin{table}[h]\n"
                + "    \\begin{tabularx}{\\textwidth}{l l l l}\n"
                + "    Data\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ & Situação\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ & Observacoes\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ & Valor \\\\\n"
                + "		\\hline\n"
                + "    04/01/2016 & Quitado & Conserto X & 485   \\\\\n"
                + "    06/01/2016 & Quitado & exemplo de texto & 125   \\\\\n"
                + "		07/01/2016 & Não Quitado & freio de charrete & 387  \\\\\n"
                + "    17/01/2016 & Quitado & Bolinho de aipim & 387  \\\\\n"
                + "    23/01/2016 & Não Quitado & Batata & 56    \\\\\n"
                + "		& &\\textbf{Valor Total} & 1259\n"
                + "    \\end{tabularx}\n"
                + "\\end{table}\n"
                + "\n"
                + "\\begin{center}\n"
                + "\\textbf{Despesas}\n"
                + "\\end{center}\n"
                + "\n"
                + "\\end{document}"
        );
        writer.close(); //Close já dá writer.flush(); mas em alguns casos pode ser recomendado fazer separado.
        //Finalização de criação de comandos

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
