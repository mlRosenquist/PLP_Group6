# Guides

## Setting up the project
Steps to be performed when initializing the project: 
* Clone it
* Verify Java / Scala / Sbt version (Working ones are Java:, Scala: , Sbt: )

## Debug
Steps to be performed when you want to debug the project: 
* Create Run/Debug configuration (Remote Jvm Debug -> Set port to 5005)
* Open terminal in root folder
* Write the command "sbt -jvm-debug 5005"
* Run the created Run/Debug configuration
* Set breakpoint in code
* Write the command "run"