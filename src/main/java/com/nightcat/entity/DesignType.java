package com.nightcat.entity;

public enum  DesignType {

    //十三项
    UNDEFINDED("未设置"),
    Types_1("策划"),
    Types_2("规划设计"),
    Types_3("建筑设计"),
    Types_4("结构设计"),
    Types_5("给排水"),
    Types_6("电气设计"),
    Types_7("暖通设计"),
    Types_8("景观设计"),
    Types_9("室内设计"),
    Types_10("软装设计"),
    Types_11("项目经理"),
    Types_12("概预算"),
    Types_13("审图");


    String text;
    DesignType(String text){
        this.text = text;
    }


    public String getText() {
        return text;
    }

}
