package main

import (
	"fmt"
	"math"
)

type coordinate struct {
	col int
	row int
}

type line struct {
	start coordinate
	end   coordinate
}

func (l line) isHorizontal() bool {
	return l.start.row == l.end.row
}

func (l line) isVertical() bool {
	return l.start.col == l.end.col
}

type Grid [][]int

func newGrid(xSize, ySize int) Grid {
	grid := make(Grid, xSize+1)
	for i := range grid {
		grid[i] = make([]int, ySize+1)
	}

	return grid
}

func (g Grid) addLine(l line) {
	lowCol := math.Min(float64(l.start.col), float64(l.end.col))
	highCol := math.Max(float64(l.start.col), float64(l.end.col))
	lowRow := math.Min(float64(l.start.row), float64(l.end.row))
	highRow := math.Max(float64(l.start.row), float64(l.end.row))

	if l.isHorizontal() {
		for col := lowCol; col <= highCol; col++ {
			g.addPoint(coordinate{
				col: int(lowRow),
				row: int(col),
			})
		}
	} else if l.isVertical() {
		for row := lowRow; row <= highRow; row++ {
			g.addPoint(coordinate{
				col: int(row),
				row: int(lowCol),
			})
		}
	}
}

func (g Grid) addPoint(c coordinate) {
	g[c.row][c.col]++
}

func (g Grid) draw() {
	for _, row := range g {
		for _, col := range row {
			if col > 0 {
				fmt.Print(col)
			} else {
				fmt.Print(`.`)
			}
		}
		fmt.Println()
	}
}

func (g Grid) pointsWithAtLeastTwoVents() int {
	var total int
	for _, row := range g {
		for _, col := range row {
			if col > 1 {
				total++
			}
		}
	}

	return total
}
