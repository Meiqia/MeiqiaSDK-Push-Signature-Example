#!/usr/bin/env python
# -*- coding: utf-8 -*-
import base64
import hashlib
import hmac

# In Flask and Django, raw_data is request.data.

# Example:
# if MTSigner(secrect_key).sign(request.data) !=  request.headers.get('Authorization')
#	return {"Authorization is invalid"}, status.HTTP_401_BAD_REQUEST


class MTSigner(object):

    def __init__(self, key):
    	self.checkValue(key)
        self.key = key

    def sign(self, raw_data):
    	self.checkValue(raw_data)
        hash_str = hmac.new(self.key, raw_data, hashlib.sha1).hexdigest()
        sign_str = base64.b64encode(hash_str)
        return "meiqia_sign:" + sign_str

    def checkValue(self, value):
    	if not isinstance(value, str):
        	raise ValueError('value is not str')
