package com.android.volley.multipart;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 任伟伟
 * Datetime: 2016/10/29-13:22
 * Email: renweiwei@ufashion.com
 */
public interface Part {
    public long getContentLength(Boundary boundary);
    public void writeTo(final OutputStream out, Boundary boundary) throws IOException;
}
