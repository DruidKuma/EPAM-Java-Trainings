/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.task.text;

import ua.epam.text.logic.TextParser;
import ua.epam.text.markers.PartOfSentence;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * My representation of Sentence
 * @author Yuriy Miedviediev
 * @version 1.0 Build 3.05.2014
 */
public class Sentence {
    
    //Sentence can consist words and syntax
    private ArrayList<PartOfSentence> contents;
    
    public Sentence(String sentence) {
        this.contents = TextParser.parseSentence(sentence);
    }
    
    public ArrayList<PartOfSentence> getContents() {
        return this.contents;
    }
    
    @Override
    public String toString() {
        StringBuilder sentence = new StringBuilder();
        for(PartOfSentence part : this.contents) {
            if(!Pattern.matches("[.?!/,:;]", part.toString())) {
                sentence.append(" ");
            }
            sentence.append(part.toString());
        }
        
        //Delete first unnecessary space
        sentence.deleteCharAt(0);
        return sentence.toString();
    }
}
