package main

import (
	"testing"

	"git.fuyu.moe/Fuyu/assert"
)

func TestIsCoordinate(t *testing.T) {
	assert := assert.New(t)

	coord := `894,805`
	fold := `fold along y=223`

	assert.True(isCoordinate(coord))
	assert.False(isCoordinate(fold))
	assert.False(isCoordinate(``))
}

func TestReadCoordinate(t *testing.T) {
	assert := assert.New(t)

	s := `894,805`
	coord := readCoordinate(s)

	assert.Eq(894, coord.x)
	assert.Eq(805, coord.y)
}

func TestReadFold(t *testing.T) {
	assert := assert.New(t)

	f := `fold along y=223`
	fold := readFold(f)

	assert.Eq(`y`, fold.axis)
	assert.Eq(223, fold.pos)
}
