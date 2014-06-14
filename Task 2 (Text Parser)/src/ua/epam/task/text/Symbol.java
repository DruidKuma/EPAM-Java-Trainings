/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.task.text;

import ua.epam.text.markers.PartOfWord;

/**
 * Symbol for representing a letter,
 * just a char that can be either a-z or A-Z, or 0-9
 * 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 3.05.2014
 */
public class Symbol implements PartOfWord {
    
    private char letter;
    
    public Symbol(char s) {
        this.letter = s;
    }
    
    public char getContents() {
        return this.letter;
    }
    
    @Override
    public String toString() {
        return Character.toString(this.letter);
    }
}
