package com.board.methods;

import com.board.domain.board.Board;

public class BoardTestMethods {

    public static String BOARD_TITLE ="TITLE";
    public static String BOARD_CONTENT ="CONTENT";
    public static String BOARD_AUTHOR = "AUTHOR";

    public static Board createBoard(){
        return Board.builder()
                .author(BOARD_AUTHOR)
                .title(BOARD_TITLE)
                .content(BOARD_CONTENT)
                .build();
    }

}
