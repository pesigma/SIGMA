/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import ConecBD.ConexaoBanco;
import Controles.CadastroCControle;
import Entidades.Cliente;
import java.awt.Dimension; //Usada para "setar" frame no meio da tela
import javax.swing.JOptionPane;
import java.awt.Image; //Usada para setar logo. Idem para a de baixo
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.sql.*;
import java.util.regex.Pattern; //Usada apenas para dar "split" na string de endereço, quando necessário

/**
 * 29/11/15 - Juliano Felipe Alteração para campos formatados.
 */
/**
 *
 * @author Maycon
 */
public class CadastroCliente extends javax.swing.JFrame {

    /**
     * 15/01 - Maycon Conexão com o banco de dados
     */
    Connection concliente = null;
    int rowid=-1; //Para ser usado como aux pelos métodos

    public TelaPrincipal telaanterior;
    public int metodo;
    String title = null; //Título do frame (Para ser usado nas janelas de aviso, erro, etc

    /**
     * Creates new form TelaCadastro
     */
    private CadastroCliente() {
        initComponents();
        
        //Seta janela para o meio da tela, independente da resolução.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        initNoicon();  //Seta "Logo vazio".
    }

    /**
     * 12/12/15 - Juliano Felipe Seta icone 1*1px (para "remover" icone default)
     */
    private void initNoicon() {
        Image No_ico = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        this.setIconImage(No_ico);
        title = this.getTitle(); //Pega título do frame
    }

    /**
    * 03/02/16 - Juliano Felipe
    * "Pseudo-construtor", chama o construtor padrão, função de reutilização de jFrame e salva
    * a instância do jFrame que chamou este (para poder habilitá-lo quando esta tela é fechada.
    * @param telanterior - Instância da tela anterior. 
    * @param option - Modo que o jFrame será utilizado (1 para Cadastro, 2 para consulta, etc).
    */
    public CadastroCliente(TelaPrincipal telanterior, int option) {
        //Chamar construtor
        this();
        this.telaanterior = telanterior;
        metodo = option;
        metodosCliente(option);
    }

