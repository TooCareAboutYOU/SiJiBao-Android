package com.tuoniao.sijibao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.util.TimeUtils;

import android.icu.util.TimeUnit;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tuoniao.sijibao.model.UserDataBean;
import com.tuoniao.sijibao.widgets.FreeTrialDialogViews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityS";

    FreeTrialDialogViews mFreeTrialDialogViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFreeTrialDialogViews = findViewById(R.id.free_hint_view);

        findViewById(R.id.tv_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFreeTrialDialogViews.show("Hello World!");
            }
        });

        UserFactory<UserDataBean> factory = UserDataBean::new;
        UserDataBean bean = factory.create("姓", "名-非工作日加班，上/下班忘打开，补卡申请");

        //Predicate接口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Predicate<String> predicate = (s) -> s.length() > 0;
            predicate.test("Hello Java8");

            Predicate<Boolean> pb = Objects::nonNull;
            pb.test(true);
        }

        //Function 接口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Function<String, Integer> userBea = Integer::new;
            Function<String, String> backToString = userBea.andThen(String::valueOf);
            String s = backToString.apply("123");
            Log.i(TAG, "Function: " + s);
        }

        //Supplier 接口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Supplier<UserDataBean> beanSupplier = UserDataBean::new;
            UserDataBean bean1 = beanSupplier.get();
        }

        //Consumer 接口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Consumer<UserDataBean> consumer = (userDataBean) -> Log.i(TAG, "哈哈哈哈");
            consumer.accept(new UserDataBean());
        }

        //Comparator 接口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Comparator<UserDataBean> comparable = (u1, u2) -> u1.getFirstName().compareTo(u2.getFirstName());
            UserDataBean u1 = new UserDataBean("Hello", "World");
            UserDataBean u2 = new UserDataBean("Welcome", "Android");
            comparable.compare(u1, u2);
            comparable.reversed().compare(u1, u2);
        }

        //Optional 接口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Optional<String> optionalS = Optional.of("Tom");
            optionalS.isPresent();
            optionalS.get();
            optionalS.orElse("FallBack");
            optionalS.ifPresent(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    Log.i(TAG, "Optional：" + s.charAt(0));
                }
            });
        }

        //Stream 接口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            List<String> stringList = new ArrayList<>();
            stringList.add("ddd2");
            stringList.add("aaa2");
            stringList.add("bbb1");
            stringList.add("aaa1");
            stringList.add("bbb3");
            stringList.add("ccc");
            stringList.add("bbb2");
            stringList.add("ddd1");
            stringList.add("aaa3");
            stringList.add("ccc2");

            //过滤性
            stringList.stream()
                    .filter(new Predicate<String>() {
                        @Override
                        public boolean test(String s) {
                            return s.startsWith("a");
                        }
                    }).forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    Log.i(TAG, "filter: " + s.toString());
                }
            });

            //排序
            stringList.stream()
                    .sorted()
                    .filter(new Predicate<String>() {
                        @Override
                        public boolean test(String s) {
                            return s.startsWith("a");
                        }
                    }).forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    Log.i(TAG, "sorted: " + s.toString());
                }
            });

            //Map 映射
            stringList.stream()
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String s) {
                            return s.toUpperCase();
                        }
                    }).sorted(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    Log.i(TAG, "compare: " + o1.toString() + "\t\t" + o2);
                    return o2.compareTo(o1);
                }
            }).forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    Log.i(TAG, "map: " + s.toString());
                }
            });
            //1、Map - putIfAbsent
            Map<Integer, String> map = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                map.putIfAbsent(i, "val-" + i);
            }
            map.forEach(new BiConsumer<Integer, String>() {
                @Override
                public void accept(Integer integer, String s) {
                    Log.i(TAG, "map: key = " + integer + ", s = " + s);
                }
            });
            //2、computeIfPresent等同于 map.get(3); 且重新赋值了
            String s1 = map.computeIfPresent(3, new BiFunction<Integer, String, String>() {
                @Override
                public String apply(Integer integer, String s) {
                    return "computeIfPresent: key = " + integer + ", s = " + s;
                }
            });
            Log.i(TAG, "打印computeIfPresent：" + s1);
            //3、computeIfPresent等同于 map.containsKey(9); 且重新赋值了
            String s2 = map.computeIfPresent(9, new BiFunction<Integer, String, String>() {
                @Override
                public String apply(Integer integer, String s) {
                    return null;
                }
            });
            Log.i(TAG, "打印computeIfPresent：" + s2);
            //3、computeIfAbsent等同于 map.get(3);
            String s3 = map.computeIfAbsent(4, new Function<Integer, String>() {
                @Override
                public String apply(Integer integer) {
                    return "bam";
                }
            });
            Log.i(TAG, "打印computeIfAbsent：" + s3);
            //4、删除一个键值对全都匹配上
            boolean removeBool1 = map.remove(7, "val-7");
            Log.i(TAG, "移除键值对都匹配上：" + removeBool1 + ", 再次获取：" + map.get(7));

            boolean removeBool2 = map.remove(8, "val-88");
            Log.i(TAG, "移除键值对匹配不上：" + removeBool2 + ", 再次获取：" + map.get(8));

            String result = map.getOrDefault(144, "no found");
            Log.i(TAG, "打印getOrDefault：" + result);

            //Merge做的事情是如果键名不存在则插入，否则则对原键对应的值做合并操作并重新插入到map中
            map.merge(0, "(来自合并-1)", new BiFunction<String, String, String>() {
                @Override
                public String apply(String s, String s2) {
                    return s.concat(s2);
                }
            });
            String resultMerge = map.merge(0, "(来自合并-2)", new BiFunction<String, String, String>() {
                @Override
                public String apply(String s, String s2) {
                    return s.concat(s2);
                }
            });
            Log.i(TAG, "打印merge：" + resultMerge);


            //Match 匹配
            boolean anyBool = stringList.stream()
                    .anyMatch(new Predicate<String>() {
                        @Override
                        public boolean test(String s) {
                            return s.startsWith("a");
                        }
                    });
            Log.i(TAG, "anyBool：" + anyBool);

            boolean allBool = stringList.stream()
                    .allMatch(new Predicate<String>() {
                        @Override
                        public boolean test(String s) {
                            return s.startsWith("a");
                        }
                    });
            Log.i(TAG, "allBool：" + allBool);

            boolean nonBool = stringList.stream()
                    .noneMatch(new Predicate<String>() {
                        @Override
                        public boolean test(String s) {
                            return s.startsWith("a");
                        }
                    });
            Log.i(TAG, "nonBool：" + nonBool);


            //Count 计数
            long count = stringList.stream()
                    .filter(new Predicate<String>() {
                        @Override
                        public boolean test(String s) {
                            return s.startsWith("a");
                        }
                    })
                    .count();
            Log.i(TAG, "count：" + count);


            //Reduce 规约
            Optional<String> optional = stringList.stream()
                    .sorted()
                    .reduce(new BinaryOperator<String>() {
                        @Override
                        public String apply(String s, String s2) {
                            return s + " # " + s2;
                        }
                    });
            optional.ifPresent(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    Log.i(TAG, "Reduce：" + s);
                }
            });
        }

        //并行Streams
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            //创建数据
            int max = 100;
            List<String> stringList = new ArrayList<>(max);
            for (int i = 0; i < max; i++) {
                stringList.add(UUID.randomUUID().toString());
            }
            //串行排序 打印排序的时间
            long time0 = System.nanoTime();
            long count = stringList.stream().sorted().count();
            long time1 = System.nanoTime();
            long tResult = time1 - time0;
            Log.i(TAG, "串行排序时间：" + tResult);


            //并行排序 打印排序的时间
            long time00 = System.nanoTime();
            long count1 = stringList.parallelStream().sorted().count();
            long time11 = System.nanoTime();
            long tResult1 = time11 - time00;
            Log.i(TAG, "并行排序时间：" + tResult1);
        }

        ScannerTest();

    }

    /**
     * 通过 Scanner 类来获取用户的输入。
     */
    private void ScannerTest() {
        AppCompatEditText tv = findViewById(R.id.acTvMsg);
        Scanner scanner = new Scanner(System.in);
        //从键盘接收数据

        Log.i(TAG, "next方式接收>>>>>>>>>>>>");
        //判断是否还有输入
        if (scanner.hasNext()) {
            Log.i(TAG, "next: "+scanner.next());
        }
        Log.i(TAG, "next方式接收>>>>>>>>>>>>");
        scanner.close();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFreeTrialDialogViews.releaseView();
    }
}