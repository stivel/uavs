package com.uavs.common.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	/**
	 * 字符串隐藏部分内容方法
	 * 
	 * @param str
	 *            待处理字符串
	 * @param start
	 *            前面保留长度
	 * @param end
	 *            末尾保留长度
	 * @return 处理过的字符串
	 */
	public static String formatStringByRange(String str, int start, int end) {
		if (StringUtils.isEmpty(str) || str.length() <= start + end) {
			return str;
		}
		int startNum = str.length() - start - end;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (i >= start && i < start + startNum) {
				sb.append("*");
				continue;
			}
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 * @author zhoujie
	 * @date 2017年3月27日 下午2:13:29
	 */
	public static Boolean isBlank(Object obj) {
		if (null == obj || StringUtils.isBlank(obj.toString())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断对象是否不为空
	 * 
	 * @param obj
	 * @return
	 * @author zhoujie
	 * @date 2017年3月27日 下午2:13:33
	 */
	public static Boolean isNotBlank(Object obj) {
		if (isBlank(obj)) {
			return false;
		}
		return true;
	}

	/**
	 * 获取随机名称
	 * 
	 * @param shopPrefix
	 *            名称前缀
	 * @param length
	 *            后缀随机数长度
	 * @return
	 * @author zhoujie
	 * @date 2017年6月3日 下午3:02:38
	 */
	public static String randomName(String shopPrefix, int length) {
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			int r = (int) (Math.random() * 2);
			int rn1 = (int) (48 + Math.random() * 10);
			int rn2 = (int) (97 + Math.random() * 26);

			switch (r) {
			case 0:
				builder.append((char) rn1);
				break;
			case 1:
				builder.append((char) rn2);
				break;
			}
		}
		return shopPrefix + builder.toString();
	}

	/**
	 * 获取店铺简介
	 * @return
	 * @author zhoujie
	 * @date 2017年6月3日 下午3:15:52
	 */
	public static String randomIntroduction() {
		String[] introduction = new String[] { "拥有多年行业从业经验，丰富的行业经验可以为您提供更专业、更实用的咨询服务。", "渊博的行业知识储备为您提供更专业咨询服务，相约面包云！",
				"置身行业领域研究多年，有丰厚的行业知识储备以及行业展望，我在这里用专业解答您的疑惑。", "丰富的学识研究基础能帮助您更好的进行抉择，解答您的疑惑，我在面包云等您！", "十多年深耕行业，为您提供专业的咨询服务！",
				"本人做事儿准则：一切以客户为出发点，为您专业解惑！", "宗旨：客户至上、诚信做事。曾有多年行业从业经验，具备较强的行业学识和经验。", "资深行业专家，深耕行业多年，对本行业有深入研究，专业咨询服务等着您。",
				"多年行业从业经验和行业研究基础，为您提供前沿、专业的咨询服务。", "专攻行业多年，拥有最扎实可靠的信息和知识。愿秉持诚信专业的态度，为您提供所需的服务。", "毕业于知名大学，拥有多年从业经验，希望能为您提供专业的服务！",
				"初心：坚持只给客户最好的，用个人丰富的行业知识，为您答疑解惑！", "来到面包云，只为给更多家庭带来幸福保障。", "坚持诚心做人，诚信做事的原则，愿用多年行业经验，给您最科学的咨询服务体验！",
				"宗旨：量身打造最适合您的咨询服务，不求最好，只求更好！", "浸营行业数年，丰富的行业从业经验和行业知识积淀，为您提供最适用的咨询服务。", "数年行业深耕，丰厚的行业知识储备，诚心为您提供专业服务。",
				"用丰富行业经验，为您解决问题，我在面包云等你！", "用我数年对行业的热忱之心，奉献给您最真实可靠的行业咨询！" };
		return introduction[(int) (Math.random() * 20)];
	}

}
