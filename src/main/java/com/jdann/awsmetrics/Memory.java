package com.jdann.awsmetrics;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class Memory {

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public double getAvailable() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        return round(formatBytes(hal.getMemory().getAvailable()), 1);
    }

    private double formatBytes(long bytes) {
        //copied from oshi.util.FormatUtil, returning double instead of string
        if (bytes == 1L) {
            return bytes;
        } else if (bytes < 1024L) {
            return bytes;
        } else if (bytes < 1048576L) {
            return formatUnits(bytes, 1024L);
        } else if (bytes < 1073741824L) {
            return formatUnits(bytes, 1048576L);
        } else if (bytes < 1099511627776L) {
            return formatUnits(bytes, 1073741824L);
        } else if (bytes < 1125899906842624L) {
            return formatUnits(bytes, 1099511627776L);
        } else {
            return 0;
        }
    }

    private double formatUnits(long value, long prefix) {
        return value % prefix == 0L ? value / prefix :  (double)value / (double)prefix;
    }
}
