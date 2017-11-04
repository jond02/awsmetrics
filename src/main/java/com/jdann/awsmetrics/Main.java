package com.jdann.awsmetrics;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.*;

public class Main {

    public static void main(String[] args) {

        Memory memory = new Memory();

        final AmazonCloudWatch cw =
                AmazonCloudWatchClientBuilder.defaultClient();

        Dimension dimension = new Dimension()
                .withName("Memory")
                .withValue("Available");

        MetricDatum datum = new MetricDatum()
                .withMetricName("amount")
                .withUnit(StandardUnit.None)
                .withValue(memory.getAvailable())
                .withDimensions(dimension);

        PutMetricDataRequest request = new PutMetricDataRequest()
                .withNamespace("EC2 Specs")
                .withMetricData(datum);

        PutMetricDataResult response = cw.putMetricData(request);
    }
}
