require 'test/unit'
require 'json'
require './signature'

class TestSignature < Test::Unit::TestCase
  def test_signature
    sample = JSON.load(File.read('../sample.txt'))
    tool = MeiqiaSignTools.new(sample['key'])
    sample['maps'].each do |map|
      assert_equal(map['sign'], tool.sign(map['text']))
    end
  end
end
