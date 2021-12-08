package main

import (
	"strings"
	"unicode/utf8"
)

type digit string

func (d digit) containsSegment(s string) bool {
	return strings.Contains(string(d), s)
}

func (d digit) equals(s string) bool {
	for _, segment := range d {
		if !strings.ContainsRune(s, segment) {
			return false
		}
	}

	return utf8.RuneCountInString(string(d)) == utf8.RuneCountInString(s)
}

func (d digit) getValue() string {
	return string(d)
}

func createDigit(s string) digit {
	return digit(s)
}

type Line struct {
	input  []string
	output []string
}

type Mapping map[string]digit

func (m Mapping) getValue(s string) string {
	for key, value := range m {
		if value.equals(s) {
			return key
		}
	}

	panic(1)
}
