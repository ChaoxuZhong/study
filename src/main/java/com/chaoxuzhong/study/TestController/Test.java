package com.chaoxuzhong.study.TestController;


public class Test {

    public static void main(String[] srag) throws NoSuchFieldException, IllegalAccessException {
//        Object vo = getVo();
//
//        Class clazz = TestObject.class;
//        Field field = clazz.getDeclaredField("value");
//        field.setAccessible(true);
//        System.out.println(field.get(vo));
//        Long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            field.get(vo);
//        }
//        Long endTime = System.currentTimeMillis();
//        System.out.println("耗时：" + (endTime- startTime));

        System.out.println(String.format("%.3f", null));


    }

    private static Object getVo() {
        TestObject vo = new TestObject();
        vo.setValue("value");
        return vo;
    }
}
