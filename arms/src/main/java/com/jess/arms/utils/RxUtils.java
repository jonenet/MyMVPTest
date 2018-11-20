package com.jess.arms.utils;



import com.jess.arms.SomeType;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Func1;

/**
 * 作者:      周来
 * 包名:      com.dashuf.disp.utils
 * 工程名:    MyMVPTest
 * 时间:      2018/11/8
 * 说明:
 */
@SuppressWarnings("all")
public class RxUtils {
    /**
     * https://www.jianshu.com/p/c08bfc58f4b6
     * 采用 concat 操作符先读取缓存再通过网络请求获取数据，利用只有前一个发射器执行了onComplete才能执行下一个的特性
     * flatMap 实现多个网络请求依次依赖
     * zip  融合多个类型，同时zip还可以让每个发射器存在多个事件，每个发射器都出一个事件发射，然后匹配，如果有一个发射器没有发射数据就认为匹配不成功
     * distinct 使用hashMap来实现去重
     * debounce(500, TimeUnit.MILLISECONDS) 去抖,去除小于500ms的项
     * defer 让订阅到订阅这一步操作时才开始调用
     * last 只取最后一个值，及时取得值大于所有的值
     * merge 合并多个Observable
     * reduce
     *
     * @param iUiTask
     */
    public static void test(IUiTask iUiTask) {
        Observable<Integer> just1 = Observable.just(1, 2, 3);
        Observable<String> just2 = Observable.just("2", "3", "4");
        Observable<Float> just3 = Observable.just(3.0f, 4.0f);

        Observable.zip(just1, just2, just3, new Function3<Integer, String, Float, String>() {
            @Override
            public String apply(Integer integer, String s, Float aFloat) throws Exception {
                return integer + s + aFloat;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });


//        Observable.just(iUiTask).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(iUiTask1 -> {
//            iUiTask1.runOnUiThread();
//        }, throwable -> {
//            System.out.println(throwable.getMessage());
//        });
//        Observable.timer(3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                System.out.println("aLong = " + aLong);
//            }
//        });

//        Observable.just("1").map(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) throws Exception {
//                return Integer.valueOf(s);
//            }
//        }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                System.out.println("integer = " + integer);
//            }
//        });

//        Observable.just("1").filter(new Predicate<String>() {
//            @Override
//            public boolean test(String s) throws Exception {
//                return "1".equals(s);
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                System.out.println("s = " + s);
//            }
//        });

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//            }
//        }).concatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(Integer integer) throws Exception {
//                final List<String> list = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    list.add("I am value " + integer);
//                }
//                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d("qqq", s);
//            }
//        });

//        Observable.just(1).flatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(Integer integer) throws Exception {
//                final List<String> list = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    list.add("I am value " + integer);
//                }
//                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d("qqq", s);
//            }
//        });
        //map是对值的转换，flat map可对转换后的值再次操作
        Observable.just(1, "222", 3.0F).flatMap(new Function<Object, ObservableSource<Object>>() {
            @Override
            public ObservableSource<Object> apply(Object integer) throws Exception {
                //拿到第一次的结果后，接着执行第二次的操作
                ArrayList list = new ArrayList<>();
                list.add(integer);
                Observable stringObservable = Observable.fromIterable(list);
                return stringObservable;
//                return new ObservableSource<String>() {
//                    @Override
//                    public void subscribe(Observer<? super String> observer) {
//                        observer.onNext(String.valueOf(integer));
//                    }
//                };
            }
        }).distinct().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                System.out.println("s = " + s);
            }
        });


        rx.Observable.just(1).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return null;
            }
        });

//        Observable.just("1","2").flatMap(new Function<String, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(String s) throws Exception {
//                return null;
//            }
//        })
//       Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.io())
//               .subscribe(new Observer<Long>() {
//                   @Override
//                   public void onSubscribe(Disposable d) {
//
//                   }
//
//                   @Override
//                   public void onNext(Long aLong) {
//                       System.out.println("Along = " +aLong);
//                   }
//
//                   @Override
//                   public void onError(Throwable e) {
//
//                   }
//
//                   @Override
//                   public void onComplete() {
//
//                   }
//               });
    }



    public void runOnUiThread(IUiTask uiTask) {
        Observable.just(uiTask).observeOn(AndroidSchedulers.mainThread()).subscribe(IUiTask::runOnUiThread);
    }



    public interface IUiTask {
        void runOnUiThread();
    }

    public static void testSubscribe() {
        Observable.just(1).subscribeOn(Schedulers.io()).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                System.out.println("name = " + Thread.currentThread().getName());
                return String.valueOf(integer);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Exception {
                System.out.println("name = " + Thread.currentThread().getName());
            }
        });
    }

    //把多步操作融合成一步,转换器的擦做，返回一个转换器，转换器可将一个Observable转换成另一个Observable
    public static <T extends Integer, F extends String> ObservableTransformer<T, F> testCompose() {
        return new ObservableTransformer<T, F>() {
            @Override
            public ObservableSource<F> apply(Observable<T> upstream) {
                return upstream.map(new Function<T, F>() {
                    @Override
                    public F apply(T t) throws Exception {
                        Integer t1 = t;
                        return (F) (String.valueOf(t1) + " compose");
                    }
                });
            }
        };
    }

    public static void testDelay() {
        SomeType instance = new SomeType();
        Observable<String> value = instance.valueObservable();
        instance.setValue("Some Value");
        instance.setValue("Some test");
        value.subscribe(System.out::println);
    }

    public static void testMerge() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        Observable.merge(Observable.fromIterable(list), Observable.just(1002, 1005, 1006, 1007, 1008, 1009, 2000)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("integer = " + integer);
            }
        });
    }

    //转换 ，过滤 ， 自定义

//    OnSubscribeMap

}