package co.com.hyunseda.market.presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import co.com.hyunseda.market.service.Category;
import co.com.hyunseda.market.service.CategoryService;

public class GUICategories extends JDialog {
    private CategoryService categoryService;
    private JTextField txtCategoryName;
    private JList<Category> listCategories;
    private DefaultListModel<Category> listModel;

    public GUICategories(JFrame parent, CategoryService categoryService) {
        super(parent, "Gestionar Categorías", true);
        this.categoryService = categoryService;
        initComponents();
        loadCategories();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel para crear categoría
        JPanel createPanel = new JPanel(new FlowLayout());
        txtCategoryName = new JTextField(20);
        JButton btnAddCategory = new JButton("Añadir Categoría");
        createPanel.add(new JLabel("Nombre:"));
        createPanel.add(txtCategoryName);
        createPanel.add(btnAddCategory);
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(txtCategoryName);
        inputPanel.add(btnAddCategory);

        // Lista de categorías
        listModel = new DefaultListModel<>();
        listCategories = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listCategories);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnEdit = new JButton("Editar");
        JButton btnDelete = new JButton("Eliminar");
        JButton btnClose = new JButton("Cerrar");
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClose);

        // Añadir componentes al frame
        add(createPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(listCategories), BorderLayout.CENTER);

        // Acción para añadir categoría
        btnAddCategory.addActionListener(e -> {
            String name = txtCategoryName.getText().trim();
            if (!name.isEmpty()) {
                categoryService.saveCategory(name);
                loadCategories();
                txtCategoryName.setText("");
            }
        });

        // Acción para editar categoría
        btnEdit.addActionListener(e -> {
            Category selected = listCategories.getSelectedValue();
            if (selected != null) {
                String newName = JOptionPane.showInputDialog(this, "Nuevo nombre:", selected.getName());
                if (newName != null && !newName.isEmpty()) {
                    selected.setName(newName);
                    categoryService.editCategory(selected.getCategoryId(), selected);
                    loadCategories();
                }
            }
        });

        // Acción para eliminar categoría
        btnDelete.addActionListener(e -> {
            Category selected = listCategories.getSelectedValue();
            if (selected != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar esta categoría?");
                if (confirm == JOptionPane.YES_OPTION) {
                    categoryService.deleteCategory(selected.getCategoryId());
                    loadCategories();
                }
            }
        });

        // Acción para cerrar
        btnClose.addActionListener(e -> dispose());

        setSize(400, 300);
        setLocationRelativeTo(getParent());
    }

    private void loadCategories() {
        listModel.clear();
        for (Category category : categoryService.findAllCategories()) {
            listModel.addElement(category);
        }
    }
}