package com.yemao.other.text;

public class BuilderTest {

    static abstract class OriBuilder<RETURN extends OriBuilder> {

        StringBuilder builder;

        OriBuilder(StringBuilder builder) {
            this.builder = builder;
        }

        RETURN append(String append) {
            builder.append(append);
            return getQuery();
        }

        abstract RETURN getQuery();

        String list() {
            return builder.toString();
        }

    }


    public static void main(String[] args) {
        SuperBuilder superBuilder = new SuperBuilder(new StringBuilder());
        superBuilder.append("custom");
        superBuilder.appendSuper();
        System.out.println(superBuilder.list());

        ClideBuilder clideBuilder = new ClideBuilder(new StringBuilder());
        clideBuilder.append("custom").appendClide().append("csadas");
        System.out.println(clideBuilder.list());
    }

    static class SuperBuilder extends OriBuilder<SuperBuilder> {

        SuperBuilder(StringBuilder builder) {
            super(builder);
        }

        SuperBuilder appendSuper() {
            builder.append("super");
            return this;
        }

        @Override
        SuperBuilder getQuery() {
            return this;
        }
    }

    static class ClideBuilder extends OriBuilder<ClideBuilder> {

        ClideBuilder(StringBuilder builder) {
            super(builder);
        }

        ClideBuilder appendClide() {
            builder.append("super");
            return this;
        }

        @Override
        ClideBuilder getQuery() {
            return this;
        }
    }

}
