import ballerina.net.http;

@Description {value:"Defining Person struct."}
struct Person {
    string name;
    int age;
    string city;
}

@Description {value:"Defining transformer to convert from Person type to constrained JSON."}
transformer <Person p, json<Person> j> Foo(string city) {
    j.name = p.name;
    j.age = p.age;
    j.city = city;
}

@http:configuration {
    basePath:"/person"
}
@Description {value:"Defining Person service which provides person details."}
service<http> PersonService {

    @http:resourceConfig {
        methods:["POST"],
        path:"/"
    }
    @Description {value:"Defining POST resource for the service to get person details."}
    resource getPerson (http:Request req, http:Response res) {
        // Get the JSON payload from the request.
        json j = req.getJsonPayload();

        // Declare a Person variable.
        Person p;

        // Declare a type conversion error to accept any type conversion errors thrown.
        TypeConversionError err;
        // Convert JSON to a Person type variable.
        p, err = <Person>j;

        // Print if an error is thrown.
        if (err != null) {
            println(err);
        }

        // Define a constant city value as "London".
        string city = "London";

        // Convert p of type Person to the response JSON, using the transformer defined earlier.
        json<Person> response = <json<Person>; Foo(city)> p;


        // Set the new JSON payload to the message.
        res.setJsonPayload(response);

        // Reply from the resource with message m.
        res.send();
    }
}
