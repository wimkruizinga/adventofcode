package main

import (
	"testing"

	"git.fuyu.moe/Fuyu/assert"
)

func TestReadLine(t *testing.T) {
	assert := assert.New(t)

	fl := `750,500 -> 73,520`
	line := readLine(fl)

	assert.Eq(line.start.col, 750)
	assert.Eq(line.end.col, 73)
	assert.Eq(line.start.row, 500)
	assert.Eq(line.end.row, 520)
}

func TestGrid(t *testing.T) {
	assert := assert.New(t)

	testLines := []line{
		readLine(`0,9 -> 5,9`), // horizontaal
		readLine(`0,0 -> 0,3`), // verticaal
		readLine(`9,4 -> 3,4`),
		readLine(`7,0 -> 7,4`),
		readLine(`6,4 -> 2,0`),
		readLine(`0,9 -> 2,9`),
		readLine(`3,4 -> 1,4`),
		readLine(`0,0 -> 8,8`),
		readLine(`0,5 -> 8,2`),
	}
	x, y := getGridDimensions(testLines)
	assert.Eq(9, x)
	assert.Eq(9, y)

	grid := newGrid(x, y)
	grid.addLine(testLines[0])
	assert.Cmp([]int{1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, grid[9][:])

	grid = newGrid(x, y)
	grid.addLine(testLines[1])
	assert.Cmp([]int{1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, grid[:][0])
}
