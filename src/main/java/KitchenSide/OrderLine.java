package KitchenSide;

import FontSide.Drink;
import FontSide.GetData;
import FontSide.Pasta;
import FontSide.Pizza;
import FontSide.SaveData;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Imperfeton
 */
public class OrderLine extends javax.swing.JFrame {

    /**
     * Creates new form OrderLine
     * @throws java.sql.SQLException
     */
    public OrderLine() throws SQLException {
        initComponents();
        loadOrders();
    }

    private static ArrayList<String> listOfAllProducts;
    public static void loadOrders() throws SQLException{
        listOfAllProducts = new ArrayList<String>();
        String[] row;
        String productName;
        String quantity;
        String typeOfProduct;
        DefaultTableModel model = (DefaultTableModel) tableOrderLine.getModel();
        HashMap<Integer, ArrayList<String>> orders = new HashMap<Integer, ArrayList<String>>();
        ArrayList<String> listOfProducts  = new  ArrayList<String>();
        orders = GetData.getAllOrders();
        for(int id = 1; id < GetData.getNewOrderID() + 1; id++){
            listOfProducts = orders.get(id);
            Integer ID = id;
            for(int i = 0;i < listOfProducts.size(); i++){
                System.out.println(listOfProducts.get(i));
                listOfAllProducts.add(listOfProducts.get(i));
                if(listOfProducts.get(i).split(",")[0].contains("Pizza")){
                    typeOfProduct = "Pizza";
                }else if(listOfProducts.get(i).split(",")[0].contains("Pasta")){
                    typeOfProduct = "Pasta";
                }else if(listOfProducts.get(i).split(",")[0].equals("Cups")){
                    typeOfProduct = "Cups";
                }else{
                    typeOfProduct = "Drink";
                }
                System.out.println(typeOfProduct);
                switch (typeOfProduct) {
                    case "Pizza":
                        productName = listOfProducts.get(i).split(",")[0];                       
                        row = new String[]{ID.toString(), productName};
                        model.addRow(row);
                        break;
                    case "Pasta":
                        productName = listOfProducts.get(i).split(",")[0];
                        quantity = listOfProducts.get(i).split(",")[1];
                        row = new String[]{ID.toString(),productName, quantity};
                        model.addRow(row);
                        break;

                    case "Cups":
                        String cups = listOfProducts.get(i).split(",")[1];
                        row = new String[]{ID.toString(), "Cups", cups};
                        model.addRow(row);
                        break;
                        
                    default :
                    productName = listOfProducts.get(i).split(",")[0];
                    quantity = listOfProducts.get(i).split(",")[1];
                    //row Drink
                    row = new String[]{ID.toString(), productName, quantity};
                    model.addRow(row);
                    break;
                }
            }
        }
    }
    
class ButtonRendererView extends JButton implements TableCellRenderer {

  public ButtonRendererView() {
    setOpaque(true);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "View" : value.toString());
    return this;
  }
}

class ButtonEditorView extends DefaultCellEditor 
  {
    private JButton button;
    private String label;
    
    public ButtonEditorView(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener((ActionEvent e) -> {
        DefaultTableModel model = (DefaultTableModel) tableOrderLine.getModel();
        int row = tableOrderLine.getSelectedRow();
        String product = listOfAllProducts.get(row);
        if (product.split(",")[0].contains("Pizza")){
            String pizzaName = product.split(",")[0];
            String size = product.split(",")[1];
            String base = product.split(",")[2];
            String sauce = product.split(",")[3];
            String note = product.split(",")[4];
            JOptionPane.showMessageDialog(null, String.format(
                    "Details: \n"+
                    "Pizza: %s\n"+
                    "Size: %s\n"+
                    "Base: %s\n"+
                    "Sauce: %s\n"+        
                    "Note: %s\n",pizzaName, size, base, sauce, note));
        }
    });
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) 
    {
      label = (value == null) ? "View" : value.toString();
      button.setText(label);
      return button;
    }
    @Override
    public Object getCellEditorValue() 
    {
      return new String(label);
    }
  }

class ButtonRendererDone extends JButton implements TableCellRenderer {

  public ButtonRendererDone() {
    setOpaque(true);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "Serve" : value.toString());
    return this;
  }
}

