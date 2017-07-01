package com.android.volley.multipart;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/29-13:22
 * Email: renweiwei@ufashion.com
 */
public abstract class BasePart implements Part {
    
    protected static final byte[] CRLF = EncodingUtils.getAsciiBytes(MultipartEntity.CRLF);
    
    protected interface IHeadersProvider {
        public String getContentDisposition();
        public String getContentType();
        public String getContentTransferEncoding();
    }
    
    protected IHeadersProvider headersProvider; // must be initialized in descendant constructor
    
    private byte[] header;
    
    protected byte[] getHeader(Boundary boundary) {
        if (header == null) {
            header = generateHeader(boundary); // lazy init
        }
        return header;
    }
    
    private byte[] generateHeader(Boundary boundary) {
        if (headersProvider == null) {
            throw new RuntimeException("Uninitialized headersProvider");    //$NON-NLS-1$
        }
        final ByteArrayBuffer buf = new ByteArrayBuffer(256);
        append(buf, boundary.getStartingBoundary());
        append(buf, headersProvider.getContentDisposition());
        append(buf, CRLF);
        append(buf, headersProvider.getContentType());
        append(buf, CRLF);
        append(buf, headersProvider.getContentTransferEncoding());
        append(buf, CRLF);
        append(buf, CRLF);
        return buf.toByteArray();
    }
    
    private static void append(ByteArrayBuffer buf, String data) {
        append(buf, EncodingUtils.getAsciiBytes(data));
    }
    
    private static void append(ByteArrayBuffer buf, byte[] data) {
        buf.append(data, 0, data.length);
    }
}
