package io.card.payment;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.squareup.okhttp.internal.http.HttpTransport;
import java.util.Date;

public final class CardIOActivity extends Activity implements C0159c {
    public static final String EXTRA_APP_TOKEN = "io.card.payment.appToken";
    public static final String EXTRA_GUIDE_COLOR = "io.card.payment.guideColor";
    public static final String EXTRA_LANGUAGE_OR_LOCALE = "io.card.payment.languageOrLocale";
    public static final String EXTRA_NO_CAMERA = "io.card.payment.noCamera";
    public static final String EXTRA_REQUIRE_CVV = "io.card.payment.requireCVV";
    public static final String EXTRA_REQUIRE_EXPIRY = "io.card.payment.requireExpiry";
    public static final String EXTRA_REQUIRE_POSTAL_CODE = "io.card.payment.requirePostalCode";
    public static final String EXTRA_REQUIRE_ZIP = "io.card.payment.requireZip";
    public static final String EXTRA_SCAN_RESULT = "io.card.payment.scanResult";
    public static final String EXTRA_SUPPRESS_CONFIRMATION = "io.card.payment.suppressConfirmation";
    public static final String EXTRA_SUPPRESS_MANUAL_ENTRY = "io.card.payment.suppressManual";
    public static final String EXTRA_USE_CARDIO_LOGO = "io.card.payment.useCardIOLogo";
    public static final int RESULT_CARD_INFO;
    public static final int RESULT_CONFIRMATION_SUPPRESSED;
    public static final int RESULT_ENTRY_CANCELED;
    public static final int RESULT_SCAN_NOT_AVAILABLE;
    public static final int RESULT_SCAN_SUPPRESSED;
    static Bitmap f1113a;
    private static int f1114b;
    private static final long[] f1115c;
    private static final int f1116d;
    private static String f1117e;
    private static int f1118q;
    private static boolean f1119u;
    private static /* synthetic */ boolean f1120v;
    private ac f1121f;
    private OrientationEventListener f1122g;
    private ad f1123h;
    private CreditCard f1124i;
    private Rect f1125j;
    private int f1126k;
    private int f1127l;
    private boolean f1128m;
    private boolean f1129n;
    private RelativeLayout f1130o;
    private FrameLayout f1131p;
    private CardScanner f1132r;
    private C0151Q f1133s;
    private boolean f1134t;

    static {
        f1120v = !CardIOActivity.class.desiredAssertionStatus();
        f1114b = 13274384;
        f1114b = 13274385;
        RESULT_CARD_INFO = 13274384;
        int i = f1114b;
        f1114b = i + 1;
        RESULT_ENTRY_CANCELED = i;
        i = f1114b;
        f1114b = i + 1;
        RESULT_SCAN_NOT_AVAILABLE = i;
        i = f1114b;
        f1114b = i + 1;
        RESULT_SCAN_SUPPRESSED = i;
        i = f1114b;
        f1114b = i + 1;
        RESULT_CONFIRMATION_SUPPRESSED = i;
        f1115c = new long[]{0, 70, 10, 40};
        f1116d = 10;
        f1118q = 0;
        f1119u = true;
        f1113a = null;
    }

    public CardIOActivity() {
        this.f1128m = false;
        this.f1129n = false;
        this.f1134t = false;
    }

    private void m1068a(int i) {
        if (i >= 0 && this.f1132r != null) {
            int i2;
            int rotation = ((WindowManager) getSystemService("window")).getDefaultDisplay().getRotation();
            if (rotation != 0) {
                if (rotation == 1) {
                    rotation = 90;
                } else if (rotation == 2) {
                    rotation = 180;
                } else if (rotation == 3) {
                    rotation = 270;
                }
                rotation += i;
                i2 = rotation <= 360 ? rotation - 360 : rotation;
                rotation = -1;
                if (i2 >= 15 || i2 > 345) {
                    this.f1127l = 1;
                    rotation = 0;
                } else if (i2 > 75 && i2 < 105) {
                    this.f1127l = 4;
                    rotation = 90;
                } else if (i2 > 165 && i2 < 195) {
                    rotation = 180;
                    this.f1127l = 2;
                } else if (i2 > 255 && i2 < 285) {
                    this.f1127l = 3;
                    rotation = 270;
                }
                if (rotation >= 0 && rotation != this.f1126k) {
                    new StringBuilder("onOrientationChanged(").append(rotation).append(") calling setDeviceOrientation(").append(this.f1127l).append(")");
                    this.f1132r.m716a(this.f1127l);
                    m1074b(rotation);
                    if (rotation != 90 && rotation != 270) {
                        float f = (float) rotation;
                        return;
                    }
                    return;
                }
            }
            rotation = 0;
            rotation += i;
            if (rotation <= 360) {
            }
            rotation = -1;
            if (i2 >= 15) {
            }
            this.f1127l = 1;
            rotation = 0;
            if (rotation >= 0) {
            }
        }
    }

