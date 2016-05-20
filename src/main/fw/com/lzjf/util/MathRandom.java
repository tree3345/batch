package com.lzjf.util;

public class MathRandom {

	/**
	 * Math.random()产生一个double型的随机数，判断一下 ：1 出现的几率为0.01% 返回4；2~1000 出现的几率为 9.99%，1001~9999出现的几率为 90%；
	 * 
	 * @return int
	 * 
	 */
	public int PercentageRandom() {
		int randomNumber = (int) (Math.random()*100);
		
		if (randomNumber >= 5 && randomNumber <= 99) {
			return 2;
		}else if(randomNumber >=0 && randomNumber <= 4 ){
			return 3;
		}
		return -1;
	}
	
}
