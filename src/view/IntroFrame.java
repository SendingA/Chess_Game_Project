package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static view.ChessGameFrame.gameController;

public class IntroFrame extends JFrame {
private final int WIDTH;
private final int HEIGTH;

private String username;


    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGTH() {
        return HEIGTH;
    }

    public IntroFrame(int width, int height,String username){
    this.WIDTH = width;
    this.HEIGTH = height;
    this.username=username;

    setSize(WIDTH, HEIGTH);
    setLocationRelativeTo(null); // Center the window.
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
    setLayout(null);

    addSingleButton();
    addDoubleButton();
    addReturnButton();
    addRuleButton();
    addSettingsButton();
    addCover();
}
private void addSingleButton(){
    JButton button=new JButton("人机对战");
    button.setLocation(HEIGTH-360, HEIGTH / 10+30);
    button.setSize(200, 60);
    button.setFont(new Font("Rockwell", Font.BOLD, 20));
    button.setBackground(new Color(0x600303));
    button.setForeground(new Color(0xD05905));
    Font f=new Font("宋体",Font.BOLD,30);
    button.setFont(f);
    add(button);
    button.addActionListener(e->{
        System.out.println("Click Enter");
        ChessGameFrame game=new ChessGameFrame(WIDTH,HEIGTH,username);
        setVisible(false);
        game.setVisible(true);
    });
}
    private void addDoubleButton() {
        JButton button = new JButton("双人对战");
        button.setLocation(HEIGTH-360, HEIGTH / 10 + 150);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(new Color(0x600303));
        button.setForeground(new Color(0xD05905));
        Font f=new Font("宋体",Font.BOLD,30);
        button.setFont(f);
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click DoubleMode");
            ChessGameFrame game=new ChessGameFrame(WIDTH,HEIGTH,username);
            setVisible(false);
            game.setVisible(true);
        });
    }
    private void addReturnButton() {
        JButton button = new JButton("返回上一页");
        button.setLocation(HEIGTH-360, HEIGTH / 10 + 270);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(new Color(0x600303));
        button.setForeground(new Color(0xD05905));
        Font f=new Font("宋体",Font.BOLD,30);
        button.setFont(f);
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click Return");
            FirstFrame game=new FirstFrame(WIDTH,HEIGTH);
            setVisible(false);
            game.setVisible(true);
        });
    }
    private void addRuleButton() {
        JButton button = new JButton("规则");
        button.setLocation(HEIGTH - 210, HEIGTH / 10 + 400);
        button.setSize(100, 40);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(new Color(0x600303));
        button.setForeground(new Color(0xD05905));
        Font f = new Font("宋体", Font.BOLD, 30);
        button.setFont(f);
        add(button);
        button.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Hello, world!"));
    }
    /**
     * 设置
     */
    private void addSettingsButton() {
        JButton button = new JButton("设置");
        button.setLocation(HEIGTH - 410, HEIGTH / 10 + 400);
        button.setSize(100, 40);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(new Color(0x600303));
        button.setForeground(new Color(0xD05905));
        Font f = new Font("宋体", Font.BOLD, 30);
        button.setFont(f);
        add(button);
        button.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Hello, world!"));
    }
private void addCover(){
 JLabel picture=new JLabel();
 ImageIcon img=new ImageIcon("./images/Cover.png");
 img.setImage(img.getImage().getScaledInstance(WIDTH,HEIGTH,Image.SCALE_DEFAULT));
 picture.setIcon(img);
 picture.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
 this.add(picture);
}
}
