/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tolteco.sigma.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPanel;
import tolteco.sigma.controller.ClienteController;
import tolteco.sigma.controller.FinancaController;
import tolteco.sigma.controller.ServicoController;
import tolteco.sigma.controller.UsuarioController;
import tolteco.sigma.controller.VersionController;
import tolteco.sigma.model.dao.DatabaseException;
import tolteco.sigma.model.entidades.Access;
import tolteco.sigma.model.entidades.Version;
import tolteco.sigma.utils.DefaultConfigs;
import tolteco.sigma.utils.SDate;
import tolteco.sigma.utils.logging.BufferedPaneOutputStream;
import tolteco.sigma.utils.logging.PaneHandler;
import tolteco.sigma.view.cliente.MainCliente;
import tolteco.sigma.view.financas.MainFinanca;
import tolteco.sigma.view.servicos.MainServico;
import tolteco.sigma.view.usuarios.MainUsuario;
import tolteco.sigma.view.version.MainVersion;

/**
 *
 * @author Juliano
 */
public class MainFrame extends javax.swing.JFrame {

    //private static final int ACCESS_LEVEL = 0; //Usado para testes - Nivel de acesso
    public static final Logger LOG = Logger.getLogger(MainFrame.class.getName());
    private final MainView child;
    private final ClienteController clienteController;
    private Version ver;
    
    public ClienteController getClienteController(){
        return clienteController;
    }
    
    private static void initLogger(MainView mainChild){
        mainChild.console.setEditable(false);
        BufferedPaneOutputStream oStream = new BufferedPaneOutputStream(mainChild.console);
        LOG.addHandler(new PaneHandler(oStream));
    }
    
    private JButton defineLogOutButton(){
        JButton logOut = new JButton("Logout");
        
        logOut.setIcon(
            new javax.swing.ImageIcon(
                getClass().getResource("/tolteco/sigma/view/images/User/LogOut.png")));
        
        logOut.addActionListener((ActionEvent e) -> {
            try {
                Sistema.logout();
            } catch (DatabaseException ex) {
                MainFrame.LOG.severe("Falha ao tentar deslogar seguramente do sistema.");
            }
            this.setVisible(false);
            this.dispose();
        });
        
        logOut.setPreferredSize( new Dimension (120,26));
        
        return logOut;
    }
    
    private JButton defineExitButton(){
        JButton exit = new JButton("Sair");
        
        exit.setIcon(
            new javax.swing.ImageIcon(
                getClass().getResource("/tolteco/sigma/view/images/User/SystemOut.png")));
        
        exit.addActionListener((ActionEvent e) -> {
            Sistema.shutdown();
            this.setVisible(false);
            this.dispose();
        });
        
        exit.setPreferredSize( new Dimension (90,26));
        
        return exit;
    }
    
    /**
     * Creates new form MainFrame
     * @param cliente
     * @param financa
     * @param servico
     * @param usuario
     * @param version
     */
    public MainFrame(ClienteController cliente, 
           FinancaController financa, ServicoController servico, 
           UsuarioController usuario, VersionController version) {
        
        initComponents();
        clienteController = cliente;
        //new Font(DefaultConfigs.SYSTEMFONT, Font.BOLD|Font.ITALIC, 16)
        PainelGuias.setFont( new Font(DefaultConfigs.SYSTEMFONT, Font.PLAIN, 16) );
            
        child = new MainView(this);
        PainelGuias.add(child);
        PainelGuias.setTitleAt(0, "Principal");
        MainFrame.initLogger(child);
        
        JPanel panel1 = new MainCliente(this, cliente);
        PainelGuias.add(panel1);
        PainelGuias.setTitleAt(1, "Clientes");
        
        JPanel panel2 = new MainFinanca(this, financa);
        PainelGuias.add(panel2);
        PainelGuias.setTitleAt(2, "Finanças");
        
        JPanel panel3 = new MainServico(this, servico);
        PainelGuias.add(panel3);
        PainelGuias.setTitleAt(3, "Serviços");
        
        if (Sistema.getUser().getAccessLevel() == Access.ROOT){
            JMenu jmenu = new JMenu("Configurações");
            BarraDeMenu.add(Box.createHorizontalGlue());
            BarraDeMenu.add(jmenu);
            
            JPanel panel4 = new MainUsuario(this, usuario);
            PainelGuias.add(panel4);
            PainelGuias.setTitleAt(4, "Usuários");
        
            JPanel panel5 = new MainVersion(this, version);
            PainelGuias.add(panel5);
            PainelGuias.setTitleAt(5, "Versões");
        }
        try {
            ver = version.fetchLatestVersion();
        } catch (DatabaseException ex) {
            LOG.severe(ex.getLocalizedMessage());
        }
        
        BarraDeMenu.add(defineLogOutButton());
        
        
        BarraDeMenu.add(defineExitButton());
        //BalloonTip bal = new BalloonTip(panel, "Tooltip msg");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelGuias = new javax.swing.JTabbedPane();
        BarraDeMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIGMA");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                MainFrame.this.windowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        PainelGuias.setMinimumSize(new java.awt.Dimension(887, 580));

        jMenu1.setText("Help");

        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        BarraDeMenu.add(jMenu1);

        setJMenuBar(BarraDeMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelGuias, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelGuias, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        ViewUtils.centerFrame(this);
        ViewUtils.setSIGMAIcon(this);
    }//GEN-LAST:event_formWindowOpened

    private void windowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosed
        String consoleData = child.console.getText();
        
        final File FILE = new File("log\\" +  SDate.DATE_LOG_FILE.format(new Date()) + ".LOG");
        BufferedWriter bw;
        try {
            bw = new BufferedWriter( new FileWriter(FILE));
            bw.write(consoleData);
            bw.close();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_windowClosed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        About ab = new About(this, ver, ver.getMinorDate());
        ab.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Método principal para a criação de um frame.
     * Coloca o sistema no "look and feel" de acordo
     * com:
     * {@link tolteco.sigma.view.MainFrame#LOOKANDFEEL}.
     * Chama o construtor default para inicialização
     * de componentes.
     * @param args the command line arguments
     */
    public static void main(String args[]) {       
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (DefaultConfigs.SYSTEMLOOK.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sistema.assembleMain().setVisible(true);
            }
        });
    }
    
    public JComponent getExceptionTab(){
        return PainelGuias;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar BarraDeMenu;
    private javax.swing.JTabbedPane PainelGuias;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
