require "norikra/udf"

module Norikra
  module UDF
  	class Anomaly < Norikra::UDF::AggregationSingle
      def self.init
        require 'norikra-udf-anomaly.jar'
      end

      def definition
        # function name, UDAF Factory Class Name
        ["anomaly", "is.tagomor.norikra.udf.AnomalyFactory"]
      end
    end
  end
end
