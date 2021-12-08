package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

type filterFunc func([]string, []string) []string

func main() {
	rows := readFile(`input.txt`)

	oxygenRating := getRating(rows, oxygenRatingFilter)
	co2Rating := getRating(rows, co2RatingFilter)

	fmt.Println(`Life support rating: `, oxygenRating*co2Rating)
}

func getRating(input []string, filter filterFunc) int64 {
	binRating := applyFilter(input, 0, filter)[0]

	return binToDecimal(binRating)
}

func applyFilter(input []string, pos int, filter filterFunc) []string {
	if len(input) == 1 {
		return input
	}

	var ones, zeroes []string
	for _, row := range input {
		if row[pos] == '1' {
			ones = append(ones, row)
		} else {
			zeroes = append(zeroes, row)
		}
	}

	result := filter(ones, zeroes)

	return applyFilter(result, pos+1, filter)
}

func oxygenRatingFilter(ones, zeroes []string) []string {
	if len(ones) >= len(zeroes) {
		return ones
	} else {
		return zeroes
	}
}

func co2RatingFilter(ones, zeroes []string) []string {
	if len(ones) < len(zeroes) {
		return ones
	} else {
		return zeroes
	}
}

func readFile(fileName string) []string {
	file, err := os.Open(fileName)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	var nums []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		nums = append(nums, scanner.Text())
	}

	return nums
}

func binToDecimal(b string) int64 {
	dec, err := strconv.ParseInt(b, 2, 64)
	if err != nil {
		panic(err)
	}

	return dec
}
