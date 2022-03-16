package com.example.shoponline.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Discount {
    @PrimaryKey
    private long Id;
    @ColumnInfo
    private int DiscountPercent;
    @ColumnInfo
    private boolean isUsed;
    @ColumnInfo
    private boolean isClaimed;
    @ColumnInfo
    private long AccountClaimId;

    public Discount(){}

    public Discount(long id, int discountPercent, boolean isUsed, boolean isClaimed, long accountClaimId) {
        Id = id;
        DiscountPercent = discountPercent;
        this.isUsed = isUsed;
        this.isClaimed = isClaimed;
        AccountClaimId = accountClaimId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        DiscountPercent = discountPercent;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean claimed) {
        isClaimed = claimed;
    }

    public long getAccountClaimId() {
        return AccountClaimId;
    }

    public void setAccountClaimId(long accountClaimId) {
        AccountClaimId = accountClaimId;
    }
}
