package tw.core;


import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {
    private RandomIntGenerator randomIntGenerator;

    @Before
    public void setUp() throws Exception {
        this.randomIntGenerator = new RandomIntGenerator();
    }

    @Test
    public void should_get_4_digits_numstr_and_every_digit_less_than_10() throws Exception {
        //given
        //when
        String numStr = randomIntGenerator.generateNums(9, 4);
        //then
        assertThat(numStr.length(), is(7));

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_dufutmax_less_than_numbersOfNeed() throws Exception {

        //given
        //when
        String numStr = randomIntGenerator.generateNums(4, 5);
    }

}