    /**
     * 06/01/16 - Juliano Felipe Define metodos sobre a janela clientes,
     * reutilizando a mesma Variavel op chama o respectivo metodo
     * @param op - Opção de reutilização
     * 1 - Cadastro (esse método não é chamado, devido a condição de chamada no construtor modificado);
     * 2 - Consulta;
     * 3 - Modificação;
     * 4 - Exclusão;
     */
    public void metodosCliente(int op) {
        //Colocado como "cascateamento" pois toda vez que tem que modificar
        //ou excluir, passa por uma consulta
        concliente = ConexaoBanco.concliente(); //Colocado aqui para poder resetar funções sem ter que fechar a janela
        if (op >= 2) {//Op==2 - Consulta
            this.setTitle("Consulta de clientes");
            jFormattedTextField1.setEditable(false);
            jFormattedTextField2.setEditable(false);
            jComboBox1.setEnabled(false);
            jTextField4.setEditable(false);
            jTextPane2.setEnabled(false);
            jButton2.setText("Consultar");
        }
        if (op == 3) {//Op==3 - Modificar
            this.setTitle("Modificação de clientes");
        }
        if (op == 4) {//Op==4 - Excluir
            this.setTitle("Exclusão de clientes");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField4 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de clientes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salvar");
        jButton2.setMaximumSize(new java.awt.Dimension(75, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(75, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(75, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefone"));

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setText("");
        jFormattedTextField1.setToolTipText("");
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("CPF"));

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alameda", "Avenida", "Rodovia", "Rua", "Travessa" }));
        jComboBox1.setSelectedIndex(3);
        jComboBox1.setToolTipText("Selecione o tipo do logradouro");
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jComboBox1MouseExited(evt);
            }
        });

        jTextField4.setToolTipText("");
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Observações"));

        jTextPane2.setToolTipText("Qualquer observação adicional sobre o cliente");
        jScrollPane2.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Botão Cancelar pressionado
        this.dispose();
        telaanterior.setEnabled(true);
        //telaanterior.setExtendedState(telaanterior.MAXIMIZED_BOTH); //Maximiza a tela
        telaanterior.requestFocus(); //Traz o foco para tela anterior
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * 03/02/16 - Juliano Felipe
     * Função que chama a classe "MultipleEntries", que permite o cliente selecionar uma entrada por meio
     * de um jDialog. No fim, esse método obtém o nome e o id de uma instância da outra classe. Depois de
     * obter os dados, ela elimina a instância "MultipleEntries" e retorna o id do cliente selecionado pelo
     * usuário.
     * @param tel - Telefone do cliente
     * @param cpf - CPF do cliente
     * @param nome - Nome completo do cliente (Será dividido para inserão no banco de dados
     * @param obs - Observações associadas ao cliente.
     * @param end - Endereço do cliente
     */
    private void insertClient(String tel, String cpf, String nome, String obs, String end) {
        String[] split = nome.split(" ",2);     //split by spaces
        String fname = split[0]; // Primeiro nome
        String lname = split[1]; // "Resto do nome"

        try {
            String sql1 = "INSERT INTO cliente (nome,cpf,tel,end,obs,lname) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = concliente.prepareStatement(sql1);
            pst.setString(1, fname);
            pst.setString(6, lname);
            pst.setString(2, cpf);
            pst.setString(3, tel);
            pst.setString(4, end);
            pst.setString(5, obs);
            pst.execute();
            concliente.close();
            pst.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro. Código: 04-02-01.", title, JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
        }
    }
    
    /**
     * 03/02/16 - Juliano Felipe
     * Função que chama a classe "MultipleEntries", que permite o cliente selecionar uma entrada por meio
     * de um jDialog. No fim, esse método obtém o nome e o id de uma instância da outra classe. Depois de
     * obter os dados, ela elimina a instância "MultipleEntries" e retorna o id do cliente selecionado pelo
     * usuário.
     * @param nome - Nome completo caso seja necessário
     * @param fname - Primeiro nome do cliente, usado para consulta de múltiplos nomes no banco de dados
     * @return id - Id do cliente no banco de dados
     */
    private int selMulClient (String nome, String fname){
        this.setEnabled(false);
        MultipleEntries multipleEntries = new MultipleEntries(this, fname);
        multipleEntries.setVisible(true);
        
        nome = multipleEntries.getString();
        rowid = multipleEntries.getId();
        
        multipleEntries.parafechar.dispose();
        
        return rowid;
    }
    
    /**
     * 01/02/16 - Juliano Felipe
     * Função consulta um cliente no banco de dados, "setando" seus dados nos fields da interface.
     * @param nome - Nome para consultar no banco de dados, completo ou somente o primeiro nome.
     * @return id - Id do cliente no banco de dados
     */
    //PROBLEMAS ENCONTRADOS
    //Quando alguns fields estão vazios, os debaixo do primeiro campo vazio não são setados na janela
    private int selectClient (String nome){
        String[] splitname = nome.split(" ",2);     //split by spaces
        String fname = splitname[0]; // Para garantir que sempre pega o primeiro nome
        
        /**
        * 17/01 - Maycon Tentativa de função de consulta
        */
        int id=-1;
        try {
            //Tem "rowid" no select para pegar o valor do rowid
            String sql2 = "SELECT rowid, * FROM cliente WHERE nome=?";
            PreparedStatement pst = concliente.prepareStatement(sql2);
            pst.setString(1, fname);
            ResultSet rs = pst.executeQuery();
            
            int num_clients=0;
            while (rs.next()){ //Descobre o número de linhas retornadas
                num_clients++;
            }            
            if (num_clients>1){
                id = selMulClient (nome, fname);
                
                //Como teve de consultar para selecionar o nome
                //Fecho e abro novamente, com consulta por id (retorno o id do outro método)
                pst.close();
                rs.close();
                sql2 = "SELECT rowid, * FROM cliente WHERE rowid="+id;
                pst = concliente.prepareStatement(sql2);
                rs = pst.executeQuery();
            }
            
            if (rs.next()) {
                id = rs.getInt("rowid");
                String fno = rs.getString("nome");              //}
                String lno = rs.getString("lname");             //} Pega primeiro e últimos nomes separados e os concatena.
                String no = fno + " " + lno;                    //}
                jTextField1.setText(no);
                String te = rs.getString("tel");
                jFormattedTextField1.setText(te);
                String ce = rs.getString("cpf");
                jFormattedTextField2.setText(ce);

                String en = rs.getString("end");
                String[] split = en.split(Pattern.quote(".")); // Split no "." (Ponto final)
                String combo = split[0]; // String para ir no jcombobox
                String field = split[1]; // String para ir no text field
                jComboBox1.setSelectedItem(combo); //Seta item selecionado
                jTextField4.setText(field);

                String ob = rs.getString("obs");
                jTextPane2.setText(ob);
            }
        } catch (Exception e) {
            String error = e.getClass().getName();
            System.out.println("Olha o erro:" + error);
            JOptionPane.showMessageDialog(this, "Erro. Código: 04-02-02.", title, JOptionPane.ERROR_MESSAGE);
        }
        /**
        * FIM DA TENTATIVA
        */
        
        return id; //Retorna id do cliente
    }
    
    /**
     * 01/02/16 - Juliano Felipe
     * Função modificação de cliente.
     * @param tel - Telefone do cliente.
     * @param cpf - CPF do cliente.
     * @param nome - Nome do cliente.
     * @param obs - Observação associada ao cliente.
     * @param end - endereço do cliente.
     * @param id - Id do cliente no banco de dados.
     */
    private void updateClient (String tel, String cpf, String nome, String obs, String end, int id){
        String[] split = nome.split(" ",2);     //split by spaces
        String fname = split[0]; // Primeiro nome
        String lname = split[1]; // "Resto do nome"
        
        if (rowid==-1){
            JOptionPane.showMessageDialog(this, "Erro. Código: 04-02-06.", title, JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println(fname + lname + cpf + tel + end + "  " + obs + "   " + id);
        
        try {
            String sql1 = "UPDATE cliente SET nome=?, cpf=?, tel=?, end=?, obs=?, lname=? WHERE rowid="+id;
            PreparedStatement pst = concliente.prepareStatement(sql1);            
            pst.setString(1, fname);
            pst.setString(6, lname);
            pst.setString(2, cpf);
            pst.setString(3, tel);
            pst.setString(4, end);
            pst.setString(5, obs);
            pst.execute();
            concliente.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro. Código: 04-02-05.", title, JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
}

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /**
         * 06/01/16 - Juliano Definido a opcao 2 (consultar), altera-se a funcao
         * do botao salvar Idem para op. 3 (modificar). Idem para op. 4
         * (excluir).
         */
        if (metodo == 2) {
            //Id (no retorno) não é necessário.
            selectClient (jTextField1.getText());
            return; //Somente consulta, nao necessario salvar dados
        }

        if (metodo == 3) {
            String flag = jButton2.getText();
            int id=-1;
            if (flag!="Modificar"){
                id = selectClient (jTextField1.getText());
                if (id==-1){
                    JOptionPane.showMessageDialog(this, "Erro. Código: 04-02-04.", title, JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    rowid=id;
                }
            }else{
                //Update data and disable jbutton2
                Object ruaobj = jComboBox1.getSelectedItem();
                String ruatemp = ruaobj.toString();
                String end = ruatemp + "." + jTextField4.getText();
                
                updateClient (jFormattedTextField1.getText(), jFormattedTextField2.getText(), jTextField1.getText(), jTextPane2.getText(), end, rowid);
                
                //jButton2.setEnabled(false); //Para não tentar salvar novamente
                metodosCliente(3); //Resetar modificação
                return;
            }
            jFormattedTextField1.setEditable(true); //Todos os fields podem ser alterados novamente
            jFormattedTextField2.setEditable(true);
            jComboBox1.setEnabled(true);
            jTextField4.setEditable(true);
            jTextPane2.setEnabled(true);
            jButton2.setText("Modificar");
            return;
        }

        //Função bugada
        if (metodo == 4) {
            String flag = jButton2.getText();
            int id=-1;
            if (flag!="Excluir"){
                id = selectClient (jTextField1.getText());
                if (id==-1){
                    JOptionPane.showMessageDialog(this, "Erro. Código: 04-02-07.", title, JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    rowid=id;
                }
            }else{
                try {
                    String sql1 = "DELETE FROM cliente WHERE rowid="+rowid;
                    PreparedStatement pst = concliente.prepareStatement(sql1);            
                    pst.execute();
                    concliente.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro. Código: 04-02-08.", title, JOptionPane.ERROR_MESSAGE);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
                
                jButton2.setEnabled(false); //Para não tentar salvar novamente
            }
            jButton2.setText("Excluir");
            return;
        }

        //Botão Salvar pressionado
        String nome = jTextField1.getText();
        String tel = jFormattedTextField1.getText();
        String cpf = jFormattedTextField2.getText();
        String obs = jTextPane2.getText();

        Object ruaobj = jComboBox1.getSelectedItem();
        String ruatemp = ruaobj.toString();
        String end = ruatemp + "." + jTextField4.getText();

        Cliente p = new Cliente(tel, cpf, nome, obs, end);
        //Chama o controle para cadastrar
        CadastroCControle C = new CadastroCControle();

        /**
         * 15/01 - Maycon TESTE PARA VERIFICAÇÃO SE OS DADOS DO CLIENTE FORAM
         * RECEBIDOS
         */
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + tel);
        System.out.println("CPF: " + cpf);
        System.out.println("Endereco: " + end);
        System.out.println("Obs: " + obs);
        /**
         * FIM DO TESTE!!!!
         */

        if (C.cadastrarcliente(p)) {
            //Insere no banco de dados
            insertClient(tel, cpf, nome, obs, end);

            JOptionPane.showMessageDialog(this, "Cadastrado com sucesso", title, JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            telaanterior.setEnabled(true);
            telaanterior.requestFocus(); //Traz o foco para tela anterior
        } else {
            JOptionPane.showMessageDialog(this, "Erro. Código: 04-02-03.", title, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //Janela de cadastro de clientes fechada
        telaanterior.setEnabled(true);
        telaanterior.requestFocus(); //Traz o foco para tela anterior
    }//GEN-LAST:event_formWindowClosed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // Endereço
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jComboBox1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseExited

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        String nometemp = jTextField1.getText();
        String restemp = nometemp.toUpperCase();
        jTextField1.setText(restemp);
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        String endtemp = jTextField4.getText();
        String strtemp = endtemp.toUpperCase();
        jTextField4.setText(strtemp);
    }//GEN-LAST:event_jTextField4FocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        /**
         * 15/01 - Maycon Conexão com o banco de dados
         */
        //concliente = ConexaoBanco.concliente();
        //Coloquei na função "metodosCliente" para poder resetar funções sem precisar abrir novamente a janela
        //Já que a cada consulta, a conexão é fechada.
    }//GEN-LAST:event_formWindowOpened

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}
