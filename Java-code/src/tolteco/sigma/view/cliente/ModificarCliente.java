/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view.cliente;

import java.util.logging.Level;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.entidades.Cliente;
import tolteco.sigma.view.MainFrame;
import tolteco.sigma.view.Sistema;
import tolteco.sigma.view.interfaces.Adicionar;

/**
 * Painel de adição de clientes.
 * @author Juliano Felipe
 */
public class ModificarCliente extends javax.swing.JPanel implements Adicionar<Cliente>{
    private final MainCliente MAIN;
    
    /**
     * Creates new form Cliente
     * @param main
     */
    public ModificarCliente(MainCliente main) {
        initComponents();
        this.MAIN = main;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NomePanel = new javax.swing.JPanel();
        NomeField = new javax.swing.JTextField();
        TelPanel = new javax.swing.JPanel();
        TelFField = new javax.swing.JFormattedTextField();
        CPFPanel = new javax.swing.JPanel();
        CPFfField = new javax.swing.JFormattedTextField();
        EndPanel = new javax.swing.JPanel();
        EndBox = new javax.swing.JComboBox();
        EndField = new javax.swing.JTextField();
        ObsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ObsPane = new javax.swing.JTextPane();
        Salvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        idUsuario = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        idCliente = new javax.swing.JSpinner();

        NomePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));

        javax.swing.GroupLayout NomePanelLayout = new javax.swing.GroupLayout(NomePanel);
        NomePanel.setLayout(NomePanelLayout);
        NomePanelLayout.setHorizontalGroup(
            NomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NomeField)
        );
        NomePanelLayout.setVerticalGroup(
            NomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NomePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(NomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TelPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefone"));

        try {
            TelFField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TelFField.setToolTipText("");

        javax.swing.GroupLayout TelPanelLayout = new javax.swing.GroupLayout(TelPanel);
        TelPanel.setLayout(TelPanelLayout);
        TelPanelLayout.setHorizontalGroup(
            TelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelFField, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
        );
        TelPanelLayout.setVerticalGroup(
            TelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelFField)
        );

        CPFPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("CPF"));

        try {
            CPFfField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout CPFPanelLayout = new javax.swing.GroupLayout(CPFPanel);
        CPFPanel.setLayout(CPFPanelLayout);
        CPFPanelLayout.setHorizontalGroup(
            CPFPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CPFfField)
        );
        CPFPanelLayout.setVerticalGroup(
            CPFPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CPFPanelLayout.createSequentialGroup()
                .addComponent(CPFfField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        EndPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        EndBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alameda", "Avenida", "Estrada", "Rodovia", "Rua", "Travessa" }));
        EndBox.setSelectedIndex(4);
        EndBox.setToolTipText("Selecione o tipo do logradouro");

        EndField.setToolTipText("");

        javax.swing.GroupLayout EndPanelLayout = new javax.swing.GroupLayout(EndPanel);
        EndPanel.setLayout(EndPanelLayout);
        EndPanelLayout.setHorizontalGroup(
            EndPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EndPanelLayout.createSequentialGroup()
                .addComponent(EndBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EndField, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
        );
        EndPanelLayout.setVerticalGroup(
            EndPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EndPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(EndBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(EndField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ObsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Observações"));

        ObsPane.setToolTipText("Qualquer observação adicional sobre o cliente");
        jScrollPane2.setViewportView(ObsPane);

        javax.swing.GroupLayout ObsPanelLayout = new javax.swing.GroupLayout(ObsPanel);
        ObsPanel.setLayout(ObsPanelLayout);
        ObsPanelLayout.setHorizontalGroup(
            ObsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        ObsPanelLayout.setVerticalGroup(
            ObsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        );

        Salvar.setText("Salvar");
        Salvar.setMaximumSize(new java.awt.Dimension(75, 23));
        Salvar.setMinimumSize(new java.awt.Dimension(75, 23));
        Salvar.setPreferredSize(new java.awt.Dimension(75, 23));
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tolteco/sigma/view/images/General/Internal/Edit.png"))); // NOI18N

        jButton1.setText("Limpar Campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("ID User"));

        idUsuario.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(idUsuario)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Cliente"));

        idCliente.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ObsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(EndPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NomePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CPFPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 476, Short.MAX_VALUE)
                        .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CPFPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EndPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ObsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        try {
            MAIN.getController().update(getInstance());
        } catch (DatabaseException ex) {
            MainFrame.LOG.log(Level.SEVERE, null, ex);
            MAIN.displayDatabaseException(ex);
        }
    }//GEN-LAST:event_SalvarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cleanAllFields();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CPFPanel;
    private javax.swing.JFormattedTextField CPFfField;
    private javax.swing.JComboBox EndBox;
    private javax.swing.JTextField EndField;
    private javax.swing.JPanel EndPanel;
    private javax.swing.JTextField NomeField;
    private javax.swing.JPanel NomePanel;
    private javax.swing.JTextPane ObsPane;
    private javax.swing.JPanel ObsPanel;
    private javax.swing.JButton Salvar;
    private javax.swing.JFormattedTextField TelFField;
    private javax.swing.JPanel TelPanel;
    private javax.swing.JSpinner idCliente;
    private javax.swing.JSpinner idUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private static final String EMPTY="";
    
    @Override
    public void cleanAllFields() {
        NomeField.setText(EMPTY);
        CPFfField.setText(EMPTY);
        TelFField.setText(EMPTY);
        ObsPane.setText(EMPTY);
        
        EndBox.setSelectedItem("Rua");
        EndField.setText(EMPTY);
        
        idCliente.setValue(0);
        idUsuario.setValue(0);
    }

    @Override
    public void fillAllFields(Cliente object) {
        NomeField.setText(object.getNome() + " " + object.getSobrenome());
        CPFfField.setText(object.getCpf());
        TelFField.setText(object.getTel());
        ObsPane.setText(object.getObs());
        
        String end = object.getEnd();
        EndBox.setSelectedItem(end.substring(0,end.indexOf(' ')));
        EndField.setText(end.substring(end.indexOf(' ')+1));
        
        idCliente.setValue(object.getClienteId());
        idUsuario.setValue(object.getUserId());
    }

    @Override
    public Cliente getInstance() {
        String nome = NomeField.getText();
        String priNome="";
        String segundoNome="";
        
        if(nome.contains(" ")){
            priNome = nome.substring(0,nome.indexOf(' '));
            segundoNome = nome.substring(nome.indexOf(' ')+1);
        } else {
            priNome=nome;
        }
        
        String cpf = CPFfField.getText();
        String tel = TelFField.getText();
        String obs = ObsPane.getText();
        String end = (String) EndBox.getSelectedItem() + " " + EndField.getText();
        
        return new Cliente((int) idCliente.getValue(), priNome, segundoNome, obs, end, tel, cpf, Sistema.getUserID());
    }
}
