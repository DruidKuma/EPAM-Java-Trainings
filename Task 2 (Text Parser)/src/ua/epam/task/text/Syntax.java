/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.task.text;

import ua.epam.text.markers.PartOfWord;
import ua.epam.text.markers.PartOfSentence;

/**
 * My representation of syntax
 * @author Yuriy Miedviediev
 * @version 1.0 Build 3.05.2014
 */
public class Syntax implements PartOfSentence, PartOfWord {
    
    private char contents;
    
    public Syntax(char syntax) {
        this.contents = syntax;
    }
    
    public char getContents() {
        return this.contents;
    }
    
    @Override
    public String toString() {
        return Character.toString(this.contents);
    }
}
