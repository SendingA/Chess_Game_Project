package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示国际象棋里面的马
 */
public class KnightChessComponent extends ChessComponent {
    /**
     * 黑马和白马的图片，static使得其可以被所有马对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image KNIGHT_WHITE;
    private static Image KNIGHT_BLACK;

    /**
     * 马棋子对象自身的图片，是上面两种中的一种
     */
    private Image knightImage;

    /**
     * 读取加载王棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
//        if (KING_WHITE == null) {
        KNIGHT_WHITE = ImageIO.read(new File("./images/knight-white.png"));
//        }
//方便更换皮肤
//        if (KING_BLACK == null) {
        KNIGHT_BLACK = ImageIO.read(new File("./images/knight-black.png"));
//        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定knightImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                knightImage = KNIGHT_WHITE;
            } else if (color == ChessColor.BLACK) {
                knightImage = KNIGHT_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        if (getChessColor() == ChessColor.WHITE)
            name = 'n';
        else
            name = 'N';
        initiateKnightImage(color);
    }

    /**
     * 马棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 马棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (chessComponents[destination.getX()][destination.getY()].chessColor==this.chessColor){
            return false;
        }
        if (((Math.abs(source.getX() - destination.getX()) == 2) && (Math.abs(source.getY() - destination.getY()) == 1)) || ((Math.abs(source.getY() - destination.getY()) == 2) && (Math.abs(source.getX() - destination.getX()) == 1))) {
            return true;
        } else {
            return false;
        }
    }
    public List<ChessboardPoint> canMoveToList(ChessComponent[][] chessComponents) {
        ArrayList<ChessboardPoint> canMoveToList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(canMoveTo(chessComponents,new ChessboardPoint(i,j))){
                    canMoveToList.add(new ChessboardPoint(i,j));
                }
            }
        }
        return canMoveToList;
    }
        /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(knightImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
