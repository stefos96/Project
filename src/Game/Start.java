package Game;

import Verbs.Attack;
import Verbs.Consume;
import Verbs.Drop;
import Verbs.Equip;
import Verbs.Exit;
import Verbs.Go;
import Verbs.Help;
import Verbs.Look;
import Verbs.Pick;
import Verbs.Preview;
import Verbs.Stats;
import Verbs.Unequip;
import Verbs.Unlock;
import Verbs.Verbs;
import Verbs.View;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author CrashOverwrite
 */
public class Start extends javax.swing.JFrame {

    private String firstWord = "";
    private String secondWord = "";
    private String UserCommand;
    private Character Player1;
    private Room Map1;
    private HashMap<String, Verbs> verbCommand = new HashMap<>();

    public void getCommand() {

    }

    // 
    public void enterNewCommand() {
        verbCommand.put("ATTACK", new Attack());
        verbCommand.put("CONSUME", new Consume());
        verbCommand.put("DROP", new Drop());
        verbCommand.put("EQUIP", new Equip());
        verbCommand.put("GO", new Go());
        verbCommand.put("LOOK", new Look());
        verbCommand.put("PICK", new Pick());
        verbCommand.put("PREVIEW", new Preview());
        verbCommand.put("UNEQUIP", new Unequip());
        verbCommand.put("UNLOCK", new Unlock());
        verbCommand.put("VIEW", new View());
        verbCommand.put("HELP", new Help());
        verbCommand.put("STATS", new Stats());
        verbCommand.put("EXIT", new Exit());
    }

    public void splitWords() {
        UserCommand = UserCommand.trim();

        if (UserCommand.contains(" ")) {
            int i = UserCommand.indexOf(" ");
            firstWord = UserCommand.substring(0, i);
            secondWord = UserCommand.substring(i + 1, UserCommand.length());
        } else {
            firstWord = UserCommand;
        }
    }

    int counter = 1000;
    Boolean isIt = false;

    /**
     * Creates new form Start
     */
    static Thread thread = new Thread();

    public Start() {
        Game.Timer timeToLose = new Game.Timer(DIFF.EASY);
        new Thread(timeToLose).start();
        MapCreation a = new MapCreation();
        a.mapReader();
//        a.passwordReader();
        a.itemReader();
        a.monsterReader();

        initComponents();

        Player1 = new Character();
        Map1 = new Room();
        enterNewCommand();
        outgui.append("Welcome to our game\n");
        outgui.append(Map1.getDoorNumber() + "\n");
        outgui.append(Map1.getRoomItems() + "\n");
        outgui.append(Map1.printMonster() + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outgui = new javax.swing.JTextArea();
        input = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Seconds = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        outgui.setEditable(false);
        outgui.setBackground(new java.awt.Color(255, 255, 255));
        outgui.setColumns(20);
        outgui.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        outgui.setForeground(new java.awt.Color(0, 0, 0));
        outgui.setLineWrap(true);
        outgui.setRows(5);
        outgui.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outgui.setEnabled(false);
        jScrollPane1.setViewportView(outgui);

        input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputKeyPressed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Replay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Exit");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Time to Finish : ");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("00:00:00");

        Seconds.setBackground(new java.awt.Color(0, 0, 0));
        Seconds.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Seconds.setForeground(new java.awt.Color(255, 255, 255));
        Seconds.setText("Seconds");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(input)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Seconds)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(Seconds))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jMenu2.setText("File");

        jMenuItem6.setText("Save");
        jMenu2.add(jMenuItem6);

        jMenuItem5.setText("Load");
        jMenu2.add(jMenuItem5);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Pause");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setText("Continue");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem4.setText("Exit");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Level");

        jMenuItem1.setText("Easy 30 min");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Medium 10 min");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Hard 2 min");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Timer timer = new Timer();
        counter = 1800;
        TimerTask task = new TimerTask() {
            public void run() {
                jLabel2.setText(Integer.toString(counter));
                counter--;
                if (counter == -1) {
                    timer.cancel();
                    jLabel2.setText("Game Over!\n");
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000); //ta dio teleftea einai to delay kai to period
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Timer timer = new Timer();
        counter = 600;
        TimerTask task = new TimerTask() {
            public void run() {
                jLabel2.setText(Integer.toString(counter));
                counter--;
                if (counter == -1) {
                    timer.cancel();
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Timer timer = new Timer();
        counter = 120;
        TimerTask task = new TimerTask() {
            public void run() {
                jLabel2.setText(Integer.toString(counter));
                counter--;
                if (counter == -1) {
                    timer.cancel();
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                UserCommand = input.getText().toUpperCase();
                splitWords();
                outgui.append("\n\n\n" + verbCommand.get(firstWord).checkVerb(Map1, Player1, secondWord));
            } catch (Exception e) {
                outgui.append(("Not an available command\n"));
            }
            input.setText("");
        }
    }//GEN-LAST:event_inputKeyPressed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        isIt=true;
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        isIt=false;
            // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                } else {
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Seconds;
    public javax.swing.JTextField input;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea outgui;
    // End of variables declaration//GEN-END:variables
}
