# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)

Gem::Specification.new do |spec|
  spec.name          = "norikra-udf-anomaly"
  spec.version       = "0.0.1"
  spec.authors       = ["baba5246"]
  spec.email         = ["baba_writer@yahoo.co.jp"]
  spec.summary       = %q{Norikra UDF anomaly()}
  spec.description   = %q{This plugin adds a function named anomaly, which calculates anomaly scores for specified fields}
  spec.homepage      = "https://github.com/tagomoris/norikra-udf-anomaly"
  spec.license       = "MIT"
  spec.platform      = "java"

  spec.files         = `git ls-files`.split($/)
  spec.executables   = spec.files.grep(%r{^bin/}) { |f| File.basename(f) }
  spec.test_files    = spec.files.grep(%r{^(test|spec|features)/})
  spec.require_paths = ["lib", "jar"]

  spec.add_runtime_dependency "norikra"

  spec.add_development_dependency "bundler", "~> 1.10"
  spec.add_development_dependency "rake", "~> 10.0"
  spec.add_development_dependency "rspec"
end
