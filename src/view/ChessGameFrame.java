package view;

import controller.GameController;
import model.ChessColor;
import model.ChessComponent;
import model.PlayerInformation;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static model.PlayerInformation.getWinningPercentage;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private Chessboard chessboard;
    public static GameController gameController;

    private ChessColor recordColor;


    private final int TimeLimit;
    private int T;

    //    private GameController gameController;
    private static final Color[] LABEL_COLORS = {new Color(0x9A450D), new Color(0xEABFA1), new Color(0x6A1BAD), new Color(0xE4CEF8), new Color(0x0909BB), new Color(0xB4B4D3)};
//    private GameController gameController;
    private int i;
    public static JLabel statusLabel;
    private static JLabel picture;

    private String username;
    private PlayerInformation player;


    public ChessGameFrame(int width, int height, String username) {
        setTitle("国际象棋"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;
        this.username=username;
        this.player=new PlayerInformation(username);

        this.TimeLimit = 20;


        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addLabel();
        addChessboard();
        addThemeButton();
        addStoreButton();
        addLoadButton();
        addResetButton();
        addRetractButton();
        addRankButton();
        addReturnButton();
//        addTimerLabel();
        setBack();
    }

    public void setI(int i) {
        this.i = i;
    }



    /**
     * * 设置背景图片
     * */
    public void setBack(){
        picture=new JLabel();
        ImageIcon img=new ImageIcon("./images/back1.png");
        img.setImage(img.getImage().getScaledInstance(WIDTH,HEIGTH,Image.SCALE_DEFAULT));
        picture.setIcon(img);
        picture.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
        this.add(picture);
    }


    private void addChessboard() {
        this.chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.statusLabel = statusLabel;
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        this.recordColor = chessboard.getCurrentColor();
        add(chessboard);
        String path = "./record/" + 0 + ".txt";
        File file = new File(path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            for (int i = 0; i < 9; i++) {
                fileWriter.write(gameController.getChessboardGraph().get(i) + "\n");
            }
            //将字符串写入到指定的路径下的文件中
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel("Current Player:\n" + "WHITE");
        statusLabel.setLocation(HEIGTH - 30, HEIGTH / 20);
        statusLabel.setSize(300, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
//        statusLabel.setBackground(Color.BLUE);

        add(statusLabel);
    }



    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addStoreButton() {
        JButton button = new JButton("Store");
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click store");
            if(gameController.storeChessboard())
                JOptionPane.showMessageDialog(this, "The game has been stored successfully!");
        });
    }



    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String name = JOptionPane.showInputDialog(this,"Input Path here");
            String path = "./resource/" + name + ".txt";
            gameController.loadGameFromFile(path);
            //将加载后的棋盘记录下来
            String path1 = "./record/" + 0 + ".txt";
            File file = new File(path1);
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(file);
                for (int i = 0; i < 9; i++) {
                    fileWriter.write(gameController.getChessboardGraph().get(i) + "\n");
                }
                //将字符串写入到指定的路径下的文件中
                fileWriter.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void addResetButton() {
        JButton button = new JButton("Reset");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click reset");
            remove(chessboard);
            remove(picture);
            addChessboard();
            setBack();
            repaint();
        });
    }

    private void addRetractButton() {
        JButton button = new JButton("Retract");
        button.setLocation(HEIGTH, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click retract");
            chessboard.count--;
            chessboard.loadGame(gameController.loadGameFromFile("./record/" + chessboard.count+ ".txt"));
        });
    }
    /**
     * 主题切换
     */
    private void addThemeButton(){
        JButton button = new JButton("主题");
        button.addActionListener(e-> {
//        button.setBackground(LABEL_COLORS[0+i]);
//        button.setForeground(LABEL_COLORS[1+i]);
                    Object[] options = {"经典", "棕榈", "紫藤", "鸢尾"};
                    int n = JOptionPane.showOptionDialog(this, "请选择喜欢的主题颜色：", "主题", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                    i = 2 * n;
//                    addLabel();
//                    addResetButton();
//                    addRetractButton();
//                    addThemeButton();
//                    addLoadButton();
//                    addStoreButton();
                    for (ChessComponent[] chesscomponet : chessboard.getChessComponents()) {
                        for (ChessComponent c : chesscomponet) {
                            c.setI(i);
                            c.repaint();
                        }
                    }
                });
        button.setLocation(0, 0);
        button.setSize(100, 60);
        button.setFont(new Font("黑体", Font.BOLD, 30));
        add(button);
    }

    /**
     * 排行榜
     */
    private void addRankButton(){
        JButton button = new JButton("排行榜");
        button.addActionListener(e->{
            File file=new File("./PlayerList");
            File[] files=file.listFiles();
            StringBuilder rank=new StringBuilder();
            ArrayList<String> list=new ArrayList<>();
            int n=0;
            for (File f:files) {
                String p=f.getName().replace(".txt","");
                list.add(p);
                n++;
            }
            if(list!=null){
            for (int j = 1; j < list.size(); j++) {
                for (int k = 0; k <j ; k++) {
                    if(getWinningPercentage(list.get(k))>getWinningPercentage(list.get(j))){
                        String min=list.get(j);
                        String max=list.get(k);
                        list.set(k,min);
                        list.set(j,max);
                    }
                }
            }
                for (int j = 0; j < list.size(); j++) {
                    rank.append(list.get(j));
                    rank.append(" ");
                    rank.append(getWinningPercentage(list.get(j)));
                    rank.append("\n");
                }
            }
            else rank.append("");
            JOptionPane.showMessageDialog(this,rank.toString());
                });
        button.setLocation(100, 0);
        button.setSize(150, 60);
        button.setFont(new Font("黑体", Font.BOLD, 30));
        add(button);
    }
    private void addReturnButton() {
        JButton button = new JButton("返回上一页");
        button.setLocation(HEIGTH, HEIGTH / 10 + 540);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(new Color(0x600303));
        button.setForeground(new Color(0xD05905));
        Font f=new Font("宋体",Font.BOLD,30);
        button.setFont(f);
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click Return");
            IntroFrame introFrame =new IntroFrame(WIDTH,HEIGTH,username);
            setVisible(false);
            introFrame.setVisible(true);
        });
    }

    public static Color[] COLORS() {
        return LABEL_COLORS;
    }
