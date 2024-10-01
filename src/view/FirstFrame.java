package view;

import controller.SoundController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class FirstFrame extends JFrame{
    private final int WIDTH;
    private final int HEIGTH;
    public static JCheckBox clickMusic;
    public static JCheckBox backgroundMusic;
    public FirstFrame(int WIDTH, int HEIGTH) {
        setTitle("国际象棋"); //设置标题
        this.WIDTH = WIDTH;
        this.HEIGTH = HEIGTH;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addLabel();
        addBottom();
        addPlayerButton();
        addVisitorButton();
        addRuleButton();
        addSettingsButton();
        addCover();

        new Thread(() -> new SoundController().bgMusic()).start();
    }

    /**
     *加封面
     */
    private void addCover(){
        JLabel picture=new JLabel();
        ImageIcon img=new ImageIcon("./images/back.png");
        img.setImage(img.getImage().getScaledInstance(WIDTH,HEIGTH,Image.SCALE_DEFAULT));
        picture.setIcon(img);
        picture.setBounds(0,0,WIDTH,HEIGTH);
        this.add(picture);
    }


    /**
     * chess game的标题
     */
    private void addLabel() {
        JLabel title=new JLabel("Chess Game");
        title.setLocation(HEIGTH-630, HEIGTH / 20+100);
        title.setSize(800, 200);
        title.setFont(new Font("Rockwell", Font.BOLD, 120));
        title.setForeground(new Color(0xDC5CB4));
        title.setOpaque(false);
        add(title);
    }
    /**
     * 在游戏面板中添加标签
     */
    private void addBottom() {
        JLabel bottom=new JLabel("这是世上独一无二的高贵游戏,蕴含着丰富的想像空间,你即将进入无尽冒险的世界！");
        bottom.setLocation(50, 600);
        bottom.setSize(1000, 40);
        bottom.setFont(new Font("微软雅黑",Font.PLAIN,25));
        bottom.setForeground(new Color(0x8CF390));
        bottom.setOpaque(false);
        add(bottom);
    }

    /**
     * 游客登录
     */
    private void addVisitorButton() {
        JButton button = new JButton("游客登录");
        button.setLocation(HEIGTH - 510, HEIGTH / 10 + 300);
        button.setSize(200, 60);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(new Color(0xA368DA));
        button.setForeground(new Color(0xFCFBFA));
        Font f = new Font("宋体", Font.BOLD, 30);
        button.setFont(f);
        add(button);
        button.addActionListener(e -> {
            System.out.println("Clock Visitor Enter");
            String name="";
            JFrame game = new IntroFrame(WIDTH, HEIGTH,name);
            game.setVisible(true);
            dispose();
        });
    }

    /**
     * 玩家登录
     */
    private void addPlayerButton(){
        JButton button = new JButton("玩家登录");
        button.setLocation(HEIGTH - 210, HEIGTH / 10 + 300);
        button.setSize(200, 60);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(new Color(0xA368DA));
        button.setForeground(new Color(0xFCFBFA));
        Font f = new Font("宋体", Font.BOLD, 30);
        button.setFont(f);
        add(button);
        button.addActionListener(e -> {
            System.out.println("Clock Enter");
            Enterframe game = new Enterframe(300,200, WIDTH, HEIGTH);
            game.setVisible(true);
            dispose();
        });
    }

    /**
     * 游戏规则
     */
        private void addRuleButton() {
            JButton button = new JButton("规则");
            button.setLocation(HEIGTH - 210, HEIGTH / 10 + 400);
            button.setSize(100, 40);
//            button.setFont(new Font("Rockwell", Font.BOLD, 20));
            button.setBackground(new Color(0xA368DA));
            button.setForeground(new Color(0xFCFBFA));
            Font f = new Font("宋体", Font.BOLD, 30);
            button.setFont(f);
            add(button);
            button.addActionListener(e ->
                    JOptionPane.showMessageDialog(this, "1. 六种棋子的正常走子规则实现(6 * 2% = 12%)\n" +
                            "(1) 王：横、直、斜都可以走，但每次限走一步。\n" +
                            "(2) 后：横、直、斜都可以走，步数不受限制，但不能越子。\n" +
                            "(3) 车：横、竖均可以走，步数不受限制，不能斜走。除王车易位外不能越子。\n" +
                            "(4) 象：只能斜走。格数不限，不能越子。\n" +
                            "(5) 马：走“日”字，不受蹩腿限制。\n" +
                            "(6) 兵：只能向前直走，每次只能走一格。但走第一步时，可以走一格或两格。兵的吃\n" +
                            "子方法与行棋方向不一样，它是直走斜吃，即如果兵的斜进一格内有对方棋子，就\n" +
                            "可以吃掉它而占据该格。\n" +
                            "2. 3 种特殊走子规则的实现(3 * 4% = 12%)\n" +
                            "(1) 吃过路兵：如果对方的兵第一次行棋且直进两格，刚好形成本方有兵与其横向\n" +
                            "紧贴并列，则本方的兵可以立即斜进，把对方的兵吃掉，并视为一步棋。这个\n" +
                            "动作只能在对方兵进行了两步行棋之后立即进行，过后无效。\n" +
                            "(2) 王车易位：每局棋中，双方各有一次机会，让王朝车的方向移动两格，然后车越过\n" +
                            "王，放在与王紧邻的一格上，作为王执行的一步棋。有“长易位”和“短易位”两种。\n" +
                            "王车易位的条件（可选）：\n" +
                            "i. 王和车之间不能有棋子阻隔\n" +
                            "ii. 王不能正在被将军\n" +
                            "iii.\n" +
                            "王经过或者到达的位置受其他棋子攻击\n" +
                            "iv. 王和车不能移动过\n" +
                            "(3) 兵底线升变：本方任何一个兵直进达到对方底线时，即可升变为除“王”和“兵”以外\n" +
                            "的任何一种棋子，可升变为“后”、“车”、“马”、“象”，不能不变。\n" +
                            "3. 游戏胜负判定（4 * 4% =16%）\n" +
                            "(1) 将死：某一方的“王”被对手棋子攻击，且无法避开将军，则该方判负。\n" +
                            "(2) 和局：\n" +
                            "i. 长将和棋：某一方持续对另一方的“王”发动将军，且另一方无法避免，则\n" +
                            "判定和棋。\n" +
                            "ii. 三次重复：对于某一局面，连续重复超过 3 次，则判定和棋。\n" +
                            "iii.\n" +
                            "无子可动和棋：某方行棋时，发现没有可以移动的棋子，则判定和棋。"));
        }
    /**
     * 设置
     */
    private void addSettingsButton() {
        JButton button = new JButton("设置");
        button.setLocation(HEIGTH - 410, HEIGTH / 10 + 400);
        button.setSize(100, 40);
        button.setBackground(new Color(0xA368DA));
        button.setForeground(new Color(0xFCFBFA));
        Font f = new Font("宋体", Font.BOLD, 30);
        button.setFont(f);
        add(button);

        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(200, 200);
        jFrame.setLocation(500, 300);
        jFrame.setVisible(false);
        clickMusic = new JCheckBox("下棋音效");
        clickMusic.setSelected(true);
        clickMusic.setLocation(50,50);

         backgroundMusic= new JCheckBox("背景音乐");
        backgroundMusic.setSelected(true);
        backgroundMusic.setLocation(70,70);
//        Thread thread = new Thread(() -> new SoundController().playSound("./BGM/云水禅心.wav"));
        if (backgroundMusic.isSelected()){
            new Thread(() -> new SoundController().playSound("./BGM/云水禅心.wav")).start();}
        jFrame.add(clickMusic);
        jFrame.add(backgroundMusic);
        button.addActionListener(e -> {
            jFrame.setVisible(true);
        });
    }
}
