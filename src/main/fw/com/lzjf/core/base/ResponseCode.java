package com.lzjf.core.base;

public interface ResponseCode{
	
//	public static final String SUCCESS = "success";
//	public static final String ERROR = "error";
	public static final String UNVALIDATE = "unvalidate";
	public static final String UNVALIDATE_MESSAGE = "数据校验失败";
	public static final String ERRORMSG = "请求方式非法！";

	/** 后台异常 */
	public static final String ERROR = "9999";
	/** 处理成功 */
	public static final String SUCCESS = "0000";
	/**----------- user_error：1 */
	/** 用户不存在 */
	public static final String NOUSER = "1000";
	/** 账户余额不足。 */
	public static final String NOT_ENOUGH_AVAMONEY = "1001";
	/** qq没有绑定用户。 */
    public static final String NOT_QQINFO = "1003";
    /** 手机号和用户名不匹配 */
    public static final String USER_MOBILE_NOTCONSISTENCE = "1004";
    /** 此手机号已达验证上限 */
    public static final String MOBILE_SENT_OVERUPPER = "1005";
    /** 手机动态码错误*/
    public static final String MOBILE_CODE_ERROR = "1006";
    /** 重置密码成功！*/
    public static final String PASSWORD_RESET_SUCCESS = "1007";
    /** 重置密码失败！*/
    public static final String PASSWORD_RESET_FAIL = "1008";
    /** 充值失败 */
    public static final String RECHARGE_FAILED = "1009";
    /** 手机号已经存在,请更换后重试 */
    public static final String MOBILE_HAVE = "1010";
    /** weibo没有绑定用户。 */
    public static final String NOT_WBINFO = "1011";
    /** 原密码错误，请重试！ */
    public static final String OLD_PASSWORD_ERROR = "1012";
    /** 新密码和原始密码不能相同！ */
    public static final String PASSWORD_SAMED = "1013";
    /** 借款人不存在   */
    public static final String NON_LOANUSER = "1014";
    /**新邮箱与原邮箱地址一样。*/
    public static final String EMAIL_SAMED = "1015";
    /** 新手机号不能与原手机号相同。*/
    public static final String MOBILE_SAMED = "1018";
    /** 此用户绑定的手机号码不存在 */
    public static final String MOBILE_NOT_EXIST = "1019";
    /** 此邮箱已经被注册 */
    public static final String BIND_EMAIL_EXIST = "1020";
    /** 此手机号尚未使用过 */
    public static final String MOBILE_UN_USED = "1021";
    /** 手机号已被使用 */
    public static final String MOBILE_YET_USED = "1022";
    /** 邮箱没有发生改变 */
    public static final String BIND_EMAIL_SAME = "1023";
    /** 发送邮件失败 */
    public static final String SEND_EMAIL_FAILURE = "1024";
    /** 更新邮箱状态失败 */
    public static final String UPDATE_EMAIL_FAILURE = "1025";
    /** 激活使用的邮箱状态码和数据库中不一致 */
    public static final String ACTIVE_CODE_EMAIL_DIF = "1026";
    /** ac和p2p用户已创建*/
    public static final String AC_P2P_USER_ALLDIF = "1027";
    /** 邮箱已经激活,不用重复激活！ */
    public static final String EMAIL_ACTIVED = "1028";
    /** 发放对象信息不存在 */
    public static final String NOUSERSEARCH = "1029";
    /** 受邀请人信息不存在 */
    public static final String NOINVITEDUSER = "1030";
    /** 用户认证失败 */
    public static final String USER_VALIDATE_ERROR = "1031";
    /** 用户认证失败，没超过认证次数可以再次认证 */
    public static final String USER_VALIDATE_RETRY = "1041";
    /** 用户认证失败已认证过 */
    public static final String USER_VALIDATE_EXIST = "1042";
    /** 交易密码与原交易密码相同*/
    public static final String OLD_ACPASSWORD_ERROR = "1032";
    /** 输入交易密码与登录密码相同 */
    public static final String ACPASSWORD_PASSWORD_ERROR = "1033";
    /** 输入的原交易密码与表中数据不同 */
    public static final String TRADEOLD_OLDPWD_ERROR = "1034";
    /** 输入的交易密码与用户账号相同 */
    public static final String TRADEOLD_ACCOUNT_ERROR = "1035";
    /** 新密码和用户名不能相同！ */
    public static final String PASSWORD_ACCOUNT_ERROR = "1036";
    /** 交易密码错误 */
    public static final String JYPASSWORD_ERROR = "1037";
    /** 交易密码错未设置*/
    public static final String JYPASSWORD_NOTSET = "1038";
    /** 登录密码与交易密码相同*/
    public static final String PASSWORD_ACPASSWORD_NOTSET = "1039";
    /** 用户身份证号码已存在已被使用*/
    public static final String USER_IDNUMBER_EXIST = "1040";
    /** 绑定的银行卡已存在已被绑定使用*/
    public static final String BANKCARD_BINDING_EXIST = "1041";
    /** 绑定的银行卡错误*/
    public static final String BANKCARD_BINDING_ERROR = "1042";
    
    
	/**------------project_error:2 */
	/** 查询项目不存在 */
	public static final String NOPROJECT = "2000";
	/** 收益计算失败 */
	public static final String INCOME_CALCULATE_FAILED = "2001";
	/** 查询的品牌不存在 */
	public static final String NOBRAND = "2002";
	/** 查询的品牌证书图片不存在 */
	public static final String NOCERTIFY = "2003";
	/** 查询担保公司不存在 */
	public static final String NOGUARANTEE = "2004";
	/** 转让中债权不存在 */
	public static final String NOASSIGNMENT = "2005";
	/** 已转让债权不存在 */
	public static final String NOASSIGNED = "2006";
	/** 已认购债权不存在 */
	public static final String NOPURCHASEDCREDITINFO = "2007";
	/** 查询债权转让原投资不存在 */
    public static final String NO_PROJECT_INVEST = "2008";
    /** 查询债权转让转让人不存在 */
    public static final String NO_PROJECT_INVEST_USER = "2009";
    /** 查询债权标的不存在 */
    public static final String NO_CREDIT_PROJECT = "2010";
    /** 债权标的不可投 */
    public static final String NO_INVEST_ENABLE_CREDIT_PROJECT = "2011";
    /** 查询债权转让原标的项目不存在 */
    public static final String NO_PROJECT = "2012";
    /** 查询债权转让原标的借款人不存在 */
    public static final String NO_PROJECT_LOANER = "2013";
    /** 债权卖出者不可以购买自己的债权 */
    public static final String SELLER_NO_BUY = "2014";
	/**------------trans_error:3 */
	/** 投标金额必须100的整数倍. */
	public static final String INVEST_AMOUNT_ERROR = "3000";
	/** 投资金额大于标的可投余额. */
	public static final String NOT_ENOUGH_INVEST_MONEY = "3001";
	/** 红包使用中，请过两分钟后再试. */
	public static final String HONGBAO_INUSE = "3002";
	/** 红包已经使用，无法重复操作. */
	public static final String HONGBAO_USED = "3003";
	/** 解冻失败 */
	public static final String UNFREEZING_FAILED = "3004";
	/** 更新投资相关信息失败 */
	public static final String UPDATE_INVEST_INFO_FAILED = "3005";
	/** 更新发放红包相关信息失败 */
	public static final String UPDATE_HONGBAO_DELIVER_FAILED = "3006";
	/** 发放金额失败 */
	public static final String NOMONEY = "3007";
	/** 红包无法使用 */
	public static final String HBPKGENTITY_CAN_NOT_USE = "3008";
	/** 不满足新手标投资要求 */
	public static final String NOVICE_ERROR = "3009";
	
