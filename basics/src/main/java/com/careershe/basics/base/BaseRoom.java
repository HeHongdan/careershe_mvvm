package com.careershe.basics.base;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.careershe.common.C;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
//@Database(entities = {WeChatBean.class}, version = 1, exportSchema = false)
public abstract class BaseRoom extends RoomDatabase {

    /** 第一层锁：保证变量可见性 */
    private volatile static BaseRoom instance ;

    private BaseRoom(){
    }

    /**
     * 获取本类单例。
     *
     * @param context 上下文(应用)。
     * @return Room数据库实例。
     */
    public static BaseRoom getInstance(final Context context) {
        //第一次判空：无需每次都加锁，提高性能
        if (instance == null) {
            //第二层锁：保证线程同步
            synchronized (BaseRoom.class) {
                //第二次判空：避免多线程同时执行getInstance()（此）方法，产生多个instance（本类）对象
                if (instance == null) {
                    instance = Room
                            .databaseBuilder(context, BaseRoom.class, C.DB_NAME)
                            .build();
                }
            }
        }
        return instance;
    }

}
