 Pre-requisite
 ====================
 * Maven
 * JDK 1.8
 * Preferred is to install the project in the choice of IDE, makes it easier to compile and run.
 
INFO
 ====================
 * This is a core java based project without any UI.
 * It intends to connect to a Restful API using HTTPClient component. The obtained response is a json string which is parsed and mapped to pojo using an api.
 * Results are processed and printed on console 
 
 What project do
 ====================
 * The project prints all the dates when the api shows that the weather is sunny and temperature is more than 20 degree celcius in next 5 days in Sydney(Australia) city.
 
 How to run
 ====================
 * Download the project and run the maven goals - mvn clean install
 * The main class for running the project is placed inside the src/main/java package.
 * The main class need to executed as any other java main class.  
 
 Algorithm of the project
 =========================
 
 * Configure the client to connect with the web api using Apache HTTPClient
 * Model the request and parse the response received
 * Created POJO and mapped it to the JSON response using third party API (Jackson)
 * Perform logical operations on the response object to fetch the relevant information as per the requirements.
 * Print the results on the console
  
