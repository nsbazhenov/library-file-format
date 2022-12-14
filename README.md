A library that allow reading, modification and conversion of different file formats.
======
Examples:
------
+ Run the __mvn clean install__ command to build the distribution;
+ Add library dependencies to your project:
```java
    <dependency>
        <groupId>com.github.nsbazhenov</groupId>
        <artifactId>library-parse-file</artifactId>
        <version>1.0</version>
    </dependency>
```
------
+ The library includes xml and bin parsers:
```java
    Parser xmlParser = Parser.builder()
        .withXmlDecoder()
        .withXmlEncoder()
        .build();
```
```java
   Parser binParser = Parser.builder()
        .withBinDecoder()
        .withBinEncoder()
        .build();
```
------
+ To add your own parsers, you need to implement the Decoder and Encoder interface and
  override to decode and encode methods.
  When adding your own parsers in a different format, use:
```java
    Parser parser = Parser.builder()
        .withDecoder(new UserDecoder())
        .withEncoder(new UserEncoder())
        .build();
```
------
+ Functions available in the library are:
  + Writing an object to a file
    ```java
    xmlParser.writeValue(new File("example/src/main/resources/cars.xml"), document);
    ```
  + Reading from a file to an object
    ```java
    Document document = xmlParser.readValue(new File("example/src/main/resources/cars.bin"));
    ```
------
+ Methods are available to change the Car object in the Document class: addCar(), deleteCar(), editCar().
------
  An example of using the library can be found in the module - example.
------
------

## The advantages of the library:
  + Easily extensible API;

-----
## Further development:
  + If necessary, put the library in the Maven Central;
  + Functional road is possible after clarification of requirements;
  + Extension by other file formats;
  + Adding methods to convert from one file format to another in automatic mode;
