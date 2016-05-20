package com.lz.constant;
/**
* description：
* @author zhujinhua
* @version create time：2015年12月14日 下午4:09:56
*/
public final class FactorTableGeneratorConstant {


	
	public static final int NATURE_CALENDAR = 0;
	
	public static final int EXCHANGE_CALENDAR = 1;
	
	public static final String STK_CODE_TABLE= "LZ_GPA_TMP_STK";
	
	public static final String INDX_CODE_TABLE = "LZ_GPA_TMP_INDX";
	
	public static final String EXCHANGE_CALENDAR_TABLE = "LZ_GPA_EXCHGNCAL";
	
	public static final int GENERATE_MODE_FULL = 0;
	
	public static final int GENERATE_MODE_INCREMENT = 1;
	
	public static final int GENERATE_MODE_UPDATE = 2;
	
	public static final int GENERATE_MODE_STOCK_STOP = 3;
	
	public static final String SEPERATOR = ",";
	
	/**
	 * 中国A股停复牌信息
	 */
	public static final String GENERATE_STOCK_STOP_FLAG = "LZ_GPA_STPCLDR_STOPFLAG";
	
	public static final String GENERATE_INDXWGT = "LZ_GPA_INDXWGT_WPCT";
	
	public static final String ENVIRONMENT_WINDOWS = "WINDOWS";
	
	public static final String ENVIRONMENT_LINUX = "LINUX";
	
	/**
	 * factor fill indicator
	 */
	public static final int FILL_INDICATOR_NATURE = 0;
	
	public static final int FILL_INDICATOR_FORWARD= 1; 
	
	public static final int FILL_INDICATOR_BACKWARD = 2;


	public static final String FILL_STR = "NaN";
	public static final String JEN_TYPE_0 = "0";
	public static final String JEN_TYPE_1 = "1";


	public static final String JEN_TABLE = "lzjf_jenData";
	public static final String ISOPEN = "ISOPEN";
	public static final String MINTIMESTAMP ="19900101000000" ;

	public enum CodeType {
		INDX_CODE_SORT_INDICATOR(0,"INDX_CODE_SORT_INDICATOR"),

		STK_CODE_SORT_INDICATOR(1,"INDX_CODE_SORT_INDICATOR"),

		INDU_ZJH_CODE_SORT_INDICATOR(2,"INDX_CODE_SORT_INDICATOR"),

		INDU_SW_CODE_SORT_INDICATOR(3,"INDX_CODE_SORT_INDICATOR"),

		INDU_XCF_CODE_SORT_INDICATOR(4,"INDX_CODE_SORT_INDICATOR");
		private Integer codeSort;
		private String codeStr;

		CodeType(int codeSort, String codeStr) {
			this.codeSort = codeSort;
			this.codeStr = codeStr;
		}

		public Integer getCodeSort() {
			return codeSort;
		}

		public void setCodeSort(Integer codeSort) {
			this.codeSort = codeSort;
		}

		public String getCodeStr() {
			return codeStr;
		}

		public void setCodeStr(String codeStr) {
			this.codeStr = codeStr;
		}
	}
}
