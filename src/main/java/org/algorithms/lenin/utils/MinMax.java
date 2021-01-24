package org.algorithms.lenin.utils;

public class MinMax {
    private Integer min;
    private Integer max;

    public MinMax(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMin() {
        return this.min;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMax() {
        return this.max;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        MinMax b = (MinMax) o;
        return this.getMin() == b.getMin() && this.getMax() == b.getMax();
    }
}
