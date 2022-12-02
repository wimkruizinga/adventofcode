package main

import (
	"testing"

	"git.fuyu.moe/Fuyu/assert"
)

var r = map[string]string{
	`CH`: `B`,
	`HH`: `N`,
	`CB`: `H`,
	`NH`: `C`,
	`HB`: `C`,
	`HC`: `B`,
	`HN`: `C`,
	`NN`: `C`,
	`BH`: `H`,
	`NC`: `B`,
	`NB`: `B`,
	`BN`: `B`,
	`BB`: `N`,
	`BC`: `B`,
	`CC`: `N`,
	`CN`: `C`,
}

func TestMain(t *testing.T) {
	assert := assert.New(t)

	template := `NNCB`
	expect := `NCNBCHB`
	actual := polymerize(template, r)
	assert.Eq(expect, actual)
}
