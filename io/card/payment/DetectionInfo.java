package io.card.payment;

class DetectionInfo {
    public boolean bottomEdge;
    public CreditCard detectedCard;
    public float focusScore;
    public boolean leftEdge;
    public int[] prediction;
    public boolean rightEdge;
    public boolean topEdge;

    public DetectionInfo() {
        this.prediction = new int[16];
        this.prediction[0] = -1;
        this.prediction[15] = -1;
        this.detectedCard = new CreditCard();
    }

    final boolean m730a() {
        return this.prediction[0] >= 0;
    }

    final CreditCard m731b() {
        String str = new String();
        int i = 0;
        while (i < 16 && this.prediction[i] >= 0 && this.prediction[i] < 10) {
            str = str + String.valueOf(this.prediction[i]);
            i++;
        }
        this.detectedCard.cardNumber = str;
        return this.detectedCard;
    }

    final int m732c() {
        int i = 1;
        int i2 = (this.leftEdge ? 1 : 0) + ((this.bottomEdge ? 1 : 0) + (this.topEdge ? 1 : 0));
        if (!this.rightEdge) {
            i = 0;
        }
        return i2 + i;
    }
}