	/**------------common_error:4 */
	/** 签名失败，请求方式非法！ */
	public static final String SIGN_FAILED = "4000";
	/** 交易超时，请重新投资 */
	public static final String TRANS_OVERTIME = "4001";
	/** 交易失败，请重新投资 */
	public static final String TRANS_FAILED = "4002";
	/** DB异常 */
	public static final String DB_ERROR = "4003";
	/** 请求方式非法！ */
	public static final String REQUEST_ILLEGAL = "4004";
	/** 平台公告不存在*/
    public static final String NON_NOTIFICATION_DETAIL = "4005";
    /** 在汇付已经注册 */
    public static final String HF_REGERROR = "4006";
    
    /** 在汇付已经注册 */
    public static final String HF_REWITHDRAW = "4007";
    /** 预览合同失败 */
    public static final String PRE_CONTRACT_FAILED = "4008";
    /** rongshu开户失败 */
    public static final String OPENPAYACCOUNT_FAILED = "4009";
    /** 投资调用融数返回失败 */
	public static final String RONGSHU_TRANS_FAILED = "4010";
	 /** 投资成功接口调用返回失败 */
	public static final String INVESTSUCCESS_TRANS_FAILED = "4011";
    
    /**锁机制**/
    /***调用JRDLOGICLOCKER出错*/
    public static final String JRD_LOGICLOCKER_FAILED = "5000";
    /***调用JRDLOGICLOCKER超时*/
    public static final String JRD_LOGICLOCKER_TIMEOUT = "5001";
    
    /**------------redPackage_error:6 */
	/** 兑换兑换码时出错 */
	public static final String NOEXCHANGEUCODE = "6000";
	/** 已向用户发放过此类红包 */
	public static final String HBPKGENTITY_ALREADYSEND = "6002";
	/** 红包实体不存在 */
	public static final String NO_HBPKGENTITY = "6006";
	/** 红包已兑换 */
	public static final String HBPKGENTITY_GET = "6007";
	/** 优惠券已失效 */
	public static final String HBPKGENTITY_INVALID = "6008";
	/** 兑换码兑换数量达到上限 */
	public static final String HBEXGENTITY_USED = "6012";
	/** 优惠券已使用 */
	public static final String HBPKGENTITY_ALREADYUSED = "6013";
	/** 不符合投资金额限制 */
	public static final String INVEST_LIMIT_INVALID = "6014";
	/** 不符合标的期限限制 */
	public static final String PRJ_TIME_LIMIT_INVALID = "6015";
	/** 不符合品牌限制 */
	public static final String LIMIT_BRAND_INVALID = "6016";
	/** 不符合标的限制 */
	public static final String LIMIT_PROJECT_INVALID = "6017";
	/** 不符合担保公司限制 */
	public static final String LIMIT_GUARANTEE_INVALID = "6018";
	/** 红包已经拆过 */
	public static final String ALREADY_OPEND = "6019";
    
  /** ------------dqb_error:7 */
    /** 短期宝已封闭 */
    public static final String JR_TD_PROJECT_DISPLAY_STATUS_CLOSE = "7000";

    /** 短期宝已满额 */
    public static final String JR_TD_PROJECT_STATUS_BIDDED = "7001";
    
    /**AMQ 队列错误 */
    public static final String JR_AMQPEXCEPTION = "8000";


    /** 微信电商(拼划算)非首次注册 */
    public static final String JR_TD_DIANSHANG_STATUS_REGIED = "8001";

    /** 微信电商(拼划算)首次注册 */
    public static final String JR_TD_DIANSHANG_STATUS_UN_REGIED = "8002";
    
    
    /** 昨日收益不存在 */
    public static final String NON_INCOME = "9001";

}
