package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;
import tw.views.GameView;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    @Mock
    private GameView mockGameView;
    @Mock
    private InputCommand mockCommand;
    @Mock
    private AnswerGenerator mockGenerator;
    @Mock
    private Game game;

    private Answer correctAnswer;
    private Answer errorAnswer;
    private GameController gameController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        correctAnswer = Answer.createAnswer("1 2 3 4");
        errorAnswer = Answer.createAnswer("1 2 5 6");

//        when(mockGenerator.generate()).thenReturn(correctAnswer);
        gameController = new GameController(game, mockGameView);
    }

    @Test
    public void should_return_begin_game() throws Exception {
        gameController.beginGame();
        verify(mockGameView, times(1)).showBegin();//查看我们到底有没有调用过mockGameView的showBegin方法
    }

    @Test
    public void should_start_play_game_success() throws Exception {

        //given
        when(game.checkCoutinue()).thenReturn(true, false);
        when(mockCommand.input()).thenReturn(correctAnswer);
        when(game.guessHistory()).thenReturn(new ArrayList<>());
        when(game.checkStatus()).thenReturn("");
        when(game.guess(errorAnswer)).thenReturn(new GuessResult("", errorAnswer));
        GameController gameController = new GameController(game, mockGameView);

        //when
        gameController.play(mockCommand);

        //then
        verify(mockGameView).showGuessResult(any());
        System.out.println(mockGenerator.generate());
        verify(mockGameView).showGuessHistory(anyList());
        verify(mockGameView).showGameStatus(anyString());
    }

    @Test
    public void should_start_play_game_fail() throws IOException {
        //given
        when(mockCommand.input()).thenReturn(errorAnswer);
        when(game.checkStatus()).thenReturn("");
        when(game.checkCoutinue()).thenReturn(false);
        GameController gameController = new GameController(game, mockGameView);

        //when
        gameController.play(mockCommand);

        //then
        verify(mockGameView).showGameStatus(anyString());
    }

}