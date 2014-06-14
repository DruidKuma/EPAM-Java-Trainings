/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.text.logic;

import java.util.ArrayList;
import ua.epam.task.text.Sentence;
import ua.epam.task.text.Symbol;
import ua.epam.task.text.Text;
import ua.epam.task.text.Word;
import ua.epam.text.markers.PartOfSentence;

/**
 * Collection of methods for subtasks
 * @author Yuriy Miedviediev
 * @version 1.0 Build 3.05.2014
 */
public class Task {
    
    /**
     * SubTask #5
     * In every Sentence of the text swap first and last words without
     * changing the length of the sentence
     * 
     * @param text Text to work with 
     * @return  String, result text
     */
    public static String swapWords(Text text) {
        for(Sentence sentence : text.getContents()) {
            swapWordsInSentence(sentence);
        }
        return text.toString();
    }
    
    /**
     * Helper for swapping words in sentence
     * @param sentence Sentence to swap first and last words
     */
    private static void swapWordsInSentence(Sentence sentence) {
        ArrayList<PartOfSentence> contents = sentence.getContents();
        Word first = (Word) contents.get(0);
        Word last = null;

        int indexLast = contents.size() - 1;
        while(indexLast >= 0) {
            if(TextParser.checkForSyntax(contents.get(indexLast).toString())) indexLast--;
            else {
                last = (Word) contents.get(indexLast);
                break;
            }
        }
        
        //change letters to uppercase/lowercase
        char newFirstWordLetter = (Character.toString(((Symbol)first.getContents().get(0)).getContents()).toLowerCase()).charAt(0);
        char newLastWordLetter = (Character.toString(((Symbol)last.getContents().get(0)).getContents()).toUpperCase()).charAt(0);
        first.getContents().set(0, new Symbol(newFirstWordLetter));
        last.getContents().set(0, new Symbol(newLastWordLetter));
        
        contents.set(0, last);
        contents.set(indexLast, first);
    }
    
    public static void main(String[] args) {
        String actual = "This is a cute";
        Text t = new Text(actual);
        System.out.println(t);
        swapWords(t);
        System.out.println(t);
    }
}
