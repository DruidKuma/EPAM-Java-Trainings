/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.task.text;

import java.util.ArrayList;
import ua.epam.text.logic.TextParser;

/**
 * My representation of text
 * @author Yuriy Miedviediev
 * @version 1.0 Build 3.05.2014
 */
public class Text {
    
    private ArrayList<Sentence> contents;
    
    public Text(String text) {
        this.contents = TextParser.parseText(text);
    }
    
    public ArrayList<Sentence> getContents() {
        return this.contents;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Sentence sentence : this.contents) {
            result.append(sentence.toString());
            result.append(" ");
        }
        
        //remove last space
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }
}
