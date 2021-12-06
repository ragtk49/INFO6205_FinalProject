package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.huskySort.sort.huskySortUtils.HuskyCoder;

public class HindiWordsCoder implements HuskyCoder<String>{

	@Override
	public long huskyEncode(String x) {
		long res=0;
		byte[] arr=x.getBytes();
		int length=8;
		for(int i=0;i<length;i++) {
			res<<=8;
			res |= (arr[i]&255);
		}
		return res;
	}
	
}
