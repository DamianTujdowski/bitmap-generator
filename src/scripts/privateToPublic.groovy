def fileName = 'BinaryToDecimal.java'
def basePath = "B:/Programowanie/Aplikacje/imageGenerator/src/main/java/${fileName}"

def destinationPath = "B:/Programowanie/Aplikacje/bitmap-generator_for_testing_private_methods/src/main/java/${fileName}"

def editedBaseFile = new File(basePath).getText().replaceAll("private", "public")
def editedFileReadyToWrite = new File(destinationPath)
editedFileReadyToWrite.write(editedBaseFile)