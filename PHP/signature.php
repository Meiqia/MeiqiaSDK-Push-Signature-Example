<?php
// PHP 5 >= 5.1.2
// raw_data is http_get_request_body() that get request body as string
// Example:
// $body = http_get_request_body()  「如果 http_get_request_body() 不能使用，可以用 $body = @file_get_contents('php://input')」
// $dt = new DTSigner($secrect_key)
// $headers = apache_request_headers()
// if($dt->sign($body) != $headers['Authorization']){
//
//}

  class DTSigner {

    private $raw_data;
    private $key;

    public function __construct($key) {
        $this->key = $key;
    }

    public function sign($raw_data) {
        $hash_str = hash_hmac('sha1', $raw_data, $this->key);
        $sign_str = strtr(base64_encode($hash_str), '+/', '-_');
        return 'meiqia_sign:'.$sign_str;
    }

  }
?>
