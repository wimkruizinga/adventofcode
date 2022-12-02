package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	file, err := os.Open(`testinput.txt`)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	lines := readFile(file)

	x, y := getGridDimensions(lines)
	grid := newGrid(x, y)

	for _, line := range lines {
		grid.addLine(line)
	}

	grid.draw()
	fmt.Println(`Points with at least 2 vents: `, grid.pointsWithAtLeastTwoVents())

}

func readFile(file *os.File) []line {
	lines := []line{}
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, readLine(scanner.Text()))
	}

	return lines
}

func readLine(s string) line {
	fields := strings.Split(s, ` -> `)
	return line{
		start: getCoordinate(fields[0]),
		end:   getCoordinate(fields[1]),
	}
}

func getCoordinate(s string) coordinate {
	coords := strings.Split(s, `,`)

	x, err := strconv.Atoi(coords[0])
	if err != nil {
		panic(err)
	}
	y, err := strconv.Atoi(coords[1])
	if err != nil {
		panic(err)
	}

	return coordinate{
		row: x,
		col: y,
	}
}
