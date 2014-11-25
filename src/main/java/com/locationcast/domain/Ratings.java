/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;

/**
 * @author Khoa
 *
 */
public class Ratings implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum RatingStar{
		
		ONESTAR(1),
		TWOSTAR(2),
		THREESTAR(3),
		FOURSTAR(4),
		FIVESTAR(5);
		
		private int level = 0 ;
		
		RatingStar(int level){
			this.level = level;
		}
		
		public int getLevel(){
			return this.level;
		}
	}
}
