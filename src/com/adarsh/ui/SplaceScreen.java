package com.adarsh.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image; // Import java.awt.Image

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class SplaceScreen extends JWindow {
    
    JProgressBar progressBar;
    
    // --- THEME COLORS ---
    Color bgDark = new Color(44, 62, 80);       // Dark Blue Background
    Color accentColor = new Color(230, 126, 34); // Orange Accent

    SplaceScreen() {
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(bgDark);
        setContentPane(contentPanel);

        // --- LOGO RESIZING SECTION ---
        
        // 1. Load the original image
        ImageIcon originalIcon = new ImageIcon("images/vote_logo.png");
        
        // 2. Define desired dimensions (Adjust these numbers to fit your needs)
        int targetWidth = 400;
        int targetHeight = 300;
        
        // 3. Get the image instance and scale it
        // SCALE_SMOOTH gives the best quality result
        Image scaledImage = originalIcon.getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        
        // 4. Create a new ImageIcon from the scaled image
        ImageIcon finalIcon = new ImageIcon(scaledImage);
        
        // 5. Put the final icon into the label
        JLabel lb = new JLabel(finalIcon);
        // --- END LOGO RESIZING SECTION ---
        
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(lb, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(bgDark);
        bottomPanel.setBorder(new EmptyBorder(0, 0, 50, 0)); // Add padding at bottom so it's not stuck to edge

        JLabel loadingText = new JLabel("Loading System Modules...", SwingConstants.CENTER);
        loadingText.setForeground(Color.WHITE);
        loadingText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loadingText.setBorder(new EmptyBorder(0, 0, 10, 0)); // Space between text and bar
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(accentColor);
        progressBar.setBackground(new Color(236, 240, 241));
        progressBar.setBorderPainted(false);
        progressBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // Use BasicUI to remove the default 3D look (makes it flat)
        progressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected Color getSelectionBackground() { return Color.BLACK; }
            @Override
            protected Color getSelectionForeground() { return Color.BLACK; }
        });

        // Wrapper panel to squeeze the progress bar (so it isn't full width)
        JPanel barContainer = new JPanel(new BorderLayout());
        barContainer.setBackground(bgDark);
        barContainer.setBorder(new EmptyBorder(0, 100, 0, 100)); // 100px padding on Left/Right
        barContainer.add(progressBar, BorderLayout.CENTER);

        // Assemble Bottom Panel
        bottomPanel.add(loadingText, BorderLayout.NORTH);
        bottomPanel.add(barContainer, BorderLayout.SOUTH);

        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        loadProgress();
    }

    Timer timer;
    void loadProgress() {
        timer = new Timer(70, e -> {
            int val = progressBar.getValue();
            if (val < 100) {
                progressBar.setValue(val + 1);
                progressBar.setString(val + "%");
                
            } else {
                timer.stop();
                dispose();
                // Open Login Screen
                Login login = new Login(); // Commented out for compilation testing
                login.setVisible(true);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SplaceScreen splaceScreen = new SplaceScreen();
        splaceScreen.setVisible(true);
    }
}