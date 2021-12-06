package edu.neu.coe.info6205.sort.counting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class HuskySortTestRunner {
	@Test
	public void sort4() throws Exception {
        HuskySortImplementation husky=new HuskySortImplementation();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        husky.mergeSort(hindiWords);
        assertEquals("खईयस", hindiWords[0]);
        assertEquals("गऌफव", hindiWords[1]);
        assertEquals("टउपग", hindiWords[2]);
        assertEquals("णऊबझ", hindiWords[3]);
    }
	
	@Test
	public void lengthCheck() throws Exception{
		HuskySortImplementation husky=new HuskySortImplementation();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        husky.mergeSort(hindiWords);
        assertEquals(4,hindiWords.length);
	}
	
	@Test
	public void sort4Pure() throws Exception {
        HuskySortImplementation husky=new HuskySortImplementation();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        husky.pureSort(hindiWords);
        assertEquals("खईयस", hindiWords[0]);
        assertEquals("गऌफव", hindiWords[1]);
        assertEquals("टउपग", hindiWords[2]);
        assertEquals("णऊबझ", hindiWords[3]);
    }
	
	@Test
	public void lengthCheckPure() throws Exception{
		HuskySortImplementation husky=new HuskySortImplementation();
        String[] hindiWords=new String[4];
        hindiWords[0]="गऌफव";
        hindiWords[1]="खईयस";
        hindiWords[2]="टउपग";
        hindiWords[3]="णऊबझ";
        husky.pureSort(hindiWords);
        assertEquals(4,hindiWords.length);
	}
}
