package com.hehongdan.statusbarcompat;

import com.hehongdan.statusbarcompat.compat.OsCompat;
import com.hehongdan.statusbarcompat.compat.OsCompatDef;
import com.hehongdan.statusbarcompat.compat.OsCompatFlyme;
import com.hehongdan.statusbarcompat.compat.OsCompatMiui;
import com.hehongdan.statusbarcompat.compat.OsCompatOppo;
import com.hehongdan.statusbarcompat.utils.OsUtils;

/**
 * 类描述：判断系统工具。
 *
 * @author HeHongdan
 * @date 2021/1/16
 * @since v2021/1/16
 *
 *
 * @author CuiZhen
 * @date 2019/11/17
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
class OsCompatHolder {

    private static OsCompat sOsCompat = null;

    static OsCompat get() {
        if (sOsCompat == null) {
            if (OsUtils.isMiui()) {
                sOsCompat = new OsCompatMiui();
            } else if (OsUtils.isFlyme()) {
                sOsCompat = new OsCompatFlyme();
            } else if (OsUtils.isOppo()) {
                sOsCompat = new OsCompatOppo();
            } else {
                sOsCompat = new OsCompatDef();
            }
        }
        return sOsCompat;
    }
}
