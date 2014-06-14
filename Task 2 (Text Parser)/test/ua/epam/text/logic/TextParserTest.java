/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.text.logic;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ua.epam.task.text.Sentence;
import ua.epam.task.text.Text;
import ua.epam.task.text.Word;

/**
 * Tests for TextParser
 * @author Yuriy Miedviediev
 * @version 1.0 Build 3.05.2014
 */
public class TextParserTest {
    
    public ArrayList<String> textExamples;
    
    @Before
    public void setUp() {
        textExamples = new ArrayList();
        textExamples.add("This is just a simple one sentenced text.");
        textExamples.add("This is two-sentenced text with a few syntax. For example, in this sentence there is a comma and - here is a slash.");
        textExamples.add("This is text with many exclamation marks in the end!!!");
        textExamples.add("This is a complex text with many sentences and syntax. What do you think about Java? Do you think Java is Object-Oriented programming language?! Yes, you are definitely right!!! Moreover,- Java is the best programming language in the whole world...");
    }

    @Test
    public void ParseTextCorreclyParsesSimpleOneSentencedText() {
        Text result = new Text(textExamples.get(0));
        assertEquals("parseText does not split one-sentenced text into just one sentence", 1, result.getContents().size());
        assertEquals("parseText misses symbols while parsing simple sentence", "This is just a simple one sentenced text.", result.getContents().get(0).toString());
    }
    
    @Test
    public void ParseTextCorrectlyParsesMoreComplexText() {
        Text result = new Text(textExamples.get(1));
        assertEquals("parseText does not split two-sentenced text into two sentences", 2, result.getContents().size());
        assertEquals("parseText misses symbols while parsing more complex sentence", "This is two-sentenced text with a few syntax.", result.getContents().get(0).toString());
    }
    
    @Test
    public void ParseTextCorreclyParsesTextWithManySentences() {
        Text result = new Text(textExamples.get(3));
        assertEquals("parseText does not split complex text into sentences", 5, result.getContents().size());
        assertEquals("parseText misses symbols while parsing complex sentence", "What do you think about Java?", result.getContents().get(1).toString());
    }

    @Test
    public void ParseSentenceCorrectlyParsesSimpleSentence() {
        Sentence result = new Sentence(textExamples.get(0));
        assertEquals("parseSentence incorrectly splits simple sentence", 9, result.getContents().size());
        assertEquals("parseSentence misses symbols while parsing simple sentence", ".", result.getContents().get(result.getContents().size()-1).toString());
    }
    
    @Test
    public void ParseSentenceCorrectlyParsesMoreComplexSentence() {
        Sentence result = new Sentence(textExamples.get(2));
        assertEquals("parseSentence incorrectly splits sentence with many syntax in the end", 13, result.getContents().size());
        assertEquals("parseSentence misses symbols while parsing more complex sentence", "!", result.getContents().get(result.getContents().size()-1).toString());
    }
    
    @Test
    public void ParseSentenceCorrectlyParsesComplexSentence() {
        Sentence result = new Sentence("Moreover, - Java is the best programming language in the whole world...");
        assertEquals("parseSentence incorrectly splits sentence with many syntax in the end", 16, result.getContents().size());
        assertEquals("parseSentence misses symbols while parsing complex sentence", "Moreover, - Java is the best programming language in the whole world...", result.toString());
    }

    @Test
    public void ParseWordCorrectlyParsesSimpleWord() {
        Word result = new Word("simple");
        assertEquals("parseWord incorrectly splits simple word", 6, result.getContents().size());
    }
    
    @Test
    public void ParseWordCorrectlyParsesComplexWord() {
        Word result = new Word("object-oriented");
        assertEquals("parseWord incorrectly splits complex word", 15, result.getContents().size());
        assertEquals("parseWord misses symbols", "object-oriented", result.toString());
    }  
}
