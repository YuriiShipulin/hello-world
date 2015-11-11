
import java.io.*;
import java.util.List;

public class Chunker implements Processor {
    private int chunkSize;

    public Chunker(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public byte[] process(byte[] data, List<String> headers) {
        try {

            ChunkEncoder encoder = new ChunkEncoder(chunkSize);

            int n = data.length / chunkSize;
            int tail = data.length % chunkSize;
            int offset = 0;
            String head = Integer.toHexString(chunkSize) + "\r\n";

            for (int i = 0; i < n; i++) {
                encoder.write(head.getBytes());
                encoder.write(data, offset, chunkSize);
                encoder.write("\r\n".getBytes());
                offset += chunkSize;
            }
            if (tail > 0) {
                head = Integer.toHexString(tail) + "\r\n";
                encoder.write(head.getBytes());
                encoder.write(data, offset, tail);
                encoder.write("\r\n".getBytes());
            }

            encoder.write("0\r\n\r\n".getBytes());

            headers.add("Transfer-Encoding: chunked\r\n");

            return encoder.getBytesArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int value) {
        chunkSize = value;
    }
}
