package com.paypal.android.sdk;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

final class ck extends HttpEntityWrapper {
    public ck(HttpEntity httpEntity) {
        super(httpEntity);
    }

    public final InputStream getContent() {
        return new GZIPInputStream(this.wrappedEntity.getContent());
    }

    public final long getContentLength() {
        return -1;
    }
}
