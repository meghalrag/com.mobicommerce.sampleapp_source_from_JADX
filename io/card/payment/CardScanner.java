package io.card.payment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CardScanner implements AutoFocusCallback, PreviewCallback, Callback {
    private static boolean f782b;
    private static boolean f783p;
    private static /* synthetic */ boolean f784q;
    private Bitmap f785a;
    private WeakReference f786c;
    private boolean f787d;
    private int f788e;
    private boolean f789f;
    private long f790g;
    private long f791h;
    private Camera f792i;
    private byte[] f793j;
    private boolean f794k;
    private boolean f795l;
    private int f796m;
    private int f797n;
    private int f798o;

    static {
        f784q = !CardScanner.class.desiredAssertionStatus();
        f782b = false;
        try {
            System.loadLibrary("cardioDecider");
            new StringBuilder("Loaded card.io decider library.  nUseNeon():").append(nUseNeon()).append(",nUseTegra():").append(nUseTegra());
            if (nUseNeon() || nUseTegra()) {
                System.loadLibrary("opencv_core");
                System.loadLibrary("opencv_imgproc");
            }
            if (nUseNeon()) {
                System.loadLibrary("cardioRecognizer");
            } else if (nUseTegra()) {
                System.loadLibrary("cardioRecognizer_tegra2");
            } else {
                Log.w("card.io", "unsupported processor - card.io scanning requires ARMv7 architecture");
                f782b = true;
            }
        } catch (UnsatisfiedLinkError e) {
            Log.e("card.io", "Failed to load native library: " + e.getMessage());
            f782b = true;
        }
        f783p = false;
    }

    CardScanner(CardIOActivity cardIOActivity, int i) {
        this.f787d = false;
        this.f788e = 1;
        this.f789f = true;
        this.f790g = 0;
        this.f791h = 0;
        this.f792i = null;
        this.f794k = true;
        this.f795l = false;
        Intent intent = cardIOActivity.getIntent();
        if (intent != null) {
            intent.getBooleanExtra("io.card.payment.suppressScan", false);
            this.f787d = false;
        }
        this.f786c = new WeakReference(cardIOActivity);
        this.f788e = i;
        nSetup(false, 6.0f);
    }

    static boolean m712a() {
        return !f782b && (nUseNeon() || nUseTegra());
    }

    private Camera m713b(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f794k) {
            int i2 = i;
            do {
                try {
                    return Camera.open();
                } catch (RuntimeException e) {
                    try {
                        Log.w("card.io", "Wasn't able to connect to camera service. Waiting and trying again...");
                        Thread.sleep(50);
                    } catch (Throwable e2) {
                        Log.e("card.io", "Interrupted while waiting for camera", e2);
                    }
                } catch (Throwable e3) {
                    Log.e("card.io", "Unexpected exception. Please report it to support@card.io", e3);
                    i2 = 0;
                }
            } while (System.currentTimeMillis() - currentTimeMillis < ((long) i2));
            Log.w("CardScanner", "camera connect timeout");
            return null;
        }
        Log.w("CardScanner", "camera connect timeout");
        return null;
    }

    private boolean m714b(SurfaceHolder surfaceHolder) {
        if (!f784q && surfaceHolder == null) {
            throw new AssertionError();
        } else if (f784q || surfaceHolder.getSurface() != null) {
            new StringBuilder("surfaceFrame: ").append(String.valueOf(surfaceHolder.getSurfaceFrame()));
            this.f789f = true;
            if (this.f794k) {
                try {
                    this.f792i.setPreviewDisplay(surfaceHolder);
                    try {
                        this.f792i.startPreview();
                        this.f792i.autoFocus(this);
                    } catch (Throwable e) {
                        Log.e("card.io", "startPreview failed on camera. Error: ", e);
                        return false;
                    }
                } catch (Throwable e2) {
                    Log.e("card.io", "can't set preview display", e2);
                    return false;
                }
            }
            return true;
        } else {
            throw new AssertionError();
        }
    }

    private native void nCleanup();

    private native long nGetElapsedClock();

    private native double nGetElapsedTime();

    private native void nGetFrameCounts(int[] iArr);

    private native void nGetGuideFrame(int i, int i2, int i3, Rect rect);

    private native void nResetAnalytics();

    private native void nScanFrame(byte[] bArr, int i, int i2, int i3, DetectionInfo detectionInfo, Bitmap bitmap);

    private native void nSetup(boolean z, float f);

    private native void nSkipFrame();

    public static native boolean nUseNeon();

    public static native boolean nUseTegra();

    final Rect m715a(int i, int i2) {
        int i3 = this.f788e;
        if (!m712a()) {
            return null;
        }
        Rect rect = new Rect();
        nGetGuideFrame(i3, i, i2, rect);
        return rect;
    }

    final void m716a(int i) {
        this.f788e = i;
    }

    final void m717a(boolean z) {
        if (this.f794k) {
            if ((this.f791h < this.f790g ? 1 : null) == null) {
                try {
                    this.f790g = System.currentTimeMillis();
                    this.f792i.autoFocus(this);
                    if (z) {
                        this.f796m++;
                    } else {
                        this.f797n++;
                    }
                } catch (RuntimeException e) {
                    Log.w("CardScanner", "could not trigger auto focus: " + e);
                }
            }
        }
    }

    final boolean m718a(SurfaceHolder surfaceHolder) {
        new StringBuilder("resumeScanning(").append(surfaceHolder).append(")");
        if (this.f792i == null) {
            m719b();
        }
        if (this.f794k && this.f792i == null) {
            return false;
        }
        if (f784q || surfaceHolder != null) {
            if (this.f794k && this.f793j == null) {
                new StringBuilder("- mCamera:").append(this.f792i);
                int previewFormat = this.f792i.getParameters().getPreviewFormat();
                new StringBuilder("- preview format: ").append(previewFormat);
                previewFormat = ImageFormat.getBitsPerPixel(previewFormat) / 8;
                new StringBuilder("- bytes per pixel: ").append(previewFormat);
                previewFormat = (previewFormat * 307200) * 3;
                new StringBuilder("- buffer size: ").append(previewFormat);
                this.f793j = new byte[previewFormat];
                this.f792i.addCallbackBuffer(this.f793j);
            }
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(3);
            if (this.f794k) {
                this.f792i.setPreviewCallbackWithBuffer(this);
            }
            if (this.f795l) {
                m714b(surfaceHolder);
            }
            m720b(false);
            System.currentTimeMillis();
            nResetAnalytics();
            return true;
        }
        throw new AssertionError();
    }

    final void m719b() {
        this.f789f = true;
        this.f790g = 0;
        this.f791h = 0;
        this.f796m = 0;
        this.f797n = 0;
        this.f798o = 0;
        if (this.f794k && this.f792i == null) {
            this.f792i = m713b(5000);
            if (this.f792i == null) {
                Log.e("card.io", "prepare scanner couldn't connect to camera!");
                return;
            }
            this.f792i.setDisplayOrientation(90);
            Parameters parameters = this.f792i.getParameters();
            List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            if (supportedPreviewSizes != null) {
                C0155V.m761a((List) supportedPreviewSizes);
                for (Size size : supportedPreviewSizes) {
                    if (size.width == 640 && size.height == 480) {
                        break;
                    }
                }
                Size size2 = null;
                if (size2 == null) {
                    Log.w("card.io", "Didn't find a supported 640x480 resolution, so forcing");
                    size2 = (Size) supportedPreviewSizes.get(0);
                    size2.width = 640;
                    size2.height = 480;
                }
            }
            new StringBuilder("- parameters: ").append(parameters);
            parameters.setPreviewSize(640, 480);
            this.f792i.setParameters(parameters);
        } else if (!this.f794k) {
            Log.w("CardScanner", "useCamera is false!");
        } else if (this.f792i != null) {
            new StringBuilder("we already have a camera instance: ").append(this.f792i);
        }
        if (this.f785a == null) {
            this.f785a = Bitmap.createBitmap(428, 270, Config.ARGB_8888);
        }
    }

    public final boolean m720b(boolean z) {
        if (this.f792i != null) {
            new StringBuilder("setFlashOn: ").append(z);
            try {
                Parameters parameters = this.f792i.getParameters();
                parameters.setFlashMode(z ? "torch" : "off");
                this.f792i.setParameters(parameters);
                this.f798o++;
                return true;
            } catch (RuntimeException e) {
                Log.w("CardScanner", "Could not set flash mode: " + e);
            }
        }
        return false;
    }

    public final void m721c() {
        m720b(false);
        if (this.f792i != null) {
            try {
                this.f792i.stopPreview();
                this.f792i.setPreviewDisplay(null);
            } catch (Throwable e) {
                Log.w("card.io", "can't stop preview display", e);
            }
            this.f792i.setPreviewCallback(null);
            this.f792i.release();
            this.f793j = null;
            this.f792i = null;
        }
    }

    public final void m722d() {
        if (this.f792i != null) {
            m721c();
        }
        nCleanup();
        this.f793j = null;
    }

    final Map m723e() {
        Map hashMap = new HashMap(11);
        int[] iArr = new int[6];
        nGetFrameCounts(iArr);
        hashMap.put("num_frames_total", Integer.valueOf(iArr[0]));
        hashMap.put("num_frames_captured", Integer.valueOf(iArr[1]));
        hashMap.put("num_frames_processed", Integer.valueOf(iArr[2]));
        hashMap.put("num_frames_in_focus", Integer.valueOf(iArr[3]));
        hashMap.put("num_frames_scanned", Integer.valueOf(iArr[4]));
        hashMap.put("num_frames_usable", Integer.valueOf(iArr[5]));
        hashMap.put("elapsed_time", Double.valueOf(nGetElapsedTime()));
        hashMap.put("elapsed_clock", Long.valueOf(nGetElapsedClock()));
        hashMap.put("num_manual_refocusings", Integer.valueOf(this.f796m));
        hashMap.put("num_auto_triggered_refocusings", Integer.valueOf(this.f797n));
        hashMap.put("num_manual_torch_changes", Integer.valueOf(this.f798o));
        return hashMap;
    }

    public final boolean m724f() {
        return !this.f794k ? false : this.f792i.getParameters().getFlashMode().equals("torch");
    }

    public void onAutoFocus(boolean z, Camera camera) {
        this.f791h = System.currentTimeMillis();
    }

    void onEdgeUpdate(DetectionInfo detectionInfo) {
        ((CardIOActivity) this.f786c.get()).m1084a(detectionInfo);
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (bArr == null) {
            Log.w("CardScanner", "frame is null! skipping");
        } else if (f783p) {
            Log.e("CardScanner", "processing in progress.... dropping frame");
            nSkipFrame();
            if (camera != null) {
                camera.addCallbackBuffer(bArr);
            }
        } else {
            f783p = true;
            if (this.f789f) {
                this.f789f = false;
                this.f788e = 1;
                ((CardIOActivity) this.f786c.get()).m1082a();
            }
            DetectionInfo detectionInfo = new DetectionInfo();
            nScanFrame(bArr, 640, 480, this.f788e, detectionInfo, this.f785a);
            if (!(detectionInfo.focusScore >= 6.0f)) {
                m717a(false);
            } else if (detectionInfo.m730a()) {
                new StringBuilder("detected card: ").append(detectionInfo.m731b());
                ((CardIOActivity) this.f786c.get()).m1083a(this.f785a, detectionInfo);
            }
            if (camera != null) {
                camera.addCallbackBuffer(bArr);
            }
            f783p = false;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        String str = "Preview.surfaceChanged(holder?:%b, f:%d, w:%d, h:%d )";
        Object[] objArr = new Object[4];
        objArr[0] = Boolean.valueOf(surfaceHolder != null);
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = Integer.valueOf(i3);
        String.format(str, objArr);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.f792i == null && this.f794k) {
            Log.wtf("card.io", "CardScanner.surfaceCreated() - camera is null!");
            return;
        }
        this.f795l = true;
        m714b(surfaceHolder);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.f792i != null) {
            try {
                this.f792i.stopPreview();
            } catch (Throwable e) {
                Log.e("card.io", "error stopping camera", e);
            }
        }
        this.f795l = false;
    }
}
