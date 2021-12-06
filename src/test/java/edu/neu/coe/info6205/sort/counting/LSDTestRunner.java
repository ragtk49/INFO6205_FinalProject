package edu.neu.coe.info6205.sort.counting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import org.junit.Test;

@SuppressWarnings("all")
public class LSDTestRunner {
	@Test
	public void sort4() throws Exception {
        LSD lsd=new LSD();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        lsd.sort(hindiWords);
        assertEquals("खईयस", hindiWords[0]);
        assertEquals("गऌफव", hindiWords[1]);
        assertEquals("टउपग", hindiWords[2]);
        assertEquals("णऊबझ", hindiWords[3]);
    }
	
	@Test
	public void lengthCheck() throws Exception{
		LSD lsd=new LSD();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        lsd.sort(hindiWords);
        assertEquals(4,hindiWords.length);
	}
	
	@Test
	public void invalidCase1() throws Exception{
		LSD lsd=new LSD();
		String[] hindiWords=new String[4];
        hindiWords[0]="3";
        hindiWords[1]="2";
        hindiWords[2]="1";
        hindiWords[3]="";
        lsd.sort(hindiWords);
        assertNotEquals("1", hindiWords[0]);
	}
	
	public void invalidCase2() throws Exception{
		LSD lsd=new LSD();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        lsd.sort(hindiWords);
        assertNotEquals("खईयस", hindiWords[1]);
        assertNotEquals("गऌफव", hindiWords[2]);
        assertNotEquals("टउपग", hindiWords[0]);
        assertNotEquals("णऊबझ", hindiWords[3]);
	}
	
	@Test
	public void emptyString() throws Exception{
		LSD lsd=new LSD();
		String[] hindiWords=new String[2];
		hindiWords[0]="";
		hindiWords[1]="टउपग";
		lsd.sort(hindiWords);
		assertNotEquals("टउपग", hindiWords[0]);
		assertNotEquals("", hindiWords[1]);
	}
}
