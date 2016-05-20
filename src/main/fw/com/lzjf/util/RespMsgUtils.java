package com.lzjf.util;

public class RespMsgUtils {

	public static String RespCodeForMsg(String ResponseCode) {
		String respMsg = "";
		int code = Integer.valueOf(ResponseCode);
		if (code == 0) {
			respMsg = "处理成功";
		} else {
		if (code < 2000) {
			switch (code) {
			case 1000:
				respMsg = "用户不存在";
				break;
			case 1001:
				respMsg = "账户余额不足.";
				break;
			case 1003:
                respMsg = "QQ登录失败.";
                break;
			case 1004:
                respMsg = "手机号和用户名不匹配";
                break;
            case 1005:
                respMsg = "此手机号已达验证上限";
                break;
            case 1006:
                respMsg = "手机动态码错误";
                break;
            case 1007:
                respMsg = "重置密码成功！";
                break;
            case 1008:
                respMsg = "重置密码失败！";
                break;
            case 1009:
            	respMsg = "充值失败";
            	break;
            case 1010:
                respMsg = "手机号已经存在,请更换后重试";
                break;
            case 1011:
                respMsg = "微博没有绑定用户";
                break;
            case 1012:
                respMsg = "原密码错误，请重试";
                break;
            case 1013:
                respMsg = "新密码和原始密码不能相同！";
                break;
            case 1014:
                respMsg = "借款人不存在";
                break;
            case 1015:
                respMsg = "新邮箱与原邮箱地址一样。";
                break;
            case 1018:
                respMsg = "新手机号不能与原手机号相同。";
                break;
            case 1019:
                respMsg = "此用户绑定的手机号码不存在";
                break;
            case 1020:
                respMsg = "此邮箱已经被注册";
                break;
            case 1021:
                respMsg = "此手机号尚未使用过";
                break;
            case 1022:
                respMsg = "手机号已被使用";
                break;
            case 1023:
                respMsg = "邮箱没有发生改变";
                break;
            case 1024:
                respMsg = "发送邮件失败";
                break;
            case 1025:
                respMsg = "更新邮箱状态失败";
                break;
            case 1026:
                respMsg = "激活使用的邮箱状态码和数据库中不一致";
                break;
            case 1027:
                respMsg = "ac和p2p用户已创建";
                break;
            case 1028:
            	respMsg = "邮箱已经激活,不用重复激活！";
            	break;
            case 1029:
            	respMsg = "发放对象信息不存在";
            	break;
            case 1030:
            	respMsg = "受邀请人信息不存在";
            	break;
            case 1032:
                respMsg = "交易密码与原密码相同";
                break;
            case 1033:
                respMsg = "交易密码与登录密码相同";
                break;
            case 1034:
                respMsg = "原交易密码输入错误";
                break;
            case 1035:
                respMsg = "交易密码与用户名相同";
                break;
            case 1036:
                respMsg = "登录密码与用户名相同";
                break;
            case 1037:
                respMsg = "交易密码错误";
                break;
            case 1038:
                respMsg = "交易密码未设置";
                break;    
            case 1039:
                respMsg = "登录密码与交易密码相同";
                break;   
            case 1041:
                respMsg = "该卡号已经绑定过，请勿重复绑定";
                break;
            case 1042:
                respMsg = "绑定银行卡失败";
                break;
			}
		} else if (code < 3000) {
			switch (code) {
			case 2000:
				respMsg = "查询项目不存在.";
				break;
			case 2001:
				respMsg = "收益计算失败";
				break;
			case 2002:
				respMsg = "查询的品牌不存在";
				break;
			case 2003:
				respMsg = "查询的品牌证书图片不存在";
				break;
			case 2004:
				respMsg = "查询担保公司不存在";
				break;
			case 2005:
				respMsg = "转让中债权不存在";
				break;
			case 2006:
				respMsg = "已转让债权不存在";
				break;
			case 2007:
				respMsg = "已认购债权不存在";
				break;
            case 2008:
                respMsg = "查询债权转让原投资不存在";
                break;
            case 2009:
                respMsg = "查询债权转让转让人不存在";
                break;
            case 2010:
                respMsg = "查询债权标的不存在";
                break;
            case 2011:
                respMsg = "此债权标的不可投";
                break;
            case 2012:
                respMsg = "转让原标的项目不存在";
                break;
            case 2013:
                respMsg = "债权转让原标的借款人不存在";
                break;
            case 2014:
                respMsg = "债权卖出者不可以购买自己的债权";
                break;
			}
		} else if (code < 4000) {
			switch (code) {
			case 3000:
				respMsg = "投标金额必须100整数倍";
				break;
			case 3001:
				respMsg = "投资金额大于标的可投余额";
				break;
			case 3002:
				respMsg = "红包使用中，请过两分钟后再试";
				break;
			case 3003:
				respMsg = "红包已经使用，无法重复操作";
				break;
			case 3004:
				respMsg = "解冻失败";
				break;
			case 3005:
				respMsg = "交易失败，请重新投资";
				break;
			case 3006:
				respMsg = "更新红包信息失败";
				break;
			case 3010:
				respMsg = "投资金额总和大于最大可加入金额";
				break;
			case 3011:
				respMsg = "提取金额大于平台本金可提取金额";
				break;
			case 3012:
				respMsg = "可提取利息为0";
				break;
			case 3013:
				respMsg = "提取金额大于用户可提取本金";
				break;
			case 3014:
				respMsg = "投标金额必须1000的整数倍";
				break;
			}
		} else if (code < 5000) {
			switch (code) {
			case 4000:
				respMsg = "签名失败，请求方式非法";
				break;
			case 4001:
				respMsg = "交易超时，请重新投资";
				break;
			case 4002:
				respMsg = "交易失败，请重新投资";
				break;
			case 4003:
				respMsg = "DB异常";
				break;
			case 4004:
				respMsg = "请求方式非法";
				break;
			case 4005:
                respMsg = "平台公告不存在";
                break;
			case 4006:
                respMsg = "在汇付已经注册";
                break;
			case 4008:
				respMsg = "预览合同失败";
				break;
			case 4010:
				respMsg = "投资调用融数接口返回失败";
				break;
			case 4011:
				respMsg = "投资成功接口调用返回失败";
				break;	
			}
		} else if (code < 6000) {
	       switch (code) {
	       case 5000:
	           respMsg = "交易失败，请与客服联系";
	           break;
	       case 5001:
	           respMsg = "调用汇付失败，调用JRDLOGICLOCKER超时！";
	           break;
	       }
		    
		}else if (code < 7000) {
		       switch (code) {
		       case 6000:
		           respMsg = "兑换码兑换失败";
		           break;
		       case 6006:
		           respMsg = "红包不存在";
		           break;
		       case 6007:
		           respMsg = "红包已兑换";
		           break;
		       }
            } else if (code < 8000) {
                switch (code) {
                case 7000:
                    respMsg = "短期宝已封闭";
                    break;
                case 7001:
                    respMsg = "短期宝已满额";
                    break;

                }
            } else if (code < 10000) {
                switch (code) {
                case 9001:
                    respMsg = "昨日收益不存在";
                    break;

                }
            }

            else {
			respMsg = "后台异常！";
		}
		}
		
		return respMsg;
	}
}
