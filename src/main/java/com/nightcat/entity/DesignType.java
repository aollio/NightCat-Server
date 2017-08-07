package com.nightcat.entity;

public enum  DesignType {

    //十三项
    UNDEFINDED("未设置"),
    Types_1("概预算"),
    Types_2("项目经理"),
    Types_3("给排水设计"),
    Types_4("策划"),
    Types_5("规划设计"),
    Types_6("建筑设计"),
    Types_7("电气设计"),
    Types_8("结构设计"),
    Types_9("审图"),
    Types_10("软装设计"),
    Types_11("室内设计"),
    Types_12("暖通设计"),
    Types_13("景观设计");


    String text;
    DesignType(String text){
        this.text = text;
    }


    public String getText() {
        return text;
    }

}
