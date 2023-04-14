# fibonacci-calculator
The application brings 2 endpoints, one of them return the fibonacci number on the `n` 
position and the other one return a list of the requested fibonacci numbers ordered
from the most requested to the least.

It's developed in Java Spring Boot connected to a PostgreSQL database, it also has
an OpenAPI(Swagger) interface to get endpoint documentation and can be used to test 
it.

The database is used as a cache to store the calculated values and also to store
the quantity of request had each fibonacci number.

This project contain unit tests on services and controllers and covers more than 
80% of the code. 

The connection to the database has to be configurated with the [env variables](.env.example),
also you can change the [application.properties file](src/main/resources/application.properties)
commenting the postgres config and uncommenting the H2 config to run it easily.

## Deployed cloud instance

This project has an instance deployed on render.com cloud services connected to a
postgres database.

**IMPORTANT:** This service is free, so you have to be patient with the response time,
in the case of the swagger UI you have to wait several seconds to get the response 
(is not broken, it's just slow).

### Cloud urls

[OpenAPI Doc](https://fibonacci-calculator.onrender.com/swagger-ui/index.html#/)

[[GET] /fibonacci/{n}](https://fibonacci-calculator.onrender.com/fibonacci/1) -> return fibonacci number  in "n" position

[[GET] /fibonacci](https://fibonacci-calculator.onrender.com/fibonacci) -> return the most requested fibonacci numbers


### Local urls

[OpenAPI Doc](http://localhost:8080/swagger-ui/index.html#/)

[[GET] /fibonacci/{n}](http://localhost:8080/fibonacci/1) -> return fibonacci number  in "n" position

[[GET] /fibonacci](http://localhost:8080/fibonacci) -> return the most requested fibonacci numbers

### OpenApi JSON

```json
{
   "openapi":"3.0.1",
   "info":{
      "title":"OpenAPI definition",
      "version":"v0"
   },
   "servers":[
      {
         "url":"http://localhost:8080",
         "description":"Generated server url"
      }
   ],
   "paths":{
      "/fibonacci":{
         "get":{
            "tags":[
               "fibonacci-controller"
            ],
            "summary":"Get most requested Fibonacci values",
            "operationId":"getMostRequestedFibonacciValues",
            "responses":{
               "200":{
                  "description":"List of most requested Fibonacci values",
                  "content":{
                     "application/json":{
                        "schema":{
                           "type":"array",
                           "items":{
                              "$ref":"#/components/schemas/FibonacciDto"
                           }
                        }
                     }
                  }
               }
            }
         }
      },
      "/fibonacci/{n}":{
         "get":{
            "tags":[
               "fibonacci-controller"
            ],
            "summary":"Get Fibonacci value for n",
            "operationId":"getFibonacci",
            "parameters":[
               {
                  "name":"n",
                  "in":"path",
                  "description":"Fibonacci number n",
                  "required":true,
                  "schema":{
                     "type":"integer",
                     "format":"int64"
                  },
                  "example":5
               }
            ],
            "responses":{
               "200":{
                  "description":"Fibonacci value for n",
                  "content":{
                     "application/json":{
                        "schema":{
                           "type":"integer",
                           "format":"int64"
                        }
                     }
                  }
               }
            }
         }
      }
   },
   "components":{
      "schemas":{
         "FibonacciDto":{
            "type":"object",
            "properties":{
               "n":{
                  "type":"integer",
                  "format":"int64"
               },
               "fn":{
                  "type":"integer",
                  "format":"int64"
               },
               "count":{
                  "type":"integer",
                  "format":"int64"
               }
            }
         }
      }
   }
}
```