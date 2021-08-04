package com.board.dummies;


import com.board.dummies.Dummy;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LombokTest {

    @Test
    public void test(){
        //given
        Dummy dummy = new Dummy();
        String name = "chan";

        //when
        dummy.setName(name);

        //then
        assertThat(dummy.getName()).isEqualTo(name);
    }
}
