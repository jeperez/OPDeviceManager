package okio;

import java.nio.charset.Charset;

final class Util {
    public static final Charset UTF_8;

    static {
        UTF_8 = Charset.forName("UTF-8");
    }

    private Util() {
    }

    public static void checkOffsetAndCount(long size, long offset, long byteCount) {
        int i;
        if ((offset | byteCount) < 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (i == 0) {
            if ((offset > size ? 1 : 0) == 0) {
                if ((size - offset >= byteCount ? 1 : 0) != 0) {
                    return;
                }
            }
        }
        throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[]{Long.valueOf(size), Long.valueOf(offset), Long.valueOf(byteCount)}));
    }

    public static short reverseBytesShort(short s) {
        int i = s & 65535;
        return (short) (((65280 & i) >>> 8) | ((i & 255) << 8));
    }

    public static int reverseBytesInt(int i) {
        return ((((-16777216 & i) >>> 24) | ((16711680 & i) >>> 8)) | ((65280 & i) << 8)) | ((i & 255) << 24);
    }

    public static void sneakyRethrow(Throwable t) {
        sneakyThrow2(t);
    }

    private static <T extends Throwable> void sneakyThrow2(Throwable t) throws Throwable {
        throw t;
    }

    public static boolean arrayRangeEquals(byte[] a, int aOffset, byte[] b, int bOffset, int byteCount) {
        for (int i = 0; i < byteCount; i++) {
            if (a[i + aOffset] != b[i + bOffset]) {
                return false;
            }
        }
        return true;
    }
}