    private void m1069a(int i, Intent intent) {
        setResult(i, intent);
        f1113a = null;
        finish();
    }

    private void m1071a(Exception exception) {
        ap apVar = ap.ERROR_CAMERA_UNEXPECTED_FAIL;
        CharSequence a = ao.m801a(apVar);
        Log.e("card.io", "Unkown exception - please send the stack trace to support@card.io", exception);
        Toast makeText = Toast.makeText(this, a, 1);
        makeText.setGravity(17, 0, -75);
        makeText.show();
        this.f1134t = true;
        this.f1133s.m754a(apVar, (Throwable) exception, null);
    }

    private void m1072a(boolean z) {
        Object obj = (this.f1123h == null || this.f1121f == null || !this.f1132r.m720b(z)) ? null : 1;
        if (obj != null) {
            this.f1121f.m783a(z);
        }
    }

    private void m1074b(int i) {
        View a = this.f1123h.m787a();
        if (a == null) {
            Log.wtf("card.io", "surface view is null.. recovering... rotation might be weird.");
            return;
        }
        this.f1125j = this.f1132r.m715a(a.getWidth(), a.getHeight());
        Rect rect = this.f1125j;
        rect.top += a.getTop();
        rect = this.f1125j;
        rect.bottom = a.getTop() + rect.bottom;
        this.f1121f.m780a(this.f1125j, i);
        this.f1126k = i;
    }

    public static boolean canReadCardWithCamera() {
        try {
            return f1119u && ag.m794a();
        } catch (CameraUnavailableException e) {
            return false;
        } catch (RuntimeException e2) {
            Log.w("CardIOActivity", "RuntimeException accessing Util.hardwareSupported()");
            return false;
        }
    }

    public static boolean canReadCardWithCamera(Context context) {
        return canReadCardWithCamera();
    }

    private void m1079e() {
        if (f1119u) {
            Intent intent = getIntent();
            if (intent == null || !intent.getBooleanExtra(EXTRA_SUPPRESS_CONFIRMATION, false)) {
                new Handler().post(new C0161e(this));
                return;
            }
            intent = new Intent(this, DataEntryActivity.class);
            intent.putExtra(EXTRA_SCAN_RESULT, this.f1124i);
            this.f1124i = null;
            m1069a(RESULT_CONFIRMATION_SUPPRESSED, intent);
        }
    }

    public static Date sdkBuildDate() {
        return new Date("06/04/2014 16:25:28 -0500");
    }

    public static String sdkVersion() {
        return "sdk-3.1.5-10-gba7f52e";
    }

    final void m1082a() {
        SurfaceView a = this.f1123h.m787a();
        if (this.f1121f != null) {
            this.f1121f.m779a(new Rect(a.getLeft(), a.getTop(), a.getRight(), a.getBottom()));
        }
        this.f1127l = 1;
        m1074b(0);
        if (1 != this.f1127l) {
            Log.wtf("card.io", "the orientation of the scanner doesn't match the orientation of the activity");
        }
        m1084a(new DetectionInfo());
    }

