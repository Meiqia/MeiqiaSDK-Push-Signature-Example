#!/usr/bin/env python
# -*- coding: utf-8 -*-import unittest
import unittest
import json
from signature import MTSigner

class TestSignature(unittest.TestCase):
    def setUp(self):
        pass
            
    def tearDown(self):
        pass

    def testSign(self):
    	fo = open("../sample.txt", "r")
    	str = fo.read()
    	fo.close()
    	sample = json.loads(str)
    	
    	signer = MTSigner(sample['key'].encode('utf-8'))
    	for _map in sample['maps']:
    		self.assertEqual(_map['sign'], signer.sign(_map['text'].encode('utf-8')))
