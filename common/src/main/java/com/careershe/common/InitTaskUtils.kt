package com.careershe.common

import android.app.Application
import android.os.AsyncTask
import android.os.Process
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
 * 类描述：管理（排序、处理、执行）初始化任务工具。
 *
 * @author HHD
 * @version v2020/11/29
 * @date 2020/11/29
 */
class InitTaskRunner(private val application: Application) {

    /** 初始化任务的集合。 */
    private val mTasks: ArrayList<InitTask> = arrayListOf()

    /**
     * 添加初始化任务。
     *
     * @param task 任务。
     * @return 初始化任务。
     */
    fun add(task: InitTask): InitTaskRunner {
        mTasks.add(task)
        return this
    }

    /**
     * 排序任务、处理任务、执行任务。
     */
    fun run() {
        val isMainProcess = isMainProcess()
        val syncTasks: ArrayList<InitTask> = arrayListOf()
        val asyncTasks: ArrayList<InitTask> = arrayListOf()
        for (task in mTasks) {
            if (!isMainProcess && task.onlyMainProcess()) {
                continue
            }
            if (task.sync()) {
                syncTasks.add(task)
            } else {
                asyncTasks.add(task)
            }
        }
        runSync(syncTasks)
        runAsync(asyncTasks)
    }

    /**
     * 排序任务并初始化任务。
     *
     * @param tasks 任务集合。
     */
    private fun runSync(tasks: ArrayList<InitTask>) {
        tasks.sortBy { it.level() }
        for (task in tasks) {
            try {
                task.init(application)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 处理任务并执行任务。
     *
     * @param tasks 任务集合。
     */
    private fun runAsync(tasks: ArrayList<InitTask>) {
        val tasksMap = hashMapOf<String, ArrayList<InitTask>>()
        for (task in tasks) {
            val name = task.asyncTaskName()
            var list = tasksMap[name]
            if (list == null) {
                list = arrayListOf()
                tasksMap[name] = list
            }
            list.add(task)
        }
        for (map in tasksMap) {
            val task = map.value
            AsyncRunner(application, task).execute()
        }
    }

    /**
     * 判断是否在主线程。
     *
     * @return 是否在主线程。
     */
    private fun isMainProcess(): Boolean {
        return application.packageName == getCurrentProcessName()
    }

    /**
     * 获取当前进程名称。
     *
     * @return 进程名称。
     */
    private fun getCurrentProcessName(): String? {
        return try {
            val file = File("/proc/" + Process.myPid() + "/" + "cmdline")
            val mBufferedReader = BufferedReader(FileReader(file))
            val processName = mBufferedReader.readLine().trim { it <= ' ' }
            mBufferedReader.close()
            processName
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

/**
 * 多线程任务（后台执行）。
 */
class AsyncRunner(private val application: Application, private val tasks: ArrayList<InitTask>) : AsyncTask<Unit, Unit, Unit>() {

    /**
     * 后台执行任务。
     *
     * @param params
     */
    override fun doInBackground(vararg params: Unit?) {
        tasks.sortBy { it.level() }
        for (task in tasks) {
            try {
                task.init(application)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}

/**
 * 异步初始化任务。
 */
abstract class AsyncInitTask : InitTask {
    override fun sync(): Boolean {
        return false
    }

    override fun level(): Int {
        return 0
    }

    override fun onlyMainProcess(): Boolean {
        return true
    }

    override fun asyncTaskName(): String {
        return toString()
    }
}

/**
 * 同步初始化任务。
 */
abstract class SyncInitTask : InitTask {
    override fun sync(): Boolean {
        return true
    }

    override fun level(): Int {
        return 0
    }

    override fun onlyMainProcess(): Boolean {
        return true
    }

    override fun asyncTaskName(): String {
        return ""
    }
}

/**
 * 初始化任务接口。
 */
interface InitTask {

    /**
     * 是否同步。
     *
     * @return 是否同步。
     */
    fun sync(): Boolean

    /**
     * 同步任务名称。
     *
     * @return 任务名称。
     */
    fun asyncTaskName(): String

    /**
     * 优先级别。
     *
     * @return 优先级别。
     */
    fun level(): Int

    /**
     * 任务只在主线程执行。
     *
     * @return 是否主线程。
     */
    fun onlyMainProcess(): Boolean

    /**
     * 初始化任务。
     *
     * @param application 应用。
     */
    fun init(application: Application)
}