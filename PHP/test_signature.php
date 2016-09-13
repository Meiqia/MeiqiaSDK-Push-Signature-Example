<?php
  include 'signature.php';
  $sample = file_get_contents('../sample.txt');

  $sample = json_decode($sample, true);
  $signer = new DTSigner($sample['key']);

  foreach ($sample['maps'] as $map) {
    if (strcmp($signer->sign($map['text']), $map['sign']) != 0){
        throw new Exception('Sign is not correct');
    }
  }
?>
