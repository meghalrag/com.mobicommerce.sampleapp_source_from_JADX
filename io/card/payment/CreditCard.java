package io.card.payment;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.paypal.android.sdk.payments.BuildConfig;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

public class CreditCard implements Parcelable {
    public static final Creator CREATOR;
    public static final int EXPIRY_MAX_FUTURE_YEARS = 15;
    private static final String TAG = "CardIOCreditCardResults";
    public String cardNumber;
    public String cvv;
    public int expiryMonth;
    public int expiryYear;
    boolean flipped;
    public String postalCode;
    String scanId;
    int[] xoff;
    int yoff;
    public String zip;

    /* renamed from: io.card.payment.CreditCard.1 */
    final class C01481 implements Creator {
        C01481() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CreditCard(null);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CreditCard[i];
        }
    }

    static {
        CREATOR = new C01481();
    }

    public CreditCard() {
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        this.xoff = new int[16];
        this.scanId = UUID.randomUUID().toString();
    }

    private CreditCard(Parcel parcel) {
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        this.cardNumber = parcel.readString();
        this.expiryMonth = parcel.readInt();
        this.expiryYear = parcel.readInt();
        this.cvv = parcel.readString();
        this.postalCode = parcel.readString();
        this.zip = this.postalCode;
        this.scanId = parcel.readString();
        this.yoff = parcel.readInt();
        this.xoff = parcel.createIntArray();
    }

    CreditCard(String str) {
        int i = 0;
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        if (str != null && str.length() != 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.cardNumber = jSONObject.optString("numbers");
                new StringBuilder("- number: ").append(getRedactedCardNumber());
                JSONArray optJSONArray = jSONObject.optJSONArray("expiry");
                if (optJSONArray != null && optJSONArray.length() == 2) {
                    this.expiryYear = optJSONArray.getInt(0);
                    new StringBuilder("- year: ").append(this.expiryYear);
                    this.expiryMonth = optJSONArray.getInt(1);
                    new StringBuilder("- month: ").append(this.expiryMonth);
                }
                this.flipped = jSONObject.optBoolean("is_flipped");
                new StringBuilder("- isFlipped: ").append(this.flipped);
                this.scanId = jSONObject.optString("scan_id");
                new StringBuilder("- scanId: ").append(this.scanId);
                this.yoff = jSONObject.optInt("y_offset");
                JSONArray optJSONArray2 = jSONObject.optJSONArray("x_offsets");
                this.xoff = new int[optJSONArray2.length()];
                while (i < optJSONArray2.length()) {
                    this.xoff[i] = optJSONArray2.getInt(i);
                    i++;
                }
            } catch (Throwable e) {
                Log.w(TAG, "error parsing credit card response: ", e);
                if (this.cardNumber == null) {
                    return;
                }
                if (this.cardNumber.length() < EXPIRY_MAX_FUTURE_YEARS || !C0151Q.m748a(this.cardNumber)) {
                    this.cardNumber = null;
                }
            }
        }
    }

    public CreditCard(String str, int i, int i2, String str2, String str3) {
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        this.cardNumber = str;
        this.expiryMonth = i;
        this.expiryYear = i2;
        this.cvv = str2;
        this.zip = str3;
        this.postalCode = str3;
    }

    public int describeContents() {
        return 0;
    }

    boolean failed() {
        return this.cardNumber == null || this.cardNumber.length() == 0;
    }

    public CardType getCardType() {
        return CardType.fromCardNumber(this.cardNumber);
    }

    public String getFormattedCardNumber() {
        return C0151Q.m746a(this.cardNumber, true, null);
    }

    public String getLastFourDigitsOfCardNumber() {
        if (this.cardNumber == null) {
            return BuildConfig.VERSION_NAME;
        }
        return this.cardNumber.substring(this.cardNumber.length() - Math.min(4, this.cardNumber.length()));
    }

    public String getRedactedCardNumber() {
        if (this.cardNumber == null) {
            return BuildConfig.VERSION_NAME;
        }
        String str = BuildConfig.VERSION_NAME;
        if (this.cardNumber.length() > 4) {
            str = str + String.format("%" + (this.cardNumber.length() - 4) + "s", new Object[]{BuildConfig.VERSION_NAME}).replace(' ', '\u2022');
        }
        return C0151Q.m746a(str + getLastFourDigitsOfCardNumber(), false, CardType.fromCardNumber(this.cardNumber));
    }

    public boolean isExpiryValid() {
        int i = this.expiryMonth;
        int i2 = this.expiryYear;
        if (i <= 0 || 12 < i) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        int i3 = instance.get(1);
        return i2 >= i3 ? (i2 != i3 || i >= instance.get(2) + 1) && i2 <= i3 + EXPIRY_MAX_FUTURE_YEARS : false;
    }

    List toNameValueList() {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("card_number", this.cardNumber));
        if (this.expiryMonth > 0 && this.expiryYear > 0) {
            arrayList.add(new BasicNameValuePair("card_exp_month", String.valueOf(this.expiryMonth)));
            arrayList.add(new BasicNameValuePair("card_exp_year", String.valueOf(this.expiryYear)));
        }
        if (this.cvv != null) {
            arrayList.add(new BasicNameValuePair("card_cvv", this.cvv));
        }
        if (this.zip != null) {
            arrayList.add(new BasicNameValuePair("card_zip", this.zip));
        }
        if (this.postalCode != null) {
            arrayList.add(new BasicNameValuePair("card_postal_code", this.postalCode));
        }
        if (this.scanId != null) {
            arrayList.add(new BasicNameValuePair("scan_id", this.scanId));
        }
        return arrayList;
    }

    public String toString() {
        String str = "{" + getCardType() + ": " + getRedactedCardNumber();
        if (this.expiryMonth > 0 || this.expiryYear > 0) {
            str = str + "  expiry:" + this.expiryMonth + "/" + this.expiryYear;
        }
        if (this.zip != null) {
            str = str + "  zip:" + this.zip;
        }
        if (this.postalCode != null) {
            str = str + "  postalCode:" + this.postalCode;
        }
        if (this.cvv != null) {
            str = str + "  cvvLength:" + (this.cvv != null ? this.cvv.length() : 0);
        }
        return str + "}";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cardNumber);
        parcel.writeInt(this.expiryMonth);
        parcel.writeInt(this.expiryYear);
        parcel.writeString(this.cvv);
        parcel.writeString(this.postalCode);
        parcel.writeString(this.scanId);
        parcel.writeInt(this.yoff);
        parcel.writeIntArray(this.xoff);
    }
}
