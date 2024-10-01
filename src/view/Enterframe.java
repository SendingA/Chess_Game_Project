package view;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static view.RegisterFrame.hasName;

public class Enterframe extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;

    private final int NW;
    private final int NH;


    public Enterframe(int WIDTH, int HEIGTH, int NW, int NH) throws HeadlessException {
        setTitle("用户登录"); //设置标题
        this.WIDTH = WIDTH;
        this.HEIGTH = HEIGTH;

        this.NW = NW;
        this.NH = NH;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        JLabel user=new JLabel("用户名");
        user.setBounds(10,10,80,25);
        add(user);
        JTextField userText=new JTextField();
        userText.setBounds(10,36,165,25);
        add(userText);
        JLabel passWord=new JLabel("密码");
        passWord.setBounds(10,70,80,25);
        add(passWord);
        JPasswordField passWordText=new JPasswordField();
        passWordText.setBounds(10,96,165,25);
        add(passWordText);
/**
 * 登录按钮及界面跳转
 */
        JButton button1=new JButton("登录");
        button1.setBounds(25,130,60,25);
        add(button1);
            button1.addActionListener((e) -> {
                String name=userText.getText();
                String p=passWordText.getText();
                if(hasName(name)){
                    if(isCorrectPassword(name,p)){
                    JOptionPane.showMessageDialog(this, "成功！");
                IntroFrame game = new IntroFrame(NW, NH,name);
                game.setVisible(true);
                dispose();
                }
                    else{JOptionPane.showMessageDialog(this,"用户名或密码错误！");}
                    }
                else{
                    JOptionPane.showMessageDialog(this,"该用户名不存在，请创建！");
                }
            });
        /**
         * 注册按钮
         */
        JButton button2=new JButton("注册");
        button2.setBounds(105,130,60,25);
        add(button2);
        button2.addActionListener((e) -> {
            RegisterFrame register=new RegisterFrame(300,280,NW,NH);
            register.setVisible(true);
            dispose();
        });

    }
   public boolean isCorrectPassword(String name,String password){
        String path="./PlayerList/"+name+".txt";
        File file=new File(path);
        FileReader r;
        try{
            r=new FileReader(file);
            BufferedReader br=new BufferedReader(r);
            String line=br.readLine();
            if(line.equals(password)){
                return true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
   }
}
