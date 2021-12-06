package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.huskySort.sort.huskySort.MergeHuskySort;
import edu.neu.coe.info6205.sort.huskySort.sort.huskySort.PureHuskySort;

public class HuskySortImplementation {
	
	/**
	 * Executes the Merge Husky sort implementation
	 * @param arr String[] that has to be sorted
	 */
	public void mergeSort(String[] arr) {
		HindiWordsCoder hindiWordsCoder=new HindiWordsCoder();
		MergeHuskySort<String> mhs=new MergeHuskySort<>(hindiWordsCoder);
		mhs.sort(arr);
	}
	
	/**
	 * Executes the Pure Husky sort implementation
	 * @param arr String[] that has to be sorted
	 */
	public void pureSort(String[] arr) {
		HindiWordsCoder hindiWordsCoder=new HindiWordsCoder();
		PureHuskySort<String> phs=new PureHuskySort<>(hindiWordsCoder, false, true);
		phs.sort(arr);
	}
	
}
