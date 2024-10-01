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
public class BishopChessComponent extends ChessComponent {
    /**
     * 黑象和白象的图片，static使得其可以被所有象对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;

    /**
     * 象棋子对象自身的图片，是上面两种中的一种
     */
    private Image bishopImage;

    /**
     * 读取加载象棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
//        if (KING_WHITE == null) {
        BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
//        }
//方便更换皮肤
//        if (KING_BLACK == null) {
        BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
//        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定bishopImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        if (getChessColor() == ChessColor.WHITE)
            name = 'b';
        else
            name = 'B';
        initiateBishopImage(color);
    }

    /**
     * 象棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 象棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int dx=destination.getX()-source.getX();
        int dy=destination.getY()-source.getY();
        if (chessComponents[destination.getX()][destination.getY()].chessColor==this.chessColor){
            return false;
        }
        if (Math.abs(dx) != Math.abs(dy)) {
            return false;
        } else {
            for (int i = 1; i < Math.abs(dx); i++) {
                if((source.getX()<destination.getX())&&(source.getY()<destination.getY())){
                    if(!(chessComponents[source.getX()+i][source.getY()+i] instanceof EmptySlotComponent)){
                        return false;
                    }
                }else if((source.getX()<destination.getX())&&(source.getY()>destination.getY())){
                    if(!(chessComponents[source.getX()+i][source.getY()-i] instanceof EmptySlotComponent)){
                        return false;
                    }
                }else if((source.getX()>destination.getX())&&(source.getY()>destination.getY())){
                    if(!(chessComponents[source.getX()-i][source.getY()-i] instanceof EmptySlotComponent)){
                        return false;
                    }
                }else {
                    if(!(chessComponents[source.getX()-i][source.getY()+i] instanceof EmptySlotComponent)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
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
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
