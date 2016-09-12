var assert = require('chai').assert;
var DTSigner = require('../signature.js');

describe('TestDTSigner', function() {
	it('test DTSigner', function() {
  		var sample = require('fs').readFileSync('../sample.txt', 'utf-8')
  		sample = JSON.parse(sample);
  		sample.maps.every(function(map) {
  			assert.equal(DTSigner(map.text, sample.key), map.sign);
  		});
  	});
});