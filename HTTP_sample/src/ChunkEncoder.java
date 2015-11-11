import java.io.*;

/**
 * Created by Пользователь on 16.10.2015.
 */
public class ChunkEncoder extends OutputStream implements Closeable, Flushable {
    private int chunkSize;
    byte[] bytes = new byte[8192];
    int count;

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public ChunkEncoder(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public byte[] getBytesArray() {
        return bytes;
    }

    public int getChunkSize() {
        return chunkSize;
    }


    @Override
    public void write(int buf) throws IOException {
        bytes[count++] = (byte)buf;
    }

    public void write(byte[] buf) throws IOException {
        write(buf, 0, buf.length);
    }

    public void write(byte[] buf, int off, int len) throws IOException {
        if (buf == null) {
            throw new NullPointerException();
        } else if ((off < 0) || (off > buf.length) || (len < 0) ||
                ((off + len) > buf.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        for (int i = 0; i < len; i++) {
            write(buf[off + i]);
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }
}