//    private void addTimerLabel() {
//        JLabel TimeLabel = new JLabel();
//        TimeLabel.setLocation(HEIGTH, HEIGTH / 8);
//        TimeLabel.setSize(180, 40);
//        TimeLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        TimeLabel.setForeground(Color.BLACK);
//        TimeLabel.setVisible(true);
//        add(TimeLabel);
//        T = TimeLimit + 1;
//        java.util.Timer MainTimer = new Timer();
//        TimerTask MainTimeTask = new TimerTask() {
//            @Override
//            public void run() {
//                T--;
//                if (T < 0) {
//                    T = TimeLimit;
//                    // 强制交换颜色
//                    chessboard.swapColor();
//                    recordColor = chessboard.getCurrentColor();
//                    JOptionPane.showMessageDialog(null, "时间耗尽，交换行棋方！", "Time run out", JOptionPane.WARNING_MESSAGE);
//                    System.out.println("Time run out!");
//                } else if (recordColor != chessboard.getCurrentColor()) {
//                    T = TimeLimit;
//                    recordColor = chessboard.getCurrentColor();
//                }
//                TimeLabel.setForeground((T <= 15) ? Color.RED : Color.BLACK);
//                TimeLabel.setText("Left time:"+String.valueOf(T));
//                TimeLabel.repaint();
//            }
//        };
//        MainTimer.schedule(MainTimeTask, 0, 1000);
//    }
}
