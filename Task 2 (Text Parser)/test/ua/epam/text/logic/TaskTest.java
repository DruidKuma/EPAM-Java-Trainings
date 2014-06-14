/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.text.logic;

import static org.junit.Assert.*;
import org.junit.Test;
import ua.epam.task.text.Text;

/**
 *
 * @author DruidKuma
 */
public class TaskTest {

    @Test
    public void SwapWordsCorrectlySwapsFirstAndLastWordsInTheSentenceAndDontChangeSize() {
        Text actual = new Text("This is a cute implementation of the second EPAM task!");
        int initialLength = actual.getContents().size();
        String expected = "Task is a cute implementation of the second EPAM this!";
        Task.swapWords(actual);
        assertEquals("swapWords does not correctly swaps first and last words in a simple sentence", expected, actual.toString());
        assertEquals("swapWords changes size of the sentence", initialLength, actual.getContents().size());
    }
    
    @Test
    public void SwapWordsCorrectlySwapsFirstAndLastWordsInTheComplexSentenceAndDontChangeSize() {
        Text actual = new Text("This, is even, better, - implementation of the second EPAM Systems task with silly syntax?!.");
        int initialLength = actual.getContents().size();
        String expected = "Syntax, is even, better, - implementation of the second EPAM Systems task with silly this?!.";
        Task.swapWords(actual);
        assertEquals("swapWords does not correctly swaps first and last words in a simple sentence", expected, actual.toString());
        assertEquals("swapWords changes size of the sentence", initialLength, actual.getContents().size());
    }
}
