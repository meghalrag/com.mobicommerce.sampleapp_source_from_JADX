package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.paypal.android.sdk.aa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class aR implements Parcelable {
    public static final Creator CREATOR;
    private Map f694a;
    private String f695b;
    private String f696c;
    private String f697d;

    static {
        CREATOR = new aS();
    }

    aR() {
        this.f694a = new HashMap();
    }

    public aR(Parcel parcel) {
        this();
        if (parcel != null) {
            this.f695b = parcel.readString();
            this.f696c = parcel.readString();
            this.f697d = parcel.readString();
            int readInt = parcel.readInt();
            for (int i = 0; i < readInt; i++) {
                this.f694a.put(parcel.readString(), parcel.readString());
            }
        }
    }

    aR(aa aaVar) {
        this.f694a = aaVar.m1201g();
        this.f695b = aaVar.m1202h();
        this.f696c = aaVar.m1203i();
        this.f697d = aaVar.m1204j();
    }

    final List m623a() {
        List arrayList = new ArrayList();
        for (String str : this.f694a.keySet()) {
            if (((String) this.f694a.get(str)).toUpperCase().equals("Y")) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final String m624b() {
        return this.f695b;
    }

    public final String m625c() {
        return this.f696c;
    }

    public final String m626d() {
        return this.f697d;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f695b);
        parcel.writeString(this.f696c);
        parcel.writeString(this.f697d);
        parcel.writeInt(this.f694a.size());
        for (String str : this.f694a.keySet()) {
            parcel.writeString(str);
            parcel.writeString((String) this.f694a.get(str));
        }
    }
}
