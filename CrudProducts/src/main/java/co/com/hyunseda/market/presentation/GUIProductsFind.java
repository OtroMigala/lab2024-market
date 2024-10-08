/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hyunseda.market.presentation;

import co.com.hyunseda.market.service.Product;
import co.com.hyunseda.market.service.ProductService;
import co.com.hyunseda.market.service.Category;
import co.com.hyunseda.market.service.CategoryService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


/**
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class GUIProductsFind extends javax.swing.JDialog {
    private ProductService productService;
    private CategoryService categoryService;

    /**
     * Creates new form GUIProductsFind
     */
    public GUIProductsFind(java.awt.Frame parent, boolean modal, ProductService productService, CategoryService categoryService) {
        super(parent, modal);
        initComponents();
        this.productService = productService;
        this.categoryService = categoryService;
        initializeTable();
        loadCategories();
        setLocationRelativeTo(null);
    }
    
    private void initializeTable() {
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name", "Description", "Category"
                }
        ));
    }

    private void loadCategories() {
        DefaultComboBoxModel<Category> model = new DefaultComboBoxModel<>();
        model.addElement(new Category(0L, "Todas las categorías"));
        for (Category category : categoryService.findAllCategories()) {
            model.addElement(category);
        }
        cmbCategory.setModel(model);
    }

    
    
    private void fillTable(List<Product> listProducts) {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

        for (Product product : listProducts) {
            Object[] rowData = new Object[4];
            rowData[0] = product.getProductId();
            rowData[1] = product.getName();
            rowData[2] = product.getDescription();
            Category category = categoryService.findCategoryById(product.getCategoryId());
            rowData[3] = (category != null) ? category.getName() : "N/A";
            
            model.addRow(rowData);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        pnlNorth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdoId = new javax.swing.JRadioButton();
        rdoName = new javax.swing.JRadioButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnSearchAll = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Búsqueda de productos");

        pnlCenter.setLayout(new java.awt.BorderLayout());

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProducts);

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Buscar por:");
        pnlNorth.add(jLabel1);

        buttonGroup1.add(rdoId);
        rdoId.setSelected(true);
        rdoId.setText("Id");
        pnlNorth.add(rdoId);

        buttonGroup1.add(rdoName);
        rdoName.setText("Nombre del producto");
        pnlNorth.add(rdoName);

        jLabel2.setText("Categoría:");
        pnlNorth.add(jLabel2);
        pnlNorth.add(cmbCategory);

        txtSearch.setPreferredSize(new java.awt.Dimension(62, 32));
        pnlNorth.add(txtSearch);

        btnSearch.setText("Buscar");
        pnlNorth.add(btnSearch);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnSearchAll.setText("Buscar Todos");
        btnSearchAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAllActionPerformed(evt);
            }
        });
        pnlNorth.add(btnSearchAll);

        getContentPane().add(pnlNorth, java.awt.BorderLayout.PAGE_START);

        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClose);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
    String searchText = txtSearch.getText().trim();
    Category selectedCategory = (Category) cmbCategory.getSelectedItem();
    List<Product> products;

    if (rdoId.isSelected()) {
        try {
            Long id = Long.parseLong(searchText);
            Product product = productService.findProductById(id);
            products = (product != null) ? Collections.singletonList(product) : Collections.emptyList();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } else if (rdoName.isSelected()) {
        products = productService.findProductsByName(searchText);
    } else {
        products = productService.findAllProducts();
    }

    if (selectedCategory != null && selectedCategory.getCategoryId() != 0) {
        products = products.stream()
                .filter(p -> p.getCategoryId().equals(selectedCategory.getCategoryId()))
                .collect(Collectors.toList());
    }

    fillTable(products);
}


    private void btnSearchAllActionPerformed(java.awt.event.ActionEvent evt) {
        fillTable(productService.findAllProducts());
    }

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchAll;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JRadioButton rdoId;
    private javax.swing.JRadioButton rdoName;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtSearch;

    private javax.swing.JComboBox<Category> cmbCategory;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
