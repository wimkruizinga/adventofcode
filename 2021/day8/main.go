package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	lines := processFile(`input.txt`)
	var total int

	mapping := make(Mapping)
	for _, line := range lines {
		var digitsToDetermine []digit

		// set known digits first
		for _, digit := range line.input {
			if len(digit) == 2 {
				mapping[`1`] = createDigit(digit)
			} else if len(digit) == 3 {
				mapping[`7`] = createDigit(digit)
			} else if len(digit) == 4 {
				mapping[`4`] = createDigit(digit)
			} else if len(digit) == 7 {
				mapping[`8`] = createDigit(digit)
			} else {
				digitsToDetermine = append(digitsToDetermine, createDigit(digit))
			}
		}

		for _, digit := range digitsToDetermine {
			seg := string(mapping[`8`])
			for i := 0; i < len(digit); i++ {
				seg = strings.Replace(seg, string(digit.getValue()[i]), ``, 1)
			}

			if len(digit) == 6 {
				if mapping[`4`].containsSegment(seg) && mapping[`7`].containsSegment(seg) && mapping[`1`].containsSegment(seg) {
					mapping[`6`] = digit
				} else if mapping[`4`].containsSegment(seg) && !mapping[`7`].containsSegment(seg) && !mapping[`1`].containsSegment(seg) {
					mapping[`0`] = digit
				} else if !mapping[`4`].containsSegment(seg) && !mapping[`7`].containsSegment(seg) {
					mapping[`9`] = digit
				}

			} else if len(digit) == 5 {
				// 2, 3 of 5
				if !mapping[`1`].containsSegment(string(seg[0])) && !mapping[`1`].containsSegment(string(seg[1])) {
					mapping[`3`] = digit
				} else if mapping[`4`].containsSegment(string(seg[0])) && mapping[`4`].containsSegment(string(seg[1])) {
					mapping[`2`] = digit
				} else {
					mapping[`5`] = digit
				}
			}
		}

		number := getNumericValue(line.output, mapping)
		total += number
		fmt.Println(line.output, number)
	}

	fmt.Println(total)
}

func getNumericValue(o []string, m Mapping) int {
	var numberString string
	for _, v := range o {

		numberString = numberString + m.getValue(v)
	}

	number, err := strconv.Atoi(numberString)
	if err != nil {
		panic(err)
	}

	return number
}

func processFile(fileName string) []Line {
	file, err := os.Open(fileName)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	var lines []Line
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, readLine(scanner.Text()))
	}

	return lines
}

func readLine(s string) Line {
	slice := strings.Split(s, `|`)
	_ = slice

	return Line{
		input:  strings.Fields(slice[0]),
		output: strings.Fields(slice[1]),
	}
}
