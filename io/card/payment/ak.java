package io.card.payment;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

final class ak extends HttpEntityWrapper {
    private GZIPInputStream f903a;

    public ak(HttpEntity httpEntity) {
        super(httpEntity);
    }

    public final void consumeContent() {
        this.f903a.close();
        this.f903a = null;
        super.consumeContent();
    }

    public final InputStream getContent() {
        if (this.f903a == null) {
            this.f903a = new GZIPInputStream(this.wrappedEntity.getContent());
        }
        return this.f903a;
    }

    public final long getContentLength() {
        return -1;
    }
}
