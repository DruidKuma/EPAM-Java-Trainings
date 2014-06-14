/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.task.text;

import ua.epam.text.logic.TextParser;
import ua.epam.text.markers.PartOfWord;
import ua.epam.text.markers.PartOfSentence;
import java.util.ArrayList;

/**
 * My representation of Word
 * @author Yuriy Miedviediev
 * @version 1.0 Build 3.05.2014
 */
public class Word implements PartOfSentence {
    
    //Word can consist syntax or just symbols
    private ArrayList<PartOfWord> contents;
    
    public Word(String word) {
        this.contents = TextParser.parseWord(word);
    }
    
    public ArrayList getContents() {
        return this.contents;
    }
    
    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        for(PartOfWord c : this.contents) {
            word.append(c.toString());
        }
        return word.toString();
    }
}
