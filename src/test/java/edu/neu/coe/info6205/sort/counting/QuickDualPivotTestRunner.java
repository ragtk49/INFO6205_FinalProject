package edu.neu.coe.info6205.sort.counting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

@SuppressWarnings("all")
public class QuickDualPivotTestRunner {
	@Test
	public void sort4() throws Exception {
        QuickDualPivot qdp=new QuickDualPivot();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        qdp.sort(hindiWords);
        assertEquals("खईयस", hindiWords[0]);
        assertEquals("गऌफव", hindiWords[1]);
        assertEquals("टउपग", hindiWords[2]);
        assertEquals("णऊबझ", hindiWords[3]);
    }
	
	@Test
	public void lengthCheck() throws Exception{
		QuickDualPivot qdp=new QuickDualPivot();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        qdp.sort(hindiWords);
        assertEquals(4,hindiWords.length);
	}
	
	@Test
	public void invalidCase1() throws Exception{
		QuickDualPivot qdp=new QuickDualPivot();
		String[] hindiWords=new String[4];
        hindiWords[0]="3";
        hindiWords[1]="2";
        hindiWords[2]="1";
        hindiWords[3]="4";
        qdp.sort(hindiWords);
        assertEquals("1", hindiWords[0]);
	}
	
	@Test
	public void emptyString() throws Exception{
		QuickDualPivot qdp=new QuickDualPivot();
		String[] hindiWords=new String[2];
		hindiWords[0]="";
		hindiWords[1]="टउपग";
		qdp.sort(hindiWords);
		assertNotEquals("टउपग", hindiWords[0]);
		assertNotEquals("", hindiWords[1]);
	}
}
