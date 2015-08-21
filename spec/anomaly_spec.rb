# -*- coding: utf-8 -*-
require 'norikra/udf_spec_helper'

include Norikra::UDFSpecHelper

require 'norikra/udf/anomaly'

describe Norikra::UDF::Anomaly do
  f = udf_function(
    Norikra::UDF::Anomaly,
    :valueType => java.lang.Integer,
    :parameters => [[java.lang.Integer]]
  )

  it 'returns Integer values' do
    expect(f.getValueType).to eql(java.lang.Integer.java_class)
  end

  it 'returns 1 value as mean' do
    (0...100).each do |i|
      f._call(:enter, i.to_java())
    end

    r = f.getValue
    expect(r).to eql(49)

    (0...100).each do |i|
      f._call(:leave, i.to_java())
    end
  end

end