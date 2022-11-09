package com.example.demo.common.calculator;

import java.util.Stack;

public class Calculator implements MathSymbol {
    /**
     * 分析表达式
     * @param rogex
     * @param params 参数列表
     * @return 返回表达式计算结果
     */
    public double eval(String rogex,String... params) {
        char[] chars = rogex.toCharArray();
        StringBuilder sb = new StringBuilder();
        int tempCount = 0;
        for (int intJ = 0;intJ < params.length;intJ++) {
            String[] paramContext = params[intJ].split("=");
            String paramKey = paramContext[0];
            String paramValue = paramContext[1];
            for (int intI = 0;intI < chars.length;intI++) {
                if (String.valueOf(chars[intI]).equals("√")) {
                    //因为逆波兰算法需要两个值入栈，所以 解析表达式需要在√前面加入一个随意值，但是该值不进行运算
                    sb.append("1");
                }
                if (paramKey.indexOf(String.valueOf(chars[intI]))== -1 && !String.valueOf(chars[intI]).equals("%")){
                    sb.append(String.valueOf(chars[intI]));
                    tempCount = 0;
                }  else {
                    boolean b = !String.valueOf(chars[intI]).equals("%");
                    if (tempCount == 0 && b) {
                        sb.append(paramValue);
                        tempCount++;
                    }
                }
            }
        }
        rogex = sb.toString();
        System.out.println(rogex);
        //调用逆波兰转换
        String str = infix2Suffix(rogex);
        if(str == null) {
            throw new IllegalArgumentException("中缀表达式为空!");
        }
        String[] strings = str.split("\\s+");
        //计算后缀表达式
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < strings.length; i++) {
            if(!com.example.demo.common.calculator.Operator.isOperator(strings[i])) {
                stack.push(strings[i]);
            } else {
                com.example.demo.common.calculator.Operator op = com.example.demo.common.calculator.Operator.getInstance(strings[i]);
                double right = Double.parseDouble(stack.pop());
                double left = Double.parseDouble(stack.pop());
                double result = op.eval(left, right);
                stack.push(String.valueOf(result));
            }
        }
        return Double.parseDouble(stack.pop());
    }
     
    /**
     * 逆波兰转换 将中缀表达转换成后缀表达式
     * @param rogex
     * @return 后缀表达式
     */
    public String infix2Suffix(String rogex) {
        if(rogex == null) {
            return null;
        }
        char[] chs = rogex.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb = new StringBuilder(chs.length);
        boolean appendSeparator = false;
        boolean sign = true;
        for(int i = 0; i < chs.length; i++) {
            char c = chs[i];
            if(c == BLANK) {
                continue;
            }       
            if(appendSeparator) {
                sb.append(SEPARATOR);
                appendSeparator = false;
            }
            if(isSign(c) && sign) {
                sb.append(c);
                continue;
            }
            if(isNumber(c)) {
                sign = false;
                sb.append(c);
                continue;
            }            
            if(isLeftBracket(c)) {
                stack.push(c);
                continue;
            }
            if(isRightBracket(c)) {
                sign = false;
                while(stack.peek() != LEFT_BRACKET) {
                    sb.append(SEPARATOR);
                    sb.append(stack.pop());
                }
                stack.pop();
                continue;
            }
            appendSeparator = true;
            if(com.example.demo.common.calculator.Operator.isOperator(c)) {
                sign = true;
                if(stack.isEmpty() || stack.peek() == LEFT_BRACKET) {
                    stack.push(c);
                    continue;
                }
                int precedence = com.example.demo.common.calculator.Operator.getPrority(c);
                while(!stack.isEmpty() && com.example.demo.common.calculator.Operator.getPrority(stack.peek()) >= precedence) {
                    sb.append(SEPARATOR);
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while(!stack.isEmpty()) {
            sb.append(SEPARATOR);
            sb.append(stack.pop());
        }
        return sb.toString();
    }
     
    /**
     * 判断某个字符是否是正号或者负号
     * @param c
     * @return true or false
     */
    private boolean isSign(char c) {
        if(c == NEGATIVE_SIGN || c == POSITIVE_SIGN) {
            return true;
        }
        return false;
    }
     
    /**
     * 判断某个字符是否为数字或者小数点
     * @param c
     * @return true or false
     */
    private boolean isNumber(char c) {
        if((c >= '0' && c <= '9') || c == DECIMAL_POINT) {
            return true;
        }
        return false;
    }
     
    /**
     * 判断某个字符是否为左括号
     * @param c
     * @return true or false
     */
    private boolean isLeftBracket(char c) {
        return c == LEFT_BRACKET;
    }
     
    /**
     * 判断某个字符是否为右括号
     * @param c
     * @return true or false
     */
    private boolean isRightBracket(char c) {
        return c == RIGHT_BRACKET;
    }

  public static void main(String[] args) {
      Calculator calculator= new Calculator();
      double result = calculator.eval("((5/√Count)*100)%", "Count=10000");
      System.out.println(((result/100) * 10000));
  }
}
