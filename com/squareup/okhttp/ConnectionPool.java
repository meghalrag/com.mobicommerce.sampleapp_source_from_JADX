package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {
    private static final long DEFAULT_KEEP_ALIVE_DURATION_MS = 300000;
    private static final int MAX_CONNECTIONS_TO_CLEANUP = 2;
    private static final ConnectionPool systemDefault;
    private final LinkedList<Connection> connections;
    private final Callable<Void> connectionsCleanupCallable;
    private final ExecutorService executorService;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;

    /* renamed from: com.squareup.okhttp.ConnectionPool.1 */
    class C01331 implements Callable<Void> {
        C01331() {
        }

        public Void call() throws Exception {
            List<Connection> expiredConnections = new ArrayList(ConnectionPool.MAX_CONNECTIONS_TO_CLEANUP);
            int idleConnectionCount = 0;
            synchronized (ConnectionPool.this) {
                ListIterator<Connection> i = ConnectionPool.this.connections.listIterator(ConnectionPool.this.connections.size());
                while (i.hasPrevious()) {
                    Connection connection = (Connection) i.previous();
                    if (!connection.isAlive() || connection.isExpired(ConnectionPool.this.keepAliveDurationNs)) {
                        i.remove();
                        expiredConnections.add(connection);
                        if (expiredConnections.size() == ConnectionPool.MAX_CONNECTIONS_TO_CLEANUP) {
                            break;
                        }
                    } else if (connection.isIdle()) {
                        idleConnectionCount++;
                    }
                }
                i = ConnectionPool.this.connections.listIterator(ConnectionPool.this.connections.size());
                while (i.hasPrevious() && idleConnectionCount > ConnectionPool.this.maxIdleConnections) {
                    connection = (Connection) i.previous();
                    if (connection.isIdle()) {
                        expiredConnections.add(connection);
                        i.remove();
                        idleConnectionCount--;
                    }
                }
            }
            for (Closeable expiredConnection : expiredConnections) {
                Util.closeQuietly(expiredConnection);
            }
            return null;
        }
    }

    /* renamed from: com.squareup.okhttp.ConnectionPool.2 */
    class C01342 implements Runnable {
        C01342() {
        }

        public void run() {
        }
    }

    static {
        long keepAliveDurationMs;
        String keepAlive = System.getProperty("http.keepAlive");
        String keepAliveDuration = System.getProperty("http.keepAliveDuration");
        String maxIdleConnections = System.getProperty("http.maxConnections");
        if (keepAliveDuration != null) {
            keepAliveDurationMs = Long.parseLong(keepAliveDuration);
        } else {
            keepAliveDurationMs = DEFAULT_KEEP_ALIVE_DURATION_MS;
        }
        if (keepAlive != null && !Boolean.parseBoolean(keepAlive)) {
            systemDefault = new ConnectionPool(0, keepAliveDurationMs);
        } else if (maxIdleConnections != null) {
            systemDefault = new ConnectionPool(Integer.parseInt(maxIdleConnections), keepAliveDurationMs);
        } else {
            systemDefault = new ConnectionPool(5, keepAliveDurationMs);
        }
    }

    public ConnectionPool(int maxIdleConnections, long keepAliveDurationMs) {
        this.connections = new LinkedList();
        this.executorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.daemonThreadFactory("OkHttp ConnectionPool"));
        this.connectionsCleanupCallable = new C01331();
        this.maxIdleConnections = maxIdleConnections;
        this.keepAliveDurationNs = (keepAliveDurationMs * 1000) * 1000;
    }

    List<Connection> getConnections() {
        List arrayList;
        waitForCleanupCallableToRun();
        synchronized (this) {
            arrayList = new ArrayList(this.connections);
        }
        return arrayList;
    }

    private void waitForCleanupCallableToRun() {
        try {
            this.executorService.submit(new C01342()).get();
        } catch (Exception e) {
            throw new AssertionError();
        }
    }

    public static ConnectionPool getDefault() {
        return systemDefault;
    }

    public synchronized int getConnectionCount() {
        return this.connections.size();
    }

    public synchronized int getSpdyConnectionCount() {
        int total;
        total = 0;
        Iterator it = this.connections.iterator();
        while (it.hasNext()) {
            if (((Connection) it.next()).isSpdy()) {
                total++;
            }
        }
        return total;
    }

    public synchronized int getHttpConnectionCount() {
        int total;
        total = 0;
        Iterator it = this.connections.iterator();
        while (it.hasNext()) {
            if (!((Connection) it.next()).isSpdy()) {
                total++;
            }
        }
        return total;
    }

    public synchronized Connection get(Address address) {
        Connection foundConnection;
        foundConnection = null;
        ListIterator<Connection> i = this.connections.listIterator(this.connections.size());
        while (i.hasPrevious()) {
            Closeable connection = (Connection) i.previous();
            if (connection.getRoute().getAddress().equals(address) && connection.isAlive() && System.nanoTime() - connection.getIdleStartTimeNs() < this.keepAliveDurationNs) {
                i.remove();
                if (!connection.isSpdy()) {
                    try {
                        Platform.get().tagSocket(connection.getSocket());
                    } catch (SocketException e) {
                        Util.closeQuietly(connection);
                        Platform.get().logW("Unable to tagSocket(): " + e);
                    }
                }
                foundConnection = connection;
                break;
            }
        }
        if (foundConnection != null && foundConnection.isSpdy()) {
            this.connections.addFirst(foundConnection);
        }
        this.executorService.submit(this.connectionsCleanupCallable);
        return foundConnection;
    }

    public void recycle(Connection connection) {
        if (!connection.isSpdy()) {
            if (connection.isAlive()) {
                try {
                    Platform.get().untagSocket(connection.getSocket());
                    synchronized (this) {
                        this.connections.addFirst(connection);
                        connection.resetIdleStartTime();
                    }
                    this.executorService.submit(this.connectionsCleanupCallable);
                    return;
                } catch (SocketException e) {
                    Platform.get().logW("Unable to untagSocket(): " + e);
                    Util.closeQuietly((Closeable) connection);
                    return;
                }
            }
            Util.closeQuietly((Closeable) connection);
        }
    }

    public void maybeShare(Connection connection) {
        this.executorService.submit(this.connectionsCleanupCallable);
        if (connection.isSpdy() && connection.isAlive()) {
            synchronized (this) {
                this.connections.addFirst(connection);
            }
        }
    }

    public void evictAll() {
        synchronized (this) {
            List<Connection> connections = new ArrayList(this.connections);
            this.connections.clear();
        }
        for (Closeable connection : connections) {
            Util.closeQuietly(connection);
        }
    }
}
