/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view.financas;

import javax.swing.event.TableModelEvent;
import net.java.balloontip.BalloonTip;
import tolteco.sigma.controller.FinancaController;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.entidades.Financa;
import tolteco.sigma.model.entidades.Situacao;
import tolteco.sigma.model.tables.FinancaTable;
import tolteco.sigma.view.MainFrame;
import tolteco.sigma.view.interfaces.MainEntity;
import tolteco.sigma.view.interfaces.Operacao;

/**
 * Tela principal para operação com clientes.
 * Nela que escolhe-se qual painel será exibido.
 * Uma espécie de padrão SINGLETON é implementada
 * com o auxílio da classe:
 * {@link tolteco.sigma.view.cliente.OperacaoCliente}.
 * @author Juliano Felipe
 */
public class MainFinanca extends javax.swing.JPanel implements MainEntity<FinancaController, Financa>{
    private Operacao ultimoPanelAdicionado = null;

    private final FinancaController controller;
    private final FinancaTable model;
    private final MainFrame main;
    
    public MainFinanca(MainFrame main, FinancaController controller) {
        initComponents();
        this.controller = controller;
        this.main = main;
        model = controller.getModel();

        model.addTableModelListener((TableModelEvent e) -> {
            Financa fin = ((FinancaTable) e.getSource()).getRow( e.getFirstRow() );
            if (fin.getSituacao() == Situacao.PENDENTE){
                main.getMainView().modeloTabela.addRow(fin);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Add = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Search = new javax.swing.JButton();
        List = new javax.swing.JButton();
        Panel = new javax.swing.JScrollPane();

        Add.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tolteco/sigma/view/images/General/Buttons/Add.png"))); // NOI18N
        Add.setText("Adicionar");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Edit.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tolteco/sigma/view/images/General/Buttons/Edit.png"))); // NOI18N
        Edit.setText("Modificar");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Delete.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tolteco/sigma/view/images/General/Buttons/Delete.png"))); // NOI18N
        Delete.setText("Excluir");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Search.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tolteco/sigma/view/images/General/Buttons/Search.png"))); // NOI18N
        Search.setText("Buscar");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        List.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        List.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tolteco/sigma/view/images/General/Buttons/List.png"))); // NOI18N
        List.setText("Listar");
        List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(List, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add)
                    .addComponent(Edit)
                    .addComponent(Delete)
                    .addComponent(Search)
                    .addComponent(List))
                .addGap(16, 16, 16)
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        if (ultimoPanelAdicionado != Operacao.Adicionar){ //Singleton - Sort of
            AdicionarFinanca add = new AdicionarFinanca(this);
            Panel.setViewportView( add );
        }
        
        ultimoPanelAdicionado = Operacao.Adicionar;
    }//GEN-LAST:event_AddActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        if (ultimoPanelAdicionado != Operacao.Buscar &&
            ultimoPanelAdicionado != Operacao.Modificar &&
            ultimoPanelAdicionado != Operacao.Remover    ){
            BuscarFinanca modif = new BuscarFinanca(this);
            Panel.setViewportView( modif );
            BalloonTip tooltipBalloon = new BalloonTip(Edit, "Busque uma Finança para modificar");
            tooltipBalloon.setVisible(true);
        }
        
        ultimoPanelAdicionado = Operacao.Modificar;
    }//GEN-LAST:event_EditActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        if (ultimoPanelAdicionado != Operacao.Buscar &&
            ultimoPanelAdicionado != Operacao.Modificar &&
            ultimoPanelAdicionado != Operacao.Remover    ){
            BuscarFinanca add = new BuscarFinanca(this);
            Panel.setViewportView( add );
            BalloonTip tooltipBalloon = new BalloonTip(Delete, "Busque uma Finança para excluir");
            tooltipBalloon.setVisible(true);
        }

        ultimoPanelAdicionado = Operacao.Remover;
    }//GEN-LAST:event_DeleteActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        if (ultimoPanelAdicionado != Operacao.Buscar &&
            ultimoPanelAdicionado != Operacao.Modificar &&
            ultimoPanelAdicionado != Operacao.Remover    ){ //Singleton - Sort of
            BuscarFinanca add = new BuscarFinanca(this);
            Panel.setViewportView( add );
        }
        ultimoPanelAdicionado = Operacao.Buscar;
    }//GEN-LAST:event_SearchActionPerformed

    private void ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListActionPerformed
        if (ultimoPanelAdicionado != Operacao.Listar){ //Singleton - Sort of
            ListarFinanca add = new ListarFinanca(this);
            Panel.setViewportView( add );
        }

        ultimoPanelAdicionado = Operacao.Listar;
    }//GEN-LAST:event_ListActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Edit;
    private javax.swing.JButton List;
    private javax.swing.JScrollPane Panel;
    private javax.swing.JButton Search;
    // End of variables declaration//GEN-END:variables

    @Override
    public void displayException(Exception ex) {
        BalloonTip tooltipBalloon = new BalloonTip(main.getExceptionTab(), "Exceção jogada.");
        tooltipBalloon.setVisible(true);
    }

    @Override
    public void displayDatabaseException(DatabaseException ex) {
        BalloonTip tooltipBalloon = new BalloonTip(main.getExceptionTab(), "Exceção do Banco de dados jogada.");
        tooltipBalloon.setVisible(true);
    }

    @Override
    public FinancaController getController() {
        return controller;
    }

    @Override
    public FinancaTable getModel() {
        return model;
    }

    @Override
    public void pressEdit(Financa toFill) {
        ModificarFinanca modif = new ModificarFinanca(this);
        Panel.setViewportView( modif ); 
        modif.fillAllFields(toFill);
        ultimoPanelAdicionado = Operacao.Modificar;
    }
}