    final void m1083a(Bitmap bitmap, DetectionInfo detectionInfo) {
        try {
            ((Vibrator) getSystemService("vibrator")).vibrate(f1115c, -1);
        } catch (SecurityException e) {
            Log.e("card.io", "Could not activate vibration feedback. Please add <uses-permission android:name=\"android.permission.VIBRATE\" /> to your application's manifest.");
        } catch (Throwable e2) {
            Log.w("card.io", "Exception while attempting to vibrate: ", e2);
        }
        this.f1132r.m721c();
        this.f1130o.setVisibility(4);
        if (detectionInfo.m730a()) {
            this.f1124i = detectionInfo.m731b();
            this.f1121f.m781a(this.f1124i);
            this.f1133s.m758c(this.f1132r.m723e());
        }
        float f = (this.f1127l == 1 || this.f1127l == 2) ? (((float) this.f1125j.right) / 428.0f) * 0.95f : (((float) this.f1125j.right) / 428.0f) * 1.15f;
        Matrix matrix = new Matrix();
        new StringBuilder("Scale factor: ").append(f);
        matrix.postScale(f, f);
        this.f1121f.m778a(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false));
        m1079e();
    }

    final void m1084a(DetectionInfo detectionInfo) {
        this.f1121f.m782a(detectionInfo);
    }

    public final void authorizeScanFailed(Throwable th) {
        Log.w("CardIOActivity", "Scan authorization failed: " + th.getMessage());
    }

    public final void authorizeScanSuccessful() {
    }

    public final void authorizeScanUnsuccessful() {
        if (f1119u) {
            f1119u = false;
            Log.e("card.io", "This app is not authorized to scan. Please register it at https://card.io. All card scans will be disabled.");
            new Builder(this).setTitle(ao.m801a(ap.WHOOPS)).setMessage(ao.m801a(ap.APP_NOT_AUTHORIZED_MESSAGE)).setNegativeButton(ao.m801a(ap.CANCEL), new C0165i(this)).setCancelable(false).create().show();
        }
    }

    final void m1085b() {
        m1072a(!this.f1132r.m724f());
    }

    final void m1086c() {
        this.f1132r.m717a(true);
    }

    public final Rect getTorchRect() {
        return this.f1121f == null ? null : this.f1121f.m786d();
    }

    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String.format("onActivityResult(requestCode:%d, resultCode:%d, ...", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        if (i2 == RESULT_CARD_INFO || i2 == RESULT_ENTRY_CANCELED || this.f1134t) {
            if (intent != null && intent.hasExtra(EXTRA_SCAN_RESULT)) {
                new StringBuilder("data entry result: ").append(intent.getParcelableExtra(EXTRA_SCAN_RESULT));
            }
            m1069a(i2, intent);
        } else if (this.f1130o != null) {
            this.f1130o.setVisibility(0);
        }
    }

    public final void onBackPressed() {
        if (!this.f1134t) {
            ac acVar = this.f1121f;
            ac.m775c();
        }
        if (this.f1133s != null && this.f1132r != null) {
            this.f1133s.m756a(this.f1132r.m723e());
            super.onBackPressed();
        }
    }

    protected final void onCreate(Bundle bundle) {
        int i = f1118q + 1;
        f1118q = i;
        if (i != 1) {
            String.format("INTERNAL WARNING: There are %d (not 1) CardIOActivity allocations!", new Object[]{Integer.valueOf(f1118q)});
        }
        super.onCreate(bundle);
        Intent intent = getIntent();
        ao.m803a(intent);
        String stringExtra = intent.getStringExtra(EXTRA_APP_TOKEN);
        f1117e = stringExtra;
        if (stringExtra == null || f1117e.length() == 0 || f1117e.contains(" ")) {
            throw new IllegalArgumentException("A valid card.io app token must be supplied as a stringExtra with the key CardIOActivity.EXTRA_APP_TOKEN. Get one at https://card.io");
        }
        this.f1129n = false;
        this.f1133s = new C0151Q(this, f1117e, false);
        stringExtra = f1117e;
        C0158a a = C0156Y.m764a();
        a.m772a(stringExtra, null);
        a.m769a(this, "https://api.card.io/0/sdk/auth.json", new ae(this));
        stringExtra = ag.m792a(getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT), CardIOActivity.class);
        if (stringExtra != null) {
            throw new RuntimeException(stringExtra);
        }
        this.f1128m = intent.getBooleanExtra(EXTRA_SUPPRESS_MANUAL_ENTRY, false);
        if (intent.getBooleanExtra(EXTRA_NO_CAMERA, false)) {
            this.f1134t = true;
        } else {
            try {
                if (!ag.m794a()) {
                    ap apVar = ap.ERROR_NO_DEVICE_SUPPORT;
                    Log.w("card.io", apVar + ": " + ao.m801a(apVar));
                    this.f1134t = true;
                    this.f1133s.m755a(apVar, null);
                }
            } catch (Throwable e) {
                ap apVar2 = ap.ERROR_CAMERA_CONNECT_FAIL;
                CharSequence a2 = ao.m801a(apVar2);
                Log.e("card.io", apVar2 + ": " + a2);
                Toast makeText = Toast.makeText(this, a2, 1);
                makeText.setGravity(17, 0, -75);
                makeText.show();
                this.f1134t = true;
                this.f1133s.m754a(apVar2, e, null);
            } catch (Exception e2) {
                m1071a(e2);
            }
        }
        if (f1119u && !this.f1134t) {
            try {
                int intExtra;
                requestWindowFeature(1);
                this.f1125j = new Rect();
                this.f1127l = 1;
                if (!intent.getBooleanExtra("io.card.payment.cameraBypassTestMode", false)) {
                    this.f1132r = new CardScanner(this, this.f1127l);
                } else if (getPackageName().contentEquals("io.card.development")) {
                    this.f1132r = (CardScanner) Class.forName("io.card.payment.CardScannerTester").getConstructor(new Class[]{getClass(), Integer.TYPE}).newInstance(new Object[]{this, Integer.valueOf(this.f1127l)});
                } else {
                    Log.e("CardIOActivity", getPackageName() + " is not correct");
                    throw new IllegalStateException("illegal access of private extra");
                }
                this.f1132r.m719b();
                this.f1131p = new FrameLayout(this);
                this.f1131p.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                this.f1131p.setLayoutParams(new LayoutParams(-1, -1));
                View frameLayout = new FrameLayout(this);
                frameLayout.setId(1);
                this.f1132r.getClass();
                this.f1132r.getClass();
                this.f1123h = new ad(this);
                this.f1123h.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 48));
                frameLayout.addView(this.f1123h);
                this.f1121f = new ac(this, ag.m795a((Context) this));
                this.f1121f.setLayoutParams(new LayoutParams(-1, -1));
                if (getIntent() != null) {
                    this.f1121f.m785b(getIntent().getBooleanExtra(EXTRA_USE_CARDIO_LOGO, false));
                    intExtra = getIntent().getIntExtra(EXTRA_GUIDE_COLOR, 0);
                    if (intExtra != 0) {
                        int i2 = ViewCompat.MEASURED_STATE_MASK | intExtra;
                        if (intExtra != i2) {
                            Log.w("card.io", "Removing transparency from provided guide color.");
                        }
                        this.f1121f.m777a(i2);
                    } else {
                        this.f1121f.m777a(-16711936);
                    }
                }
                frameLayout.addView(this.f1121f);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(10);
                layoutParams.addRule(2, 2);
                this.f1131p.addView(frameLayout, layoutParams);
                this.f1130o = new RelativeLayout(this);
                this.f1130o.setGravity(80);
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(12);
                this.f1130o.setLayoutParams(layoutParams2);
                this.f1130o.setId(2);
                this.f1130o.setGravity(85);
                if (!this.f1128m) {
                    View button = new Button(this);
                    button.setId(3);
                    button.setText(ao.m801a(ap.KEYBOARD));
                    button.setTextSize(12.0f);
                    button.setOnClickListener(new C0163g(this));
                    this.f1130o.addView(button);
                    C0150O.m742a(button, false, (Context) this);
                    button.setTextSize(14.0f);
                    button.setMinimumHeight(C0150O.m738a("42dip", this));
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) button.getLayoutParams();
                    layoutParams3.width = -2;
                    layoutParams3.height = -2;
                    layoutParams3.addRule(12);
                    C0150O.m741a(button, "16dip", null, "16dip", null);
                    C0150O.m744b(button, "4dip", "4dip", "4dip", "4dip");
                }
                layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams2.addRule(12);
                intExtra = (int) ((getResources().getDisplayMetrics().density * 15.0f) + 0.5f);
                layoutParams2.setMargins(0, intExtra, 0, intExtra);
                this.f1131p.addView(this.f1130o, layoutParams2);
                setContentView(this.f1131p);
                this.f1122g = new C0160d(this, this);
            } catch (Exception e22) {
                m1071a(e22);
            }
        }
        if ((this.f1134t || !f1119u) && this.f1128m) {
            m1069a(RESULT_SCAN_NOT_AVAILABLE, null);
        }
    }

    protected final void onDestroy() {
        this.f1121f = null;
        f1118q--;
        if (this.f1132r != null) {
            this.f1132r.m722d();
            this.f1132r = null;
        }
        super.onDestroy();
    }

    protected final void onPause() {
        super.onPause();
        if (this.f1122g != null) {
            this.f1122g.disable();
        }
        m1072a(false);
        if (this.f1132r != null) {
            this.f1132r.m721c();
        } else if (!this.f1134t) {
            Log.wtf("card.io", "cardScanner is null in onPause()");
        }
    }

    protected final void onResume() {
        super.onResume();
        if (this.f1134t) {
            this.f1133s.m757b(null);
            m1079e();
        } else if (f1119u) {
            ag.m796b();
            getWindow().addFlags(HttpTransport.DEFAULT_CHUNK_LENGTH);
            getWindow().addFlags(TransportMediator.FLAG_KEY_MEDIA_NEXT);
            setRequestedOrientation(1);
            this.f1122g.enable();
            this.f1124i = null;
            if (f1120v || this.f1123h != null) {
                boolean a = this.f1132r.m718a(this.f1123h.m788b());
                if (a) {
                    this.f1130o.setVisibility(0);
                }
                if (a) {
                    m1072a(false);
                    this.f1133s.m753a();
                } else {
                    Log.e("CardIOActivity", "Could not connect to camera.");
                    ap apVar = ap.ERROR_CAMERA_UNEXPECTED_FAIL;
                    CharSequence a2 = ao.m801a(apVar);
                    Log.e("card.io", "error display: " + a2);
                    Toast.makeText(this, a2, 1).show();
                    m1079e();
                    this.f1133s.m755a(apVar, this.f1132r.m723e());
                }
                m1068a(this.f1126k);
                return;
            }
            throw new AssertionError();
        } else {
            Log.e("card.io", "This app is not authorized to scan");
            m1069a(0, null);
        }
    }
}
