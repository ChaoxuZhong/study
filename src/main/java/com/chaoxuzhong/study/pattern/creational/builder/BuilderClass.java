package com.chaoxuzhong.study.pattern.creational.builder;

import org.springframework.util.StringUtils;

public class BuilderClass {
    private String notRequired;
    private String required;
    private int odd;
    private String a;
    private String bDependsOnA;

    private BuilderClass(Builder builder) {
        this.notRequired = builder.notRequired;
        this.required = builder.required;
        this.odd = builder.odd;
        this.a = builder.a;
        this.bDependsOnA = builder.bDependsOnA;
    }

    public static class Builder {
        private String notRequired;
        private String required;
        private int odd;
        private String a;
        private String bDependsOnA;

        public BuilderClass build() {
            if (StringUtils.isEmpty(a) && !StringUtils.isEmpty(bDependsOnA)) {
                throw new RuntimeException("if a bDependsOnA is not empty, a must not be empty");
            }
            if (StringUtils.isEmpty(required)) {
                throw new IllegalArgumentException("required required");
            }
            return new BuilderClass(this);
        }

        public Builder setNotRequired(String notRequired) {
            this.notRequired = notRequired;
            return this;
        }

        public Builder setRequired(String required) {
            this.required = required;
            return this;
        }

        public Builder setOdd(int odd) {
            if((odd & 1) == 0){
                throw new IllegalArgumentException("odd required odd");
            }
            this.odd = odd;
            return this;
        }

        public Builder setA(String a) {
            this.a = a;
            return this;
        }

        public Builder setBDependsOnA(String b) {
            this.bDependsOnA = b;
            return this;
        }
    }

    @Override
    public String toString() {
        return "AnyClass{" +
                "notRequired='" + notRequired + '\'' +
                ", required='" + required + '\'' +
                ", odd=" + odd +
                ", a='" + a + '\'' +
                ", bDependsOnA='" + bDependsOnA + '\'' +
                '}';
    }
}
