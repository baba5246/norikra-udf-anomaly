package is.tagomor.norikra.udf;

import com.espertech.esper.epl.agg.aggregator.AggregationMethod;

import java.util.ArrayList;

public class Anomaly implements AggregationMethod {
  private static int initSize = 100000;
  private ArrayList<Integer> countList;

  public Anomaly() {
    countList = null;
  }

  public Class getValueType() {
    return Integer.class;
  }

  public Integer convertValue(Object v) {
    if (v.getClass() == Integer.class)
      return (Integer) v;
    else if (v.getClass() == Float.class)
      return new Integer(((Float) v).intValue());
    else if (v.getClass() == Integer.class)
      return new Integer(((Double) v).intValue());
    else if (v.getClass() == Long.class)
      return new Integer(((Long) v).intValue());
    else
      return new Integer((String) v);
  }    

  public void enter(Object value) {
    if (value == null)
      return;

    if (countList == null) {
      countList = new ArrayList<Integer>(initSize);
    }

    Integer count = convertValue(value);
    synchronized (countList) {
      countList.add(count);
    }
  }

  public void leave(Object value) {
    if (value == null)
      return;

    Integer count = convertValue(value);
    synchronized (countList) {
      countList.remove(0);
    }
  }

  public Object getValue() {
    if (countList.size() == 0)
      return null;

    Integer[] counts = (Integer[]) countList.toArray(new Integer[]{});
    // for thread safety
    if (counts.length == 0)
      return null;

    Integer score = cumulateDeviation(counts);
    return score;
  }

  public void clear() {
    countList = null;
  }

  public double mean(Integer[] m) {
    Integer sum = 0;
    for (int i = 0; i < m.length; i++) {
        sum += m[i];
    }
    return sum.doubleValue() / m.length;
  }

  public Integer cumulateDeviation(Integer[] counts) {
    double mean = mean(counts);
    double cumulativeSum = 0;
    double maxSum = cumulativeSum;

    for (int i = 0; i < counts.length; i++) {
      double dev = counts[i].doubleValue() - mean;
      cumulativeSum += dev;
      if (i == 0) maxSum = cumulativeSum;
      else maxSum = Math.max(maxSum, cumulativeSum);
    }

    return new Integer((int) maxSum);
  }
}
