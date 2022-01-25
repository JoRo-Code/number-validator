all:
	javac -d classes src/validator/*.java
	javac -d classes src/validator/*/*.java
	javac -d classes test/*.java


run: all
	java -cp classes  src.validator.Validator

clean:
	rm -rf classes

test: all
	java -cp classes  Test



.PHONY: all run clean test