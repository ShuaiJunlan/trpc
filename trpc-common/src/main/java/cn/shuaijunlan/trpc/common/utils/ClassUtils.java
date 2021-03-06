package cn.shuaijunlan.trpc.common.utils;

import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Set;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:49 AM 3/1/19.
 */
public class ClassUtils {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Set<Class<?>> getAllSubClassByInterfaceName(String name) throws ClassNotFoundException {
        Reflections reflections = new Reflections(name.split("\\.")[0]);
        return reflections.getSubTypesOf((Class<Object>) Class.forName(name));
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ArrayList<Class> getAllClassByInterface(Class clazz) {
        ArrayList<Class> list = new ArrayList<>();
        //判断是否是一个接口
        if (clazz.isInterface()) {
            try {
                ArrayList<Class> allClass = getAllClass(clazz.getPackage().getName());
                /**
                 * 循环判断路径下的所有类是否实现了指定的接口
                 * 并且排除接口类自己
                 */
                for (int i = 0; i < allClass.size(); i++) {
                    /**cn/shuaijunlan/trpc/common/utils/ClassUtils.java:22
                     * 判断是不是同一个接口
                     * 该方法的解析，请参考博客：
                     * http://blog.csdn.net/u010156024/article/details/44875195
                     */
                    if (clazz.isAssignableFrom(allClass.get(i))) {
                        //自身并不加进去
                        if (!clazz.equals(allClass.get(i))) {
                            list.add(allClass.get(i));
                        } else {

                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("出现异常");
            }
        } else {
            //如果不是接口不作处理
        }
        return list;
    }

    @SuppressWarnings("rawtypes")
    private static ArrayList<Class> getAllClass(String packageName) {
        ArrayList<Class> list = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        try {
            ArrayList<File> fileList = new ArrayList<>();
            /**
             * 这里面的路径使用的是相对路径
             * 如果大家在测试的时候获取不到，请理清目前工程所在的路径
             * 使用相对路径更加稳定！
             * 另外，路径中切不可包含空格、特殊字符等！
             * 本人在测试过程中由于空格，吃了大亏！！！
             */
            Enumeration<URL> enumeration = classLoader.getResources("../bin/" + path);
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                fileList.add(new File(url.getFile()));
            }
            for (int i = 0; i < fileList.size(); i++) {
                list.addAll(findClass(fileList.get(i), packageName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static ArrayList<Class> findClass(File file, String packagename) {
        ArrayList<Class> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        File[] files = file.listFiles();
        for (File file2 : files) {
            if (file2.isDirectory()) {
                assert !file2.getName().contains(".");//添加断言用于判断
                ArrayList<Class> arrayList = findClass(file2, packagename + "." + file2.getName());
                list.addAll(arrayList);
            } else if (file2.getName().endsWith(".class")) {
                try {
                    //保存的类文件不需要后缀.class
                    list.add(Class.forName(packagename + '.' + file2.getName().substring(0,
                            file2.getName().length() - 6)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}
