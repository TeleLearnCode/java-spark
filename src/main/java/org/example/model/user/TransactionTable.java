package org.example.model.user;

public class TransactionTable {
    private String productName;
    private String numberTrxSuccess;
    private String numberTrxFailed;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNumberTrxSuccess() {
        return numberTrxSuccess;
    }

    public void setNumberTrxSuccess(String numberTrxSuccess) {
        this.numberTrxSuccess = numberTrxSuccess;
    }

    public String getNumberTrxFailed() {
        return numberTrxFailed;
    }

    public void setNumberTrxFailed(String numberTrxFailed) {
        this.numberTrxFailed = numberTrxFailed;
    }
}