class ButtonEditorDone extends DefaultCellEditor 
  {
    private JButton button;
    private String label;
    
    public ButtonEditorDone(JCheckBox checkBox) throws SQLException{
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener((ActionEvent e) -> {
        int respond = JOptionPane.showConfirmDialog(null,"Serve Item ?", "Serve Item", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(respond == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel) tableOrderLine.getModel();
            int row = tableOrderLine.getSelectedRow();
            Integer orderID = Integer.parseInt(model.getValueAt(row, 0).toString());           
            String product = listOfAllProducts.get(row);
            listOfAllProducts.remove(row);
            Integer quantity;
            String typeOfProduct;
            if(product.split(",")[0].contains("Pizza")){
                typeOfProduct = "Pizza";
            }else if(product.split(",")[0].contains("Pasta")){
                typeOfProduct = "Pasta";
            }else if(product.split(",")[0].equals("Cups")){
                typeOfProduct = "Cups";
            }else{
                typeOfProduct = "Drink";
            }
            try {
                switch (typeOfProduct) {
                    case "Pizza":
                        String pizzaName = product.split(",")[0];
                        Integer size = Integer.parseInt(product.split(",")[1]);
                        String base = product.split(",")[2];
                        String sauce = product.split(",")[3];
                        String note = product.split(",")[4];
                        Pizza newPizzaOrder = new Pizza(orderID, pizzaName, size, base, sauce, note);
                        SaveData.removeOrderPizza(newPizzaOrder);
                        System.out.println("Remove pizza");
                        break;
                    case "Pasta":
                        String pastaName = product.split(",")[0];
                        quantity = Integer.parseInt(product.split(",")[1]);
                        Pasta newPastaOrder  = new Pasta(orderID, pastaName, quantity);
                        SaveData.removeOrderPasta(newPastaOrder);
                        System.out.println("Remove pasta");
                        break;
                        
                    case "Cups" :                       
                        quantity = Integer.parseInt(product.split(",")[1]);
                        SaveData.removeOrderCup(orderID, quantity);
                        System.out.println("Remove cups");
                        break;
                        
                    default:
                        String drinkName = product.split(",")[0];
                        quantity = Integer.parseInt(product.split(",")[1]);
                        Drink newDrinkOrder = new Drink(orderID, drinkName, quantity);
                        SaveData.removeOrderDrink(newDrinkOrder);
                        System.out.println("Remove drink");
                        break;
                    }            
                refresh();
            } catch (SQLException ex) {
                Logger.getLogger(OrderLine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) 
    {
      label = (value == null) ? "Serve" : value.toString();
      button.setText(label);
      return button;
    }
    @Override
    public Object getCellEditorValue() 
    {
      return new String(label);
    }
 }
    private void refresh() throws SQLException{
        OrderLine ordeLine = new OrderLine();
        dispose();
        ordeLine.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOrderLine = new javax.swing.JTable();
        Refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setText("Order Line");

        tableOrderLine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Item", "Quantity", "Details", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOrderLine.getColumn("Status").setCellRenderer(new ButtonRendererDone());
        try{
            tableOrderLine.getColumn("Status").setCellEditor(new ButtonEditorDone(new JCheckBox()));
        }catch(SQLException ex){
            System.out.print(ex);
        }
        tableOrderLine.setColumnSelectionAllowed(true);
        tableOrderLine.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableOrderLine);
        tableOrderLine.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableOrderLine.getColumnModel().getColumnCount() > 0) {
            tableOrderLine.getColumnModel().getColumn(0).setResizable(false);
            tableOrderLine.getColumnModel().getColumn(1).setResizable(false);
            tableOrderLine.getColumnModel().getColumn(2).setResizable(false);
            tableOrderLine.getColumnModel().getColumn(3).setResizable(false);
            tableOrderLine.getColumnModel().getColumn(4).setResizable(false);
        }
        tableOrderLine.getColumn("Details").setCellRenderer(new ButtonRendererView());
        tableOrderLine.getColumn("Details").setCellEditor(new ButtonEditorView(new JCheckBox()));

        Refresh.setText("Refresh");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(304, 304, 304)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(369, 369, 369)
                                .addComponent(Refresh)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Refresh)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        try {
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(OrderLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RefreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderLine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderLine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderLine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderLine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new OrderLine().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(OrderLine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Refresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable tableOrderLine;
    // End of variables declaration//GEN-END:variables
}
