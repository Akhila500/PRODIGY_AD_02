import java.awt.*;
import javax.swing.*;

public class ToDoListGUI extends JFrame {
    private DefaultListModel<String> taskModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public ToDoListGUI() {
        setTitle("To-Do List App");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(224, 255, 255)); 

        
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(224, 255, 255));
        taskInput = new JTextField(20);
        taskInput.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JButton addButton = createButton("Add Task", new Color(144, 238, 144)); 

        inputPanel.add(taskInput);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.NORTH);

        
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        taskList.setBackground(new Color(245, 255, 250)); 
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(224, 255, 255));
        JButton removeButton = createButton("Remove Selected", new Color(255, 182, 193)); 
        JButton clearButton = createButton("Clear All", new Color(211, 211, 211)); 
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

    
        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                taskModel.addElement(task);
                taskInput.setText("");
            }
        });

        
        removeButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                taskModel.remove(selected);
            }
        });

        
        clearButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Clear all tasks?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskModel.clear();
            }
        });

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListGUI::new);
    }
}
