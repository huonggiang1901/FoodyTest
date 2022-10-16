package common.helpers;

import org.testng.Assert;

/**
 * This class supports verifying check point, screenshots for steps and failed check points.
 * チェックポイントの検証、ステップのスクリーンショット、および失敗したチェックポイントをサポートする。
 *
 */
public class AssertHelper {

    public static void checkEqual(Object expected, Object actual, String message) {
        Assert.assertEquals(expected, actual, message);
    }

    public static void checkEqual(Object expected, Object actual) {
        Assert.assertEquals(expected, actual);
    }

    public static void checkTrue(boolean actual, String message) {
        Assert.assertTrue(actual, message);
    }

    public static void checkTrue(boolean actual) {
        Assert.assertTrue(actual);
    }

    public static void checkFalse(boolean actual, String message) {
        Assert.assertFalse(actual, message);
    }

    public static void checkFalse(boolean actual) {
        Assert.assertFalse(actual);
    }

    public static void checkNotEqual(Object expected, Object actual, String message) {
        Assert.assertEquals(expected, actual, message);
    }

    public static void checkNotEqual(Object expected, Object actual) {
        Assert.assertEquals(expected, actual);
    }
}
