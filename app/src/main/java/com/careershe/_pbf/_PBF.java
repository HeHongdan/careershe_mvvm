package com.careershe._pbf;

/**
 * 类描述：PBF（按功能分包 Package By Feature）。
 *
 * PBF 与 PBL（按层分包 Package By Layer）相比较有如下优势：
 * <p>
 * package 内高内聚，package 间低耦合
 * 哪块要添新功能，只改某一个 package 下的东西，而PBL 需要改多个 package，非常麻烦。
 *
 * package 有私有作用域（package-private scope）
 * 原则上一个 package 下的不允许其他类访问都是不应该加上 public 的。
 *
 * 很容易删除功能
 * 统计发现新功能没人用，这个版本那块功能得去掉。如果是 PBL，得从功能入口到整个业务流程把受到牵连的所有能删的代码和 class 都揪出来删掉，一不小心就完蛋。如果是 PBF，好说，先删掉对应包，再删掉功能入口（删掉包后入口肯定报错了），完事。
 *
 * 高度抽象
 * 解决问题的一般方法是从抽象到具体，PBF 包名是对功能模块的抽象，包内的 class 是实现细节，符合从抽象到具体，而 PBL 弄反了。PBF 从确定 AppName 开始，根据功能模块划分 package，再考虑每块的具体实现细节，而 PBL 从一开始就要考虑要不要 dao 层，要不要 com 层等等。
 *
 * 只通过 class 来分离逻辑代码
 * PBL 既分离 class 又分离 package，而 PBF 只通过 class 来分离逻辑代码。
 *
 * package 的大小有意义了
 * PBL 中包的大小无限增长是合理的，因为功能越添越多，而 PBF 中包太大（包里 class 太多）表示这块需要重构（划分子包）。
 * </p>
 *
 * @author HeHongdan
 * @date 2021/2/3
 * @since v2021/2/3
 */
public class _PBF {
    /**
     * 1/ 包名:
     * 包名全部小写，不允许出现中文、大写字母或者下划线，前面为子模块命名，再根据 PBF 方式进行命名。
     */


    /**
     *
     * 2/ 类名:
     * 包名全部小写，不允许出现中文、大写字母或者下划线，前面为子模块命名，再根据 PBF 方式进行命名。
     * | 类        | 描述   |  例如  |
     * | --------   | -------- | -------- |
     * | 计算机      | $1600   |   5     |
     * | 手机        |   $12   |   12   |
     * | 管线        |    $1    |  234  |
     *
     * Activity 类             模块名 + Activity  闪屏页类 SplashActivity
     * Fragment 类             模块名 + Fragment	主页类 HomeFragment
     * Service 类              模块名 + Service	时间服务 TimeService
     * BroadcastReceiver 类    功能名 + Receiver  推送接收 JPushReceiver
     * ContentProvider 类      功能名 + Provider	ShareProvider
     * 自定义 View              功能名 + View/ViewGroup(组件名称)	ShapeButton
     * Dialog对话框            功能名+Dialog	ImagePickerDialog
     * Adapter 类             模块名 + Adapter	课程详情适配器 LessonDetailAdapter
     * 解析类                 功能名 + Parser	首页解析类 HomePosterParser
     * 工具方法类              功能名 + Utils 或 Manager	线程池管理类：ThreadPoolManager
     * 日志工具类：LogUtils（Logger 也可）
     * 打印工具类：PrinterUtils
     * 数据库类	功能名 + DBHelper	新闻数据库：NewsDBHelper
     * 自定义的共享基础类	Base + 基础	BaseActivity, BaseFragment
     * 抽象类	Base / Abstract 开头	AbstractLogin
     * 异常类	Exception 结尾	LoginException
     * 接口	able / ible 结尾 / I 开头	Runnable, Accessible ，ILoginView
     *
     */
}
