# Guides

## Setting up the project
Steps to be performed when initializing the project: 
* Clone it
* Download Java and add it to path (Working one is Java:"1.8.0_281")
* Download sbt and add it to path

## Debug
Steps to be performed when you want to debug the project: 
* Create Run/Debug configuration (Remote Jvm Debug -> Set port to 5005)
* Open terminal in root folder
* Write the command "sbt -jvm-debug 5005"
* Run the created Run/Debug configuration
* Set breakpoint in code
* Write the command "run"