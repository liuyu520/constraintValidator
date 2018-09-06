package wx;

import com.kunlunsoft.util.RateLimitUtil;
import com.kunlunsoft.wxcp.mp.aes.AesException;
import com.kunlunsoft.wxcp.mp.aes.WXBizMsgCrypt;
import com.string.widget.util.ValueWidget;
import com.time.util.TimeHWUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Test2 {
    /***
     * 2018/7/23
     */
//    @Test
    public final void test_Descrpt() {
        WXBizMsgCrypt wxcpt = null;
        try {
//            wxcpt = new WXBizMsgCrypt("LWGlsuxr", "NDa2M6bTU9Qm8YCCvJvMVXExwBQunAU52ykGDjvuoml", "ww92653858095c7bd4");
            wxcpt = new WXBizMsgCrypt("RKpp9U", "W5itiVekWSy2usL9taeaoDSWJJAUji5UemtBa1IPOTS", "ww92653858095c7bd4", "");
            try {
                String sEchoStr = wxcpt.decrypt("jrS/g5LKT7erKl5yZb/puSDJJhTUxV7onIZcHOVaFlkLbc1VbuO052twSPlGo82clXFWTghRwP3eF0D0pv36CA==");
//            String decrypted= wxBizMsgCrypt.decrypt(echostr);
                System.out.println("sEchoStr :" + sEchoStr);
            } catch (AesException e) {
                e.printStackTrace();
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
    }

    /***
     * 2018/9/6
     */
    @Test
    public final void test_RateLimit() {
        String[] formatedDates = new String[]{
                "2018-09-06 09:30:13"
                , "2018-09-06 09:30:15"
                , "2018-09-06 09:30:17"
        };
        String nowStr = "2018-09-06 09:30:19";
        boolean exceed = isExceedLimit(formatedDates, nowStr);
        Assert.assertTrue(exceed);
        System.out.println("exceed :" + exceed);
    }

    @Test
    public final void test_RateLimit2() {
        String[] formatedDates = new String[]{
                "2018-09-06 09:30:13"
                , "2018-09-06 09:30:15"
                , "2018-09-06 09:30:17"
        };
        String nowStr = "2018-09-06 09:30:20";
        boolean exceed = isExceedLimit(formatedDates, nowStr);
        Assert.assertTrue(exceed);
        System.out.println("exceed :" + exceed);
    }

    @Test
    public final void test_RateLimit3() {
        String[] formatedDates = new String[]{
                "2018-09-06 09:30:13"
                , "2018-09-06 09:30:15"
                , "2018-09-06 09:30:17"
        };
        String nowStr = "2018-09-06 09:30:21";
        boolean exceed = isExceedLimit(formatedDates, nowStr);
        Assert.assertTrue(exceed);
        System.out.println("exceed :" + exceed);
    }

    @Test
    public final void test_RateLimit4() {
        String[] formatedDates = new String[]{
                "2018-09-06 09:30:13"
                , "2018-09-06 09:30:17"
        };
        String nowStr = "2018-09-06 09:30:18";
        boolean exceed = isExceedLimit(formatedDates, nowStr);
        Assert.assertFalse(exceed);
        System.out.println("exceed :" + exceed);
    }


    @Test
    public final void test_RateLimit5() {
        String[] formatedDates = new String[]{
                "2018-09-06 09:30:13"
                , "2018-09-06 09:30:15"
                , "2018-09-06 09:30:17"
        };
        String nowStr = "2018-09-06 09:31:21";
        boolean exceed = isExceedLimit(formatedDates, nowStr);
        Assert.assertFalse(exceed);
        System.out.println("exceed :" + exceed);
    }

    private static boolean isExceedLimit(String[] formatedDates, String nowStr) {
        long now = TimeHWUtil.getDate4Str(nowStr).getTime();
        Object[] timestamps = Arrays.stream(formatedDates).map((String str) -> {
            return String.valueOf(TimeHWUtil.getDate4Str(str).getTime());
        }).toArray();
        String[] strings = convert2StrArr(timestamps);

        return RateLimitUtil.checkLimit(now, 3, 20, Arrays.asList(strings));
    }

    public static String[] convert2StrArr(Object[] objects) {
        if (ValueWidget.isNullOrEmpty(objects)) {
            return new String[]{};
        }
        int length = objects.length;
        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            strings[i] = (String) objects[i];
        }
        return strings;
    }
}
