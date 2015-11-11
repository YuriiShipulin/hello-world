import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Пользователь on 16.10.2015.
 */
public class ChunkDecoder extends InputStream {

    private int chunkSize;
    private byte[] buf;
    protected ByteArrayInputStream is = new ByteArrayInputStream(buf);

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public byte[] getBuf() {
        return buf;
    }

    public void setBuf(byte[] buf) {
        this.buf = buf;
    }


    public ChunkDecoder(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public ChunkDecoder(int chunkSize, byte[] buf) {
        this.chunkSize = chunkSize;
        this.buf = buf;
    }

    @Override
    public int read() throws IOException {
        return is.read();
    }

    public int read(byte b[]) throws IOException {
        return is.read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        return is.read(b, off, len);
    }

    @Override
    public int available() throws IOException {
        return is.available();
    }

    @Override
    public void close() throws IOException {
        is.close();
    }
}
