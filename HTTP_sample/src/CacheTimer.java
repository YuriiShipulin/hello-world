/**
 * Created by Пользователь on 15.10.2015.
 */
class CacheTimer {
    long time;
    static byte[] data;
    final static long TIME_TO_LIVE = 60_000;

    CacheTimer(byte[] data) {
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    public static boolean timeCheck(long time) {
        return ((System.currentTimeMillis() - time) < TIME_TO_LIVE);
    }

    public long getTime() {
        return time;
    }

    public byte[] getData() {
        return data;
    }

}
