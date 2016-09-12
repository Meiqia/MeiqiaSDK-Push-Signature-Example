// ECMAScript 5
// the method to read raw body in Express
// app.configure(function() {
// 	//. . .
// 	app.use(express.bodyParser());
// 	app.use(function(req, res, next) {
// 	        var data = '';
// 	        req.setEncoding('utf8');
// 	        req.on('data', function(chunk) { 
// 	            data += chunk;
// 	        });
// 	        req.on('end', function() {
// 	            req.rawBody = data;
// 	        });
// 	        next();
// 	    });
	
// 	//. . .
// });
// 
// Example:
// if(DTSigner(req.rawBody, key) != req.getHeaders('Authorization')){
//
// }
var crypto = require('crypto');

var DTSigner = function(raw_body, key){
	var hash_str = crypto.createHmac('sha1', key).update(raw_body, 'utf8').digest('hex');
	var sign_str = new Buffer(hash_str).toString('base64');
	return 'meiqia_sign:' + sign_str;
}

module.exports = DTSigner;
