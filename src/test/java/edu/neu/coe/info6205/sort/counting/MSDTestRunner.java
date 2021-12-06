package edu.neu.coe.info6205.sort.counting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

@SuppressWarnings("all")
public class MSDTestRunner {
	@Test
	public void sort4() throws Exception {
        MSD msd=new MSD();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        msd.sort(hindiWords);
        assertEquals("खईयस", hindiWords[0]);
        assertEquals("गऌफव", hindiWords[1]);
        assertEquals("टउपग", hindiWords[2]);
        assertEquals("णऊबझ", hindiWords[3]);
    }
	
	@Test
	public void lengthCheck() throws Exception{
		MSD msd=new MSD();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        msd.sort(hindiWords);
        assertEquals(4,hindiWords.length);
	}
	
	@Test
	public void emptyString() throws Exception{
		MSD msd=new MSD();
		String[] hindiWords=new String[2];
		hindiWords[0]="";
		hindiWords[1]="टउपग";
		msd.sort(hindiWords);
		assertNotEquals("टउपग", hindiWords[0]);
		assertNotEquals("", hindiWords[1]);
	}
}
