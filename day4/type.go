package main

import (
	"strconv"
	"strings"
)

type field struct {
	value  int
	marked bool
}

type board struct {
	rows [][]field
}

func (b *board) checkNumber(number int) bool {
	for i := 0; i < len(b.rows); i++ {
		for j := 0; j < len(b.rows[i]); j++ {
			if b.rows[i][j].value == number {
				b.rows[i][j].marked = true
				return true
			}
		}
	}

	return false
}

func (b *board) clear() {
	b.rows = [][]field{}
}

func (b *board) addRow(s string) {
	row := []field{}
	for _, number := range strings.Fields(s) {
		row = append(row, field{
			value:  stringToInt(number),
			marked: false,
		})
	}

	b.rows = append(b.rows, row)
}

func (b *board) hasBingo() bool {
	// check rows
	for i := 0; i < len(b.rows); i++ {
		bingo := true
		for j := 0; j < len(b.rows[i]); j++ {
			bingo = bingo && b.rows[i][j].marked
		}
		if bingo {
			return true
		}
	}

	// check columns
	for i := 0; i < len(b.rows[0]); i++ {
		bingo := true
		for j := 0; j < len(b.rows); j++ {
			bingo = bingo && b.rows[j][i].marked
		}
		if bingo {
			return true
		}
	}

	return false
}

type game struct {
	drawStack  []int
	lastNumber int
	boards     []board
	winner     *board
}

func (g *game) playRound() bool {
	g.lastNumber = g.drawStack[0]
	g.drawStack = g.drawStack[1:]

	for _, board := range g.boards {
		if board.checkNumber(g.lastNumber) && board.hasBingo() {
			g.winner = &board
			return true
		}
	}

	return false
}

func (g *game) getScore() int {
	var score int
	for i := 0; i < len(g.winner.rows); i++ {
		for j := 0; j < len(g.winner.rows[0]); j++ {
			if !g.winner.rows[i][j].marked {
				score = g.winner.rows[i][j].value + score
			}
		}
	}

	return score * g.lastNumber
}

func (g *game) nextRound() bool {
	return len(g.drawStack) > 0
}

func newGame(numbers []int, boards []board) game {
	return game{
		drawStack: numbers,
		boards:    boards,
	}
}

func stringToInt(s string) int {
	n, err := strconv.Atoi(s)
	if err != nil {
		panic(err)
	}

	return n
}
