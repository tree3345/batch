package com.lzjf.util;

public class RespUtils {
	public static String RespCodeForDesc(String RespCode) {
		String respDesc = "";
		switch (Integer.valueOf(RespCode)) {
		case 0:
			respDesc =  "成功";
			break;
	    case 99:
	        respDesc =  "汇付系统异常";
	        break;
		case 100:
			respDesc =  "请求参数非法";
			break;
		case 101:
			respDesc =  "验证签名失败";
			break;
		case 102:
			respDesc =  "加签名失败";
			break;
		case 103:
			respDesc =  "没有对应的验签方式";
			break;
		case 104:
			respDesc =  "json字符串转换对象失败";
			break;
		case 105:
			respDesc =  "对象转换json字符串失败";
			break;
		case 106:
			respDesc =  "从json字符串找到指定参数值失败";
			break;
		case 107:
			respDesc =  "重复交易";
			break;
		case 200:
			respDesc =  "用户未登录";
			break;
		case 201:
			respDesc =  "商户或用户不存在";
			break;
		case 202:
			respDesc =  "此商户不存在";
			break;
		case 203:
			respDesc =  "此用户不存在";
			break;
		case 204:
			respDesc =  "商户或用户状态不正常";
			break;
		case 205:
			respDesc =  "商户状态不正常";
			break;
		case 206:
			respDesc =  "用户状态不正常";
			break;
		case 207:
			respDesc =  "用户状态未实名";
			break;
		case 208:
			respDesc =  "用户被锁";
			break;
		case 209:
			respDesc =  "用户未激活";
			break;
		case 210:
			respDesc =  "用户在商户下不存在";
			break;
		case 211:
			respDesc =  "用户在商户下已关闭";
			break;
		case 212:
			respDesc =  "用户不是P2P类型商户";
			break;
		case 213:
			respDesc =  "错误次数超过限制，交易密码已被锁定";
			break;
		case 214:
			respDesc =  "审核处理中不允许商户重复提交注册";
			break;
		case 215:
			respDesc =  "用户角色不支持转账";
			break;
		case 216:
			respDesc =  "企业用户注册信息不存在";
			break;
		case 217:
			respDesc =  "企业用户注册审核拒绝";
			break;
		case 218:
			respDesc =  "企业用户注册审核中";
			break;
		case 219:
			respDesc =  "企业用户注册已提交待审核";
			break;
		case 220:
			respDesc =  "已开户成功不允许商户重复提交注册";
			break;
		case 221:
			respDesc =  "开户失败、汇付处理中、不允许商户重复提交注册";
			break;
		case 222:
			respDesc =  "开户中不允许商户重复提交注册";
			break;
		case 223:
			respDesc =  "没有生利宝记录";
			break;
		case 224:
			respDesc =  "生利宝账户信息查询失败";
			break;
		case 225:
			respDesc =  "生利宝产品信息查询失败";
			break;
		case 301:
			respDesc =  "投资用户在本商户下不存在";
			break;
		case 302:
			respDesc =  "投资用户在本商户下已关闭";
			break;
		case 303:
			respDesc =  "借款用户在本商户下不存在";
			break;
		case 304:
			respDesc =  "借款用户在本商户下已关闭";
			break;
		case 307:
			respDesc =  "商户不能为投资人";
			break;
		case 308:
			respDesc =  "商户不能为借款人";
			break;
		case 309:
			respDesc =  "投资人不能为借款人";
			break;
		case 310:
			respDesc =  "借款金额总和不等于投标金额";
			break;
		case 311:
			respDesc =  "商户配置信息不存在";
			break;
		case 312:
			respDesc =  "投资手续费率超过商户限额";
			break;
		case 313:
			respDesc =  "借款手续费超过商户限额";
			break;
		case 314:
			respDesc =  "添加投标信息异常";
			break;
		case 315:
			respDesc =  "更新投标信息异常";
			break;
		case 316:
			respDesc =  "交易密码错误";
			break;
		case 317:
			respDesc =  "投标记录不存在";
			break;
		case 318:
			respDesc =  "投标记录状态异常";
			break;
		case 319:
			respDesc =  "投标类型错误";
			break;
		case 320:
			respDesc =  "投标记录匹配异常";
			break;
		case 321:
			respDesc =  "投标记录已放款";
			break;
		case 322:
			respDesc =  "投标已确认";
			break;
		case 323:
			respDesc =  "投标记录匹配用户不正确";
			break;
		case 324:
			respDesc =  "分标记录下不存在";
			break;
		case 325:
			respDesc =  "分标记录状态异常";
			break;
		case 326:
			respDesc =  "投标记录匹配异常";
			break;
		case 327:
			respDesc =  "投标计划设置异常";
			break;
		case 328:
			respDesc =  "自动投标计划不存在";
			break;
		case 329:
			respDesc =  "自动投标计划状态异常";
			break;
		case 330:
			respDesc =  "自动投标计划已关闭";
			break;
		case 331:
			respDesc =  "自动投标计划类型错误";
			break;
		case 332:
			respDesc =  "投资金额超过自动投标计划上限";
			break;
		case 333:
			respDesc =  "手续费不等于分账给各账户的资金总额";
			break;
		case 334:
			respDesc =  "已放款金额加本次放款金额超过投资人原单中的投资金额";
			break;
		case 335:
			respDesc =  "已扣除手续费加本次扣除手续费超过原单中的投资金额乘以投资手续费率";
			break;
		case 336:
			respDesc =  "放款金额超过借款金额";
			break;
		case 337:
			respDesc =  "账户不存在";
			break;
		case 338:
			respDesc =  "账户关闭";
			break;
		case 339:
			respDesc =  "转出账户为保证金账户";
			break;
		case 340:
			respDesc =  "转入转出为同一账户";
			break;
		case 341:
			respDesc =  "银行卡不存在";
			break;
		case 342:
			respDesc =  "银行卡关闭";
			break;
		case 343:
			respDesc =  "出账用户余额不足";
			break;
		case 344:
			respDesc =  "添加放款记录失败";
			break;
		case 345:
			respDesc =  "重复的放款请求";
			break;
		case 346:
			respDesc =  "完成放款失败";
			break;
		case 347:
			respDesc =  "还款资金流向不对";
			break;
		case 348:
			respDesc =  "本次垫付金额加上已垫付金额超过还款总额";
			break;
		case 349:
			respDesc =  "本次还款金额加上已还款金额超过还款总额";
			break;
		case 350:
			respDesc =  "添加还款记录失败";
			break;
		case 351:
			respDesc =  "重复的还款请求";
			break;
		case 352:
			respDesc =  "完成还款失败";
			break;
		case 353:
			respDesc =  "放款失败";
			break;
		case 354:
			respDesc =  "还款失败";
			break;
		case 355:
			respDesc =  "重复的转账请求";
			break;
		case 356:
			respDesc =  "转账失败";
			break;
		case 357:
			respDesc =  "冻结请求已经被解冻";
			break;
		case 358:
			respDesc =  "冻结失败";
			break;
		case 359:
			respDesc =  "解冻失败";
			break;
		case 360:
			respDesc =  "存在一笔相同订单号的冻结记录";
			break;
		case 361:
			respDesc =  "用户账户不存在或状态异常";
			break;
		case 362:
			respDesc =  "商户账户不存在或状态异常";
			break;
		case 363:
			respDesc =  "投标失败";
			break;
		case 364:
			respDesc =  "余额不足";
			break;
		case 365:
			respDesc =  "投资人和借款人不能同时企业用户";
			break;
		case 366:
			respDesc =  "投资人不能为担保类型";
			break;
		case 367:
			respDesc =  "借款人不能为担保类型";
			break;
		case 368:
			respDesc =  "商户不能为转让人";
			break;
		case 369:
			respDesc =  "商户不能为承接人";
			break;
		case 370:
			respDesc =  "转让人不能为承接人";
			break;
		case 371:
			respDesc =  "转让金额或手续费不匹配";
			break;
		case 372:
			respDesc =  "转让用户不匹配";
			break;
		case 373:
			respDesc =  "原始订单不匹配或状态异常";
			break;
		case 374:
			respDesc =  "承接人和借款人不能同时为企业用户";
			break;
		case 375:
			respDesc =  "实际成交金额超过限额";
			break;
		case 376:
			respDesc =  "转让手续费超过限额";
			break;
		case 377:
			respDesc =  "添加债权转让记录失败";
			break;
		case 378:
			respDesc =  "债权转让转账失败";
			break;
		case 379:
			respDesc =  "更新债权转让状态失败";
			break;
		case 380:
			respDesc =  "债权转让金额超过可转让金额";
			break;
		case 381:
			respDesc =  "订单号重复";
			break;
		case 382:
			respDesc =  "承接人不能为借款人";
			break;
		case 383:
			respDesc =  "已还本金非法";
			break;
		case 384:
			respDesc =  "自动债权转让计划不存在";
			break;
		case 385:
			respDesc =  "自动债权转让计划状态异常";
			break;
		case 386:
			respDesc =  "自动债权转让计划已关闭";
			break;
		case 387:
			respDesc =  "自动债权转让计划类型错误";
			break;
		case 388:
			respDesc =  "投资金额超过自动债权转让计划上限";
			break;
		case 400:
			respDesc =  "取现失败";
			break;
		case 401:
			respDesc =  "重复取现申请,取现失败";
			break;
		case 402:
			respDesc =  "专属账户查询异常";
			break;
		case 403:
			respDesc =  "余额不足,取现失败";
			break;
		case 404:
			respDesc =  "历史取现记录查询异常";
			break;
		case 405:
			respDesc =  "历史充值记录查询异常";
			break;
		case 406:
			respDesc =  "当前取现卡不存在,取现失败";
			break;
		case 407:
			respDesc =  "当前取现卡未实名,取现失败";
			break;
		case 408:
			respDesc =  "当前取现卡姓名不一致,取现失败";
			break;
		case 409:
			respDesc =  "查询取现卡异常,取现失败";
			break;
		case 410:
			respDesc =  "当前用户无可用的取现银行卡,取现失败";
			break;
		case 411:
			respDesc =  "当前取现通道取现配置信息不存在";
			break;
		case 412:
			respDesc =  "当前取现通道取现异常";
			break;
		case 413:
			respDesc =  "直连银行卡校验失败";
			break;
		case 414:
			respDesc =  "手续费计算失败";
			break;
		case 415:
			respDesc =  "取现申请信息不存在";
			break;
		case 416:
			respDesc =  "该订单已完成审核";
			break;
		case 417:
			respDesc =  "充值手续费配置不存在";
			break;
		case 418:
			respDesc =  "充值手续费计算失败";
			break;
		case 419:
			respDesc =  "可解冻的冻结交易不存在";
			break;
		case 420:
			respDesc =  "当前取现卡不支持直连取现";
			break;
		case 421:
			respDesc =  "借款交易记录不存在";
			break;
		case 422:
			respDesc =  "还款交易记录不存在";
			break;
		case 423:
			respDesc =  "商户转账交易记录不存在";
			break;
		case 424:
			respDesc =  "不支持的对账查询类型";
			break;
		case 425:
			respDesc =  "用户没有真实的手机号";
			break;
		case 426:
			respDesc =  "审核金额与申请金额不一致";
			break;
		case 427:
			respDesc =  "验卡银行错误";
			break;
		case 428:
			respDesc =  "验卡失败";
			break;
		case 429:
			respDesc =  "代发验卡处理中";
			break;
		case 430:
			respDesc =  "当前取现卡实名失败,取现失败";
			break;
		case 431:
			respDesc =  "账户可取金额不足或手续费账户余额不足,取现失败";
			break;
		case 432:
			respDesc =  "当前卡不能作为取现卡,取现失败";
			break;
		case 433:
			respDesc =  "取现正在处理中";
			break;
		case 434:
			respDesc =  "取现页面数据数据被篡改";
			break;
		case 435:
			respDesc =  "最大商户服务费率未配置";
			break;
		case 436:
			respDesc =  "商户服务费不合法";
			break;
		case 501:
			respDesc =  "商户规则未配置";
			break;
		case 502:
			respDesc =  "银行卡号已存在";
			break;
		case 503:
			respDesc =  "不是取现银行";
			break;
		case 504:
			respDesc =  "用户未实名，绑卡失败";
			break;
		case 505:
			respDesc =  "省份地区不存在";
			break;
		case 506:
			respDesc =  "身份证和姓名校验失败";
			break;
		case 507:
			respDesc =  "该商户配置开户时不允许传递的手机号为空";
			break;
		case 508:
			respDesc =  "手机号在该商户下已存在";
			break;
		case 509:
			respDesc =  "证件号在该商户下已存在";
			break;
		case 510:
			respDesc =  "用户名必须是6-25位的英文、数字、特殊字符@._-组合";
			break;
		case 511:
			respDesc =  "密码必须是6-16位半角字符";
			break;
		case 512:
			respDesc =  "新密码不能与原密码一致";
			break;
		case 513:
			respDesc =  "用户操作员信息异常";
			break;
		case 514:
			respDesc =  "密码更新失败";
			break;
		case 515:
			respDesc =  "密码加密异常";
			break;
		case 516:
			respDesc =  "商户没有开通权限";
			break;
		case 517:
			respDesc =  "商户接口权限已关闭";
			break;
		case 518:
			respDesc =  "收费手续费配置错误";
			break;
		case 519:
			respDesc =  "收费手续费账户错误";
			break;
		case 520:
			respDesc =  "收费手续费账户余额不足";
			break;
		case 521:
			respDesc =  "用户在该商户下已存在";
			break;
		case 522:
			respDesc =  "代扣卡不存在";
			break;
		case 523:
			respDesc =  "代扣卡状态不正常";
			break;
		case 524:
			respDesc =  "商户无卡代扣配置信息不存在";
			break;
		case 525:
			respDesc =  "充值金额超过单笔金额上限";
			break;
		case 526:
			respDesc =  "充值笔数超过单卡总笔数上限";
			break;
		case 527:
			respDesc =  "充值金额超过单卡总金额上限";
			break;
		case 528:
			respDesc =  "充值笔数超过累计总笔数上限";
			break;
		case 529:
			respDesc =  "充值金额超过累计总金额上限";
			break;
		case 530:
			respDesc =  "充值笔数超过单日单卡笔数上限";
			break;
		case 531:
			respDesc =  "充值金额超过单日单卡金额上限";
			break;
		case 532:
			respDesc =  "充值笔数超过单日单商户笔数上限";
			break;
		case 533:
			respDesc =  "充值金额超过单日单商户金额上限";
			break;
		case 534:
			respDesc =  "重复的投标请求";
			break;
		case 535:
			respDesc =  "充值失败";
			break;
		case 536:
			respDesc =  "更新充值累计值失败";
			break;
		case 537:
			respDesc =  "银行卡未做代扣验证";
			break;
		case 538:
			respDesc =  "重复的充值请求";
			break;
		case 539:
			respDesc =  "转入账户不是专属账户";
			break;
		case 540:
			respDesc =  "操作超时，请重新获取校验码";
			break;
		case 541:
			respDesc =  "无此绑定卡";
			break;
		case 542:
			respDesc =  "默认取现卡不允许删除";
			break;
		case 543:
			respDesc =  "银行卡删除失败";
			break;
		case 544:
			respDesc =  "此银行卡已被删除";
			break;
		case 545:
			respDesc =  "手续费收款用户类型不正确";
			break;
		case 546:
			respDesc =  "手续费支付方不能为手续费收取方";
			break;
		case 547:
			respDesc =  "重复的订单请求";
			break;
		case 548:
			respDesc =  "订单不存在或状态异常";
			break;
		case 549:
			respDesc =  "同一笔充值订单信息不匹配";
			break;
		case 600:
			respDesc =  "用户后台转账已经授权";
			break;
		case 601:
			respDesc =  "用户后台转账未授权";
			break;
		case 602:
			respDesc =  "用户角色不允许后台转账";
			break;
		case 603:
			respDesc =  "生利宝开户失败";
			break;
		case 604:
			respDesc =  "生利宝转入失败、用户为企业用户";
			break;
		case 605:
			respDesc =  "生利宝转入失败、用户为商户";
			break;
		case 606:
			respDesc =  "生利宝转入失败、用户非实名用户";
			break;
		case 607:
			respDesc =  "生利宝转入失败、用户未绑卡";
			break;
		case 608:
			respDesc =  "生利宝交易失败";
			break;
		case 609:
			respDesc =  "转入成功";
			break;
		case 610:
			respDesc =  "转出失败";
			break;
		case 611:
			respDesc =  "转出处理中";
			break;
		case 612:
			respDesc =  "转入处理中";
			break;
		case 613:
			respDesc =  "没有开通生利宝";
			break;
		case 615:
			respDesc =  "授权金额超限";
			break;
		case 616:
			respDesc =  "调用生利宝加签文失败";
			break;
		case 617:
			respDesc =  "调用生利宝接口失败";
			break;
		case 618:
			respDesc =  "调用生利宝返回验签失败";
			break;
		case 619:
			respDesc =  "页面签名错误";
			break;
		case 620:
			respDesc =  "转入失败";
			break;
		case 621:
			respDesc =  "状态非法";
			break;
		case 630:
			respDesc =  "商户不能作为收款人";
			break;
		case 631:
			respDesc =  "用户定向支付未授权";
			break;
		case 632:
			respDesc =  "用户定向支付失败";
			break;
		case 997:
			respDesc =  "交易数据与原订单数据不一致";
			break;
		case 998:
			respDesc =  "页面数据被篡改";
			break;
		case 999:
			respDesc =  "请求正在处理中";
			break;
		default:
			respDesc =  "";
			break;
		}
		return respDesc;

	}
}
