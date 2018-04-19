package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.model.Record;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    private Answer actualAnswer;

    @Before
    public void setUp() throws Exception {
        actualAnswer = Answer.createAnswer("1 2 3 4");
    }


    @Test
    public void should_return_0A0B_when_no_number_is_correct() {
        //when
        String inputAnswerStr = "5 6 7 8";
        String expectValue = "0A0B";

        //then
        validateGuessNumber(inputAnswerStr, expectValue);
    }

    @Test
    public void should_return_4A0B_when_1_is_correct() {
        //given
        String inputAnswerStr = "1 2 3 4";
        String expectValue = "4A0B";

        //then
        validateGuessNumber(inputAnswerStr, expectValue);
    }

    @Test
    public void should_return_0A4B_when_2_and_4_are_include() {
        //when
        String inputAnswerStr = "4 3 2 1";
        String expectValue = "0A4B";
        //then
        validateGuessNumber(inputAnswerStr, expectValue);
    }


    @Test
    public void should_return_2A2B_when_1_3_are_correct_and_4_2_are_include() {
        //when
        String inputAnswerStr = "1 5 8 2";
        String expectValue = "1A1B";
        //then
        validateGuessNumber(inputAnswerStr, expectValue);
    }

    private void validateGuessNumber(String inputAnswerStr, String expectValue) {
        Answer inputAnswer = Answer.createAnswer(inputAnswerStr);

        //when
        Record result = actualAnswer.check(inputAnswer);

        //then
        String actualResult = result.getValue()[0] + "A" + result.getValue()[1] + "B";
        assertThat(actualResult, is(expectValue));
    }

}