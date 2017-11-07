package com.uavs.common.directive;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("cuMoney")
public class MoneyDirective  implements TemplateDirectiveModel{

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String value="";
		if( null!=params.get("value")){
//			value=((SimpleScalar)params.get("value")).getAsString();
			value = params.get("value").toString();
		}
		String fmoney="";
		try {
			BigDecimal money=new BigDecimal(value);
			fmoney=this.fmoney(money, 2);
		} catch (Exception e) {}
		env.getOut().write(fmoney); 
	}
	public static String  fmoney(BigDecimal money,int n) throws Exception{
		String s="";
		BigDecimal nmoney=money.setScale(n,  RoundingMode.HALF_UP);  
		s=nmoney.toString();
        if (s == null || s.length() < 1) {  
            return "";  
        }  
        NumberFormat formater = null;  
        double num = Double.parseDouble(s);  
        if (n == 0) {  
            formater = new DecimalFormat("###,###");  
  
        } else {  
            StringBuffer buff = new StringBuffer();  
            buff.append("###,###.");  
            for (int i = 0; i < n; i++) {  
                buff.append("#");  
            }  
            formater = new DecimalFormat(buff.toString());  
        }  
        String result = formater.format(num);  
        if(result.indexOf(".") == -1)   {  
            result = result + ".00";  
        } else if(result.indexOf(".")==(result.length()-1-1)){  
            result = result+"0";  
        }  
        return result; 
	}
	public static void main(String argus[]){
		
		try {
			System.out.println(new MoneyDirective().fmoney(new BigDecimal("1033322320.233"), 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
