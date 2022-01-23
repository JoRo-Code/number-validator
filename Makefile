all:
	javac -d classes src/validator/*.java

run: all
	java -cp classes  validator.Validator

clean:
	rm -rf classes


.PHONY: all run clean