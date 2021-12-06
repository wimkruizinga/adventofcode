package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	moves := readFile(`input.txt`)
	pos := Position{}

	for _, move := range moves {
		pos.addMove(move)
	}

	fmt.Printf("Horizontal position: %d\nDepth: %d\nTotal: %d", pos.horizontal, pos.depth, pos.depth*pos.horizontal)

}

func readFile(fileName string) []Move {
	file, err := os.Open(fileName)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var moves []Move

	for scanner.Scan() {
		moves = append(moves, readMove(scanner.Text()))
	}

	return moves
}

func readMove(s string) Move {
	f := strings.Fields(s)
	u, err := strconv.Atoi(f[1])
	if err != nil {
		panic(err)
	}

	return Move{
		direction: f[0],
		units:     u,
	}
}
