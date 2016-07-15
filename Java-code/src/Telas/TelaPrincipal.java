/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import ConecBD.*;
import Controles.ErrorPane;
import tolteco.sigma.model.entidades.Financa;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Tela principal do programa
 * @author Maycon
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection connGeral = null;

    /**
    * Usurário que logou no sistema. Variável usada apenas para restrição
    * do evento de dois cliques na tabela. Recebe "Nenhum" para o programa
    * rodar como usuaário em caso de erro.
    * 
    * @author Juliano Felipe
    */
    private String user="Nenhum";
    /**
     * Número de colunas na tabela de finanças
     * da tela principal. Há uma 4ª coluna para
     * armazenamento de "rowid", não é mostrada.
     * 
     * @author Juliano Felipe
     */
    public int length_row=4;
    private int fin = -1;
    
    int num_rs = 0;
    String title = null;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();

        //Seta janela para o meio da tela, independente da resolução.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        initLogo(); //Seta logo SIGMA
        
        /*FinancaTable.setAutoCreateRowSorter(true);
        TableRowSorter sorter = new TableRowSorter(FinancaTable.getModel());
        FinancaTable.setRowSorter(sorter);*/
        UserField.setEditable(false);
        UserField.setText("   " + user);
        Permissao (user);
        title = this.getTitle();
        fillFinancaTable();
        Console.setEditable(false);
        ConsoleINFO("Paranauês adicionados");
        ConsoleWARN("Look out! Time is running out!");
        ConsoleERR("Erros severos. Hora de fazer a materia novamente de novo mais uma vez.");
    }
    
    /**
     * @author Juliano Felipe
     * Construtor que seta o nível de acesso do usuário.
     * 
     * @param user_extern - Usuário que logou. Oriunda da tela de login.
     */
    public TelaPrincipal(String user_extern) {
        this ();
        user = user_extern;
        UserField.setText("   " + user_extern);
        Permissao (user_extern);
    }

    /**
     * @author Juliano Felipe
     * Desabilita os campos conforme o nível de acesso. Dessa maneira,
     * apenas o administrador possui acesso à todos os campos e botões.
     * 
     * @param user - Usuário que logou. Oriunda da tela de login.
     */
    private void Permissao (String user){
        boolean access = false;
        if (user.equals("Administrador"))
            access = true;
        CadastroFinanca.setEnabled(access);
        ConsultaFinanca.setEnabled(access);
        ModificaFinanca.setEnabled(access);
        QuitaFinanca.setEnabled(access);
        ExcluiFinanca.setEnabled(access);
        NovoRelatorio.setEnabled(access);
        ConsultaRelatorio.setEnabled(access);
        ExcluiRelatorio.setEnabled(access);
        ExcluiServico.setEnabled(access);
        ExcluiCliente.setEnabled(access);
        FinancaTable.setEnabled(access);
    }
    
    /**
     * 12/12/15 - Juliano Felipe Seta icone "Logo 100x100.png"
     */
    private void initLogo() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/Logo 100x100.png")));
    }
    
    /**
     * 16/02/16 - Juliano Felipe Preenche a matriz de dados conforme consulta no
     * banco de dados (finanças não quitadas)
     *
     * @return data ArrayList de entidades financa com
     * os dados da consulta.
     */
    private ArrayList<Financa> getDataFinancas() {
        ArrayList<Financa> lista_financa = new ArrayList<>();

        String sit = "0";  //Pago = true/1; Não pago = false/0

        Connection Mul = ConexaoBanco.Multiple();
        try {
            String sql2 = "SELECT rowid, * FROM financa WHERE sit=?";
            PreparedStatement pst = Mul.prepareStatement(sql2);
            pst.setString(1, sit);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int rowid = rs.getInt("rowid");
                boolean tipo = rs.getBoolean("tipo");
                Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(rs.getString("data"));
                double valor = rs.getDouble("valor");
                boolean situ = rs.getBoolean("sit");
                String obs = rs.getString("obs");
                
                Financa tmp = new Financa(rowid, tipo, date, valor, obs, situ);
                lista_financa.add(tmp);
                num_rs++;
            }
            rs.close();
            pst.close();
            Mul.close();

            return lista_financa;
        } catch (Exception e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error(title, "Erro ao obter finanças do banco", "04-08-01.", error);
        }
        return null; //Erro
    }

    /**
     * 20/02/16 - Juliano Felipe 
     * Ordena array de entidades financa por data.
     *
     * @param data conforme armazenada no banco de dados.
     * @return data ArrayList ordenada por data.
     */
    private ArrayList<Financa> ArraySort (ArrayList<Financa> data){
        Collections.sort(data);
        return data;
    }
    
    /**
     * 16/02/16 - Juliano Felipe Configura a tabela de financas
     */
    private void fillFinancaTable() {
        DefaultTableModel model = (DefaultTableModel) FinancaTable.getModel();
        String[] columnNames = {"Data", "Valor", "Observações", "rowid"};

        ArrayList<Financa> lista_financa =  getDataFinancas();

        if (num_rs < 1) { //Se só for um resultado, seleciona-se a única row?
            //System.err.println("Erro. Código: 04-08-02.\nErro ao carregar atividades. Nenhum resultado encontrado.");
            model.addColumn("Erro obtendo finanças");
            Object[] strErr0 = {"Warning! Código: 04-08-02"};
            model.addRow(strErr0);
            Object[] strErr1 = {"Não existem finanças"};
            model.addRow(strErr1);
            Object[] strErr2 = {"com pagamento pendente!"};
            model.addRow(strErr2);
            return;
        } else if (num_rs>1){//Não precisa ordenar se há somente um valor
            lista_financa = ArraySort (lista_financa);
        }

        int i = lista_financa.size();
        int j = columnNames.length; //Gambiarra para não pegar o size do "ArrayList interno"
        int t, n;
        
        for (t = 0; t < j; t++) {
            model.addColumn(columnNames[t]);
        }

        Object[] list = new Object[j];
        for (n = 0; n < i; n++) {
            Financa tmp = lista_financa.get(n);
            list[0] = tmp.DateToString(tmp.getData()); //Data
            list[1] = tmp.getValor(); //Valor
            list[2] = tmp.getObs(); //Observações
            list[3] = tmp.getRowid(); //rowId
            model.addRow(list);
        }
        FinancaTable.setModel(model);
        //Remove VISUALIZAÇÃO da quarta coluna
        FinancaTable.removeColumn(FinancaTable.getColumnModel().getColumn(3));
        
        FinancaTable.getColumnModel().getColumn(0).setMinWidth(75); //Data - min
        FinancaTable.getColumnModel().getColumn(0).setMaxWidth(75); //Data - max
        FinancaTable.getColumnModel().getColumn(1).setMinWidth(100); //Valor - min
        FinancaTable.getColumnModel().getColumn(1).setMaxWidth(100); //Valor - max        
        
        FinancaTable.setAutoResizeMode(FinancaTable.AUTO_RESIZE_LAST_COLUMN); //DESC 
        
        revalidate();
        
        //Sorters. NÃ£o Ã© necessÃ¡rio o de Data, pois esse jÃ¡ Ã© implementado com o "compareTo" da Financa
        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setComparator(1, Financa.valorComparator());
        sorter.setComparator(2, Financa.obsComparator());
        FinancaTable.setRowSorter(sorter);
    }

    /**
     * 16/02/16 - Juliano Felipe Reseta a table da finanças.
     */
    public void RefreshTable() {
        DefaultTableModel clear = (DefaultTableModel) FinancaTable.getModel();
        clear.setRowCount(0);
        clear.setColumnCount(0);
        fillFinancaTable(); //Preenche novamente
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        CadastroCliente = new javax.swing.JButton();
        ModificaCliente = new javax.swing.JButton();
        ExcluiCliente = new javax.swing.JButton();
        ConsultaCliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        CadastroServico = new javax.swing.JButton();
        ModificaServico = new javax.swing.JButton();
        ExcluiServico = new javax.swing.JButton();
        ConsultaServico = new javax.swing.JButton();
        QuitaServico = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        CadastroFinanca = new javax.swing.JButton();
        ModificaFinanca = new javax.swing.JButton();
        ExcluiFinanca = new javax.swing.JButton();
        ConsultaFinanca = new javax.swing.JButton();
        QuitaFinanca = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        NovoRelatorio = new javax.swing.JButton();
        ExcluiRelatorio = new javax.swing.JButton();
        ConsultaRelatorio = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        FinancaTable = new javax.swing.JTable();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel6 = new javax.swing.JPanel();
        ConnButton = new javax.swing.JToggleButton();
        UserField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Console = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        ErrorMenu = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIGMA");
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        CadastroCliente.setText("Cadastrar");
        CadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastroClienteActionPerformed(evt);
            }
        });

        ModificaCliente.setText("Modificar");
        ModificaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificaClienteActionPerformed(evt);
            }
        });

        ExcluiCliente.setText("Excluir");
        ExcluiCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluiClienteActionPerformed(evt);
            }
        });

        ConsultaCliente.setText("Consultar");
        ConsultaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CadastroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ConsultaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ModificaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExcluiCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CadastroCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultaCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ModificaCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExcluiCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Serviços", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        CadastroServico.setText("Cadastrar");
        CadastroServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastroServicoActionPerformed(evt);
            }
        });

        ModificaServico.setText("Modificar");
        ModificaServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificaServicoActionPerformed(evt);
            }
        });

        ExcluiServico.setText("Excluir");
        ExcluiServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluiServicoActionPerformed(evt);
            }
        });

        ConsultaServico.setText("Consultar");
        ConsultaServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaServicoActionPerformed(evt);
            }
        });

        QuitaServico.setText("Quitar");
        QuitaServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitaServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CadastroServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ConsultaServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ModificaServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ExcluiServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(QuitaServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CadastroServico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultaServico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ModificaServico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QuitaServico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExcluiServico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Finanças", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        CadastroFinanca.setText("Cadastrar");
        CadastroFinanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastroFinancaActionPerformed(evt);
            }
        });

        ModificaFinanca.setText("Modificar");
        ModificaFinanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificaFinancaActionPerformed(evt);
            }
        });

        ExcluiFinanca.setText("Excluir");
        ExcluiFinanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluiFinancaActionPerformed(evt);
            }
        });

        ConsultaFinanca.setText("Consultar");
        ConsultaFinanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaFinancaActionPerformed(evt);
            }
        });

        QuitaFinanca.setText("Quitar");
        QuitaFinanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitaFinancaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ConsultaFinanca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ModificaFinanca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ExcluiFinanca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CadastroFinanca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(QuitaFinanca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CadastroFinanca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultaFinanca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ModificaFinanca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QuitaFinanca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExcluiFinanca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Relatórios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        NovoRelatorio.setText("Novo");
        NovoRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NovoRelatorioActionPerformed(evt);
            }
        });

        ExcluiRelatorio.setText("Excluir");

        ConsultaRelatorio.setText("Consultar");
        ConsultaRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NovoRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ConsultaRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExcluiRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NovoRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultaRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExcluiRelatorio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Atividades", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        FinancaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            })
            {public boolean isCellEditable(int row, int column){return false;}}
        );
        FinancaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FinancaTableMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(FinancaTable);

        jCalendar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Banco de dados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        ConnButton.setText("Desconectado");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ConnButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(ConnButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        UserField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setViewportView(Console);

        jMenu5.setText("Ajuda");
        jMenu5.setToolTipText("");

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem12.setText("Tópicos de ajuda");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);
        jMenu5.add(jSeparator2);

        jMenuItem11.setText("Sobre o SIGMA");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuBar1.add(jMenu5);

        ErrorMenu.setText("Consulta de Erros");
        ErrorMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ErrorMenuMousePressed(evt);
            }
        });
        jMenuBar1.add(ErrorMenu);

        jMenu1.setText("Deslogar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UserField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(242, 242, 242))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(UserField))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        /**
         * 18/02 - Maycon Funcao prototipo que chama o manual para o botao
         * "topicos de ajuda"
         */
        String username = System.getProperty("user.name");
        File pdf = new File("C:/Users/" + username + "/Documents/SIGMA/Manual.pdf");
        try {
            Desktop.getDesktop().open(pdf);
        } catch (Exception e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            ErrorPane err = new ErrorPane();
            err.Error(title, "Erro no desktop.", "04-08-03.", error);
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void CadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastroClienteActionPerformed
        /**
         * 05/12 - Maycon Botão Cadastrar Clientes
         */
        this.setEnabled(false);
        new CadastroCliente(this, 1).setVisible(true);
    }//GEN-LAST:event_CadastroClienteActionPerformed

    private void CadastroServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastroServicoActionPerformed
        /**
         * 05/12 - Maycon Botão Cadastro de Serviços
         */
        this.setEnabled(false);
        new CadastroServicos(this, 1).setVisible(true);
    }//GEN-LAST:event_CadastroServicoActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        /**
         * 05/12 - Maycon Botão Sobre o SIGMA
         */
        this.setEnabled(false);
        new About(this).setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void CadastroFinancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastroFinancaActionPerformed
        /**
         * 19/12 - Maycon Botão Cadastro de Registros Financeiros
         */
        this.setEnabled(false);
        new CadastroFinancas(this, 1).setVisible(true);
        RefreshTable();
    }//GEN-LAST:event_CadastroFinancaActionPerformed

    private void ConsultaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaClienteActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Consulta de Clientes
         */
        this.setEnabled(false);
        new CadastroCliente(this, 2).setVisible(true);
    }//GEN-LAST:event_ConsultaClienteActionPerformed

    private void ConsultaServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaServicoActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Consulta de Servicos
         */
        this.setEnabled(false);
        new CadastroServicos(this, 2).setVisible(true);
    }//GEN-LAST:event_ConsultaServicoActionPerformed

    private void ConsultaFinancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaFinancaActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Consulta de Registros Financeiros
         */
        this.setEnabled(false);
        new CadastroFinancas(this, 2).setVisible(true);
    }//GEN-LAST:event_ConsultaFinancaActionPerformed

    private void ModificaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificaClienteActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Modificar clientes
         */
        this.setEnabled(false);
        new CadastroCliente(this, 3).setVisible(true);
    }//GEN-LAST:event_ModificaClienteActionPerformed

    private void ExcluiClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluiClienteActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Exclusão de clientes
         */
        this.setEnabled(false);
        new CadastroCliente(this, 4).setVisible(true);    }//GEN-LAST:event_ExcluiClienteActionPerformed

    private void ModificaServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificaServicoActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Modificar Servicos
         */
        this.setEnabled(false);
        new CadastroServicos(this, 3).setVisible(true);
    }//GEN-LAST:event_ModificaServicoActionPerformed

    private void ExcluiServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluiServicoActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Exlusão Servicos
         */
        this.setEnabled(false);
        new CadastroServicos(this, 4).setVisible(true);
    }//GEN-LAST:event_ExcluiServicoActionPerformed

    private void ModificaFinancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificaFinancaActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Modifica Registro Financeiro
         */
        this.setEnabled(false);
        new CadastroFinancas(this, 3).setVisible(true);
    }//GEN-LAST:event_ModificaFinancaActionPerformed

    private void ExcluiFinancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluiFinancaActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Exclui Registro Financeiro
         */
        this.setEnabled(false);
        new CadastroFinancas(this, 4).setVisible(true);
    }//GEN-LAST:event_ExcluiFinancaActionPerformed

    private void QuitaServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitaServicoActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Quitar Servicos
         */
        this.setEnabled(false);
        new CadastroServicos(this, 5).setVisible(true);
    }//GEN-LAST:event_QuitaServicoActionPerformed

    private void QuitaFinancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitaFinancaActionPerformed
        /**
         * 06/01/2016 - Juliano Felipe Botão Quita Registro Financeiro
         */
        this.setEnabled(false);
        new CadastroFinancas(this, 5).setVisible(true);    }//GEN-LAST:event_QuitaFinancaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        connGeral = ConexaoBanco.Geral();
        ConnButton.setEnabled(false);
        //connGeral = null; //Teste para caso queira ver como mostra o banco desconectado!!!
        if (connGeral != null) {
            Color conectado = new Color(0, 102, 0);
            ConnButton.setBackground(conectado);
            ConnButton.setForeground(conectado);
            ConnButton.setText("   Conectado   ");
        } else {
            ConnButton.setEnabled(false);
            Color desconectado = new Color(204, 0, 0);
            ConnButton.setBackground(desconectado);
            ConnButton.setForeground(desconectado);
        }
    }//GEN-LAST:event_formWindowOpened

    public int getFinancaId (){
        return fin;
    }
    
    private void FinancaTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FinancaTableMousePressed
        if (user.equals("Administrador")){
            /**
            * 21/02/2016 - Juliano Felipe 
            * Evento para quitar registros a partir da tabela na janela principal.
            */
            FinancaTable =(JTable) evt.getSource();
            Point p = evt.getPoint();
            int row = FinancaTable.rowAtPoint(p); //Posição da row.
            //Pode ser pego com "ErrorTable.getSelectedRow()"
            if (evt.getClickCount() == 2) {
                DefaultTableModel model = (DefaultTableModel) FinancaTable.getModel();

                fin = Integer.parseInt(model.getValueAt(row, 3).toString());

                this.setEnabled(false);
                new CadastroFinancas(this, 6).setVisible(true);
            }
        }
    }//GEN-LAST:event_FinancaTableMousePressed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        if ((evt.getNewState() & this.ICONIFIED) == this.ICONIFIED){
            System.out.println("Minimizado");
        } else if ((evt.getNewState() & this.MAXIMIZED_BOTH) == this.MAXIMIZED_BOTH){
            System.out.println("Maximizado");
        }
    }//GEN-LAST:event_formWindowStateChanged

    private void ErrorMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ErrorMenuMousePressed
        /**
         * 13/02/16 - Juliano Felipe Abre a tela de consulta de erros.
         */
        String[] columnNames = {"Id", "Pacote", "Arquivo", "Interno", "Descrição"};
        this.setEnabled(false);
        new Vizualizaca(this, columnNames).setVisible(true);
    }//GEN-LAST:event_ErrorMenuMousePressed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        Object[] choices = {"Sim", "Não"};
        Object defaultChoice = choices[0];
        int dialogRet = JOptionPane.showOptionDialog(this, "Deseja realmente deslogar?", "Deslogar", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
        if(dialogRet == 1) { //==0 para sim
            return;
        }

        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu1MousePressed

    private void ConsultaRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaRelatorioActionPerformed
        this.setEnabled(false);
        new ConsultaRelatorio(this).setVisible(true);
    }//GEN-LAST:event_ConsultaRelatorioActionPerformed

    private void NovoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NovoRelatorioActionPerformed
        /**
         * 14/05 - Maycon
         * Chama o escritor de Latex
         */
        EscritaRelatorio ER = new EscritaRelatorio();
    }//GEN-LAST:event_NovoRelatorioActionPerformed

    public void ConsoleINFO(String msg){
        StyledDocument doc = Console.getStyledDocument();
        
        Style style = Console.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.BLACK);

        try { doc.insertString(doc.getLength(), "\n" +  msg,style); }
        catch (BadLocationException e){e.printStackTrace();}
    }
   
    public void ConsoleWARN(String msg){
        StyledDocument doc = Console.getStyledDocument();

        Style style = Console.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.ORANGE);
        
        try { doc.insertString(doc.getLength(), "\n" +  msg,style); }
        catch (BadLocationException e){e.printStackTrace();}
    }
    
    public void ConsoleERR(String msg){
        StyledDocument doc = Console.getStyledDocument();

        Style style = Console.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.RED);
        
        try { doc.insertString(doc.getLength(),"\n" +  msg,style); }
        catch (BadLocationException e){e.printStackTrace();}
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CadastroCliente;
    private javax.swing.JButton CadastroFinanca;
    private javax.swing.JButton CadastroServico;
    private javax.swing.JToggleButton ConnButton;
    private javax.swing.JTextPane Console;
    private javax.swing.JButton ConsultaCliente;
    private javax.swing.JButton ConsultaFinanca;
    private javax.swing.JButton ConsultaRelatorio;
    private javax.swing.JButton ConsultaServico;
    private javax.swing.JMenu ErrorMenu;
    private javax.swing.JButton ExcluiCliente;
    private javax.swing.JButton ExcluiFinanca;
    private javax.swing.JButton ExcluiRelatorio;
    private javax.swing.JButton ExcluiServico;
    private javax.swing.JTable FinancaTable;
    private javax.swing.JButton ModificaCliente;
    private javax.swing.JButton ModificaFinanca;
    private javax.swing.JButton ModificaServico;
    private javax.swing.JButton NovoRelatorio;
    private javax.swing.JButton QuitaFinanca;
    private javax.swing.JButton QuitaServico;
    private javax.swing.JTextField UserField;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
