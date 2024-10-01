package view;

import model.PlayerInformation;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;

    private final int NW;
    private final int NH;

    public RegisterFrame (int WIDTH, int HEIGTH, int NW, int NH) {
        setTitle("用户注册"); //设置标题
        this.WIDTH = WIDTH;
        this.HEIGTH = HEIGTH;

        this.NW = NW;
        this.NH = NH;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        JLabel user=new JLabel("设置新用户名");
        user.setBounds(10,10,80,25);
        add(user);
        user.setVisible(true);
        JTextField userText=new JTextField();
        userText.setBounds(10,36,165,25);
        add(userText);
        userText.setVisible(true);
        JLabel passWord=new JLabel("设置新密码");
        passWord.setBounds(10,70,80,25);
        add(passWord);
        passWord.setVisible(true);
        JPasswordField passWordText=new JPasswordField();
        passWordText.setBounds(10,96,165,25);
        add(passWordText);
        passWordText.setVisible(true);
        JLabel passWord1=new JLabel("确认你的密码");
        passWord1.setBounds(10,130,80,25);
        add(passWord1);
        passWord1.setVisible(true);
        JPasswordField passWordText1=new JPasswordField();
        passWordText1.setBounds(10,156,165,25);
        add(passWordText1);
        passWordText1.setVisible(true);

        JButton button1=new JButton("确认");
        button1.setBounds(130,195,60,25);
        button1.addActionListener((e) -> {
            String name=userText.getText();
            String p=passWordText.getText();
            String q=passWordText1.getText();
            if(name==null||p==null||q==null){
                JOptionPane.showMessageDialog(this, "请完善你的用户再点击确认！");
            }
            else if(hasName(name)){
                JOptionPane.showMessageDialog(this, "该用户名已被使用，请重新填写");
            }
            else if(p.equals(q)){
                //存储
                String path = "./PlayerList/"+name+".txt";
                File file = new File(path);
                FileWriter fileWriter;
                PlayerInformation player=new PlayerInformation(name,p,0,0,0);
                    try {
                        fileWriter = new FileWriter(file);
                            fileWriter.write(player.toString());
                        //将字符串写入到指定的路径下的文件中
                        fileWriter.close();
                    } catch (IOException a) {
                        a.printStackTrace();
                    }

                JOptionPane.showMessageDialog(this, "设置成功！");
                IntroFrame game = new IntroFrame(NW, NH,name);
                game.setVisible(true);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,"两次输入密码不符！");
            }
        });
        add(button1);

    }
    public static boolean hasName(String name){
        String target=name+".txt";
        File file=new File("./PlayerList");
        File[] files=file.listFiles();
        if(files!=null){
        for (File f:files) {
            if(f.getName().equals(target)){
            return true;
        }}
        return false;}
        else return false;
    }
}
