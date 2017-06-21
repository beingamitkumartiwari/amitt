package com.getfreerecharge.trainschedule.models.cancelledtrains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 3/28/2017.
 */

public class Train {
    @SerializedName("dest")
    @Expose
    private Dest dest;
    @SerializedName("train")
    @Expose
    private Train_ train_;
    @SerializedName("source")
    @Expose
    private Source source;

    public Dest getDest() {
        return dest;
    }

    public void setDest(Dest dest) {
        this.dest = dest;
    }

    public Train_ getTrain() {
        return train_;
    }

    public void setTrain(Train_ train_) {
        this.train_ = train_;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }


}
