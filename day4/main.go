package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	file, err := os.Open(`input.txt`)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	numbers, boards := processInput(file)
	game := newGame(numbers, boards)

	for game.nextRound() {
		bingo := game.playRound()
		if bingo {
			fmt.Println(`Winner!`)
			fmt.Println(game.winner)
			fmt.Println(game.getScore())
			break
		}
	}

}

func processInput(file *os.File) ([]int, []board) {
	scanner := bufio.NewScanner(file)

	scanner.Scan()
	numbersToDraw := readNumbers(scanner.Text(), `,`)
	var boards []board
	var board board
	var ln int
	for scanner.Scan() {
		line := scanner.Text()
		if line == `` {
			continue
		}

		ln++
		board.addRow(line)
		// 5 regels getallen per bord

		if ln%5 == 0 {
			boards = append(boards, board)
			board.clear()
		}
	}

	return numbersToDraw, boards
}

func readNumbers(s string, sep string) []int {
	numberStrings := strings.Split(s, sep)
	var numbers []int

	for _, number := range numberStrings {
		numbers = append(numbers, stringToInt(number))
	}

	return numbers
}
