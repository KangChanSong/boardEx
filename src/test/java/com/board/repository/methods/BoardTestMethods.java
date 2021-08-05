package com.board.repository.methods;

import com.board.domain.Board;

public class BoardTestMethods {

    public static String BOARD_TITLE ="TITLE";
    public static String BOARD_CONTENT ="CONTENT";
    public static String BOARD_AUTHOR = "AUTHOR";

    public static Board createBoard(){
        Board board = new Board();
        board.setTitle(BOARD_TITLE);
        board.setContent(BOARD_CONTENT);
        board.setAuthor(BOARD_AUTHOR);

        return board;
    }

}
