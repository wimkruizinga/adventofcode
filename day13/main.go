package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	file, err := os.Open(`input.txt`)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	coordinates, folds := processInput(file)
	grid := makeGrid(coordinates)
	for _, c := range coordinates {
		grid[c.y][c.x] = 1
	}

	for _, fold := range folds {
		if fold.axis == `x` {
			grid = makeXFold(grid, fold)
		} else {
			grid = makeYFold(grid, fold)
		}
	}

	fmt.Println(`Dots:`, countDots(grid))
	printGrid(grid)
}

func countDots(grid [][]int) int {
	var total int
	for row := 0; row < len(grid); row++ {
		for col := 0; col < len(grid[row]); col++ {
			if grid[row][col] == 1 {
				total++
			}
		}
	}

	return total
}

func printGrid(grid [][]int) {
	for i := 0; i < len(grid); i++ {
		for j := 0; j < len(grid[i]); j++ {
			if grid[i][j] == 1 {
				fmt.Print(`#`)
			} else {
				fmt.Print(`.`)
			}
		}
		fmt.Println()
	}
}

func processInput(file *os.File) ([]coordinate, []fold) {
	coordinates := []coordinate{}
	folds := []fold{}

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		if isCoordinate(line) {
			coordinates = append(coordinates, readCoordinate(line))
		} else if line != `` {
			folds = append(folds, readFold(line))
		}
	}

	return coordinates, folds
}

func isCoordinate(s string) bool {
	return !strings.Contains(s, `fold along`) && s != ``
}

func readCoordinate(s string) coordinate {
	fields := strings.Split(s, `,`)
	return coordinate{
		x: stringToInt(fields[0]),
		y: stringToInt(fields[1]),
	}
}

func readFold(s string) fold {
	fields := strings.Split(strings.TrimPrefix(s, `fold along `), `=`)
	return fold{
		axis: fields[0],
		pos:  stringToInt(fields[1]),
	}
}

func makeYFold(grid [][]int, f fold) [][]int {
	// per column
	for col := 0; col < len(grid[0]); col++ {
		for row := 0; row < len(grid); row++ {
			if row > f.pos && grid[row][col] != 0 {
				grid[getNewPos(f.pos, row)][col] = 1
			}
		}
	}

	return grid[:f.pos]
}

func makeXFold(grid [][]int, f fold) [][]int {
	// per row
	for row := 0; row < len(grid); row++ {
		for col := 0; col < len(grid[0]); col++ {
			if col > f.pos && grid[row][col] != 0 {
				grid[row][getNewPos(f.pos, col)] = 1
			}
		}
	}

	for i := 0; i < len(grid); i++ {
		grid[i] = grid[i][:f.pos]
	}

	return grid
}

func getNewPos(fold, index int) int {
	return fold - (index - fold)
}

func stringToInt(s string) int {
	n, err := strconv.Atoi(s)
	if err != nil {
		panic(err)
	}

	return n
}

func makeGrid(c []coordinate) [][]int {
	maxX, maxY := getGridSize(c)

	// rows
	grid := make([][]int, maxY)
	for i := range grid {
		// cols
		grid[i] = make([]int, maxX)
	}

	return grid
}

func getGridSize(coordinates []coordinate) (int, int) {
	var maxX, maxY int
	for _, c := range coordinates {
		if c.x > maxX {
			maxX = c.x
		}
		if c.y > maxY {
			maxY = c.y
		}
	}

	return maxX + 1, maxY + 1
}
