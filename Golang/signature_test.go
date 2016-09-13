package meiqia

import (
	"testing"
	"io/ioutil"
    "encoding/json"
)

type jsonType struct {
    Key string
    Maps  []mapType
}

type mapType struct {
	Text string
	Sign string
}

func TestMSigner(t *testing.T) {
	sample_text, err := ioutil.ReadFile("../sample.txt")
    if err != nil {
        panic(err)
    }

    var sample_json jsonType 
    json.Unmarshal(sample_text, &sample_json)

    for _, _map := range sample_json.Maps {
    	expected := _map.Sign
    	actual := MSigner(_map.Text, sample_json.Key)
    	if actual != expected {
			t.Error(_map.Text, _map.Sign)
		}
    }
}
