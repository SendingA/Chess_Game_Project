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
 * 这个类表示国际象棋里面的兵
 */
public class PawnChessComponent extends ChessComponent {
    /**
     * 黑兵和白兵的图片，static使得其可以被所有兵对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;

    /**
     * 兵棋子对象自身的图片，是上面两种中的一种
     */
    private Image pawnImage;

    /**
     * 读取加载兵棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
//        if (KING_WHITE == null) {
        PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
//        }
//方便更换皮肤
//        if (KING_BLACK == null) {
        PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
//        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定kingImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        if (getChessColor() == ChessColor.WHITE)
            name = 'p';
        else
            name = 'P';
        initiatePawnImage(color);
    }

    /**
     * 兵棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 兵棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (chessComponents[destination.getX()][destination.getY()].chessColor==this.chessColor){
            return false;
        }
        if (getChessColor()==ChessColor.BLACK){
            if (destination.getX()<=source.getX()){
                return false;
            }
            if(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent){
                if((source.getX()==1)&&(source.getY()==destination.getY())&&(destination.getX()==3)&&(chessComponents[2][destination.getY()] instanceof EmptySlotComponent)) {
                    return true;
                }
                if((destination.getX()==source.getX()+1)&&(source.getY()==destination.getY())){
                    return true;
                }
            }if(chessComponents[destination.getX()][destination.getY()].getChessColor()==ChessColor.WHITE){
                if((destination.getX()==source.getX()+1)&&Math.abs(destination.getY()-source.getY())==1){
                    return true;
                }
            }
        }
        if (getChessColor()==ChessColor.WHITE){
            if (destination.getX()>=source.getX()){
                return false;
            }
            if(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent){
                if((source.getX()==6)&&(source.getY()==destination.getY())&&(destination.getX()==4)&&(chessComponents[5][source.getY()] instanceof EmptySlotComponent)) {
                    return true;
                }
                if((destination.getX()==source.getX()-1)&&(source.getY()==destination.getY())){
                    return true;
                }
            }if(chessComponents[destination.getX()][destination.getY()].getChessColor()==ChessColor.BLACK){
                if((destination.getX()==source.getX()-1)&&Math.abs(destination.getY()-source.getY())==1){
                    return true;
                }
            }
        }
        return false;
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
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
