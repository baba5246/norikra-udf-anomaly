package is.tagomor.norikra.udf;

import com.espertech.esper.client.hook.AggregationFunctionFactory;
import com.espertech.esper.epl.agg.service.AggregationValidationContext;
import com.espertech.esper.epl.agg.aggregator.AggregationMethod;

// import java.util.Map;

public class AnomalyFactory implements AggregationFunctionFactory {
  public void setFunctionName(String functionName) {
    // no action taken
  }

  /*
    percentile(field_name, {percentile_specifications_array}[, samples])
    ex: percentile(response_time, 50)
   */
  public void validate(AggregationValidationContext validationContext) {
    
  }

  /*
    In order for the engine to validate the type returned by the aggregation function against
    the types expected by enclosing expressions, the getValueType must return the result type
    of any values produced by the aggregation function:
   */
  public Class getValueType() {
    return Integer.class;
  }

  /*
    Finally the factory implementation must provide a newAggregator method that returns instances
    of AggregationMethod. The engine invokes this method for each new aggregation state to be allocated.
   */
  public AggregationMethod newAggregator() {
    return new Anomaly();
  }
}