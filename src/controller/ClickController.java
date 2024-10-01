package controller;


import model.ChessColor;
import model.ChessComponent;
import model.KingChessComponent;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;
import view.FirstFrame;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;
    public boolean hasBlackKing=false;
    public boolean hasWhiteKing=false;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
                paintAll();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
//                first.repaint();
                ChessComponent recordFirst = first;
                cancelRP();
                first = null;
                recordFirst.repaint();
                System.out.println("cc:" + chessboard.getCurrentColor());
            } else if (handleSecond(chessComponent)) {
                //repaint in swap chess method.
                chessboard.swapChessComponents(first, chessComponent);

                if ((!isHasWhiteKing()) && (isHasBlackKing())) {
                    JOptionPane.showMessageDialog(null, "黑方胜利！", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
                }
                if ((isHasWhiteKing()) && (!isHasBlackKing())) {
                    JOptionPane.showMessageDialog(null, "白方胜利！", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
                }


                // 多线程，增加下棋时吃子的音效
                if (FirstFrame.clickMusic.isSelected()){
                new Thread(() -> new SoundController().playSound("./BGM/place.wav")).start();}
//                chessboard.add()
                chessboard.swapColor();
                cancelRP();
                first.setSelected(false);
                first = null;

                System.out.println(chessboard.PC);
                tryPC();
            }
        }
    }

    private void tryPC() {
        if(chessboard.PC && chessboard.getCurrentColor() == ChessColor.BLACK) {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++) {
                    if (chessboard.getChessComponents()[i][j].getChessColor() == ChessColor.BLACK)
                        for (int x = 0; x < 8; x++)
                            for (int y = 0; y < 8; y++)
                                if (chessboard.getChessComponents()[x][y].canMoveTo(chessboard.getChessComponents(), new ChessboardPoint(x, y))) {
                                    chessboard.swapChessComponents(chessboard.getChessComponents()[i][j], chessboard.getChessComponents()[x][y]);
                                    chessboard.swapColor();
                                }
                }
        }
    }

    private void paintAll() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard.getChessComponents()[i][j].CMT = first.canMoveTo(chessboard.getChessComponents(), new ChessboardPoint(i, j));
                chessboard.getChessComponents()[i][j].repaint();
            }
        }
    }

    private void cancelRP() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard.getChessComponents()[i][j].CMT = false;
                chessboard.getChessComponents()[i][j].repaint();
            }
        }
    }


    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }
    public boolean isHasWhiteKing(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(((chessboard.getChessComponents()[i][j] instanceof KingChessComponent)&&(chessboard.getChessComponents()[i][j].getChessColor()==ChessColor.WHITE))){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isHasBlackKing(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(((chessboard.getChessComponents()[i][j] instanceof KingChessComponent)&&(chessboard.getChessComponents()[i][j].getChessColor()==ChessColor.BLACK))){
                    return true;
                }
            }
        }
        return false;
    }

}
