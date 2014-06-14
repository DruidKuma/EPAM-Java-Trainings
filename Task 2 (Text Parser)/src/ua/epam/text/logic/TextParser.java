
package ua.epam.text.logic;

import ua.epam.text.markers.PartOfWord;
import ua.epam.text.markers.PartOfSentence;
import java.util.ArrayList;
import java.util.regex.Pattern;
import ua.epam.task.text.Sentence;
import ua.epam.task.text.Symbol;
import ua.epam.task.text.Syntax;
import ua.epam.task.text.Word;

/**
 * Parser for text, sentences and words
 * @author Yuriy Miedviediev
 * @version 1.0 Build 3.05.2014
 */
public class TextParser {
    
    /**
     * Split given text into sentences
     * @param text String, text to split
     * @return ArrayList of sentences
     */
    public static ArrayList<Sentence> parseText(String text) {
        ArrayList<Sentence> parsed = new ArrayList();
        String clearText = text.replaceAll("\\s{1,}", " ");
        String[] sentences = clearText.split("(?<=[.?!])\\s+(?=[a-zA-Z])");
        for(String sentence : sentences) {
            parsed.add(new Sentence(sentence));
        }
        return parsed;
    }
    
    /**
     * Split given sentence into words and syntactical symbols
     * @param sentence sentence to split
     * @return ArrayList of words and syntax
     */
    public static ArrayList<PartOfSentence> parseSentence(String sentence) {
        ArrayList<PartOfSentence> parsed = new ArrayList();
        
        //First split into parts by spaces
        String[] pieces = sentence.split(" ");
        for(String piece : pieces) {
            
            int length = piece.length();
            
            //if at the end there is no syntax
            if(!checkForSyntax(Character.toString(piece.charAt(length-1)))) {
                parsed.add(new Word(piece));
            }
            
            //if parser found syntax and length of the piece is one, then the whole piece is syntax
            else if(length <= 1) {
                parsed.add(new Syntax(piece.charAt(0)));
            }
            
            //if there is syntax
            else {
                
                //find end of last word
                int lastSyntax = length - 1;
                while(lastSyntax >= 0) {
                    if(checkForSyntax(Character.toString(piece.charAt(lastSyntax)))) lastSyntax--;
                    else break;
                }
                //add word
                parsed.add(new Word(piece.substring(0, lastSyntax+1)));
                
                //add every syntax after last word
                for(int pos = lastSyntax+1; pos < length; pos++) {
                    parsed.add(new Syntax(piece.charAt(pos)));
                }
            }
        }
        return parsed;
    }
    
    /**
     * Split given word into symbols and syntax
     * @param word word to split 
     * @return ArrayList of contents of the word
     */
    public static ArrayList<PartOfWord> parseWord(String word) {
        ArrayList<PartOfWord> parsed = new ArrayList();
        for(char c : word.toCharArray()) {
            if(checkForSyntax(Character.toString(c))) parsed.add(new Syntax(c));
            else parsed.add(new Symbol(c));
        }
        return parsed;
    }
    
    /**
     * Check the current char for being syntax
     * @param s string or char to check
     * @return true if given char is syntax, false otherwise
     */
    public static boolean checkForSyntax(String s) {
        return Pattern.matches("\\p{Punct}", s);
    }
}
